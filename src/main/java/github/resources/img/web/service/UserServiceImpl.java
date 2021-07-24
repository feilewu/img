package github.resources.img.web.service;

import github.resources.img.web.dao.UserMapper;
import github.resources.img.web.dto.Response;
import github.resources.img.web.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public Response login(User user) {
        User userInDatabase = userMapper.getUserById(1L);
        System.out.println(userInDatabase);
        return null;
    }
}
