package blogappapis.blogapplication.service.serviceImpl;

import blogappapis.blogapplication.exception.ResourceNotFoundException;
import blogappapis.blogapplication.payloads.userDTO;
import blogappapis.blogapplication.repository.userRepo;
import blogappapis.blogapplication.service.userService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import blogappapis.blogapplication.entity.user;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private userRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public userDTO createUser(userDTO userDTO) {
        user user=this.dtoToUser(userDTO);
        user savedUser=this.userRepo.save(user);

        return this.userToUserDTO(savedUser);
    }

    @Override
    public userDTO updateUser(userDTO userDTO, Integer userId) {
        user user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());

        user saveduser=this.userRepo.save(user);
        userDTO userDTO1= this.userToUserDTO(saveduser);
        return userDTO1;
    }

    @Override
    public userDTO getUserById(Integer userId) {
        user user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        userDTO userDTO=this.userToUserDTO(user);
        return userDTO;
    }

    @Override
    public List<userDTO> getAllUsers() {
        List<user> userList=this.userRepo.findAll();
        List<userDTO> userDTOList=userList.stream().map(user -> this.userToUserDTO(user)).collect(Collectors.toList());
        return userDTOList;
    }

    @Override
    public void deleteUser(Integer userId) {
      user user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
      this.userRepo.delete(user);
    }

    private user dtoToUser(userDTO userDTO){
      user user1=this.modelMapper.map(userDTO,user.class);
      return user1;
    }

    public userDTO userToUserDTO(user user){
        userDTO userDTO1=this.modelMapper.map(user,userDTO.class);

        return userDTO1;
    }
}
