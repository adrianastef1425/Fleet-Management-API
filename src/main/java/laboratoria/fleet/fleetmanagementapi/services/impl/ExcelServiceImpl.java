package laboratoria.fleet.fleetmanagementapi.services.impl;

import laboratoria.fleet.fleetmanagementapi.dto.ExportExcelDto;
import laboratoria.fleet.fleetmanagementapi.dto.TrajectoriesDto;
import laboratoria.fleet.fleetmanagementapi.entities.Trajectories;
import laboratoria.fleet.fleetmanagementapi.mappers.TrajectoriesMapper;
import laboratoria.fleet.fleetmanagementapi.repositories.TrajectoriesRepository;
import laboratoria.fleet.fleetmanagementapi.services.EmailService;
import laboratoria.fleet.fleetmanagementapi.services.ExcelService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExcelServiceImpl implements ExcelService {

    private EmailService emailService;

    @Autowired
    private TrajectoriesRepository trajectoriesRepository;

    public ExcelServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void writeExcelToStream(List<ExportExcelDto> trajectories, ByteArrayOutputStream output)
            throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        Row headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("ID");
        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Plate");
        Cell headerCell3 = headerRow.createCell(2);
        headerCell3.setCellValue("Latitude");
        Cell headerCell4 = headerRow.createCell(3);
        headerCell4.setCellValue("Longitude");
        Cell headerCell5 = headerRow.createCell(4);
        headerCell5.setCellValue("Date");

        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));


        int row = 1;
        for (ExportExcelDto trajectory : trajectories) {
            Row dataRow = sheet.createRow(row);

            Cell dataCell1 = dataRow.createCell(0);
            dataCell1.setCellValue(trajectory.getTaxiId());
            Cell dataCell2 = dataRow.createCell(1);
            dataCell2.setCellValue(trajectory.getPlate());
            Cell dataCell3 = dataRow.createCell(2);
            dataCell3.setCellValue(trajectory.getLatitude());
            Cell dataCell4 = dataRow.createCell(3);
            dataCell4.setCellValue(trajectory.getLongitude());
            Cell dataCell5 = dataRow.createCell(4);
            dataCell5.setCellValue(trajectory.getDate());
            dataCell5.setCellStyle(dateCellStyle);
            row++;
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);

        workbook.write(output);
        workbook.close();

    }

    @Override
    public List<ExportExcelDto> exportTrajectoriesExcelByIdAndDate(long taxiId, String dateString) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Trajectories> trajectoriesList = trajectoriesRepository.getExcelByTaxisIdAndDate(taxiId, date);

        return trajectoriesList.stream()
                .map(trajectories -> TrajectoriesMapper.mapToExportExcelDto(trajectories))
                .collect(Collectors.toList());

    }
}
