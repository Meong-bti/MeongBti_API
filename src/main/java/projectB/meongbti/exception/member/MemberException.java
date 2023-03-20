package projectB.meongbti.exception.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;

    public MemberException(ErrorCode errorCode){
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
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
