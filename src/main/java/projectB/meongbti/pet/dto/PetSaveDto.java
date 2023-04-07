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

    private String birthYear;
    private String birthMonth;
    private String birthDay;

    private PetGender petGender;
    private PetNtlz petNtlz;

    private String positiveWeight;
    private String negativeWeight;

    private MultipartFile petImage;

    private Long memberId;

    //== 비즈니스 로직 ==//
    //강아지의 생년 월일
    public LocalDate getBirthday() {
        return LocalDate.of(Integer.parseInt(this.birthDay), Integer.parseInt(birthMonth), Integer.parseInt(birthDay));
    }

    //강아지의 몸무게
    public Double getWeight() {
        String weight = this.positiveWeight + "." + negativeWeight;

        return Double.parseDouble(weight);
    }
}

