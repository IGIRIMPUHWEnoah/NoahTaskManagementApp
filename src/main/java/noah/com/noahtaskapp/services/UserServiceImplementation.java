package noah.com.noahtaskapp.services;

import lombok.AllArgsConstructor;
import noah.com.noahtaskapp.dtos.RegisterUserRequest;
import noah.com.noahtaskapp.models.UserModel;
import noah.com.noahtaskapp.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService, UserDetailsService {

    private UserRepository userRepository;


    UserServiceImplementation(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public void createUser(UserModel userModel) {
        userRepository.save(userModel);
    }

    @Override
    public List<UserModel>  getAllUsers() {

        return  userRepository.findAll();


    }

    @Override
    public UserModel getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(UserModel userModel,Long id) {
var user=userRepository.findById(id).orElse(null);
user.setUsername(userModel.getUsername());
user.setEmail(userModel.getEmail());
user.setPassword(user.getPassword());

userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
   var user=userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User Not Found"));

   return  new User(user.getUsername(),user.getPassword(), Collections.emptyList());
    }
}
