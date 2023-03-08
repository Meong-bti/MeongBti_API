package projectB.meongbti.exception.boast;

import lombok.Getter;
import projectB.meongbti.exception.pet.PetErrorCode;

@Getter
public class BoastException extends RuntimeException{

    private BoastErrorCode boastErrorCode;

    public BoastException(BoastErrorCode boastErrorCode){
        this.boastErrorCode = boastErrorCode;
    }
}
