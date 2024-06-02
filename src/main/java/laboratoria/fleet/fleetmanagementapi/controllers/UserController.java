package laboratoria.fleet.fleetmanagementapi.controllers;

import laboratoria.fleet.fleetmanagementapi.dto.UserDto;
import laboratoria.fleet.fleetmanagementapi.exception.AlreadyExistException;

import laboratoria.fleet.fleetmanagementapi.exception.ResourceNotFoundException;
import laboratoria.fleet.fleetmanagementapi.exception.ValidationException;
import laboratoria.fleet.fleetmanagementapi.services.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Build Add User REST API
    @PostMapping
    public ResponseEntity<Object> createUserController(@RequestBody UserDto userDto) {
        UserDto savedUser;
        try {
            savedUser = userService.createUser(userDto);
        }catch (ValidationException ex) {
            HashMap<String, String> response = new HashMap<>();
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (AlreadyExistException ex) {
            HashMap<String, String> response = new HashMap<>();
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } catch (Exception ex) {
            HashMap<String, String> response = new HashMap<>();
            response.put("error", "Unexpected error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Build Get All User REST API
    @GetMapping
    public ResponseEntity<Object> getAllUserController(@RequestParam(defaultValue = "0") Integer page,
                                                       @RequestParam(defaultValue = "10") Integer limit) {

        List<UserDto> userList;
        try {
            userList = userService.getAllUser(page, limit);
        }catch (ValidationException ex){
            HashMap<String, String> response = new HashMap<>();
            response.put("error", "Invalid page or limit number");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            HashMap<String, String> response = new HashMap<>();
            response.put("error", "Unexpected error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(userList);
    }

    // Build Update PUT User REST API
    @PutMapping("{idOrEmail}")
    public ResponseEntity<Object> updateUserByIdController(@PathVariable("idOrEmail") String userIdOrEmail,
                                                             @RequestBody UserDto updatedUser) {
        UserDto userDto;

        try {
            userDto = userService.updateUser(userIdOrEmail, updatedUser);
        } catch (ResourceNotFoundException ex){
            HashMap<String, String> response = new HashMap<>();
            response.put("error", "User not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (ValidationException ex){
            HashMap<String, String> response = new HashMap<>();
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }  catch (Exception ex) {
            HashMap<String, String> response = new HashMap<>();
            response.put("error", "Unexpected error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(userDto);
    }

    // Build Update PATCH User REST API
    @PatchMapping("{idOrEmail}")
    public ResponseEntity<Object> partialUpdateUserController(@PathVariable("idOrEmail") String userIdOrEmail,
                                                                @RequestBody UserDto updatedUser) {
        UserDto userDto;
        try {
            userDto = userService.partialUpdateUser(userIdOrEmail, updatedUser);
        }catch (ResourceNotFoundException ex){
            HashMap<String, String> response = new HashMap<>();
            response.put("error", "User not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (ValidationException ex){
            HashMap<String, String> response = new HashMap<>();
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            HashMap<String, String> response = new HashMap<>();
            response.put("error", "Unexpected error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(userDto);
    }

    // Build Delete User REST API
    @DeleteMapping("{idOrEmail}")
    public ResponseEntity<Object> deleteUserController(@PathVariable("idOrEmail") String userIdOrEmail) {
        UserDto userDto;
        try {
            userDto = userService.deleteUser(userIdOrEmail);
        }catch (ResourceNotFoundException ex){
            HashMap<String, String> response = new HashMap<>();
            response.put("error", "User not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            HashMap<String, String> response = new HashMap<>();
            response.put("error", "Unexpected error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(userDto);
    }
}
