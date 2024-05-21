package laboratoria.fleet.fleetmanagementapi.services;

import laboratoria.fleet.fleetmanagementapi.dto.TrajectoriesDto;

import java.text.ParseException;
import java.util.List;

public interface TrajectoriesService {

    List<TrajectoriesDto> getTrajectories();

    List<TrajectoriesDto> getTrajectoriesByIdAndDate(long id, String dateString);

    //List<Trajectories>
}
