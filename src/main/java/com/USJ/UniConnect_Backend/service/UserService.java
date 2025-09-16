package com.USJ.UniConnect_Backend.service;

import com.USJ.UniConnect_Backend.dto.LoginDto;
import com.USJ.UniConnect_Backend.dto.UserDto;
import com.USJ.UniConnect_Backend.exception.JobPortalException;


public interface UserService {

    public UserDto registerUser(UserDto userDto) throws JobPortalException;

    public UserDto getUserByEmail(String email) throws JobPortalException;

    public UserDto loginUser(LoginDto  loginDto ) throws JobPortalException;

   /*public ResponseDto changePassword(LoginDto loginDto)throws JobPortalException;*/
}
