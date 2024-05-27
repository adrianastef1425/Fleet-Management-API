package laboratoria.fleet.fleetmanagementapi.repositories;

import laboratoria.fleet.fleetmanagementapi.entities.Taxis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxisRepository extends JpaRepository<Taxis, Long> {
    //@Query
    //includes para filtrar los taxis por placa y con paginacion
}
