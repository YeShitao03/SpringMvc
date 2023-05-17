import com.yst.config.SpringConfig;
import com.yst.dao.CourseMapper;
import com.yst.pojo.Course;
import com.yst.pojo.School;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class MyTest {
    @Autowired
    private CourseMapper courseMapper;    //showAllCourseWithSchool
    @Test
    public void test1() {
        List<Course> courseList = courseMapper.showAllCourseWithSchool();
        for (Course course : courseList) {
            System.out.println(course);
        }
    }
    @Test
    public void test2(){
        Course courseById = courseMapper.findCourseById(1);
        System.out.println(courseById);
    }
    @Test
    public void test3(){
        List<Course> java = courseMapper.getCourseByName("程序设计");
        for (Course course : java) {
            System.out.println(course);
        }
    }
}
