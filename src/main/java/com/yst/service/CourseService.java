package com.yst.service;

import com.yst.pojo.Course;
import com.yst.pojo.School;
import com.yst.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CourseService {
    Course findCourseById(Integer id);
    //插入一门课程
    int insertCourse(Course course);
    //通过id删除课程
    int deleteCourseById(Integer id);
    //通过id更新课程
    int updateCourseById(Course course);
    //showAllCourseWithSchool
    List<Course> showAllCourseWithSchool();

    User validateUser(String userName, String password);

    List<Course> getCourseByName(String name);
}
