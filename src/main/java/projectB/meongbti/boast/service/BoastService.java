package projectB.meongbti.boast.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectB.meongbti.boast.dto.BoastSaveDto;
import projectB.meongbti.boast.entity.Boast;
import projectB.meongbti.boast.repository.BoastRepository;
import projectB.meongbti.exception.boast.BoastErrorCode;
import projectB.meongbti.exception.boast.BoastException;
import projectB.meongbti.exception.member.ErrorCode;
import projectB.meongbti.exception.member.MemberException;
import projectB.meongbti.exception.pet.PetErrorCode;
import projectB.meongbti.exception.pet.PetException;
import projectB.meongbti.member.entity.Member;
import projectB.meongbti.member.repository.MemberRepository;
import projectB.meongbti.pet.entity.Pet;
import projectB.meongbti.pet.repository.PetRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoastService {

    private final BoastRepository boastRepository;
    private final MemberRepository memberRepository;
    private final PetRepository petRepository;

    public Long saveBoast(BoastSaveDto boastSaveDto) {
        //전달 받은 memberId와 petId를 이용하여 정보 조회
        Member member = memberRepository.findById(boastSaveDto.getMemberId()).orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));
        Pet pet = petRepository.findOneByPetId(boastSaveDto.getPetId()).orElseThrow(() -> new PetException(PetErrorCode.PET_NOT_FOUND));

        //사진저장부분 로직 추가해야한다.
        Boast boast = Boast.builder()
                .boastDate(LocalDateTime.now())
                .boastContent(boastSaveDto.getBoastContent())
                .member(member)
                .pet(pet)
                .build();

        //자랑하기 등록
        boastRepository.saveBoast(boast);

        return boast.getBoastId();
    }

    public Long deleteBoast(Long boastId) {
        Boast boast = boastRepository.findOneByBoastId(boastId).orElseThrow(() -> new BoastException(BoastErrorCode.BOAST_NOT_FOUND));

        boastRepository.deleteBoast(boast);

        return boastId;
    }

    public List<Boast> findAll() {
        return boastRepository.findAll();
    }
}
