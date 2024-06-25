package laboratoria.fleet.fleetmanagementapi.repositories;

import laboratoria.fleet.fleetmanagementapi.entities.Taxis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxisRepository extends JpaRepository<Taxis, Long> {

}
