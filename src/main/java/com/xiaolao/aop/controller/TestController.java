package com.xiaolao.aop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.xiaolao.aop.service.ITestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: WuJianGuo
 * @Date: 2020/9/28 15:03
 * @Description:
 */
@RestController
@RequestMapping("test")
public class TestController {

    private static  final Logger log = LoggerFactory.getLogger("test");
    @Resource(name="TestServiceImpl")
    private ITestService service;

    @GetMapping("say")
    public String say(int type){
        return service.say();
    }


    @PostMapping("save")
    public String save(@RequestBody Map<String,Object> map,HttpServletRequest request, BufferedReader reader) {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            while ((line = reader.readLine()) != null){
                jb.append(line);
            }

        } catch (Exception e) {
            log.info(e.getMessage(),e);
        }
        try {
            System.out.println(jb);
        } catch (JSONException e) {
            // crash and burn
           log.info(e.getMessage(),e);
        }
        return "保存成功";
    }



}
