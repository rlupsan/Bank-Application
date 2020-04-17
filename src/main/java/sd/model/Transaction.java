package sd.model;

import java.sql.Date;

public class Transaction {
    private int id;
    private Date dateTransaction;
    private int fromAccount;
    private int toAccount;
    private double amountMoneyTransferred;
    private String description;

    public Transaction(Date dateTransaction, int fromAccount, int toAccount, double amountMoneyTransferred, String description) {
        this.dateTransaction = dateTransaction;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amountMoneyTransferred = amountMoneyTransferred;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(int fromAccount) {
        this.fromAccount = fromAccount;
    }

    public int getToAccount() {
        return toAccount;
    }

    public void setToAccount(int toAccount) {
        this.toAccount = toAccount;
    }

    public double getAmountMoneyTransferred() {
        return amountMoneyTransferred;
    }

    public void setAmountMoneyTransferred(double amountMoneyTransferred) {
        this.amountMoneyTransferred = amountMoneyTransferred;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
