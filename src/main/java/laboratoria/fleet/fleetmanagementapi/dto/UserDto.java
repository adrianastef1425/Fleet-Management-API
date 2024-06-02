package laboratoria.fleet.fleetmanagementapi.dto;

public class UserDto {

    private long id;

    private String name;

    private String email;

    //@JsonIgnore
    private String password;
    /*
    @JsonCreator
    public UserDto(@JsonProperty("password") String password) {
        this.password = password;
    }
    */
    public UserDto(long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
