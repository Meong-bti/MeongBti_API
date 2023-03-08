package projectB.meongbti.exception.heart;

import lombok.Getter;
import projectB.meongbti.exception.pet.PetErrorCode;
import projectB.meongbti.heart.dto.HeartRequestDto;

@Getter
public class HeartException extends RuntimeException{

    private HeartErrorCode heartErrorCode;

    public HeartException(HeartErrorCode heartErrorCode){
        this.heartErrorCode = heartErrorCode;
    }
}
