package com.yst.service.impl;

import com.yst.dao.CourseMapper;
import com.yst.pojo.Course;
import com.yst.pojo.School;
import com.yst.pojo.User;
import com.yst.service.CourseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    public Course findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    //插入一门课程
    public int insertCourse(Course course) {
        return courseMapper.insertCourse(course);
    }

    //通过id删除课程
    public int deleteCourseById(Integer id) {
        return courseMapper.deleteCourseById(id);
    }
    //通过id更新课程
    public int updateCourseById(Course course) {
        return courseMapper.updateCourseById(course);
    }


    //showAllCourseWithSchool
    public List<Course> showAllCourseWithSchool() {
        return courseMapper.showAllCourseWithSchool();
    }

    public User validateUser(String userName, String password){
        return courseMapper.validateUser(userName,password);
    }

    public List<Course> getCourseByName(@Param("name") String name){
        return courseMapper.getCourseByName(name);
    }
}