package projectB.meongbti.exception.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_MEMBER_EMAIL(HttpStatus.CONFLICT,"이미 가입된 이메일입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"유저를 찾을 수 없습니다."),



    ;

    private HttpStatus status;
    private String message;


}
