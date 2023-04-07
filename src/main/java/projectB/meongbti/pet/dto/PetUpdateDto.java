package projectB.meongbti.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import projectB.meongbti.pet.entity.PetGender;
import projectB.meongbti.pet.entity.PetNtlz;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetUpdateDto {

    private String petName;

    private PetNtlz petNtlz;

    private String positiveWeight;
    private String negativeWeight;

    private MultipartFile petImage;

    //== 비즈니스 로직 ==//
    //강아지의 몸무게
    public Double getWeight() {
        String weight = this.positiveWeight + "." + negativeWeight;

        return Double.parseDouble(weight);
    }
}

