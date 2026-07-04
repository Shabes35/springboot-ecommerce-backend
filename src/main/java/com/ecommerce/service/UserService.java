package com.ecommerce.service;

import com.ecommerce.dto.UserDTO;
import com.ecommerce.entity.User;
import com.ecommerce.exception.EmailAlreadyExistsException;
import com.ecommerce.exception.InvalidCredentialException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public User saveUser(User user){

        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new EmailAlreadyExistsException( "Email already exists!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        return userRepository.save(user);

    }
    public List<UserDTO> getAllUsers(){

        List<User>  users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        for( User user : users ){

            UserDTO userDTO = new UserDTO();

            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setRole(user.getRole());

            userDTOs.add(userDTO);
        }

        return userDTOs;
    }

    public String login(String email , String password) {



        User user =   userRepository.findByEmail(email);



        if(user == null){
            throw  new ResourceNotFoundException(
                    "User not found"
            );
        }

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new InvalidCredentialException(
                    "Invalid Password"
            );
        }

        String token = jwtService.generateToken(user.getEmail());
        return token;


    }
}
