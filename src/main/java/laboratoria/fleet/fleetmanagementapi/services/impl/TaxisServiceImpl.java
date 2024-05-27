package laboratoria.fleet.fleetmanagementapi.services.impl;

import laboratoria.fleet.fleetmanagementapi.dto.TaxisDto;
import laboratoria.fleet.fleetmanagementapi.entities.Taxis;
import laboratoria.fleet.fleetmanagementapi.mappers.TaxisMapper;
import laboratoria.fleet.fleetmanagementapi.repositories.TaxisRepository;
import laboratoria.fleet.fleetmanagementapi.services.TaxisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaxisServiceImpl implements TaxisService {
    @Autowired//funciona si esté o no esté
    private TaxisRepository taxisRepository;

    public TaxisServiceImpl(TaxisRepository taxisRepository) {
        this.taxisRepository = taxisRepository;
    }

    @Override
    public List<TaxisDto> getAllTaxis() {
        List<Taxis> taxisList = taxisRepository.findAll();
        return taxisList.stream()
                .map((taxis) -> TaxisMapper.mapToTaxisDto(taxis))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaxisDto> getTaxisWithPagination(int pageNumber, int pageSize){
        Page<Taxis> taxisList= taxisRepository.findAll(PageRequest.of(pageNumber, pageSize));
      return taxisList.stream()
              .map(taxis -> TaxisMapper.mapToTaxisDto(taxis))
              .collect(Collectors.toList());
    }
}
