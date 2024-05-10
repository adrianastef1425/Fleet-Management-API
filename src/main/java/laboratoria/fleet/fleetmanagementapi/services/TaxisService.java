package laboratoria.fleet.fleetmanagementapi.services;

import laboratoria.fleet.fleetmanagementapi.dto.TaxisDto;

import java.util.List;

public interface TaxisService {
    //TaxisDto createTaxis(TaxisDto taxisDto);

    //TaxisDto getTaxisById(long taxisId);

    List<TaxisDto> getAllTaxis();
}
