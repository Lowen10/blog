package com.lowen.blog.service;

import com.lowen.blog.mapper.BlAdminMapper;
import com.lowen.blog.mapper.BlProfileMapper;
import com.lowen.blog.model.BlAdmin;
import com.lowen.blog.model.BlProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private BlProfileMapper mBlProfileMapper;
    @Autowired
    private BlAdminMapper mBlAdminMapper;

    public BlProfile selectProfile() {
        return mBlProfileMapper.selectProfile();
    }

    public int insertOrUpdateProfile(BlProfile profile) {
        return mBlProfileMapper.insertOrUpdate(profile);
    }

    public BlAdmin login(String username, String password) {
        return mBlAdminMapper.login(username, password);
    }

    public int updatePasswordByPrimaryKey(Integer userId, String newPass, String oldPass) {
        return mBlAdminMapper.updatePasswordByPrimaryKey(userId, newPass, oldPass);
    }
}
