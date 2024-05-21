package laboratoria.fleet.fleetmanagementapi.dto;

import java.util.Date;

public class TrajectoriesDto {

    private long id;
    private int taxiId;
    private Date date;
    private double latitude;
    private double longitude;

    public TrajectoriesDto(long id, int taxiId, Date date, double latitude, double longitude) {
        this.id = id;
        this.taxiId = taxiId;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(int taxiId) {
        this.taxiId = taxiId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}
