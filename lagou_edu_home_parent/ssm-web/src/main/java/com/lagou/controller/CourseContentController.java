package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    /*
        课程内容展示
    */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(Integer courseId){
        List<CourseSection> sectionList = courseContentService.findSectionAndLessonByCourseId(courseId);
        return new ResponseResult(true , 200 , "响应成功" , sectionList);
    }

    /*
        回显课程名
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);
        // HashMap<String, Object> map = new HashMap<>();
        // map.put("id" , course.getId());
        // map.put("courseName" , course.getCourseName());
        return new ResponseResult(true , 200 , "响应成功" , course);
    }

    /*
        新增或者修改章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        if (courseSection.getId() != null ){
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true , 200 , "修改成功" , null);
        }else {
            courseContentService.saveSection(courseSection);
            return new ResponseResult(true , 200 , "新增成功" , null);
        }
    }

    /*
        修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(CourseSection courseSection){
        HashMap<String, Object> map = new HashMap<>();
        map.put("status" , courseSection.getStatus());
        courseContentService.updateSectionStatus(courseSection);
        return new ResponseResult(true , 200 , "响应成功" , map);
    }

    /*
        新建课时信息
     */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson courseLesson){
        courseContentService.saveLesson(courseLesson);
        return new ResponseResult(true , 200 , "响应成功" , null);
    }
}
