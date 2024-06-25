package laboratoria.fleet.fleetmanagementapi.controllers;

import laboratoria.fleet.fleetmanagementapi.dto.ExportExcelDto;
import laboratoria.fleet.fleetmanagementapi.services.EmailService;
import laboratoria.fleet.fleetmanagementapi.services.ExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/excel")
public class ExportExcelAndSendEmail {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private EmailService emailService;
    private static final Logger logger = LoggerFactory.getLogger(ExportExcelAndSendEmail.class);

    @GetMapping("/export")
    public ResponseEntity<Object> exportExcel(@RequestParam Integer id,
                                              @RequestParam String date,
                                              @RequestParam String email) {
        List<ExportExcelDto> trajectoriesList;
        ByteArrayOutputStream excelOutputStream = new ByteArrayOutputStream();

        try {
            trajectoriesList = excelService.exportTrajectoriesExcelByIdAndDate(id, date);
            excelService.writeExcelToStream(trajectoriesList, excelOutputStream);

            // Convert the ByteArrayOutputStream to a byte array
            byte[] excelBytes = excelOutputStream.toByteArray();


            emailService.sendMessageWithAttachment( email,
                                                    "Envio de excel con data de trayectorias",
                                                    "envio exitoso",
                                                    excelBytes,
                                                    "data_Trajectories.xlsx");

        } catch (Exception ex) {
            HashMap<String, String> response = new HashMap<>();
            response.put("error", "Unexpected error");
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Export successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
