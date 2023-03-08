package projectB.meongbti.pet.dto;

import lombok.*;
import projectB.meongbti.pet.entity.PetGender;
import projectB.meongbti.pet.entity.PetNtlz;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetDto {

    private Long petId;

    private String petName;

    private String petBreed;

    private LocalDate petBday;

    private PetGender petGender;

    private PetNtlz petNtlz;

    private Double petWeight;

    private String petMbti;

    private String petImage;

    private Long memberId;
}
