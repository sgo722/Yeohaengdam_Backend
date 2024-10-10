package com.ssafy.yeohaengdam.spot.controller;

import com.ssafy.yeohaengdam.spot.service.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ssafy.yeohaengdam.spot.dto.SpotData.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/spot")
public class SpotController {

    private final SpotService spotService;

    /**
     * 구군코드 조회
     * @param sidoCode
     * @param sidoName
     * @return
     */
    @GetMapping("/listGugun")
    public ResponseEntity<List<GugunCode>> listGugun(@RequestParam(value = "sidoCode") int sidoCode,
                                                    @RequestParam(value = "sidoName") String sidoName){

        try{
            List<GugunCode> gugunList = spotService.findGugunBySido(sidoCode, sidoName);
            return ResponseEntity.ok(gugunList);
        }catch(Exception e){
            throw new IllegalArgumentException("구군 조회에 실패했습니다.");
        }
    }


    /**
     * 조건(시도코드, 구군코드)에 포함되는 관광지 조회
     * @return
     */
    @GetMapping("/listSpot")
    public ResponseEntity<List<SpotInfo>> listSpot(@RequestParam(value = "sidoCode") int sidoCode,
                                                   @RequestParam(value = "gugunCode") int gugunCode,
                                                   @RequestParam(value = "contentCode") int contentCode){
        try{
            List<SpotInfo> spotList = spotService.findSpotByCondition(sidoCode, gugunCode, contentCode);
            return ResponseEntity.ok(spotList);
        }catch(Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("관광지 조회에 실패했습니다.");
        }
    }



}
