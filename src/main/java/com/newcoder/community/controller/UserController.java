package com.newcoder.community.controller;

import com.newcoder.community.entity.User;
import com.newcoder.community.service.UserService;
import com.newcoder.community.util.CommunityUtil;
import com.newcoder.community.util.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Wenyu Wang
 * @project community
 * @date 2022-07
 * @Description
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${community.path.upload}")
    private String uploadPath;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/setting", method = RequestMethod.GET)
    public String getSettingPage() {
        return "/site/setting";
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String uploadHeader(MultipartFile headerImage, Model model) {
        if (headerImage == null) {
            model.addAttribute("error", "请选择一个文件");
            return "/site/setting";
        }
        String fileName = headerImage.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (StringUtils.isBlank(suffix)) {
            model.addAttribute("error", "文件格式错误");
            return "/site/setting";
        }

        // generate random filename
        fileName = CommunityUtil.generateUUID()+"." + suffix;
        File dest = new File(uploadPath + "/" + fileName);
        try {
            // store header file
            headerImage.transferTo(dest);
        } catch (IOException e) {
            logger.error("上传文件失败 upload fail" + e.getMessage());
            throw new RuntimeException("上传文件失败 upload fail" + e.getMessage());
        }

        // update user header URL (Web)
        // http://localhost:8080/community/user/header/xxx.png
        User user = hostHolder.getUser();
        String headerUrl = domain + contextPath + "/user/header/" + fileName;
        userService.updateHeader(user.getId(), headerUrl);

        return "redirect:/index";
    }

    @RequestMapping(path = "/header/{fileName}", method = RequestMethod.GET)
    public void getHeader(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        fileName = uploadPath + "/" + fileName;
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        response.setContentType("image/" + suffix);
        try (
                FileInputStream fileInputStream = new FileInputStream(fileName);
                OutputStream outputStream = response.getOutputStream();) {


            byte[] buffer = new byte[1024];
            int b = 0;
            while ((b = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, b);
            }
        } catch (IOException e) {
            logger.error("读取头像失败" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(path = "/changepassword",method = RequestMethod.POST)
    public String changePassword(Model model, String original, String newPassword,String comfirmPassword){
        if(StringUtils.isBlank(original) ){
            model.addAttribute("original","请输入密码！");
            return "/site/setting";
        }
        if(StringUtils.isBlank(newPassword) ){
            model.addAttribute("newPassword","请输入新密码！");
            return "/site/setting";
        }
        if(StringUtils.isBlank(comfirmPassword) ){
            model.addAttribute("comfirm","请再次输入密码！");
            return "/site/setting";
        }
        User user = hostHolder.getUser();
        String encrypt = CommunityUtil.md5(original+user.getSalt());
        if(!encrypt.equals(user.getPassword()) ){
            model.addAttribute("original","原密码不正确");
            return "/site/setting";
        }
        if(!newPassword.equals(comfirmPassword)){
            model.addAttribute("comfirm","两次密码不匹配");
            return "/site/setting";
        }
        // change password
        //encrypt = CommunityUtil.md5(newPassword);
        newPassword = CommunityUtil.md5(newPassword + user.getSalt());
        userService.updatePassword(user.getId(),newPassword);

        return "redirect:/logout";

    }
}
