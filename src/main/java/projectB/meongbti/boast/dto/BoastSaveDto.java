package projectB.meongbti.boast.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import projectB.meongbti.member.entity.Member;
import projectB.meongbti.pet.entity.Pet;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoastSaveDto {

    private String boastContent;

    private MultipartFile boastImage;

    private Long memberId;

    private Long petId;
}
