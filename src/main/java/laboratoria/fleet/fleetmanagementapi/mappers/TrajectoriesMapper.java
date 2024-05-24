package laboratoria.fleet.fleetmanagementapi.mappers;

import laboratoria.fleet.fleetmanagementapi.dto.LatestTrajectoriesDto;
import laboratoria.fleet.fleetmanagementapi.dto.TrajectoriesDto;
import laboratoria.fleet.fleetmanagementapi.entities.Taxis;
import laboratoria.fleet.fleetmanagementapi.entities.Trajectories;

public class TrajectoriesMapper {

    public static TrajectoriesDto mapToTrajectoriesDto(Trajectories trajectories/*, Taxis taxis*/){
        return new TrajectoriesDto( trajectories.getId(),trajectories.getTaxis().getId() /*trajectories.getTaxiId()*/,
                                    trajectories.getDate(), trajectories.getLatitude(),
                                    trajectories.getLongitude());
    }


    public static LatestTrajectoriesDto mapToLatestTrajectoriesDto(Trajectories trajectories){
        return new LatestTrajectoriesDto( trajectories.getTaxis().getId(),trajectories.getTaxis().getPlate(),
                trajectories.getDate(), trajectories.getLatitude(),
                trajectories.getLongitude());
    }


}
