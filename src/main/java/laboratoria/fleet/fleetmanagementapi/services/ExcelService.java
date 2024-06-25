package laboratoria.fleet.fleetmanagementapi.services;

import laboratoria.fleet.fleetmanagementapi.dto.ExportExcelDto;
import laboratoria.fleet.fleetmanagementapi.dto.TrajectoriesDto;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public interface ExcelService {
    /*ResponseEntity<byte[]>*/ void writeExcelToStream(List<ExportExcelDto> trajectories, ByteArrayOutputStream output)
            throws IOException;
    List<ExportExcelDto> exportTrajectoriesExcelByIdAndDate(long taxiId, String dateString);
}
