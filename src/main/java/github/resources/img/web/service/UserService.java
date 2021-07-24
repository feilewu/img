package github.resources.img.web.service;

import github.resources.img.web.dto.Response;
import github.resources.img.web.entity.User;

public interface UserService {

    Response login(User user);

}
