package laboratoria.fleet.fleetmanagementapi.controllers;

import laboratoria.fleet.fleetmanagementapi.dto.TaxisDto;
import laboratoria.fleet.fleetmanagementapi.services.TaxisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/taxis")
public class TaxisController {

    private TaxisService taxisService;

    public TaxisController(TaxisService taxisService) {
        this.taxisService = taxisService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaxisDto>> getAllTaxisController() {
        List<TaxisDto> taxisList = taxisService.getAllTaxis();
        return ResponseEntity.ok(taxisList);
    }

    @GetMapping
    public ResponseEntity<Object> getTaxisController(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "10") Integer limit) {

        if(page < 0 ){
            HashMap<String, String> response = new HashMap<>();
            response.put("error", "Invalid page number");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        //
        List<TaxisDto> taxisPage = taxisService.getTaxisWithPagination(page, limit);
        return ResponseEntity.ok(taxisPage);
    }

}
