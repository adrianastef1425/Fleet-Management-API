package laboratoria.fleet.fleetmanagementapi.services.impl;

import laboratoria.fleet.fleetmanagementapi.dto.UserDto;
import laboratoria.fleet.fleetmanagementapi.entities.User;
import laboratoria.fleet.fleetmanagementapi.exception.ResourceNotFoundException;
import laboratoria.fleet.fleetmanagementapi.exception.ValidationException;
import laboratoria.fleet.fleetmanagementapi.repositories.TrajectoriesRepository;
import laboratoria.fleet.fleetmanagementapi.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    // Create Test
    @Test
    void createUserOk() {
        User user = new User();
        user.setId(1);
        user.setName("Alexandra Savior");
        user.setEmail("alexandra@gmail.com");
        user.setPassword("alexandra123");
        when(userRepository.save(Mockito.any())).thenReturn(user);
        when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.empty());

        UserDto userDto = userService.createUser(new UserDto(   1,
                                                                "Alexandra Savior",
                                                                "alexandra@gmail.com",
                                                                "alexandra123"));

        assertEquals(1, userDto.getId());
        assertEquals("Alexandra Savior", userDto.getName());
        assertEquals("alexandra@gmail.com", userDto.getEmail());
        assertEquals("alexandra123", userDto.getPassword());

    }

    @Test
    void createUserInvalidEmail() {
        UserDto userDto =  new UserDto(   1,
                "Alexandra Savior",
                null,
                "alexandra123");
        assertThrows(ValidationException.class, () -> userService.createUser(userDto));
    }

    @Test
    void createUserInvalidPassword() {
        UserDto userDto =  new UserDto(   1,
                "Alexandra Savior",
                "alexandra@gmail.com",
                null);
        assertThrows(ValidationException.class, () -> userService.createUser(userDto));
    }

    // Get Test
    @Test
    void getAllUserOk() {
        Pageable pageable = Pageable.ofSize(1).withPage(1);
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setName("Alexandra Savior");
        user.setEmail("alexandra123@gmail.com");
        user.setPassword("alexandra123");
        userList.add(user);

        Page<User> userPage = new PageImpl<>(userList);
        //Mockito.when(userRepository.findAll(Mockito.any(Pageable.class))).thenReturn(userPage);
        when(userRepository.findAll(pageable)).thenReturn(userPage);

        List<UserDto> pageOut = userService.getAllUser(1, 1);
        UserDto userDto = pageOut.get(0);
        assertEquals(1, pageOut.size());
        assertEquals("Alexandra Savior", userDto.getName());
        assertEquals("alexandra123@gmail.com", userDto.getEmail());
        assertEquals("alexandra123", userDto.getPassword());

    }

    @Test
    void getAllUserInvalidPageNumber() {
        assertThrows(ValidationException.class, () -> userService.getAllUser(-1, 1));
    }

    @Test
    void getAllUserInvalidPageSize() {
        assertThrows(ValidationException.class, () -> userService.getAllUser(0, -1));
    }

    // Put Test
    @Test
    void updateUserEmailOk() {
        User user = new User();
        user.setId(1);
        user.setName("Alexandra Savior");
        user.setEmail("alexandra123@gmail.com");
        user.setPassword("alexandra123");

        User userModify = new User();
        userModify.setId(1);
        userModify.setName("Alexandra Savior");
        userModify.setEmail("alexa@gmail.com");
        userModify.setPassword("1234567");

        when(userRepository.findByEmail("alexandra123@gmail.com")).thenReturn(Optional.of(user));
        when(userRepository.findByEmail("alexa@gmail.com")).thenReturn(Optional.empty());
        when(userRepository.save(Mockito.any())).thenReturn(userModify);
        //when(userRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        UserDto updatedUser = new UserDto(1,
                "Alexandra Savior",
                "alexa@gmail.com",
                "1234567");

        UserDto userDtoEmail = userService.updateUser("alexandra123@gmail.com", updatedUser);
        assertEquals(1, userDtoEmail.getId());
        assertEquals("Alexandra Savior", userDtoEmail.getName());
        assertEquals("alexa@gmail.com", userDtoEmail.getEmail());
        assertEquals("1234567", userDtoEmail.getPassword());
    }

    @Test
    void updateUserIdOk() {

        User user = new User();
        user.setId(1);
        user.setName("Alexandra Savior");
        user.setEmail("alexandra123@gmail.com");
        user.setPassword("alexandra123");

        User userModify = new User();
        userModify.setId(1);
        userModify.setName("Alexandra Savior");
        userModify.setEmail("alexa@gmail.com");
        userModify.setPassword("1234567");

        when(userRepository.save(Mockito.any())).thenReturn(userModify);
        //when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.empty());
        when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));

        UserDto updatedUser = new UserDto(1,
                                          "Alexandra Savior",
                                          "alexa@gmail.com",
                                          "1234567");

        UserDto userDtoId = userService.updateUser("1", updatedUser);
        assertEquals(1, userDtoId.getId());
        assertEquals("Alexandra Savior", userDtoId.getName());
        assertEquals("alexa@gmail.com", userDtoId.getEmail());
        assertEquals("1234567", userDtoId.getPassword());

    }

    @Test
    void updateUserResourceNotFoundEmail() {
        UserDto updatedUser = new UserDto(1,
                "Alexandra Savior",
                "alexa@gmail.com",
                "1234567");

        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser("alexa123@gmail.com", updatedUser));
    }

    @Test
    void updateUserResourceNotFoundId() {
        UserDto updatedUser = new UserDto(1,
                "Alexandra Savior",
                "alexa@gmail.com",
                "1234567");

        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser("1", updatedUser));
    }

    @Test
    void updateUserInvalidEmail(){
        User user = new User();
        user.setId(1);
        user.setName("Alexandra Savior");
        user.setEmail("alexa@gmail.com");
        user.setPassword("alexandra123");

        User userModify = new User();
        userModify.setId(1);
        userModify.setName("Alexandra Savior");
        userModify.setEmail("alexa@gmail.com");
        userModify.setPassword("1234567");

        when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
        when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(user));


        UserDto updatedUser = new UserDto(1,
                "Alexandra Savior",
                "alexa@gmail.com",
                "1234567");

        assertThrows(ValidationException.class, () -> userService.updateUser("1", updatedUser));
    }

    @Test
    void updateUserInvalidPassword(){}

    // Patch Test
    @Test
    void partialUpdateUser() {
    }

    //Delete Test
    @Test
    void deleteUser() {
    }
}