package com.ssafy.yeohaengdam.course.controller;

import com.ssafy.yeohaengdam.core.annotation.CurrentUser;
import com.ssafy.yeohaengdam.course.dto.CourseData;
import com.ssafy.yeohaengdam.course.service.CourseService;
import com.ssafy.yeohaengdam.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssafy.yeohaengdam.course.dto.CourseData.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;


    @PostMapping("/save")
    public ResponseEntity<Void> save(@CurrentUser User user,
                                     @RequestBody Save save){
        System.out.println("save " + save);
        System.out.println("니가 원하는 파일이름들" + save.getSchedules());
        courseService.save(user.getUserId(), save);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("update")
    public ResponseEntity<Void> update(@CurrentUser User user,
                                       @RequestBody Update update){
        courseService.update(user.getUserId(), update);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@CurrentUser User user,
                                       @RequestBody Delete delete){
        courseService.delete(user.getUserId(), delete);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Detail> findOne(@CurrentUser User user,
                                          @PathVariable(value = "courseId") int courseId){
        return ResponseEntity.ok(courseService.findById(courseId));
    }

    @GetMapping
    public ResponseEntity<List<ListUp>> findCourseByUser(@CurrentUser User user){
        return ResponseEntity.ok(courseService.findByUserId(user.getUserId()));
    }
}
