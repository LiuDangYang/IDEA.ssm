package com.baidu.controller;

import com.baidu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by 67545 on 2017/11/14.
 */
@Controller
@RequestMapping("/user")
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})*/
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private HttpServletRequest request;
    @RequestMapping("/showUser.do")
    public String selectUser(@RequestParam(value = "myFile", required = false) MultipartFile myFile,@RequestParam(value = "name")String name, HttpServletResponse response) {
        // 判断文件是否为空
        // 进行测试修改提交
        String fileName="";
        if (!myFile.isEmpty()) {
            try {
                // 文件保存路径  fileName
                fileName=myFile.getOriginalFilename();
                String filePatha = request.getContextPath();
                System.out.println("filePath::"+filePatha);
                // 转存文件
                String filePath = request.getServletContext().getRealPath("/") + myFile.getOriginalFilename();
                myFile.transferTo(new File(filePath));
                System.out.println("filePath::"+filePath);
                System.out.println("name::"+name);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ModelAndView modelAndView = new ModelAndView();


        HashMap<String, String> map = new HashMap<String, String>();
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Set<String> strings = map.keySet();

        modelAndView.addObject("fileName",fileName);
        return "img";
    }}

    /*@Test
    public void testSelectUser() throws Exception {
        String id = "1";
        User user = this.userService.selectUser(id);
        System.out.println(user.getUsername());
    }*/
