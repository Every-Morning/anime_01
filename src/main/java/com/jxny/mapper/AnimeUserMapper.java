package com.jxny.mapper;

import com.jxny.bean.AnimeUser;

public interface AnimeUserMapper {
    public void insert(AnimeUser animeUser);
    public AnimeUser queryone(String email);

}
