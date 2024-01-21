package com.project.todolist.services;

import com.project.todolist.dtos.UpdateUserDTO;
import com.project.todolist.entities.User;
import com.project.todolist.exceptions.ConflictException;
import com.project.todolist.exceptions.NotFoundException;
import com.project.todolist.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private  final UserRepository userRepository;
    public User save(User user){
        Optional<User> existUser = userRepository.findByEmail(user.getEmail());
        if (existUser.isPresent()){
          throw new ConflictException("User already exists");
        }
        userRepository.save(user);
        return user;
    }

    public User updateUser(Long id, UpdateUserDTO data){
        try{
            User user = userRepository.getReferenceById(id);
            updateData(user, data);
            return userRepository.save(user);
        }catch (EntityNotFoundException e){
            throw new NotFoundException("User with id " + id + " not found");
        }
    }

    public void updateData(User user, UpdateUserDTO data){
        user.setName(data.getName());
    }

    public void delete(Long id) {
        try{
            userRepository.deleteById(id);
        }catch (EntityNotFoundException e){
            throw  new NotFoundException("User with " + id + "not found");
        }
    }
}
