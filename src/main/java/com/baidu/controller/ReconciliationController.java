package com.baidu.controller;

import com.baidu.model.Reconciliation;
import com.baidu.service.IReconciliationService;
import com.baidu.service.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * Created by 67545 on 2017/11/14.
 */
@Controller
public class ReconciliationController {

    @Autowired
    private IReconciliationService productService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ReadExcel readExcel;

    @RequestMapping("/showUser.do")
    public String insetReconciliation(@RequestParam(value = "myFile", required = false) MultipartFile myFile) {
        // 判断文件是否为空
        String fileName="";
        if (!myFile.isEmpty()) {
            try {
                // 文件保存路径  fileName
                fileName=myFile.getOriginalFilename();
                String filePath = request.getServletContext().getRealPath("/") + myFile.getOriginalFilename();
                // 转存文件
                myFile.transferTo(new File(filePath));
                System.out.println("filePath::"+filePath);
                List<Reconciliation> reconciliations = readExcel.readExcel(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    /*@Test
    public void testSelectUser() throws Exception {
        String id = "1";
        User user = this.userService.selectUser(id);
        System.out.println(user.getUsername());
    }*/
}