package projectB.meongbti.pet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projectB.meongbti.pet.dto.PetDto;
import projectB.meongbti.pet.dto.PetSaveDto;
import projectB.meongbti.pet.dto.PetUpdateDto;
import projectB.meongbti.pet.service.PetService;
import projectB.meongbti.util.response.Response;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    /**
     * 펫 등록
     */
    @PostMapping("save")
    public Response<Map<String, Long>> savePet(@RequestBody PetSaveDto petSaveDto) {
        Long petId = petService.savePet(petSaveDto);

        HashMap<String, Long> resultData = new HashMap<>();
        resultData.put("petId", petId);

        return Response.success("성공", resultData);
    }


    /**
     * 펫 수정
     */
    @PatchMapping("/update/{petId}")
    public Response<Map<String, Long>> updatePet(@PathVariable Long petId, @RequestBody PetUpdateDto petUpdateDto) {
        Long updatedPetId = petService.updatePet(petId, petUpdateDto);

        HashMap<String, Long> resultData = new HashMap<>();
        resultData.put("petId", updatedPetId);

        return Response.success("정상적으로 수정되었습니다.", resultData);
    }

    /**
     * 펫 삭제
     */
    @DeleteMapping("/delete/{petId}")
    public Response<HashMap<String, Long>> deletePet(@PathVariable Long petId) {

        Long deletedPetId = petService.deletePet(petId);

        HashMap<String, Long> resultData = new HashMap<>();
        resultData.put("petId", deletedPetId);

        return Response.success("정상적으로 삭제되었습니다.", resultData);
    }

    /**
     * 펫ID를 이용하여 펫 정보 조회
     */
    @GetMapping("/{petId}")
    public Response<PetDto> findOneByPetId(@PathVariable Long petId) {
        PetDto petDto = petService.findOneByPetId(petId);

        return Response.success("정상적으로 조회되었습니다. petId = " + petId, petDto);
    }

    /**
     * 멤버ID를 이용하여 멤버의 애완동물을 조회
     */
    @GetMapping("/member/{memberId}")
    public Response<List<PetDto>> findAllByMemberId(@PathVariable Long memberId) {
        List<PetDto> findPets = petService.findAllByMemberId(memberId);

        return Response.success("정상적으로 목록이 조회되었습니다.", findPets);
    }
}
