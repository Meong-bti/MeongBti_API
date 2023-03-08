package projectB.meongbti.exception.heart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum HeartErrorCode {
    HEART_NOT_FOUND(HttpStatus.BAD_REQUEST, "좋아요 등록하지 않은 게시글입니다.")
    ;

    private HttpStatus status;
    private String message;


}
