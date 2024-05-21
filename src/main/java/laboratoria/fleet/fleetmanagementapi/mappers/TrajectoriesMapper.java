package laboratoria.fleet.fleetmanagementapi.mappers;

import laboratoria.fleet.fleetmanagementapi.dto.TrajectoriesDto;
import laboratoria.fleet.fleetmanagementapi.entities.Trajectories;

public class TrajectoriesMapper {

    public static TrajectoriesDto mapToTrajectoriesDto(Trajectories trajectories){
        return new TrajectoriesDto( trajectories.getId(), trajectories.getTaxiId(),
                                    trajectories.getDate(), trajectories.getLatitude(),
                                    trajectories.getLongitude());
    }
}
