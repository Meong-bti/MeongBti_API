package projectB.meongbti.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import projectB.meongbti.pet.entity.PetGender;
import projectB.meongbti.pet.entity.PetNtlz;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetSaveDto {

    @NotBlank(message = "펫 이름을 입력해주세요.")
    private String petName;
    @NotNull(message = "견종을 선택해주세요.")
    private String petBreed;

    @NotBlank(message = "펫이 태어난 연도를 입력해주세요.")
    private String birthYear;
    @NotBlank(message = "펫이 태어난 월을 입력해주세요.")
    private String birthMonth;
    @NotBlank(message = "펫이 태어난 일을 입력해주세요.")
    private String birthDay;

    @NotNull(message = "펫의 성별을 선택해주세요.")
    private PetGender petGender;
    @NotNull(message = "펫의 중성화 여부를 선택해주세요.")
    private PetNtlz petNtlz;

    @NotBlank(message = "펫의 몸무게를 입력해주세요.")
    private String positiveWeight;
    @NotBlank(message = "펫의 몸무게를 입력해주세요.")
    private String negativeWeight;

    private MultipartFile petImage;

    private Long memberId;

    //== 비즈니스 로직 ==//
    //강아지의 생년 월일
    public LocalDate getBirthday() {
        return LocalDate.of(Integer.parseInt(this.birthYear), Integer.parseInt(birthMonth), Integer.parseInt(birthDay));
    }

    //강아지의 몸무게
    public Double getWeight() {
        String weight = this.positiveWeight + "." + negativeWeight;

        return Double.parseDouble(weight);
    }
}

