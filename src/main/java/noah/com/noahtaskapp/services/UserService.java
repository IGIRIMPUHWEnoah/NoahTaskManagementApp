package noah.com.noahtaskapp.services;

import noah.com.noahtaskapp.dtos.RegisterUserRequest;
import noah.com.noahtaskapp.models.UserModel;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    public void   createUser(UserModel userModel);
    public List<UserModel> getAllUsers();
    public  UserModel getUserById(Long id);
    public  void deleteUser(Long id);
    public  void  updateUser(UserModel userModel,Long id);


}
