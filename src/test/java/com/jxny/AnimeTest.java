package com.jxny;

import com.jxny.bean.AnimeUser;
import com.jxny.mapper.AnimeUserMapper;
import com.jxny.service.AnimeUserService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnimeTest {
//    @Autowired
 static  AnimeUserService animeUserService;
    @BeforeClass
    public static void befo(){
        ApplicationContext con =new ClassPathXmlApplicationContext("applicationContext.xml");
        animeUserService= (AnimeUserService) con.getBean("AnimeUserServiceImpl");
    }
    @Test
    public void test1(){
        AnimeUser animeUser =new AnimeUser("每天的清晨","2529662127@qq.com","123456","no");
AnimeUser animeUser1= new AnimeUser("2529662127@qq.com","111111");
//        animeUserService.insert(animeUser);
//        System.out.println(animeUserService.queryone("2529662127@qq.com"));
        System.out.println(animeUserService.isExist("1111.qq.com")+"    ");
        System.out.println( animeUserService.isExist("2529662127@qq.com"));
        animeUserService.islogin(animeUser1);
        System.out.println(  animeUserService.islogin(animeUser1));
        animeUser1.setPassword("123456");
        System.out.println(  animeUserService.islogin(animeUser1));

    }
}
