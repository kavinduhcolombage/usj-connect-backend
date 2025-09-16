/*package com.USJ.UniConnect_Backend.util;

import com.USJ.UniConnect_Backend.dto.UserDto;
import com.USJ.UniConnect_Backend.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EntityDtoConversion {
    private final ModelMapper modelMapper;

    //user
    public UserDto toUserDto(UserEntity user) {
        return modelMapper.map(user, UserDto.class);
    }

    public UserEntity toUserEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    public List<UserDto> toUserDtoList(List<UserEntity> users) {
        return modelMapper.map(users, new TypeToken<List<UserDto>>(){}.getType());
    }




}
*/