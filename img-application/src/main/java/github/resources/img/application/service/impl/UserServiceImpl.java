package github.resources.img.application.service.impl;

import github.resources.img.application.model.bo.UserBo;
import github.resources.img.application.model.entity.UserEntity;
import github.resources.img.application.service.UserService;
import github.resources.img.application.web.dao.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public void authUser(String userName, String password){
        final UserEntity user = userMapper.getUserByName(userName);
        if (user == null){
            throw new RuntimeException("user does not exits.");
        }
        if (!StringUtils.equals(password,user.getPassword())){
            throw new RuntimeException("password is not correct.");
        }
    }

    @Override
    public boolean addUser(UserBo userBo) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userBo.getName());
        userEntity.setPassword(userBo.getPassword());
        Date date = new Date();
        userEntity.setCreateTime(date);
        userEntity.setModifyTime(date);
        if (userBo.getCreateId()==null){
            userEntity.setCreateId(0L);
        }
        if (userMapper.getUserByName(userEntity.getName())!=null){
            throw new IllegalStateException("该用户已经存在");
        }

        return userMapper.insertUser(userEntity) > 0;
    }
}
