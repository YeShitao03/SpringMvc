package com.yst.dao;

import com.yst.pojo.Course;
import com.yst.pojo.School;
import com.yst.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CourseMapper {
    @Select("select * from course where id=#{id}")
    @Results({
            @Result (id = true,property = "id",column = "id"),
            @Result (property = "name",column = "name"),
            @Result (property = "hours",column = "hours"),
            @Result (property = "sid",column = "sid"),
            @Result (property = "pictureUrl",column = "picture_url"),
            @Result (property = "schoolName",column = "sid",one = @One(select = "com.yst.dao.CourseMapper.findSchoolBySid"))
    })
    Course findCourseById(@Param("id") Integer id);

    //插入一门课程
    @Insert("insert into course (name, hours, sid,picture_url) values (#{name},#{hours},#{sid},#{pictureUrl})")
    int insertCourse(Course course);

    //通过查询sid来查询课程
    @Select("select * from course where sid=#{sid}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "hours",column = "hours"),
            @Result(property = "sid",column = "sid")
    })
    List<Course> findCourseBySid(@Param("sid") Integer sid);

    //通过id删除课程
    @Delete("delete from course where id=#{id}")
    int deleteCourseById(@Param("id") Integer id);

    //通过id更新课程
    @Update("update course set name=#{name},hours=#{hours},sid=#{sid},picture_url=#{pictureUrl} where id=#{id}")
    int updateCourseById(Course course);

    //showAllCourseWithSchool
    @Select("select * from course order by sid asc")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "hours",column = "hours"),
            @Result(property = "sid",column = "sid"),
            @Result (property = "pictureUrl",column = "picture_url"),
            @Result(property = "schoolName",column = "sid",one = @One(select = "com.yst.dao.CourseMapper.findSchoolBySid"))
    })
    List<Course> showAllCourseWithSchool();
    //findSchoolBySid
    @Select("select schoolname from school where id=#{sid}")
    String findSchoolBySid(@Param("sid") Integer sid);

    @Select("select * from user where user_name=#{userName} and password=#{password}")
    @Results({
            @Result(id = true,property = "userId",column = "user_id"),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "password",column = "password")
    })
    User validateUser(@Param("userName")String userName,@Param("password") String password);

    //getCourseByName
    @Select("select * from course where name like concat('%',#{name},'%')")
    @Results({
            @Result (id = true,property = "id",column = "id"),
            @Result (property = "name",column = "name"),
            @Result (property = "hours",column = "hours"),
            @Result (property = "sid",column = "sid"),
            @Result (property = "pictureUrl",column = "picture_url"),
            @Result (property = "schoolName",column = "sid",one = @One(select = "com.yst.dao.CourseMapper.findSchoolBySid"))
    })
    List<Course> getCourseByName(@Param("name") String name);
}