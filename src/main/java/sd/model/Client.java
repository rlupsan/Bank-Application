package sd.model;

public class Client {
    private int id;
    private String name;
    private String pnc;
    private String idCard;
    private String address;

    public Client(int id, String name, String pnc, String idCard, String address) {
        this.id = id;
        this.name = name;
        this.pnc = pnc;
        this.idCard = idCard;
        this.address = address;
    }

    public Client(String name, String pnc, String idCard, String address) {
        this.name = name;
        this.pnc = pnc;
        this.idCard = idCard;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPnc() {
        return pnc;
    }

    public void setPnc(String pnc) {
        this.pnc = pnc;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pnc='" + pnc + '\'' +
                ", idCard='" + idCard + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
