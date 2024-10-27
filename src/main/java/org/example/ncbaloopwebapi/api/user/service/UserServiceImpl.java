package org.example.ncbaloopwebapi.api.user.service;

import org.example.ncbaloopwebapi.api.user.repository.UserRepository;
import org.example.ncbaloopwebapi.api.user.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    
    UserRepository userRepository;
    UserDto userDto;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
        userDto = new UserDto();
    }

    @Override
    public List<UserDto> findAllUsers() {

        var users = userRepository.findAll();

        if(!users.isEmpty()){
            List<UserDto> userDtoList = users.stream()
                    .map(user -> {
                        userDto.setFirstname(user.getFirstname());
                        userDto.setMiddlename(user.getMiddlename());
                        userDto.setLastname(user.getLastname());
                        userDto.setEmailaddress(user.getEmailaddress());
                        return userDto;
                    })
                    .collect(Collectors.toList());

            return userDtoList;
        }

        return null;
    }

    @Override
    public UserDto findByUserByUserId(UUID id){
        var user = userRepository.findUserEntitiesById(id);

        if(user != null){
            userDto.setFirstname(user.getFirstname());
            userDto.setMiddlename(user.getMiddlename());
            userDto.setLastname(user.getLastname());
            userDto.setEmailaddress(user.getEmailaddress());
            return userDto;
        }
        return null;
    }
}
