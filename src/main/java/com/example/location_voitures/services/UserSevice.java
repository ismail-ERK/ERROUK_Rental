package com.example.location_voitures.services;

import com.example.location_voitures.dtos.UserDto;
import com.example.location_voitures.enums.UserRole;

import java.util.List;

public interface UserSevice {
    public List<UserDto> getAllUsers();
    public UserDto getOneUser(long id);
    public UserDto createUser(UserDto userDto);
    public UserDto updateOneUser(long id,UserDto userDto);
    public UserDto updateOneUserRole(long id, String userRole);
    public void deleteOneUser(long id);
}
