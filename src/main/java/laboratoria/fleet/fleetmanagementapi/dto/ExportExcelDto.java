package laboratoria.fleet.fleetmanagementapi.dto;

import java.util.Date;

public class ExportExcelDto {

    private long taxiId;
    private String plate;
    private double latitude;
    private double longitude;
    private Date date;

    public ExportExcelDto(long taxiId, String plate, double latitude, double longitude, Date date) {
        this.taxiId = taxiId;
        this.plate = plate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    }

    public long getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(long taxiId) {
        this.taxiId = taxiId;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
