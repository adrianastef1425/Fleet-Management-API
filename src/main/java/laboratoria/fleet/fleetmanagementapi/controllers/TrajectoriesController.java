package laboratoria.fleet.fleetmanagementapi.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laboratoria.fleet.fleetmanagementapi.dto.LatestTrajectoriesDto;
import laboratoria.fleet.fleetmanagementapi.dto.TrajectoriesDto;
import laboratoria.fleet.fleetmanagementapi.entities.Trajectories;
import laboratoria.fleet.fleetmanagementapi.services.TrajectoriesService;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trajectories")
public class TrajectoriesController /*extends AbstractXlsView*/ {

    private TrajectoriesService trajectoriesService;

    public TrajectoriesController(TrajectoriesService trajectoriesService) {
        this.trajectoriesService = trajectoriesService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TrajectoriesDto>> getTrajectoriesController(){
        List<TrajectoriesDto> trajectoriesList = trajectoriesService.getTrajectories();
        return ResponseEntity.ok(trajectoriesList);
    }

    @GetMapping
    public ResponseEntity<List<TrajectoriesDto>> getTrajectoriesByIdAndDateController(@RequestParam Integer id,
                                                                                      @RequestParam String date,
                                                                                      @RequestParam(defaultValue = "0") int page,
                                                                                      @RequestParam(defaultValue = "10") int limit){
        List<TrajectoriesDto> trajectoriesList = trajectoriesService.getTrajectoriesByIdAndDate(id, date, page, limit);
        return ResponseEntity.ok(trajectoriesList);
    }

    @GetMapping("/latest")
    public ResponseEntity <List<LatestTrajectoriesDto>> getTrajectoriesLastLocationController(@RequestParam(defaultValue = "0") int page,
                                                                                              @RequestParam(defaultValue = "2") int limit){
        List<LatestTrajectoriesDto> trajectoriesList = trajectoriesService.getTrajectoriesLastLocation(page, limit);
        return ResponseEntity.ok(trajectoriesList);
    }

}
