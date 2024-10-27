package org.example.ncbaloopwebapi.api.user.controller;


import org.example.ncbaloopwebapi.api.user.service.UserService;
import org.example.ncbaloopwebapi.api.utils.ResponseCustomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("api/user")
public class UserController {
    UserService userService;
    ResponseCustomEntity responseCustomEntity;


    @Autowired
    public UserController(UserService userService, ResponseCustomEntity responseCustomEntity) {
        this.userService = userService;
        this.responseCustomEntity = responseCustomEntity;
    }

    /**
     * Find all Users
     * @return ResponseCustomEntity
     */
    @GetMapping("all")
    public ResponseEntity<ResponseCustomEntity> findAllUsers(){
        try {
            var users = userService.findAllUsers();

            if(users == null){

                responseCustomEntity.setStatus(404);
                responseCustomEntity.setMessage("Users do not exist.");
                responseCustomEntity.setError(true);
                responseCustomEntity.setData(null);
                responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

                return ResponseEntity.ok(responseCustomEntity);
            }

            responseCustomEntity.setStatus(200);
            responseCustomEntity.setMessage("Successfully retrieved all accounts.");
            responseCustomEntity.setError(false);
            responseCustomEntity.setData(users);
            responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));
            return ResponseEntity.ok(responseCustomEntity);

        }catch (Exception exception){
            responseCustomEntity.setStatus(500);
            responseCustomEntity.setMessage("An error occurred, please try again later.");
            responseCustomEntity.setError(true);
            responseCustomEntity.setData(exception);
            responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(responseCustomEntity);
        }
    }

}
