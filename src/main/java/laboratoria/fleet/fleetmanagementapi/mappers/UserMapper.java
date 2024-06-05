package laboratoria.fleet.fleetmanagementapi.mappers;

import laboratoria.fleet.fleetmanagementapi.dto.UserDto;
import laboratoria.fleet.fleetmanagementapi.entities.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public static UserDto mapToUserDtoWithoutPassword(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public static User mapToUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail(), userDto.getPassword());
    }
}
