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
    @Autowired
    private TaxisRepository taxisRepository;

    public TaxisServiceImpl(TaxisRepository taxisRepository) {
        this.taxisRepository = taxisRepository;
    }
    /*
    @Override
    public TaxisDto createTaxis(TaxisDto taxisDto) {
        Taxis taxis = TaxisMapper.mapToTaxis(taxisDto);
        Taxis savedTaxis = taxisRepository.save(taxis);
        return TaxisMapper.mapToTaxisDto(savedTaxis);
    }*/
    /*
    @Override
    public TaxisDto getTaxisById(long taxisId) {
        Taxis taxis = taxisRepository.findById(taxisId).orElse(null);
        return TaxisMapper.mapToTaxisDto(taxis);
    }*/

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
              .map((taxis) -> TaxisMapper.mapToTaxisDto(taxis))
              .collect(Collectors.toList());
    }
}
