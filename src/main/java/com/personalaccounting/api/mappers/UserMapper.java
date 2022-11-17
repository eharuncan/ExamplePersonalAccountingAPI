package com.personalaccounting.api.mappers;

import com.personalaccounting.api.domain.User;
import com.personalaccounting.api.dtos.UserEditDto;
import com.personalaccounting.api.dtos.UserRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    User userRegisterDtoToUser(UserRegisterDto userRegisterDto);
    UserRegisterDto userToUserRegisterDto(User user);
    User userEditDtoToUser(UserEditDto userEditDto);
    UserEditDto userToUserEditDto(User user);
}
