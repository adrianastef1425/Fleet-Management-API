package laboratoria.fleet.fleetmanagementapi.services;

import laboratoria.fleet.fleetmanagementapi.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    List<UserDto> getAllUser(int pageNumber, int pageSize);

    UserDto updateUser(String idOrEmail, UserDto updatedUsers);

    UserDto partialUpdateUser(String idOrEmail, UserDto updatedUsers);

    UserDto deleteUser(String idOrEmail);
}
