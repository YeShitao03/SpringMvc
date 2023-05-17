package com.yst.controller;

import com.yst.pojo.Course;
import com.yst.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    //上传
    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws Exception {
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = UUID.randomUUID() + suffixName;
        //指定本地文件夹存储图片
        String path = request.getServletContext().getRealPath("/") + "img/";
        System.out.println(path+fileName);
        request.getServletContext();
        try {
            System.out.println(path);
            file.transferTo(new File(path + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(path);
        return "../img/"+fileName;
    }

    @GetMapping("/{id}")
    public Result findCourseById(@PathVariable Integer id) {
        Course course = courseService.findCourseById(id);
        Integer code = course != null ? Code.QUERY_OK : Code.QUERY_ERR;
        String msg = course != null ? "查询成功" : "无此课程";
        return new Result(code, msg, course);
    }

    //添加一门课程
    @PostMapping
    public Result insertCourse(@RequestBody Course course) {
        if(course.getPictureUrl()==null){
            course.setPictureUrl("../img/1.jpg");
        }
        Integer flag = courseService.insertCourse(course);
        Integer code = flag != 0 ? Code.ADD_OK : Code.ADD_ERR;
        String msg = flag != 0 ? "添加成功" : "添加失败";
        return new Result(code, msg);
    }

    //通过id删除课程
    @DeleteMapping("/{id}")
    public Result deleteCourseById(@PathVariable Integer id) {
        Integer flag = courseService.deleteCourseById(id);
        Integer code = flag != 0 ? Code.DELETE_OK : Code.DELETE_ERR;
        String msg = flag != 0 ? "删除成功" : "删除失败;或者该课程不存在";
        return new Result(code, msg);
    }

    //通过id更新课程
    @PutMapping
    public Result updateCourseById(@RequestBody Course course) {
        Integer flag = courseService.updateCourseById(course);
        Integer code = flag != 0 ? Code.UPDATE_OK : Code.UPDATE_ERR;
        String msg = flag != 0 ? "更新成功" : "更新失败";
        return new Result(code, msg);
    }

    //showAllCourseWithSchool
    @GetMapping("/all")
    public Result showAllCourseWithSchool() {
        List<Course> courseList = courseService.showAllCourseWithSchool();
        Integer code = !courseList.isEmpty() ? Code.QUERY_OK : Code.QUERY_ERR;
        String msg = !courseList.isEmpty() ? "查询成功" : "查询失败";
        return new Result(code, msg, courseList);
    }

    //getCourseByName
    @GetMapping("/getCourseByName/{name}")
    public Result getCourseByName (@PathVariable String name) throws UnsupportedEncodingException {
        name = URLEncoder.encode(name, "ISO-8859-1");
        name = URLDecoder.decode(name, "UTF-8");
        List<Course> courseList = courseService.getCourseByName(name);
        Integer code = !courseList.isEmpty() ? Code.QUERY_OK : Code.QUERY_ERR;
        String msg = !courseList.isEmpty() ? "查询成功" : "请输入正确的关键词";
        return new Result(code, msg, courseList);
    }
}

