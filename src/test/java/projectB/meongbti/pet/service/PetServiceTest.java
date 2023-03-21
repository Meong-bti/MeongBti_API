package projectB.meongbti.pet.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import projectB.meongbti.exception.pet.PetException;
import projectB.meongbti.member.entity.Member;
import projectB.meongbti.member.repository.MemberRepository;
import projectB.meongbti.pet.dto.PetDto;
import projectB.meongbti.pet.dto.PetSaveDto;
import projectB.meongbti.pet.dto.PetUpdateDto;
import projectB.meongbti.pet.entity.Pet;
import projectB.meongbti.pet.entity.PetGender;
import projectB.meongbti.pet.entity.PetNtlz;
import projectB.meongbti.pet.repository.PetRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@Slf4j
class PetServiceTest {

    @Autowired
    PetService petService;
    @Autowired
    PetRepository petRepository;
    @Autowired
    MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void memberSave() {
        member = Member.builder()
                .memberEmail("test@test.com")
                .memberPw("test")
                .memberNick("test")
                .build();
        memberRepository.save(member);
    }

    @DisplayName("펫 등록")
    @Test
    void petSave() {
        PetSaveDto petSaveDto = PetSaveDto.builder()
                .petName("test")
                .petBreed("골든리트리버")
                .petBday(LocalDate.now())
                .petGender(PetGender.MALE)
                .petNtlz(PetNtlz.NTLZ)
                .petWeight(11.11)
                .memberId(member.getMemberId())
                .build();

        //when
        Long petId = petService.savePet(petSaveDto);

        //then
        Pet pet = petRepository.findById(petId).get();

        assertThat(petSaveDto.getPetName()).isEqualTo(pet.getPetName());
        assertThat(petSaveDto.getPetBreed()).isEqualTo(pet.getPetBreed());
        assertThat(petSaveDto.getPetBday()).isEqualTo(pet.getPetBday());
        assertThat(petSaveDto.getPetGender()).isEqualTo(pet.getPetGender());
        assertThat(petSaveDto.getPetNtlz()).isEqualTo(pet.getPetNtlz());
        assertThat(petSaveDto.getPetWeight()).isEqualTo(pet.getPetWeight());
        assertThat(petSaveDto.getMemberId()).isEqualTo(pet.getMember().getMemberId());
    }

    @DisplayName("펫 삭제")
    @Test
    void deletePet() throws IOException {
        PetSaveDto petSaveDto = PetSaveDto.builder()
                .petName("test")
                .petBreed("골든리트리버")
                .petBday(LocalDate.now())
                .petGender(PetGender.MALE)
                .petNtlz(PetNtlz.NTLZ)
                .petWeight(11.11)
                .memberId(member.getMemberId())
                .build();

        //when
        Long petId = petService.savePet(petSaveDto);

        //then
        petService.deletePet(petId);

        assertThatThrownBy(() -> petService.findOneByPetId(petId)).isInstanceOf(PetException.class);
    }

    @DisplayName("펫 수정")
    @Test
    void petUpdate() throws IOException {
        PetSaveDto petSaveDto = PetSaveDto.builder()
                .petName("test")
                .petBreed("골든리트리버")
                .petBday(LocalDate.now())
                .petGender(PetGender.MALE)
                .petNtlz(PetNtlz.NTLZ)
                .petWeight(11.11)
                .memberId(member.getMemberId())
                .build();

        Long petId = petService.savePet(petSaveDto);

        //when
        PetUpdateDto petUpdateDto = PetUpdateDto.builder()
                .petName("updateTest")
                .petBreed("말라뮤트")
                .petBday(LocalDate.now())
                .petGender(PetGender.FEMALE)
                .petNtlz(PetNtlz.NONE)
                .petWeight(22.22)
                .build();

        Long findPetId = petService.updatePet(petId, petUpdateDto);
        Pet pet = petRepository.findById(findPetId).get();

        //then
        assertThat(petUpdateDto.getPetName()).isEqualTo(pet.getPetName());
        assertThat(petUpdateDto.getPetBreed()).isEqualTo(pet.getPetBreed());
        assertThat(petUpdateDto.getPetBday()).isEqualTo(pet.getPetBday());
        assertThat(petUpdateDto.getPetGender()).isEqualTo(pet.getPetGender());
        assertThat(petUpdateDto.getPetNtlz()).isEqualTo(pet.getPetNtlz());
        assertThat(petUpdateDto.getPetWeight()).isEqualTo(pet.getPetWeight());
    }

    @DisplayName("펫조회By펫Id")
    @Test
    void findOneByPetId() throws IOException {
        PetSaveDto petSaveDto = PetSaveDto.builder()
                .petName("test")
                .petBreed("골든리트리버")
                .petBday(LocalDate.now())
                .petGender(PetGender.MALE)
                .petNtlz(PetNtlz.NTLZ)
                .petWeight(11.11)
                .memberId(member.getMemberId())
                .build();

        //when
        Long petId = petService.savePet(petSaveDto);

        //then
        PetDto findPetDto = petService.findOneByPetId(petId);

        assertThat(petSaveDto.getPetName()).isEqualTo(findPetDto.getPetName());
        assertThat(petSaveDto.getPetBreed()).isEqualTo(findPetDto.getPetBreed());
        assertThat(petSaveDto.getPetBday()).isEqualTo(findPetDto.getPetBday());
        assertThat(petSaveDto.getPetGender()).isEqualTo(findPetDto.getPetGender());
        assertThat(petSaveDto.getPetNtlz()).isEqualTo(findPetDto.getPetNtlz());
        assertThat(petSaveDto.getPetWeight()).isEqualTo(findPetDto.getPetWeight());
    }

    @DisplayName("펫조회 실패")
    @Test
    void findPetFail () {
        assertThrows(PetException.class, () -> petService.findOneByPetId(1L));
    }


    @DisplayName("펫목록조회By멤버Id")
    @Test
    void findAllMemberId () throws IOException {
        PetSaveDto petSaveDto1 = PetSaveDto.builder()
                .petName("test")
                .petBreed("골든리트리버")
                .petBday(LocalDate.now())
                .petGender(PetGender.MALE)
                .petNtlz(PetNtlz.NTLZ)
                .petWeight(11.11)
                .memberId(member.getMemberId())
                .build();

        PetSaveDto petSaveDto2 = PetSaveDto.builder()
                .petName("test2")
                .petBreed("말라뮤트")
                .petBday(LocalDate.now())
                .petGender(PetGender.FEMALE)
                .petNtlz(PetNtlz.NONE)
                .petWeight(22.22)
                .memberId(member.getMemberId())
                .build();

        //when
        petService.savePet(petSaveDto1);
        petService.savePet(petSaveDto2);

        //then
        List<PetDto> findPets = petService.findAllByMemberId(member.getMemberId());

        assertThat(findPets.size()).isEqualTo(2);
    }

}