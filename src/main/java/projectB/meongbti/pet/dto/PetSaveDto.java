package projectB.meongbti.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import projectB.meongbti.pet.entity.PetGender;
import projectB.meongbti.pet.entity.PetNtlz;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetSaveDto {

    private String petName;

    private String petBreed;

    private LocalDate petBday;

    private PetGender petGender;

    private PetNtlz petNtlz;

    private Double petWeight;

    private MultipartFile petImage;

    private Long memberId;

}

