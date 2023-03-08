package projectB.meongbti.exception.pet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PetErrorCode {
    PET_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 펫입니다.")

    ;

    private HttpStatus status;
    private String message;


}
