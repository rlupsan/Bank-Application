package sd.ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import sd.bll.AccountBLL;
import sd.dao.AccountDAO;
import sd.model.Account;

import java.sql.Date;
import java.util.Calendar;

class PayBillsView extends GridPane {
    private TextField moneyInput, fromAccountIdInput;
    private ComboBox<String> companyInput;


    PayBillsView() {
        setPadding(new Insets(30, 30, 30, 30));
        setVgap(8);
        setHgap(10);

        Label label = new Label("From Account:");
        GridPane.setConstraints(label, 0, 0);
        Label label1 = new Label("Amount of Money:");
        GridPane.setConstraints(label1, 0, 1);
        Label label2 = new Label("To Company:");
        GridPane.setConstraints(label2, 0, 2);
        fromAccountIdInput = new TextField();
        GridPane.setConstraints(fromAccountIdInput, 1, 0);
        moneyInput = new TextField();
        GridPane.setConstraints(moneyInput, 1, 1);
        companyInput = new ComboBox<>();
        companyInput.getItems().addAll("Orange", "Vodafone", "E-ON", "Curent", "Cosmote");
        companyInput.setPromptText("Company");
        GridPane.setConstraints(companyInput, 1, 2);
        Button payButton = new Button("PAY");
        payButton.setPrefWidth(100);
        GridPane.setConstraints(payButton, 1, 3);
        payButton.setOnAction(e -> {
            Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
            try {
                AccountBLL accountBLL = new AccountBLL();
                int idFromAccount = Integer.parseInt(fromAccountIdInput.getText());
                double moneyPaid = Double.parseDouble(moneyInput.getText());
                String company = companyInput.getValue();

                Account account = AccountDAO.findById(idFromAccount);

                if (account.getAmountMoney() < moneyPaid) {
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Not enough money");
                    alert1.setContentText("There are not enough money in the account to pay the bill.\nPlease recharge!");
                    alert1.showAndWait();
                    clearAllFields();
                } else {
                    double oldBalance = account.getAmountMoney();
                    account.setAmountMoney(account.getAmountMoney() - moneyPaid);

                    account = new Account(idFromAccount, account.getAmountMoney());
                    AccountBLL.updateMoneyOnlyAccount(account);

                    clearAllFields();
                    System.out.println("SUCCESS!!!\n" + "Account " + account.getId() + "==?" + idFromAccount + " , had a balance of: " + oldBalance + " and the balance is: " + account.getAmountMoney() + "\n");
                    System.out.println("Money transferred:" + moneyPaid + "to the company: " + company);
                    System.out.println("On the date:" + currentDate);
                    clearAllFields();
                }

            } catch (Exception e1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Payment error");
                alert.setContentText("The payment could not be done.\nThe input might be missing or incompatible.\n" +
                        "Please try again.");
                alert.showAndWait();
            }
        });
        getChildren().addAll(label, label1, label2, fromAccountIdInput, moneyInput, companyInput, payButton);
    }

    private void clearAllFields() {
        fromAccountIdInput.clear();
        moneyInput.clear();
        companyInput.valueProperty().set(null);
    }
}
