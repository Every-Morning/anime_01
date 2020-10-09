package com.jxny.service;

import com.jxny.util.MailUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class MailService {

    public String sendMail(String email){
        return new MailUtil().sendMail(email);
    }
}
