package projectB.meongbti.exception.boast;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BoastErrorCode {
    BOAST_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 자랑하기입니다.")

    ;

    private HttpStatus status;
    private String message;


}
