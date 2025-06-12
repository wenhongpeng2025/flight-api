package com.example.flightapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flightapi.dto.UserDTO;
import com.example.flightapi.entity.User;
import com.example.flightapi.repository.UserRepository;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO findByUsername(String username) {

        User user = userRepository.findByEmail(username);
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        // userDTO.setUserId(user.getUserId());
        // userDTO.setEmail(user.getEmail());
        // userDTO.setPassword(user.getPassword());
        // userDTO.setCountry(user.getCountry());
        // userDTO.setFirstName(user.getFirstName());
        // userDTO.setLastName(user.getLastName());
        // userDTO.setPhone(user.getPhone());
        BeanUtils.copyProperties(user, userDTO);

        return userDTO;
    }

    public UserDTO registerUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        User saveUser = userRepository.save(user);
        UserDTO savedUserDTO = new UserDTO();
        BeanUtils.copyProperties(saveUser, savedUserDTO);
        return savedUserDTO;
    }

}
