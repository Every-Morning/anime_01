package com.jxny.controller;

import com.jxny.bean.AnimeUser;
import com.jxny.service.AnimeUserService;
import com.jxny.service.MailService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.http.HttpResponse;

@Controller
@RequestMapping(value = "static/anime")
public class MyController {
    @Autowired()
    MailService mailService;
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    AnimeUserService animeUserService;

    @GetMapping(value = "/code", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String code(String email) {// @PathVariable("code")
        System.out.println("{\"msg\":\"error\"}");
        ModelAndView model = new ModelAndView();
        //  model.addObject("msg","hhh");
        String code = mailService.sendMail(email);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("code", code);
        session.setAttribute("email",email);
        if ("false".equals(code)) {
            return "{\"msg\":\"你输入的邮箱不存在\"}";
        } else return "{\"msg\":\"发送成功请注意查收\"}";
        //"{\"code\":-101, \"msg\":\"没操作权限\"}"
    }

    @PostMapping(value = "register", produces = "application/json; charset=utf-8")
    // 有效改变response.ContentType,produces = "application/json; charset=utf-8"
    @ResponseBody
    public String register(String username, String password, String email, String code) {
        String msg = "";
        AnimeUser animeUser=new AnimeUser();
        animeUser.setUsername(username);
        animeUser.setEmail(email);
        animeUser.setPassword(password);
        //    response.setContentType("application/json; charset=utf-8"); // 无效
        HttpSession session = httpServletRequest.getSession();
//            System.out.println(request.getContentType());
        String getCode = (String) session.getAttribute("code");
        String getEmail= (String) session.getAttribute("email");
        System.out.println(getCode);
        //URLEncoder.encode编码
        //URLEncoder.decode 解码

        if (code.equals(getCode)&&getEmail.equals(email)) {
            if(!animeUserService.isExist(email)){
                animeUserService.insert(animeUser);
                msg = "{\"msg\":\"注册成功\",\"flag\":\"true\"}";
            }
            else
                msg = "{\"msg\":\"该用户已存在\",\"flag\":\"true\"}";

        } else {
            msg = "{\"msg\":\"验证码错误\",\"flag\":\"false\"}";

        }
        return msg;
    }

    @PostMapping(value = "login", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String login(String email, String password) {
        String msg="";
        AnimeUser animeUser = new AnimeUser(email,password);
        if(!animeUserService.isExist(email)){
            msg = "{\"msg\":\"用户不存在，请先注册\"}";
        }
        else {
            if(animeUserService.islogin(animeUser)){
                msg = "{\"msg\":\"true\"}";
            }
            else msg = "{\"msg\":\"密码错误\"}";
        }
        return msg;
    }

}
