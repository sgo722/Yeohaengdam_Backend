package com.ssafy.yeohaengdam.course.mapper;

import com.ssafy.yeohaengdam.course.dto.CourseData;
import com.ssafy.yeohaengdam.course.entity.Course;
import com.ssafy.yeohaengdam.course.entity.Schedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static com.ssafy.yeohaengdam.course.dto.CourseData.*;

@Mapper
public interface CourseMapper {

    public int save(Course course);

    public int update(Course course);

    public void saveSchedules(int courseId, List<Schedule> schedules);

    public void deleteSchedules(int courseId);



    public Detail findById(int courseId);

    public List<ListUp> findByUserId(int userId);

    public void delete(int courseId);

}
