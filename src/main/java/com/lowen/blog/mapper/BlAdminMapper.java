package com.lowen.blog.mapper;

import com.lowen.blog.model.BlAdmin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlAdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlAdmin record);

    BlAdmin selectByPrimaryKey(Integer id);

    List<BlAdmin> selectAll();

    int updateByPrimaryKey(BlAdmin record);

    BlAdmin login(@Param("account") String account, @Param("password") String password);

    int updatePasswordByPrimaryKey(@Param("id") Integer userId, @Param("newPass") String newPass, @Param("oldPass") String oldPass);
}