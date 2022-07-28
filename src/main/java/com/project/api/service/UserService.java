package com.project.api.service;

import com.project.api.model.UserModel;
import com.project.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> userList(){
        return userRepository.findAll();
    }

    public UserModel save(UserModel user){

        return userRepository.save(user);
    }

    public UserModel findById(Long id){
        return userRepository.findById(id).get();
    }

    public UserModel update(Long id, UserModel data){

        UserModel user = findById(id);
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setGender(data.getGender());
        user.setAge(data.getAge());

        return userRepository.save(user);
    }

    public boolean deleteUser(Long id){
        userRepository.delete(findById(id));
        return true;
    }
}
