package projectB.meongbti.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import projectB.meongbti.exception.member.ErrorCode;
import projectB.meongbti.exception.member.MemberException;
import projectB.meongbti.member.dto.MemberDto;
import projectB.meongbti.member.entity.Member;
import projectB.meongbti.member.repository.MemberRepository;

@Service
@Log4j2
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    @PostMapping("/signup")
    public Member signup(String memberEmail, String memberPw, String memberName){
        memberRepository.findByMemberEmail(memberEmail).ifPresent(it -> {
//            throw new MemberException(ErrorCode.DUPLICATED_MEMBER_EMAIL, String.format("%s is duplicated",memberEmail));
            throw new MemberException(ErrorCode.DUPLICATED_MEMBER_EMAIL);
        });

        Member member = memberRepository.save(Member.builder()
                .memberEmail(memberEmail)
                .memberPw(memberPw)
                .memberNick(memberName)
                .build());

        return MemberDto.fromEntity(member);
    }

//    public String login(String username, String password) {
//        //회원가입 체크
//
//        UserEntity user = userRepository.findByUserName(username).orElseThrow(() -> new SnsException(ErrorCode.USER_NOT_FOUND, String.format("%s not found", username)));
//        //비밀번호
//
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new SnsException(ErrorCode.INVALID_PASSWORD);
//        }
//
//        //토큰
//        String token = JwtTokenUtils.generateToken(username, secretKey, longexpireTimeMs);
//        return "";
//
//    }


//    /**
//     * 회원 수정
//     */

//    public MemberDto updateMember(Long id, MemberDto memberDto){
//        Member member = memberRepository.findById(id).orElseThrow(() -> new NotExistMember());
//
//        String FullPath = "";
//        if()
//        member.update( memberDto.getMemberNick(),memberDto.getMemberImage(), memberDto.getMemberPw());
//        memberRepository.save(member);
//        return MemberDto.toDto(member);
//
//
//    }

        /**
         * 회원 삭제
         */

//
//    public void deleteMember(Long id){
//
//        Member member = memberRepository.findById(id).orElseThrow(() -> new NotExistMember());
//        memberRepository.delete(member);
//
//
//
//    }
//    /**
//     * 회원 조회
//     */
//
//    public MemberDto findByid(Long id) {
//        Member member = memberRepository.findById(id)
//                .orElseThrow(() -> new NotExistMember());
//        return toDto(member);
//    }
//
//    public  MemberDto toDto(Member member) {
//
//        return MemberDto.builder()
//                .memberEmail(member.getMemberEmail())
//                .memberPw(member.getMemberPw())
//                .memberNick(member.getMemberNick())
//                .memberImage(member.getMemberImage())
//                .build();
//    }
//
//}

}