package projectB.meongbti.exception.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import projectB.meongbti.util.response.Response;

@Getter
@AllArgsConstructor
public class MemberException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;

    public MemberException(ErrorCode errorCode){
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

    //500서버 에러

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> applicationHandler(RuntimeException e){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(ErrorCode.INTERNAL_SERVER_ERROR.getMessage()));
    }

//    @Override
//    public String getMessage() {
//
//        if(message == null)
//            return errorCode.getMessage();
//
//        return String.format("%s : %s", errorCode.getMessage(), message);
//    }


    public String getMessage() {
        return message;
    }
}
