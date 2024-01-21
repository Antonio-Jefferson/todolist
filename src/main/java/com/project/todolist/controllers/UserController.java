package com.project.todolist.controllers;

import com.project.todolist.dtos.UpdateUserDTO;
import com.project.todolist.dtos.UserDTO;
import com.project.todolist.entities.User;
import com.project.todolist.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

   @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserDTO userDTO){
       var user = User.toUserDTO(userDTO);
       return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
   }

   @PutMapping(value = "/{id}")
    public ResponseEntity<User> UpdateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserDTO updateUser){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, updateUser));
   }

   @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id){
       System.out.println(id);
       userService.delete(id);
       ResponseEntity.noContent().build();
   }

}
