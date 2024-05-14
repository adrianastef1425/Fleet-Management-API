package laboratoria.fleet.fleetmanagementapi.services;

import laboratoria.fleet.fleetmanagementapi.dto.TaxisDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface TaxisService {
    //TaxisDto createTaxis(TaxisDto taxisDto);

    //TaxisDto getTaxisById(long taxisId);

    List<TaxisDto> getAllTaxis();

    List<TaxisDto> getTaxisWithPagination(int pageNumber, int pageSize);
}
