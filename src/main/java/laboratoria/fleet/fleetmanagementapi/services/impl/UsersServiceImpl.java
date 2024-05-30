package laboratoria.fleet.fleetmanagementapi.services.impl;

import laboratoria.fleet.fleetmanagementapi.dto.UsersDto;
import laboratoria.fleet.fleetmanagementapi.entities.Users;
import laboratoria.fleet.fleetmanagementapi.exception.ResourceNotFoundException;
import laboratoria.fleet.fleetmanagementapi.mappers.TaxisMapper;
import laboratoria.fleet.fleetmanagementapi.mappers.UsersMapper;
import laboratoria.fleet.fleetmanagementapi.repositories.UsersRepository;
import laboratoria.fleet.fleetmanagementapi.services.UsersService;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UsersDto createUsers(UsersDto usersDto){
        Users users = UsersMapper.mapToUsers(usersDto);
        Users savedUsers = usersRepository.save(users);
        return UsersMapper.mapToUsersDto(savedUsers);
    }


    @Override
    public List<UsersDto> getAllUsers() {
        List<Users> usersList = usersRepository.findAll();
        return usersList.stream()
                .map((users) -> UsersMapper.mapToUsersDto(users))
                .collect(Collectors.toList());
    }

    @Override
    public  UsersDto updateUsers(String idOrEmail, UsersDto updatedUsers){
        Users users;
        if(idOrEmail.contains("@")){
            users = usersRepository.findByEmail(idOrEmail).orElseThrow(
                    ()-> new ResourceNotFoundException("User is not exist with given id: " + idOrEmail)
            );
        }else{
            Long id = Long.parseLong(idOrEmail);
            users = usersRepository.findById(id).orElseThrow(
                    ()-> new ResourceNotFoundException("User is not exist with given id: " + id)
            );
        }

        users.setName(updatedUsers.getName());
        users.setEmail(updatedUsers.getEmail());
        users.setPassword(updatedUsers.getPassword());

        Users updateUsersObj = usersRepository.save(users);

        return UsersMapper.mapToUsersDto(updateUsersObj);
    }


    @Override
    public UsersDto partialUpdateUsers(String idOrEmail, UsersDto updatedUsers) {

        Users users;
        if(idOrEmail.contains("@")){
            users = usersRepository.findByEmail(idOrEmail).orElseThrow(
                    ()-> new ResourceNotFoundException("User is not exist with given id: " + idOrEmail)
            );
        }else{
            Long id = Long.parseLong(idOrEmail);
            users = usersRepository.findById(id).orElseThrow(
                    ()-> new ResourceNotFoundException("User is not exist with given id: " + id)
            );
        }

        if(updatedUsers.getName() !=null){
            users.setName(updatedUsers.getName());
        }
        if(updatedUsers.getEmail() !=null){
            users.setEmail(updatedUsers.getEmail());
        }
        if(updatedUsers.getPassword() !=null){
            users.setPassword(updatedUsers.getPassword());
        }

        Users updateUsersObj = usersRepository.save(users);

        return UsersMapper.mapToUsersDto(updateUsersObj);
    }

    @Override
    public void deleteUsers(String idOrEmail) {
        /*
        usersRepository.findById(usersId).orElseThrow(
                ()-> new ResourceNotFoundException("User is not exist with given id: " + usersId)
        );
        usersRepository.deleteById(usersId);
         */
        Users users;
        if(idOrEmail.contains("@")){
            users = usersRepository.findByEmail(idOrEmail).orElseThrow(
                    ()-> new ResourceNotFoundException("User is not exist with given id: " + idOrEmail)
            );
        }else{
            Long id = Long.parseLong(idOrEmail);
            users = usersRepository.findById(id).orElseThrow(
                    ()-> new ResourceNotFoundException("User is not exist with given id: " + id)
            );
        }
        usersRepository.deleteById(users.getId());
    }


}
