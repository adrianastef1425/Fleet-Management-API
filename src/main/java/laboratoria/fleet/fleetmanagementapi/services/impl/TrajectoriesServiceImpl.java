package laboratoria.fleet.fleetmanagementapi.services.impl;

import laboratoria.fleet.fleetmanagementapi.dto.LatestTrajectoriesDto;
import laboratoria.fleet.fleetmanagementapi.dto.TrajectoriesDto;
import laboratoria.fleet.fleetmanagementapi.entities.Taxis;
import laboratoria.fleet.fleetmanagementapi.entities.Trajectories;
import laboratoria.fleet.fleetmanagementapi.mappers.TrajectoriesMapper;
import laboratoria.fleet.fleetmanagementapi.repositories.TaxisRepository;
import laboratoria.fleet.fleetmanagementapi.repositories.TrajectoriesRepository;
import laboratoria.fleet.fleetmanagementapi.services.TrajectoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrajectoriesServiceImpl implements TrajectoriesService {
    @Autowired
    private TrajectoriesRepository trajectoriesRepository;
    @Autowired
    private TaxisRepository taxisRepository;

    @Override
    public List<TrajectoriesDto> getTrajectories(){
        List<Trajectories> trajectoriesList = trajectoriesRepository.findAll();
        return trajectoriesList.stream()
                .map(trajectories -> TrajectoriesMapper.mapToTrajectoriesDto(trajectories))
                .collect(Collectors.toList());
    }

    @Override
    public List<TrajectoriesDto> getTrajectoriesByIdAndDate(long taxiId, String dateString, int page, int size) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();

        }


        Pageable pageable = PageRequest.of(page, size/*, Sort.by("date").descending()*/);
        Page<Trajectories> trajectoriesPage = trajectoriesRepository.getAllByTaxisIdAndDate(taxiId, date, pageable);

        return trajectoriesPage.stream()
                .map(trajectories -> TrajectoriesMapper.mapToTrajectoriesDto(trajectories))
                .collect(Collectors.toList());

    }

    @Override
    public List<LatestTrajectoriesDto> getTrajectoriesLastLocation(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Trajectories> trajectoriesList = trajectoriesRepository.getAllTaxisWithLastTrajectory(pageable);
        return trajectoriesList.stream()
                .map(trajectories -> TrajectoriesMapper.mapToLatestTrajectoriesDto(trajectories))
                .collect(Collectors.toList());
    }


}
