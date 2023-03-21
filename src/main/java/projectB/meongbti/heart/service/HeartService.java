package projectB.meongbti.heart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectB.meongbti.boast.entity.Boast;
import projectB.meongbti.boast.repository.BoastRepository;
import projectB.meongbti.exception.boast.BoastErrorCode;
import projectB.meongbti.exception.boast.BoastException;
import projectB.meongbti.exception.heart.HeartErrorCode;
import projectB.meongbti.exception.heart.HeartException;
import projectB.meongbti.exception.member.ErrorCode;
import projectB.meongbti.exception.member.MemberException;
import projectB.meongbti.heart.dto.HeartDto;
import projectB.meongbti.heart.dto.HeartRequestDto;
import projectB.meongbti.heart.entity.Heart;
import projectB.meongbti.heart.repository.HeartRepository;
import projectB.meongbti.member.entity.Member;
import projectB.meongbti.member.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HeartService {

    private final HeartRepository heartRepository;

    private final MemberRepository memberRepository;
    private final BoastRepository boastRepository;

    /**
     * 좋아요 추가
     */
    public Long addHeart(HeartRequestDto heartRequestDto) {
        //멤버ID와 게시글ID를 조회
        Member member = memberRepository.findById(heartRequestDto.getMemberId()).orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));
        Boast boast = boastRepository.findById(heartRequestDto.getBoastId()).orElseThrow(() -> new BoastException(BoastErrorCode.BOAST_NOT_FOUND));

        Heart heart = Heart.builder()
                .member(member)
                .boast(boast)
                .build();

        heartRepository.save(heart);

        return heart.getHeartId();
    }

    /**
     * 좋아요 취소
     */
    public Long cancelHeart(HeartRequestDto heartRequestDto) {
        //멤버ID와 자랑하기ID를 이용하여 좋아요 정보 조회
        Heart heart = heartRepository.findOneByMemberAndBoast(heartRequestDto.getMemberId(), heartRequestDto.getBoastId())
                .orElseThrow(() -> new HeartException(HeartErrorCode.HEART_NOT_FOUND));

        heartRepository.delete(heart);

        return heart.getHeartId();
    }

    /**
     * 멤버ID를 이용하여 좋아요 한 자랑하기 게시글 조회
     */
    @Transactional(readOnly = true)
    public List<HeartDto> findByMemberId(Long memberId) {
        //멤버ID를 이용하여 좋아요한 게시글 조회
        List<Heart> findHeart = heartRepository.findByMemberId(memberId);

        List<HeartDto> returnList = new ArrayList<>();

        findHeart.forEach(heart -> {
            HeartDto heartDto = heart.fromEntity(heart);
            returnList.add(heartDto);
        });

        return returnList;
    }
}