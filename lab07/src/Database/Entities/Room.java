package Database.Entities;

public class Room {
    private int id_room;
    private boolean is_open;
    private int price;
    private String type;

    public Room(boolean is_open, int price, String type) {
        this.is_open = is_open;
        this.price = price;
        this.type = type;
    }

    public Room(int id_room, boolean is_open, int price, String type) {
        this.id_room = id_room;
        this.is_open = is_open;
        this.price = price;
        this.type = type;
    }

    public int getId_room() {
        return id_room;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public boolean isIs_open() {
        return is_open;
    }

    public void setIs_open(boolean is_open) {
        this.is_open = is_open;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return id_room +
                "\t is_open=" + is_open +
                "\t price=" + price +
                "\t type='" + type + '\'';
    }
}
