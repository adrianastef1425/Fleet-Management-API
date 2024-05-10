package laboratoria.fleet.fleetmanagementapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "taxis")
public class Taxis {
    public Taxis() {
    }
    public Taxis(long id, String plate) {
        this.id = id;
        this.plate = plate;
    }
    @Id
    private long id;

    @Column(name = "plate")
    private String plate;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
