package projectB.meongbti.heart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeartBoastDto {

    private Long boastId;
    private LocalDateTime boastDate;
    private String boastContent;
    private String boastImage;
    private String memberNick;
    private String petMbti;
    private HeartDto heartDto;
}
