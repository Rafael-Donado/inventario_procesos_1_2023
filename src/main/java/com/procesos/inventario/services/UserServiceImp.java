package com.procesos.inventario.services;

import com.procesos.inventario.models.User;
import com.procesos.inventario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository UserRepository;

    public User getUser(Long id){return UserRepository.findById(id).get();}

    @Override
    public Boolean createUser(User user) {
        try {
            UserRepository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<User> allUsers() {
        return UserRepository.findAll();
    }

    @Override
    public Boolean updateUser(Long id, User user) {
        try{
        User userBD = UserRepository.findById(id).get();

        userBD.setFirstName(user.getFirstName());
        userBD.setLastName(user.getLastName());
        userBD.setBirthday(user.getBirthday());
        userBD.setAddress(user.getAddress());
        UserRepository.save(userBD);
        return true;
        } catch (Exception e){
            return false;
        }
    }
}
