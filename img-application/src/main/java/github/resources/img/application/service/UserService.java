package github.resources.img.application.service;

import github.resources.img.core.model.bo.UserBo;

public interface UserService {

    void authUser(String userName, String password) ;


    boolean addUser(UserBo userBo);

}
