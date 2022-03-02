package com.example.location_voitures.serviceImpl;

import com.example.location_voitures.dtos.UserDto;
import com.example.location_voitures.entities.UserEntity;
import com.example.location_voitures.repositories.UserRepository;
import com.example.location_voitures.services.UserSevice;
import com.example.location_voitures.enums.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserSevice {
    ModelMapper mapper = new ModelMapper();
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
            List<UserEntity> users = userRepository.findAll();
            List<UserDto> usersDto = new ArrayList<>();
            for (UserEntity user: users){
                UserDto userDto = mapper.map(user, UserDto.class);
                usersDto.add(userDto);
            }
            return usersDto;
    }

    @Override
    public UserDto getOneUser(long id) {
        UserEntity user = userRepository.findById(id);
        UserDto userDto = mapper.map(user,UserDto.class);
        return userDto;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity user = mapper.map(userDto, UserEntity.class);
        user.setRole("user");
        UserEntity result = userRepository.save(user);
        UserDto dto = mapper.map(result, UserDto.class);
        return dto;
    }

    @Override
    public UserDto updateOneUser(long id, UserDto userDto) {
        UserEntity userEntity = userRepository.findById(id);
        if(userDto.getEmail()!=null){
            userEntity.setEmail(userDto.getEmail());
        }
        if(userDto.getFirstName()!=null){
            userEntity.setFirstName(userDto.getFirstName());
        }
        if(userDto.getLastName()!=null){
            userEntity.setLastName(userDto.getLastName());
        }
        UserEntity user1 = userRepository.save(userEntity);
        UserDto dto = mapper.map(user1, UserDto.class);
        return dto;
    }

    @Override
    public UserDto updateOneUserRole(long id, String userRole) {
        UserEntity user = userRepository.findById(id);
        if(user!=null){
            user.setRole(userRole);
        }
        UserEntity user1 = userRepository.save(user);
        UserDto userDto = mapper.map(user1, UserDto.class);
        return userDto;
    }

    @Override
    public void deleteOneUser(long id) {
                userRepository.deleteById(id);
    }
}
