package laboratoria.fleet.fleetmanagementapi.services.impl;

import laboratoria.fleet.fleetmanagementapi.dto.UserDto;
import laboratoria.fleet.fleetmanagementapi.entities.User;
import laboratoria.fleet.fleetmanagementapi.exception.AlreadyExistException;

import laboratoria.fleet.fleetmanagementapi.exception.ResourceNotFoundException;
import laboratoria.fleet.fleetmanagementapi.exception.ValidationException;
import laboratoria.fleet.fleetmanagementapi.mappers.UserMapper;
import laboratoria.fleet.fleetmanagementapi.repositories.UserRepository;
import laboratoria.fleet.fleetmanagementapi.services.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto){

        User user = UserMapper.mapToUser(userDto);
        if(user.getEmail() == null || user.getPassword() == null){
            throw new ValidationException("Email or password are required");
        }
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new AlreadyExistException("Email already exists");
        }
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUser(int pageNumber, int pageSize) {

        if(pageNumber < 0 || pageSize < 0 ){
            throw new ValidationException("Invalid page number");
        }

        Page<User> userList = userRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return userList.stream()
                .map((user) -> UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(String idOrEmail, UserDto updatedUsers){
        User user;

        if(idOrEmail.contains("@")){

            user = userRepository.findByEmail(idOrEmail).orElseThrow(
                    ()-> new ResourceNotFoundException("User is not exist with given id: " + idOrEmail)
            );
        }else{
            Long id = Long.parseLong(idOrEmail);
            user = userRepository.findById(id).orElseThrow(
                    ()-> new ResourceNotFoundException("User is not exist with given id: " + id)
            );
        }

        if (userRepository.findByEmail(updatedUsers.getEmail()).isPresent()) {
         throw new ValidationException("Email already exists");
        }

        if(updatedUsers.getPassword().length() < 17){
            throw new ValidationException("Password must be at least 17 characters");
        }

        user.setName(updatedUsers.getName());
        user.setEmail(updatedUsers.getEmail());
        user.setPassword(updatedUsers.getPassword());

        User updateUserObj = userRepository.save(user);

        return UserMapper.mapToUserDto(updateUserObj);
    }


    @Override
    public UserDto partialUpdateUser(String idOrEmail, UserDto updatedUsers) {

        User user;
        if(idOrEmail.contains("@")){
            user = userRepository.findByEmail(idOrEmail).orElseThrow(
                    ()-> new ResourceNotFoundException("User is not exist with given id: " + idOrEmail)
            );
        }else{
            Long id = Long.parseLong(idOrEmail);
            user = userRepository.findById(id).orElseThrow(
                    ()-> new ResourceNotFoundException("User is not exist with given id: " + id)
            );
        }

        if (userRepository.findByEmail(updatedUsers.getEmail()).isPresent()) {
            throw new ValidationException("Email already exists");
        }

        if(updatedUsers.getPassword() != null && updatedUsers.getPassword().length() < 17){
            throw new ValidationException("Password must be at least 17 characters");
        }

        if(updatedUsers.getName() !=null) user.setName(updatedUsers.getName());
        if(updatedUsers.getEmail() !=null) user.setEmail(updatedUsers.getEmail());
        if(updatedUsers.getPassword() !=null) user.setPassword(updatedUsers.getPassword());

        User updateUserObj = userRepository.save(user);

        return UserMapper.mapToUserDto(updateUserObj);
    }

    @Override
    public UserDto deleteUser(String idOrEmail) {

        User user;
        if(idOrEmail.contains("@")){
            user = userRepository.findByEmail(idOrEmail).orElseThrow(
                    ()-> new ResourceNotFoundException("User is not exist with given id: " + idOrEmail)
            );
        }else{
            Long id = Long.parseLong(idOrEmail);
            user = userRepository.findById(id).orElseThrow(
                    ()-> new ResourceNotFoundException("User is not exist with given id: " + id)
            );
        }
        userRepository.deleteById(user.getId());
        return UserMapper.mapToUserDto(user);
    }

}
