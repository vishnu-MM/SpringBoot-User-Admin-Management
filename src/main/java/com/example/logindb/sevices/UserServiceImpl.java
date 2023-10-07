package com.example.logindb.sevices;

import com.example.logindb.dto.UserDTO;
import com.example.logindb.entity.User;
import com.example.logindb.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder                           ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User existing = userRepository.findById( userDTO.getId() );
        existing.setName(userDTO.getName());
        existing.setUsername(userDTO.getUsername());
        userRepository.save(existing);
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String email) {
        return userRepository.findByUsername(email);
    }

    @Override
    public UserDTO findById(int id) {
        User existingUser =  userRepository.findById(id);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(existingUser.getId());
        userDTO.setUsername(existingUser.getUsername());
        userDTO.setName(existingUser.getName());
        userDTO.setPassword(existingUser.getPassword());
        return userDTO;
    }

    @Override
    public List<UserDTO> findAllByRole(String role) {
        List<User> users = userRepository.findAllByRole(role);
        return  users.stream()
//                .map( user -> convertUserToDto(user))
                .map(this::convertUserToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<UserDTO> searchUser(String role, String name) {
        List<User> users = userRepository.findAllByRoleAndNameStartingWithIgnoreCase(role,name);
        return users.stream()
//                .map((user) -> convertUserToDto(user))
                .map(this::convertUserToDto)
                .collect(Collectors.toList());
    }


//  Helping method for findAllByRole() and searchUser()
    private UserDTO convertUserToDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole());
        return userDto;
    }

    @Override
    public boolean isExists(String email){
        return userRepository.existsByUsername(email);
    }


}
