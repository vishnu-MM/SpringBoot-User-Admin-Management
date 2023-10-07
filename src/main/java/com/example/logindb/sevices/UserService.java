package com.example.logindb.sevices;

import com.example.logindb.dto.UserDTO;
import com.example.logindb.entity.User;
import java.util.List;

public interface UserService {

    void saveUser(UserDTO userDTO);
    void deleteUserById(int id);
    void updateUser(UserDTO userDTO);

    UserDTO findById(int id);
    User findByUsername(String email);
    List<UserDTO> findAllByRole(String role);
    List<UserDTO> searchUser(String role,String name);

    boolean isExists(String email);
}
