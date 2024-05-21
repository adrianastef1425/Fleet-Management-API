package laboratoria.fleet.fleetmanagementapi.repositories;

import laboratoria.fleet.fleetmanagementapi.entities.Taxis;
import laboratoria.fleet.fleetmanagementapi.entities.Trajectories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TrajectoriesRepository extends JpaRepository<Trajectories, Long> {

    List<Trajectories> findAllByTaxiId(Long taxiId);
    List<Trajectories> findAllByTaxiIdAndDateBetween(Long taxiId, Date startDate, Date endDate);

    //@Query

}
