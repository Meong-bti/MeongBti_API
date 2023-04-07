package projectB.meongbti.heart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projectB.meongbti.heart.dto.HeartDto;
import projectB.meongbti.heart.dto.HeartRequestDto;
import projectB.meongbti.heart.service.HeartService;
import projectB.meongbti.util.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/heart")
public class HeartController {

    private final HeartService heartService;

    /**
     * 좋아요 추가
     */
    @PostMapping("/add")
    public Response<Map<String, Long>> addHeart(@RequestBody HeartRequestDto heartRequestDto) {
        Long heartId = heartService.addHeart(heartRequestDto);

        HashMap<String, Long> resultData = new HashMap<>();
        resultData.put("heartId", heartId);

        return Response.success("좋아요를 성공적으로 등록했습니다.", resultData);
    }

    /**
     * 좋아요 취소
     */
    @DeleteMapping("/cancel")
    public Response<Map<String, Long>> cancelHeart(@RequestBody HeartRequestDto heartRequestDto) {
        Long heartId = heartService.cancelHeart(heartRequestDto);

        HashMap<String, Long> resultData = new HashMap<>();
        resultData.put("heartId", heartId);

        return Response.success("좋아요를 성공적으로 취소했습니다.", resultData);
    }

    /**
     * 멤버ID를 이용하여 좋아요 한 게시글 조회
     */
    @GetMapping("/{memberId}")
    public List<HeartDto> findByMemberId(@PathVariable Long memberId) {
        return heartService.findByMemberId(memberId);
    }

}
