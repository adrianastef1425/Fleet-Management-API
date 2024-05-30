package laboratoria.fleet.fleetmanagementapi.services;

import laboratoria.fleet.fleetmanagementapi.dto.UsersDto;

import java.util.List;

public interface UsersService {

    UsersDto createUsers(UsersDto usersDto);

    List<UsersDto> getAllUsers();

    UsersDto updateUsers(String idOrEmail, UsersDto updatedUsers);

    UsersDto partialUpdateUsers(String idOrEmail, UsersDto updatedUsers);

    void deleteUsers(String idOrEmail);
}
