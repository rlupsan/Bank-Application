package sd.ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import sd.bll.TransactionBLL;
import sd.dao.AccountDAO;
import sd.model.Account;
import sd.model.Transaction;

import java.sql.Date;
import java.util.Calendar;


class TransferMoneyView extends GridPane {

    private TextField amountMoneyToTransferInput, fromAccountIdInput, toAccountIdInput;
    private TextArea descriptionInput;

    TransferMoneyView() {
        setPadding(new Insets(30, 30, 30, 30));
        setVgap(8);
        setHgap(10);

        Label label = new Label("Amount of money to transfer:");
        GridPane.setConstraints(label, 0, 0);
        Label label1 = new Label("From account id:");
        GridPane.setConstraints(label1, 0, 1);
        Label label2 = new Label("To account id:");
        GridPane.setConstraints(label2, 0, 2);
        Label label3 = new Label("Description");
        GridPane.setConstraints(label3, 0, 3);
        amountMoneyToTransferInput = new TextField();
        GridPane.setConstraints(amountMoneyToTransferInput, 1, 0);
        fromAccountIdInput = new TextField();
        GridPane.setConstraints(fromAccountIdInput, 1, 1);
        toAccountIdInput = new TextField();
        GridPane.setConstraints(toAccountIdInput, 1, 2);
        descriptionInput = new TextArea();
        descriptionInput.setPrefSize(200, 100);
        GridPane.setConstraints(descriptionInput, 1, 3);
        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(100);
        GridPane.setConstraints(sendButton, 1, 4);
        sendButton.setOnAction(e -> {
            Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
            try {
                Double moneyTransferred = Double.parseDouble(amountMoneyToTransferInput.getText());
                int idFromAccount = Integer.parseInt(fromAccountIdInput.getText());
                int idToAccount = Integer.parseInt(toAccountIdInput.getText());

                Account fromAccount = AccountDAO.findById(idFromAccount);
                Double oldMoneyAccount1 = fromAccount.getAmountMoney();
                if ((oldMoneyAccount1 < moneyTransferred) && (idFromAccount > 0) && (idToAccount > 0)) {
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Not enough money");
                    alert1.setContentText("There are not enough money in the account to transfer the sum.\n" +
                            "Please recharge before transfer!");
                    alert1.showAndWait();
                    clearAllFields();
                } else {
                    fromAccount.setAmountMoney(oldMoneyAccount1 - moneyTransferred);
                    fromAccount = new Account(idFromAccount, fromAccount.getAmountMoney());
                    AccountDAO.updateMoneyOnly(fromAccount);

                    Account toAccount = AccountDAO.findById(idToAccount);
                    Double oldMoneyAccount2 = toAccount.getAmountMoney();
                    toAccount.setAmountMoney(oldMoneyAccount2 + moneyTransferred);
                    toAccount = new Account(idToAccount, toAccount.getAmountMoney());
                    AccountDAO.updateMoneyOnly(toAccount);

                    TransactionBLL transactionBLL = new TransactionBLL();
                    Transaction transaction1 = new Transaction(
                            currentDate,
                            idFromAccount,
                            idToAccount,
                            moneyTransferred,
                            descriptionInput.getText()
                    );
                    transactionBLL.insertTransaction(transaction1);
                    clearAllFields();
                    System.out.println("SUCCESS!!!\n" + "old Money Account " + fromAccount.getId() + "==?" + idFromAccount + " : " + oldMoneyAccount1 + " and now it has: " + fromAccount.getAmountMoney() + "\n");
                    System.out.println("old Money Account " + toAccount.getId() + "==?" + idToAccount + " : " + oldMoneyAccount2 + " and now it has: " + toAccount.getAmountMoney() + "\n");
                    System.out.println("\nMoney transferred:" + moneyTransferred);
                    System.out.println("On the date: " + currentDate);
                }

            } catch (Exception e1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert message");
                alert.setContentText("The transaction could not be done.\nThe input might be missing or incompatible.\n" +
                        "Please try again.");
                alert.showAndWait();
            }
        });


        getChildren().addAll(label, label1, label2, label3, amountMoneyToTransferInput, fromAccountIdInput,
                toAccountIdInput, descriptionInput, sendButton);
    }

    private void clearAllFields() {
        fromAccountIdInput.clear();
        toAccountIdInput.clear();
        amountMoneyToTransferInput.clear();
        descriptionInput.clear();
    }
}
