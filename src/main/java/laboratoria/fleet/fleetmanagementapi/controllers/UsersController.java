package laboratoria.fleet.fleetmanagementapi.controllers;

import laboratoria.fleet.fleetmanagementapi.dto.UsersDto;
import laboratoria.fleet.fleetmanagementapi.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersService usersService;


    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // Build Add Users REST API
    @PostMapping
    public ResponseEntity<UsersDto> createUsersController(@RequestBody UsersDto usersDto) {
        UsersDto savedUsers = usersService.createUsers(usersDto);
        return new ResponseEntity<>(savedUsers, HttpStatus.CREATED);
    }

    // Build Get All Users REST API
    @GetMapping
    public ResponseEntity<List<UsersDto>> getAllUsersController() {
        List<UsersDto> usersList = usersService.getAllUsers();
        return ResponseEntity.ok(usersList);
    }

    // Build Update PUT Users REST API
    @PutMapping("{idOrEmail}")
    public ResponseEntity<UsersDto> updateUsersByIdController(@PathVariable("idOrEmail") String userIdOrEmail,
                                                              @RequestBody UsersDto updatedUser) {
        UsersDto userDto = usersService.updateUsers(userIdOrEmail, updatedUser);
        return ResponseEntity.ok(userDto);
    }

    // Build Update PATCH Users REST API
    @PatchMapping("{idOrEmail}")
    public ResponseEntity<UsersDto> partialUpdateUsersController(@PathVariable("idOrEmail") String userIdOrEmail,
                                                                 @RequestBody UsersDto updatedUser) {
        UsersDto userDto = usersService.partialUpdateUsers(userIdOrEmail, updatedUser);
        return ResponseEntity.ok(userDto);
    }

    // Build Delete Users REST API
    @DeleteMapping("{idOrEmail}")
    public ResponseEntity<String> deleteUsersController(@PathVariable("idOrEmail") String userIdOrEmail) {
        usersService.deleteUsers(userIdOrEmail);
        return ResponseEntity.ok("Deleted user");
    }
}
