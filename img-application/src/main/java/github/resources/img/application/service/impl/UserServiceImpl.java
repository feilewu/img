package github.resources.img.application.service.impl;

import github.resources.img.application.dao.UserMapper;
import github.resources.img.application.entity.UserEntity;
import github.resources.img.application.service.UserService;
import github.resources.img.auth.AuthException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public void authUser(String userName, String password) throws AuthException {
        final UserEntity user = userMapper.getUserByName(userName);
        if (user == null){
            throw new AuthException("user does not exits.");
        }
        if (!StringUtils.equals(password,user.getPassword())){
            throw new AuthException("password is not correct.");
        }
    }
}
