package projectB.meongbti.boast.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import projectB.meongbti.boast.dto.BoastSaveDto;
import projectB.meongbti.boast.entity.Boast;
import projectB.meongbti.boast.repository.BoastRepository;
import projectB.meongbti.member.entity.Member;
import projectB.meongbti.member.repository.MemberRepository;
import projectB.meongbti.pet.entity.Pet;
import projectB.meongbti.pet.entity.PetGender;
import projectB.meongbti.pet.entity.PetNtlz;
import projectB.meongbti.pet.repository.PetRepository;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class BoastServiceTest {

    @Autowired
    BoastService boastService;

    @Autowired
    BoastRepository boastRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PetRepository petRepository;

    private Member member;
    private Pet pet;

    @BeforeEach
    void saveMemberAndPet() {
        member = Member.builder()
                .memberEmail("test@test.com")
                .memberPw("test")
                .memberNick("test")
                .build();
        memberRepository.save(member);

        pet = Pet.builder()
                .petName("test펫")
                .petBreed("test")
                .petBday(LocalDate.now())
                .petGender(PetGender.MALE)
                .petNtlz(PetNtlz.NTLZ)
                .petWeight(11.11)
                .member(member)
                .build();
        petRepository.savePet(pet);
    }

    @DisplayName("자랑하기 등록")
    @Test
    void boastSave() {
        BoastSaveDto boastSaveDto = BoastSaveDto.builder()
                .boastContent("test")
                .memberId(member.getMemberId())
                .petId(pet.getPetId())
                .build();

        Long boastId = boastService.saveBoast(boastSaveDto);

        Boast boast = boastRepository.findOneByBoastId(boastId).get();

        assertThat(boastSaveDto.getBoastContent()).isEqualTo(boast.getBoastContent());
        assertThat(boastSaveDto.getMemberId()).isEqualTo(boast.getMember().getMemberId());
        assertThat(boastSaveDto.getPetId()).isEqualTo(boast.getPet().getPetId());
    }

    @DisplayName("자랑하기 삭제")
    @Test
    void deleteBoast() {
        BoastSaveDto boastSaveDto = BoastSaveDto.builder()
                .boastContent("test")
                .memberId(member.getMemberId())
                .petId(pet.getPetId())
                .build();

        Long boastId = boastService.saveBoast(boastSaveDto);

        boastService.deleteBoast(boastId);

        List<Boast> list = boastRepository.findAll();
        assertThat(list.size()).isEqualTo(0);
    }
}