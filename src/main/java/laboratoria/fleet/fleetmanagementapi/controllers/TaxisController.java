package laboratoria.fleet.fleetmanagementapi.controllers;

import laboratoria.fleet.fleetmanagementapi.dto.TaxisDto;
import laboratoria.fleet.fleetmanagementapi.services.TaxisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//se utiliza para crear API RESTful en Spring MVC
//Se usa comúnmente para crear endpoints de API RESTful que no renderizan vistas HTML, sino que devuelven datos en formato JSON o XML.
@RestController
//todas las solicitudes HTTP que comienzan con /taxis serán manejadas por este controlador
@RequestMapping("/taxis")
public class TaxisController {

    private TaxisService taxisService;

    public TaxisController(TaxisService taxisService) {
        this.taxisService = taxisService;
    }

    //Build Get All Taxis REST API
    @GetMapping("/all")
    public ResponseEntity<List<TaxisDto>> getAllTaxisController() {
        List<TaxisDto> taxisList = taxisService.getAllTaxis();
        return ResponseEntity.ok(taxisList);
    }

    //http://localhost:8080/taxis?pageSize=5&pageNumber=1
    @GetMapping
    public ResponseEntity<List<TaxisDto>> getTaxisController(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        //List<TaxisDto> taxisPage = List.of();
        //if(page != null || limit != null){
        List<TaxisDto> taxisPage = taxisService.getTaxisWithPagination(page, limit);
        //}
        return ResponseEntity.ok(taxisPage);
    }
    /*
    @GetMapping
    public ResponseEntity<List<TaxisDto>> getTaxisWithPaginationController(@RequestParam Integer page,@RequestParam Integer limit) {
        List<TaxisDto> taxisPage = taxisService.getTaxisWithPagination(page, limit);
        return ResponseEntity.ok(taxisPage);
    }
    */

}
