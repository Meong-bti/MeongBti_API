package projectB.meongbti.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import projectB.meongbti.exception.member.ErrorCode;
import projectB.meongbti.exception.member.MemberException;
import projectB.meongbti.member.dto.MemberDto;
import projectB.meongbti.member.entity.Member;
import projectB.meongbti.member.repository.MemberRepository;
import projectB.meongbti.member.util.JwtTokenUtils;

import java.util.Optional;

@Service
@Log4j2
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expire-time-ms}")
    private Long longexpireTimeMs;

    public MemberDto loadMemberByMemberEmail(String memberEmail){
        return memberRepository.findByMemberEmail(memberEmail).map(MemberDto::fromEntity).orElseThrow(() ->
                new MemberException(ErrorCode.USER_NOT_FOUND, String.format("%s not found", memberEmail)));

    }

@Transactional
    @PostMapping("/signup")
    public MemberDto signup(String memberEmail, String memberPw, String memberNick){
        memberRepository.findByMemberEmail(memberEmail).ifPresent(it -> {

            throw new MemberException(ErrorCode.DUPLICATED_MEMBER_EMAIL);
        });

        Member member = memberRepository.save(Member.builder()
                .memberEmail(memberEmail)
                .memberPw(encoder.encode(memberPw))
                .memberNick(memberNick)
                .build());


        return MemberDto.fromEntity(member);
    }
@Transactional
    public String login(String memberEmail, String memberPw) {
        //회원가입 체크

        Member member = memberRepository.findByMemberEmail(memberEmail).orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));
        //비밀번호

        if (!encoder.matches(memberPw, member.getMemberPw())) {
            throw new MemberException(ErrorCode.INVALID_PASSWORD);
        }

        //토큰

    String token = JwtTokenUtils.generateToken(memberEmail, secretKey, longexpireTimeMs);

    return token;

    }
    //회원 수정
    @Transactional
    public MemberDto updateMember(String memberEmail, String memberPw, String memberNick) {

        Member member = memberRepository.findByMemberEmail(memberEmail).orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));



        Member updateMember = memberRepository.save(Member.builder()
                .memberId(member.getMemberId())
                .memberEmail(member.getMemberEmail())
                .memberPw(encoder.encode(memberPw))
                .memberNick(memberNick)
                .build());

        // MemberDto로 변환하여 반환
        return MemberDto.fromEntity(updateMember);
    }
    @Transactional
    public void deleteMember(String memberEmail) {


        Member member = memberRepository.findByMemberEmail(memberEmail).orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));

        if(member.isDeleted()){

            throw new MemberException(ErrorCode.ALREADY_DELETED_USER);
        }
           memberRepository.save(member.toBuilder()
                .deleted(true)
                .build());
    }

    public MemberDto findMember(String memberEmail) {


        Member member = memberRepository.findByMemberEmail(memberEmail).orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));
        return MemberDto.fromEntity(member);
    }
}





