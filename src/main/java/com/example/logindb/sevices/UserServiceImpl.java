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
        user.setPassword(userDTO.getPassword()); // add password encoder
        userRepository.save(user);
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        int id=userDTO.getId();
        User existing = userRepository.findById(id);
        existing.setName(userDTO.getName());
        existing.setUsername(userDTO.getUsername());
        return userRepository.save(existing);
    }

    @Override
    public User deleteUserById(int id) {
        return userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String email) {
        return userRepository.findByUsername(email);
    }

    @Override
    public List<UserDTO> findAllByRole(String role) {
        List<User> users = userRepository.findAllByRole(role);
        return  users.stream()
                .map( user -> convertUserToDto(user))
                .collect(Collectors.toList());
    }


    @Override
    public List<UserDTO> searchUser(String role, String name) {
        List<User> users = userRepository.findAllByRoleAndNameStartingWithIgnoreCase(role,name);
        return users.stream().map((user) -> convertUserToDto(user))
                .collect(Collectors.toList());
    }


//  Helping method for findAllByRole() and searchUser()
    private UserDTO convertUserToDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        return userDto;
    }
}
