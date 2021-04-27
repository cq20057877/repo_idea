package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){
        List<Course> courseList = courseService.findCourseByCondition(courseVO);

        //Boolean success, Integer state, String message, Object content
        return new ResponseResult(true , 200 , "请求成功" , courseList);
    }

    /*
        课程文件上传
     */
    @RequestMapping("/courseUpload")
    public ResponseResult courseUpload(@RequestParam("file") MultipartFile file , HttpServletRequest request) throws IOException {
        //判断文件是否为空
        if (file.isEmpty()){
            throw new RuntimeException("上传文件为空");
        }

        //获取项目部署路径
        // D:\apache-tomcat-8.5.56\webapps\ssm_web\
        String path = request.getServletContext().getRealPath("/");
        //截取上级目录
        // D:\apache-tomcat-8.5.56\webapps\
        path = path.substring(0 , path.indexOf("ssm-web"));

        //获取源文件名
        String filename = file.getOriginalFilename();
        //生成新文件名 系统时间戳 + 源文件名的.后面的字符串 , 就是时间 + 文件格式
        String newName = System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));

        //文件上传
        String uploadPath = path + "upload\\";
        File filePath = new File(uploadPath, newName);

        //如果目录不存在就创建
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录:" + filePath);
        }

        //上传图片
        file.transferTo(filePath);

        //将文件名和路径返回 , 进行响应
        Map<String , String> map = new HashMap<>();
        map.put("fileName" , newName);
        map.put("filePath" , "http://localhost:8080/upload/" + newName);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);
        return responseResult;
    }

    /*
        新增课程信息和讲师信息
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        if (courseVO.getId() == null){
            courseService.saveCourseOrTeacher(courseVO);
            return new ResponseResult(true , 200 , "新增成功" , null);
        }else {
            courseService.updateCourseOrTeacher(courseVO);
            return new ResponseResult(true , 200 , "修改成功" , null);
        }
    }

    /*
        根据课程id查询课程
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVO courseVO = courseService.findCourseById(id);

        return new ResponseResult(true , 200 , "响应成功" , courseVO);
    }

    /*
        修改课程状态
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id , Integer status){
        courseService.updateCourseStatus(id , status);
        HashMap<String, Object> map = new HashMap<>();
        map.put("status" , status);
        return new ResponseResult(true , 200 , "响应成功" , map);
    }
}
