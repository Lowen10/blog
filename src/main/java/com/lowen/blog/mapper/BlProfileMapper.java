package com.lowen.blog.mapper;

import com.lowen.blog.model.BlProfile;

public interface BlProfileMapper {
    int deleteByPrimaryKey(Integer id);

    int insertOrUpdate(BlProfile record);

    BlProfile selectByPrimaryKey(Integer id);

    BlProfile selectProfile();

    int updateByPrimaryKey(BlProfile record);
}