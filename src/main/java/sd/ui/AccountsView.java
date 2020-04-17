package sd.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sd.bll.AccountBLL;
import sd.dao.AccountDAO;
import sd.dao.ClientDAO;
import sd.model.Account;
import sd.model.AccountType;
import sd.model.Client;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

class AccountsView extends VBox {

    private TextField idAccountInput, amountMoneyInput, clientIdInput;
    private ComboBox<String> typeInput;
    private TableView<Account> tableViewAccount;
    private TableView<Client> tableViewClient;

    AccountsView() {
        HBox hBoxAccount = new HBox(10);
        HBox hBoxAccount1 = new HBox(10);
        setSpacing(8);
        tableViewAccount = new TableView<>();
        Account account = new Account(-1, AccountType.DEPOSIT, 10, null, -1);
        TableGenerator<Account> t = new TableGenerator<>();
        tableViewAccount = t.generateTable(account);
        tableViewAccount.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewAccount.setMaxWidth(900);
        refreshAccountTable();

        tableViewClient = new TableView<>();
        Client client = new Client(-1, null, null, null, null);
        TableGenerator<Client> tc = new TableGenerator<>();
        tableViewClient = tc.generateTable(client);
        tableViewClient.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewClient.setMaxWidth(900);
        refreshTableClient();

        idAccountInput = new TextField();
        idAccountInput.setPromptText("Id");
        idAccountInput.setMaxWidth(70);

        typeInput = new ComboBox<>();
        typeInput.getItems().addAll("CURRENT", "DEPOSIT");
        typeInput.setPromptText("CURRENT/DEPOSIT");

        amountMoneyInput = new TextField();
        amountMoneyInput.setPromptText("Amount of Money");

        clientIdInput = new TextField();
        clientIdInput.setPromptText("Client Id");

        Button clearFieldsAccountButton = new Button("Clear");
        clearFieldsAccountButton.setOnAction(e -> clearAllAccount());

        Label chooseLabelAccount = new Label("Choose an operation: ");
        Button addAccountButton = new Button("Add Account");
        addAccountButton.setPrefWidth(100);
        Button editAccountButton = new Button("Edit Account");
        editAccountButton.setPrefWidth(100);
        Button deleteAccountButton = new Button("Delete Account");
        deleteAccountButton.setPrefWidth(100);

        addAccountButton.setOnAction(e -> {
            try {
                AccountBLL accountBLL = new AccountBLL();
                Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
                Account account1 = new Account(
                        Integer.parseInt(idAccountInput.getText()),
                        AccountType.valueOf(typeInput.getValue()),
                        Double.parseDouble(amountMoneyInput.getText()),
                        currentDate,
                        Integer.parseInt(clientIdInput.getText())
                );

                AccountBLL.insertAccount(account1);
                refreshAccountTable();
                clearAllAccount();
            } catch (Exception e1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Couldn't add account");
                alert.setContentText("The account could not be added.\nThe input might be missing or incompatible.\n" +
                        "Please try again.");
                alert.showAndWait();
            }
        });

        editAccountButton.setOnAction(e -> {
            try {
                AccountBLL accountBLL = new AccountBLL();
                Account account1 = new Account(
                        Integer.parseInt(idAccountInput.getText()),
                        AccountType.valueOf(typeInput.getValue()),
                        Double.parseDouble(amountMoneyInput.getText())
                );
                AccountBLL.updateAccount(account1);
                clearAllAccount();
                refreshAccountTable();
            } catch (Exception e1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Couldn't edit account");
                alert.setContentText("The account could not be edited.\nThe input might be missing or incompatible.\n" +
                        "Please try again.");
                alert.showAndWait();
            }
        });

        deleteAccountButton.setOnAction(e -> {
            try {
                Account selectedItem = tableViewAccount.getSelectionModel().getSelectedItem();
                int idSelected = selectedItem.getId();
                AccountDAO.delete(idSelected);
                refreshAccountTable();
                clearAllAccount();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Couldn't delete account");
                alert.setContentText("The account could not be deleted.");
                alert.showAndWait();
            }
        });
        hBoxAccount1.getChildren().addAll(chooseLabelAccount, addAccountButton, editAccountButton, deleteAccountButton);
        hBoxAccount.getChildren().addAll(idAccountInput, typeInput, amountMoneyInput, clientIdInput, clearFieldsAccountButton);
        getChildren().addAll(tableViewClient, tableViewAccount, hBoxAccount, hBoxAccount1);
    }

    private void refreshAccountTable() {
        tableViewAccount.getItems().removeAll();
        ArrayList<Account> accounts = AccountDAO.getAccountsList();
        ObservableList<Account> observableList = FXCollections.observableArrayList(accounts);
        tableViewAccount.setItems(observableList);
        tableViewAccount.refresh();
    }

    private void clearAllAccount() {
        idAccountInput.clear();
        typeInput.valueProperty().set(null);
        amountMoneyInput.clear();
        clientIdInput.clear();
    }

    private void refreshTableClient() {
        tableViewClient.getItems().removeAll();
        ArrayList<Client> clients = ClientDAO.getClientsList();
        ObservableList<Client> observableList = FXCollections.observableArrayList(clients);
        tableViewClient.setItems(observableList);
        tableViewClient.refresh();
    }
}
