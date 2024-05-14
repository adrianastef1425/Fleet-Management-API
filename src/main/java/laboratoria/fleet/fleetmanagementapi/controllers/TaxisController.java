package laboratoria.fleet.fleetmanagementapi.controllers;

import laboratoria.fleet.fleetmanagementapi.dto.TaxisDto;
import laboratoria.fleet.fleetmanagementapi.services.TaxisService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taxis")
public class TaxisController {

    private TaxisService taxisService;

    public TaxisController(TaxisService taxisService) {
        this.taxisService = taxisService;
    }



    /*
    //Build Add Taxis REST API
    @PostMapping
    public ResponseEntity<TaxisDto> createTaxis(@RequestBody TaxisDto taxisDto) {
        TaxisDto savedTaxis = taxisService.createTaxis(taxisDto);
        return new ResponseEntity<>(savedTaxis, HttpStatus.CREATED);
    }*/
    /*
    //Build Get Taxis REST API
    @GetMapping("{id}")
    public ResponseEntity<TaxisDto> getTaxisById(@PathVariable("id") Long taxisId) {
        TaxisDto taxisDto = taxisService.getTaxisById(taxisId);
        return ResponseEntity.ok(taxisDto);
    }*/

    //Build Get All Taxis REST API
    /*
    @GetMapping
    public ResponseEntity<List<TaxisDto>> getAllTaxis() {
        List<TaxisDto> taxisList = taxisService.getAllTaxis();
        return ResponseEntity.ok(taxisList);
    }*/

    //http://localhost:8080/taxis?pageSize=5&pageNumber=1
    @GetMapping
    public ResponseEntity<List<TaxisDto>> getTaxisWithPagination(@RequestParam Integer pageNumber,@RequestParam Integer pageSize) {
        List<TaxisDto> taxisPage = taxisService.getTaxisWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(taxisPage);
    }

}
