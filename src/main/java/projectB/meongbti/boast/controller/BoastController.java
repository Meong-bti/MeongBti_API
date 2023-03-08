package projectB.meongbti.boast.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projectB.meongbti.boast.dto.BoastSaveDto;
import projectB.meongbti.boast.entity.Boast;
import projectB.meongbti.boast.service.BoastService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boast")
public class BoastController {

    private final BoastService boastService ;

    @PostMapping("/save")
    public Long saveBoast(@RequestBody BoastSaveDto boastSaveDto) {
        return boastService.saveBoast(boastSaveDto);
    }

    @DeleteMapping("/delete/{boastId}")
    public Long deleteBoast(@PathVariable Long boastId) {
        return boastService.deleteBoast(boastId);
    }

    @GetMapping("/findAll")
    public List<Boast> findAll() {
        return boastService.findAll();
    }
}
