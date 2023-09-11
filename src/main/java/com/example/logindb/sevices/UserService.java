package com.example.logindb.sevices;

import com.example.logindb.dto.UserDTO;
import com.example.logindb.entity.User;
import java.util.List;

public interface UserService {

    void saveUser(UserDTO userDTO);
    User updateUser(UserDTO userDTO);
    User deleteUserById(int id);

    User findByUsername(String email);
    List<UserDTO> findAllByRole(String role);
    List<UserDTO> searchUser(String role,String name);

}
