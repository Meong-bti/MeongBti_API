package projectB.meongbti.member.configuration.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import projectB.meongbti.member.dto.MemberDto;
import projectB.meongbti.member.dto.request.MemberJoinRequestDto;
import projectB.meongbti.member.dto.request.MemberUpdateRequestDto;
import projectB.meongbti.member.dto.response.MemberGetResponseDto;
import projectB.meongbti.member.dto.response.MemberJoinResponseDto;
import projectB.meongbti.member.dto.response.MemberLoginResponseDto;
import projectB.meongbti.member.dto.response.MemberUpdateResponseDto;
import projectB.meongbti.util.response.Response;
import projectB.meongbti.member.entity.Member;
import projectB.meongbti.member.service.MemberService;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;



    @PostMapping("/signup")
    public Response<MemberJoinResponseDto> memberSignup(@RequestBody
                                                              MemberJoinRequestDto requestDto, Authentication authentication)  {
        MemberDto memberDto = memberService.signup(requestDto.getMemberEmail(), requestDto.getMemberPw(), requestDto.getMemberNick());
        return Response.success(MemberJoinResponseDto.fromMember(memberDto));
    }

    @PostMapping("/login")
    public Response<MemberLoginResponseDto> login(@RequestBody MemberJoinRequestDto requestDto){

        String token = memberService.login(requestDto.getMemberEmail(), requestDto.getMemberPw());
        return Response.success(new MemberLoginResponseDto(token));
    }

    @PutMapping("/update")
    public Response<MemberUpdateResponseDto> updateMember( Authentication authentication,
                                                          @RequestBody MemberUpdateRequestDto requestDto) {
        // 현재 인증된 사용자의 MemberDto를 가져옴
        MemberDto currentMember = (MemberDto) authentication.getPrincipal();

        // 서비스에서 회원 정보를 업데이트하고 업데이트된 회원 정보를 반환
        MemberDto updatedMember = memberService.updateMember(
                currentMember.getMemberEmail(),
                requestDto.getMemberPw(),
                requestDto.getMemberNick());

        // 업데이트된 회원 정보를 사용하여 응답 DTO를 만들고 반환
        return Response.success(MemberUpdateResponseDto.fromMember(updatedMember));
    }
    @DeleteMapping("/delete")
    public Response<?> deleteMember(Authentication authentication) {
        // 현재 인증된 사용자의 MemberDto를 가져옴
        MemberDto currentMember = (MemberDto) authentication.getPrincipal();

        // 서비스에서 회원 정보를 삭제
        memberService.deleteMember(currentMember.getMemberEmail());

        // 삭제에 성공한 경우, 응답 객체를 반환
        return Response.success("회원 삭제 완료");
    }


    @GetMapping("/find")
    public Response<MemberGetResponseDto> findMemberByEmail(Authentication authentication) {
        // 현재 인증된 사용자의 MemberDto를 가져옴
        MemberDto currentMember = (MemberDto) authentication.getPrincipal();

        // 서비스에서 회원 정보를 조회하고 조회된 회원 정보를 반환
        MemberDto memberDto = memberService.findMember(currentMember.getMemberEmail());

        return Response.success(MemberGetResponseDto.fromMember(memberDto));
    }

    }


