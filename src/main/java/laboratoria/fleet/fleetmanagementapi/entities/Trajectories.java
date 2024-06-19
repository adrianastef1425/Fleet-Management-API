package laboratoria.fleet.fleetmanagementapi.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "trajectories")
public class Trajectories {
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "taxi_id")
    private Taxis taxis;

    @Column(name = "date")
    private Date date; //clase Date es propio de java para fechas

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    public Trajectories() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Taxis getTaxis() {
        return taxis;
    }

    public void setTaxi(Taxis taxis) {
        this.taxis = taxis;
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
