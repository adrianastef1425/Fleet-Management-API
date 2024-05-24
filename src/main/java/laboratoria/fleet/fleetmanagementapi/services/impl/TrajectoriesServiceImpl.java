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
    public List<TrajectoriesDto> getTrajectoriesByIdAndDate(long taxiId, String dateString) {
        /*
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate;

        try {
            localDate = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd", e);
        }

        // Obtener el inicio del día y el inicio del día siguiente sin considerar la zona horaria
        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime startOfNextDay = localDate.plusDays(1).atStartOfDay();

        // Convertir LocalDateTime a java.util.Date usando java.sql.Timestamp
        Date startDate = java.sql.Timestamp.valueOf(startOfDay);
        Date endDate = java.sql.Timestamp.valueOf(startOfNextDay);
         */

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            // Manejo de excepción apropiado
        }


        //List<Trajectories> trajectoriesList = trajectoriesRepository.findAllByTaxiId(taxiId);

        //List<Trajectories> trajectoriesList = trajectoriesRepository.findAllByTaxis_IdAndDateBetween(taxiId, startDate, endDate);

        List<Trajectories> trajectoriesList = trajectoriesRepository.getAllByTaxisIdAndDate(taxiId, date);
        return trajectoriesList.stream()
                .map(trajectories -> TrajectoriesMapper.mapToTrajectoriesDto(trajectories))
                .collect(Collectors.toList());
    }


    @Override
    public List<LatestTrajectoriesDto> getTrajectoriesLastLocation() {
        List<Trajectories> trajectoriesList = trajectoriesRepository.getAllTaxisWithLastTrajectory();
        return trajectoriesList.stream()
                .map(trajectories -> TrajectoriesMapper.mapToLatestTrajectoriesDto(trajectories))
                .collect(Collectors.toList());
    }


}
