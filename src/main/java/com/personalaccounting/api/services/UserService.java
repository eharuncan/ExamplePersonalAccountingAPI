package com.personalaccounting.api.services;

import java.util.*;

import com.personalaccounting.api.domain.User;
import com.personalaccounting.api.dtos.UserEditDto;
import com.personalaccounting.api.dtos.UserLoginDto;
import com.personalaccounting.api.dtos.UserRegisterDto;
import com.personalaccounting.api.enums.UserTypes;
import com.personalaccounting.api.exceptions.UserNotFoundException;
import com.personalaccounting.api.mappers.UserMapper;
import com.personalaccounting.api.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final ExpenseCategoryService expenseCategoryService;

    public UserService(UserRepository userRepository, ExpenseCategoryService expenseCategoryService) {
        this.userRepository = userRepository;
        this.expenseCategoryService = expenseCategoryService;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User register(UserRegisterDto newUserDto) {
        User newUser = UserMapper.INSTANCE.userRegisterDtoToUser(newUserDto);

        if (Objects.equals(newUser.getName(), "admin")) {
            newUser.setType(UserTypes.ADMIN);
        } else {
            newUser.setType(UserTypes.CUSTOMER);
        }

        User registeredUser = userRepository.save(newUser);

        expenseCategoryService.addDefaultExpenseCategories(registeredUser.getId());

        return registeredUser;
    }

    public UserEditDto editUser(UserEditDto newUserDto, Long id) {
        log.info(newUserDto.toString());
        User newUser = UserMapper.INSTANCE.userEditDtoToUser(newUserDto);
        userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setSurname(newUser.getSurname());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
        log.info(newUser.toString());
        return UserMapper.INSTANCE.userToUserEditDto(newUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User login(UserLoginDto user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    public void logout(User user) {
        // Burada user adına tutulan oturum açma bilgileri silinir.
    }


}