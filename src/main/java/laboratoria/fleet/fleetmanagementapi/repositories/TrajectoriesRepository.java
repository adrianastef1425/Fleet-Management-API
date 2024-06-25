package laboratoria.fleet.fleetmanagementapi.repositories;

import laboratoria.fleet.fleetmanagementapi.entities.Taxis;
import laboratoria.fleet.fleetmanagementapi.entities.Trajectories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TrajectoriesRepository extends JpaRepository<Trajectories, Long> {

    //List<Trajectories> findAllByTaxiId(Long taxiId);

    //List<Trajectories> findAllByTaxis_IdAndDateBetween(Long taxiId, Date startDate, Date endDate);

    @Query(value = "SELECT t FROM Trajectories t WHERE t.taxis.id = :taxiId AND date_trunc('day', t.date) = :date")
    List<Trajectories> getExcelByTaxisIdAndDate(@Param("taxiId") long taxiId, @Param("date") Date date);

    @Query(value = "SELECT t FROM Trajectories t WHERE t.taxis.id = :taxiId AND date_trunc('day', t.date) = :date")
    Page<Trajectories> getAllByTaxisIdAndDate(@Param("taxiId") long taxiId, @Param("date") Date date, Pageable pageable);

    @Query("SELECT t FROM Trajectories t WHERE t.id in (SELECT MAX(t2.id) FROM Trajectories t2 GROUP BY t2.taxis.id)")
    Page<Trajectories> getAllTaxisWithLastTrajectory(Pageable pageable);

}
