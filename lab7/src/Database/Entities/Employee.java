package Database.Entities;


public class Employee {
    private int id_employee;
    private String first_name;
    private String last_name;
    private String email;
    private String salt;
    private String hash_code;

    public Employee(String first_name, String last_name, String email, String salt, String hash_code) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.salt = salt;
        this.hash_code = hash_code;
    }

    public Employee(int id_employee, String first_name, String last_name, String email, String salt, String hash_code) {
        this.id_employee = id_employee;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.salt = salt;
        this.hash_code = hash_code;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHash_code() {
        return hash_code;
    }

    public void setHash_code(String hash_code) {
        this.hash_code = hash_code;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id_employee=" + id_employee +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
