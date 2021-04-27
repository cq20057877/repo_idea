package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /*
        分页查询广告
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO){
        PageInfo<PromotionAd> page = promotionAdService.findAllPromotionAdByPage(promotionAdVO);
        return new ResponseResult(true , 200 , "响应成功" , page);
    }

    /*
        图片上传
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult PromotionAdUpload(@RequestParam("file") MultipartFile file , HttpServletRequest request) throws IOException {
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

        return new ResponseResult(true, 200, "图片上传成功", map);
    }

    /*
        新建和修改广告
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        System.out.println(promotionAd);
        if (promotionAd.getId() == null){
            //新增
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true, 200, "新增成功", null);
        }else {
            //修改
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true, 200, "修改成功", null);
        }
    }

    /**
     * 根据id查询广告信息做回显
     * @return
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(Integer id){
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
        return new ResponseResult(true, 200, "修改成功", promotionAd);
    }

    /**
     * 广告动态上下线
     * @param promotionAd
     * @return
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(PromotionAd promotionAd){
        promotionAdService.updatePromotionAdStatus(promotionAd);
        Map<String , Object> map = new HashMap<>();
        map.put("status" , promotionAd.getStatus());
        return new ResponseResult(true, 200, "响应成功", map);
    }
}


