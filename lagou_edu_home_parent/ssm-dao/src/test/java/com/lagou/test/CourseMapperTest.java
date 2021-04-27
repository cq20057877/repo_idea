package com.lagou.test;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-dao.xml")
public class CourseMapperTest {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    public void test1(){
        // CourseVO courseVO = new CourseVO("11", 0);
        CourseVO courseVO = new CourseVO();
        // courseVO.setCourseName("11");
        // courseVO.setStatus(0);
        List<Course> list = courseMapper.findCourseByCondition(courseVO);
        for (Course course : list) {
            System.out.println(course);
        }
    }
}
