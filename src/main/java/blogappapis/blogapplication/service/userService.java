package blogappapis.blogapplication.service;

import blogappapis.blogapplication.payloads.userDTO;

import java.util.List;

public interface userService {
     userDTO createUser(userDTO userDTO);
     userDTO updateUser(userDTO userDTO,Integer userId);
     userDTO getUserById(Integer userId);
     List<userDTO> getAllUsers();
     void deleteUser(Integer userId);
}
