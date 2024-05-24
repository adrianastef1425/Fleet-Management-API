package laboratoria.fleet.fleetmanagementapi.repositories;

import laboratoria.fleet.fleetmanagementapi.entities.Taxis;
import laboratoria.fleet.fleetmanagementapi.entities.Trajectories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TrajectoriesRepository extends JpaRepository<Trajectories, Long> {

    //List<Trajectories> findAllByTaxiId(Long taxiId);

    //List<Trajectories> findAllByTaxis_IdAndDateBetween(Long taxiId, Date startDate, Date endDate);

    @Query(value = "SELECT * FROM trajectories WHERE taxi_id = :taxiId AND date_trunc('day', date) = :date", nativeQuery = true)
    List<Trajectories> getAllByTaxisIdAndDate(@Param("taxiId") long taxiId, @Param("date") Date date);

    @Query("SELECT t FROM Trajectories t WHERE t.date = (SELECT MAX(t2.date) FROM Trajectories t2 WHERE t2.taxis.id = t.taxis.id)")
    List<Trajectories> getAllTaxisWithLastTrajectory();

}
