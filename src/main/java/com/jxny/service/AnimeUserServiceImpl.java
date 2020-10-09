package com.jxny.service;

import com.jxny.bean.AnimeUser;
import com.jxny.mapper.AnimeUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "AnimeUserServiceImpl")
public class AnimeUserServiceImpl implements AnimeUserService {
    @Autowired
    AnimeUserMapper animeUserMapper;
    @Override
    public void insert(AnimeUser animeUser) {
        animeUserMapper.insert(animeUser);
    }

    @Override
    public AnimeUser queryone(String email) {
        return animeUserMapper.queryone(email);
    }

    @Override
    public boolean isExist(String email) { //验证该账号是否 注册过
        AnimeUser animeUser_query = animeUserMapper.queryone(email);
        if (animeUser_query == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean islogin(AnimeUser animeUser) {
        if(!isExist(animeUser.getEmail())){
            return false;
        }
        AnimeUser animeUser1 =animeUserMapper.queryone(animeUser.getEmail());
        if(animeUser1.getPassword().equals(animeUser.getPassword())){
            return  true;
        }
        else
        return false;
    }
}
