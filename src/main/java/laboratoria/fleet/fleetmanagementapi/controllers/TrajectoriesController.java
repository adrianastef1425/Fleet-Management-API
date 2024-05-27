package laboratoria.fleet.fleetmanagementapi.controllers;

import laboratoria.fleet.fleetmanagementapi.dto.LatestTrajectoriesDto;
import laboratoria.fleet.fleetmanagementapi.dto.TrajectoriesDto;
import laboratoria.fleet.fleetmanagementapi.entities.Trajectories;
import laboratoria.fleet.fleetmanagementapi.services.TrajectoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trajectories")
public class TrajectoriesController {

    private TrajectoriesService trajectoriesService;

    public TrajectoriesController(TrajectoriesService trajectoriesService) {
        this.trajectoriesService = trajectoriesService;
    }


    @GetMapping()
    public ResponseEntity<List<TrajectoriesDto>> getTrajectoriesController(){
        List<TrajectoriesDto> trajectoriesList = trajectoriesService.getTrajectories();
        return ResponseEntity.ok(trajectoriesList);
    }

    @GetMapping("{taxisId}")
    public ResponseEntity<List<TrajectoriesDto>> getTrajectoriesByIdAndDateController(@PathVariable("taxisId") Integer id, @RequestParam String date){
        List<TrajectoriesDto> trajectoriesList = trajectoriesService.getTrajectoriesByIdAndDate(id, date);
        return ResponseEntity.ok(trajectoriesList);
    }




    @GetMapping("/latest")
    public ResponseEntity <List<LatestTrajectoriesDto>> getTrajectoriesLastLocationController(){
        List<LatestTrajectoriesDto> trajectoriesList = trajectoriesService.getTrajectoriesLastLocation();
        return ResponseEntity.ok(trajectoriesList);
    }



}
