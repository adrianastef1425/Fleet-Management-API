package laboratoria.fleet.fleetmanagementapi.dto;

public class TaxisDto {

    private long id;

    private String plate;

    public TaxisDto(long id, String plate) {
        this.id = id;
        this.plate = plate;
    }

    public long getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }
}
