package sd.model;

import java.sql.Date;

public class Account {
    private int id;
    private AccountType type;
    private double amountMoney;
    private Date dateCreation;
    private int clientId;

    public Account(int id, double amountMoney) {
        this.id = id;
        this.amountMoney = amountMoney;
    }

    public Account(int id, AccountType type, double amountMoney, Date dateCreation, int clientId) {
        this.id = id;
        this.type = type;
        this.amountMoney = amountMoney;
        this.dateCreation = dateCreation;
        this.clientId = clientId;
    }

    public Account(int id, AccountType type, double amountMoney) {
        this.id = id;
        this.type = type;
        this.amountMoney = amountMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public double getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(double amountMoney) {
        this.amountMoney = amountMoney;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
