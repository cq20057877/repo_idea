package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    /*
        课程内容展示
    */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /*
        根据id查询课程信息
     */
    public Course findCourseByCourseId(Integer id);

    /*
        新增章节信息
     */
    public void saveSection(CourseSection courseSection);

    /*
        修改章节信息
     */
    public void updateSection(CourseSection courseSection);

    /*
        修改章节状态
    */
    public void updateSectionStatus(CourseSection courseSection);

    /*
        新增课时信息
     */
    public void saveLesson(CourseLesson courseLesson);
}
