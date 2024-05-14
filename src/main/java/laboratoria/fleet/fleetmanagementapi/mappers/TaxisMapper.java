package laboratoria.fleet.fleetmanagementapi.mappers;

import laboratoria.fleet.fleetmanagementapi.dto.TaxisDto;
import laboratoria.fleet.fleetmanagementapi.entities.Taxis;

public class TaxisMapper {

    public static TaxisDto mapToTaxisDto(Taxis taxis) {
        return new TaxisDto(taxis.getId(), taxis.getPlate());
    }

    public static Taxis mapToTaxis(TaxisDto taxisDto) {
        return new Taxis(taxisDto.getId(), taxisDto.getPlate());
    }
}
