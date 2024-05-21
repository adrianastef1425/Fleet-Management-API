package laboratoria.fleet.fleetmanagementapi.services;

import laboratoria.fleet.fleetmanagementapi.dto.TaxisDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface TaxisService {

    List<TaxisDto> getAllTaxis();

    List<TaxisDto> getTaxisWithPagination(int pageNumber, int pageSize);
}
