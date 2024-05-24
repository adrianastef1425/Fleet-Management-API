package laboratoria.fleet.fleetmanagementapi.services;

import laboratoria.fleet.fleetmanagementapi.dto.LatestTrajectoriesDto;
import laboratoria.fleet.fleetmanagementapi.dto.TrajectoriesDto;
import laboratoria.fleet.fleetmanagementapi.entities.Trajectories;

import java.text.ParseException;
import java.util.List;

public interface TrajectoriesService {

    List<TrajectoriesDto> getTrajectories();

    List<TrajectoriesDto> getTrajectoriesByIdAndDate(long id, String dateString);

    List<LatestTrajectoriesDto> getTrajectoriesLastLocation();
}
