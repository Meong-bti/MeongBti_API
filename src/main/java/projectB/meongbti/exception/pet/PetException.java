package projectB.meongbti.exception.pet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import projectB.meongbti.exception.member.ErrorCode;
import projectB.meongbti.pet.entity.Pet;

@Getter
public class PetException extends RuntimeException{

    private PetErrorCode petErrorCode;

    public PetException(PetErrorCode petErrorCode){
        this.petErrorCode = petErrorCode;
    }
}
