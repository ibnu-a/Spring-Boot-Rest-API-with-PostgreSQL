package com.project.api.controller;

import com.project.api.service.UserService;
import com.project.api.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerErrorException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<?> getAll() {
        return userService.userList();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            UserModel user = userService.findById(id);
            return new ResponseEntity<UserModel>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            log.error("Data not found");
            return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<?> save(@RequestBody UserModel data) {
        try {
            UserModel user = userService.save(data);
            return ResponseEntity.ok(user);
        } catch (ServerErrorException e) {
            log.error("Internal server error");
            return new ResponseEntity<UserModel>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> update(@RequestBody UserModel data, @PathVariable Long id) {
        try {
            UserModel user = userService.update(id, data);
            return ResponseEntity.ok(user);
        } catch (NoSuchElementException e) {
            log.error("Data not found");
            return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Success delete data");
        }catch (NoSuchElementException e){
            log.error("Data not found");
            return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
        }
    }
}
