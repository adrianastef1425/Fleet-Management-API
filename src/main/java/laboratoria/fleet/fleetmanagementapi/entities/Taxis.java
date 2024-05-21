package laboratoria.fleet.fleetmanagementapi.entities;

import jakarta.persistence.*;

//@: anotations - son metadatos
//Entidad de persistencia en JPA(Java Persisence API)
// Representa una tabla en DB
@Entity
@Table(name = "taxis")
public class Taxis {
    public Taxis() {
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
