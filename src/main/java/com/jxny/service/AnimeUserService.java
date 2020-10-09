package com.jxny.service;

import com.jxny.bean.AnimeUser;

public interface AnimeUserService {
    public void insert(AnimeUser animeUser);
    public AnimeUser queryone(String email);
    public boolean isExist(String email);
    public boolean islogin(AnimeUser animeUser);
}
