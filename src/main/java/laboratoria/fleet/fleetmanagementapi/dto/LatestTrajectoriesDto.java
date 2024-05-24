package laboratoria.fleet.fleetmanagementapi.dto;

import laboratoria.fleet.fleetmanagementapi.entities.Taxis;

import java.util.Date;

public class LatestTrajectoriesDto {
    //Taxis taxis = new Taxis();

    private long id;
    private String plate;
    private Date date;
    private double latitude;
    private double longitude;

    public LatestTrajectoriesDto(long id, String plate, Date date, double latitude, double longitude) {
        this.id = id;
        this.plate = plate;
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

    //////////
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
    /////////

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
