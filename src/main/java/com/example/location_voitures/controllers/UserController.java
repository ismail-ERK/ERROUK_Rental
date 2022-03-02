package com.example.location_voitures.controllers;

import com.example.location_voitures.dtos.UserDto;
import com.example.location_voitures.requests.user.UserRequest;
import com.example.location_voitures.requests.user.UserUpdate;
import com.example.location_voitures.responses.UserResponse;
import com.example.location_voitures.services.UserSevice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    ModelMapper mapper = new ModelMapper();
    @Autowired
    UserSevice userSevice;
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserDto> userDtos = userSevice.getAllUsers();
        List<UserResponse> userResponses = new ArrayList<>();
        for (UserDto dto: userDtos){
            UserResponse userResponse = mapper.map(dto, UserResponse.class);
            userResponses.add(userResponse);
        }
        return new ResponseEntity<List<UserResponse>>(userResponses, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getOneUser(@PathVariable("id") long id){
        UserDto dto = userSevice.getOneUser(id);
        UserResponse userResponse = mapper.map(dto, UserResponse.class);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> setOneUser(@RequestBody UserRequest userRequest){
        if(userRequest.getPassword().equals(userRequest.getConfirmPassword())){
            UserDto dto = mapper.map(userRequest, UserDto.class);
            UserDto userDto = userSevice.createUser(dto);
            UserResponse userResponse = mapper.map(userDto, UserResponse.class);
            return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);

        }
    else {
            return new ResponseEntity<String>( "Confirm incorrect", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateOneUser(@RequestBody UserUpdate userUpdate, @PathVariable("id") long id){

        UserDto dto = mapper.map(userUpdate, UserDto.class);
        UserDto userDto = userSevice.updateOneUser(id, dto);
        UserResponse userResponse = mapper.map(userDto,UserResponse.class);

        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOneUser(@PathVariable("id") long id){
        userSevice.deleteOneUser(id);
        return new ResponseEntity<String>("Delete user "+id, HttpStatus.GONE);
    }
}
