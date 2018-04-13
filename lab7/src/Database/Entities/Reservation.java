package Database.Entities;

import java.util.Date;

public class Reservation {
    private int id_reservation;
    private Date arrival;
    private Date departure;
    private int id_client;
    private int id_room;
    private int payment;
    private int id_employee_create;
    private int id_employee_modify;

    public Reservation(Date arrival, Date departure, int id_client, int id_room, int payment, int id_employee_create, int id_employee_modify) {
        this.arrival = arrival;
        this.departure = departure;
        this.id_client = id_client;
        this.id_room = id_room;
        this.payment = payment;
        this.id_employee_create = id_employee_create;
        this.id_employee_modify = id_employee_modify;
    }

    public Reservation(int id_reservation, Date arrival, Date departure, int id_client, int id_room, int payment, int id_employee_create, int id_employee_modify) {
        this.id_reservation = id_reservation;
        this.arrival = arrival;
        this.departure = departure;
        this.id_client = id_client;
        this.id_room = id_room;
        this.payment = payment;
        this.id_employee_create = id_employee_create;
        this.id_employee_modify = id_employee_modify;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_room() {
        return id_room;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getId_employee_create() {
        return id_employee_create;
    }

    public void setId_employee_create(int id_employee_create) {
        this.id_employee_create = id_employee_create;
    }

    public int getId_employee_modify() {
        return id_employee_modify;
    }

    public void setId_employee_modify(int id_employee_modify) {
        this.id_employee_modify = id_employee_modify;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id_reservation=" + id_reservation +
                ", arrival=" + arrival +
                ", departure=" + departure +
                ", id_client=" + id_client +
                ", id_room=" + id_room +
                ", payment=" + payment +
                ", id_employee_create=" + id_employee_create +
                ", id_employee_modify=" + id_employee_modify +
                '}';
    }
}
