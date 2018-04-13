package Database.Entities;

public class Client {
    private int id_client;
    private String first_name;
    private String last_name;
    private String email;
    private String identity_number;
    private String phone_number;

    public Client(String first_name, String last_name, String email, String identity_number, String phone_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.identity_number = identity_number;
        this.phone_number = phone_number;
    }

    public Client(int id_client, String first_name, String last_name, String email, String identity_number, String phone_number) {
        this.id_client = id_client;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.identity_number = identity_number;
        this.phone_number = phone_number;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                //", email='" + email + '\'' +
                ", identity_number='" + identity_number + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }
}
