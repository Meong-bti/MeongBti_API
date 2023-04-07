package projectB.meongbti.pet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectB.meongbti.exception.member.ErrorCode;
import projectB.meongbti.exception.member.MemberException;
import projectB.meongbti.exception.pet.PetErrorCode;
import projectB.meongbti.exception.pet.PetException;
import projectB.meongbti.member.entity.Member;
import projectB.meongbti.member.repository.MemberRepository;
import projectB.meongbti.pet.dto.PetDto;
import projectB.meongbti.pet.dto.PetSaveDto;
import projectB.meongbti.pet.dto.PetUpdateDto;
import projectB.meongbti.pet.entity.Pet;
import projectB.meongbti.pet.repository.PetRepository;
import projectB.meongbti.util.image.ImageStore;
import projectB.meongbti.util.image.repository.ImageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PetService {

    private final PetRepository petRepository;
    private final MemberRepository memberRepository;

    //이미지 저장
    private final ImageStore imageStore;
    private final ImageRepository imageRepository;

    /**
     * 펫등록
     */
    public Long savePet(PetSaveDto petSaveDto) {
        //전달 받은 memberId를 통해 member 정보 조회
        Member member = memberRepository.findById(petSaveDto.getMemberId()).orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));

        //이미지를 저장한다.
//        String fullPath = "";
//        if (!petSaveDto.getPetImage().isEmpty()) {
//            ImageMapping imageMapping;
//
//            try {
//                imageMapping = imageStore.storeFile(petSaveDto.getPetImage());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//            fullPath = imageStore.getFullPath(imageMapping.getImStore());
//            imageRepository.save(imageMapping);
//        }

        //조회한 member를 pet 엔티티에 저장
        Pet pet = Pet.builder()
                .petName(petSaveDto.getPetName())
                .petBreed(petSaveDto.getPetBreed())
                .petBday(petSaveDto.getBirthday())
                .petGender(petSaveDto.getPetGender())
                .petNtlz(petSaveDto.getPetNtlz())
                .petWeight(petSaveDto.getWeight())
//                .petImage(fullPath)
                .member(member)
                .build();

        //펫 등록
        petRepository.save(pet);

        return pet.getPetId();
    }

    /**
     * 펫 삭제
     */
    public Long deletePet(Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new PetException(PetErrorCode.PET_NOT_FOUND));

        petRepository.delete(pet);

        return petId;
    }

    /**
     * 펫 수정
     */
    public Long updatePet(Long petId, PetUpdateDto petUpdateDto) {
        //petId를 이용하여 펫 정보를 우선적으로 조회
        //펫 정보가 없으면 NotExistPet 예외를 발생시킨다.
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new PetException(PetErrorCode.PET_NOT_FOUND));

        //이미지를 저장한다.
        String fullPath = pet.getPetImage();
//        if (!petUpdateDto.getPetImage().isEmpty()) {
//            ImageMapping imageMapping;
//            try {
//                imageMapping = imageStore.storeFile(petUpdateDto.getPetImage());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            fullPath = imageStore.getFullPath(imageMapping.getImStore());
//            imageRepository.save(imageMapping);
//        }

        //펫 정보를 업데이트
        pet.updatePet(petUpdateDto, fullPath);

        return pet.getPetId();
    }

    /**
     * 펫ID를 이용하여 펫 정보 조회
     */
    @Transactional(readOnly = true)
    public PetDto findOneByPetId(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new PetException(PetErrorCode.PET_NOT_FOUND));

        return pet.fromEntity(pet);
    }

    /**
     * 멤버ID를 이용하여 멤버의 애완동물을 조회
     */
    @Transactional(readOnly = true)
    public List<PetDto> findAllByMemberId(Long memberId) {
        List<Pet> findPets = petRepository.findByMemberId(memberId);

        List<PetDto> returnList = new ArrayList<>();

        findPets.stream().forEach(pet -> {
            PetDto petDto = pet.fromEntity(pet);
            returnList.add(petDto);
        });

        return returnList;
    }
}
