package laboratoria.fleet.fleetmanagementapi.mappers;

import laboratoria.fleet.fleetmanagementapi.dto.UsersDto;
import laboratoria.fleet.fleetmanagementapi.entities.Users;

public class UsersMapper {

    public static UsersDto mapToUsersDto(Users users) {
        return new UsersDto(users.getId(), users.getName(), users.getEmail(), users.getPassword());
    }

    public static Users mapToUsers(UsersDto usersDto) {
        return new Users(usersDto.getId(), usersDto.getName(), usersDto.getEmail(), usersDto.getPassword());
    }
}
