package sd.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sd.bll.ClientBLL;
import sd.dao.ClientDAO;
import sd.model.Client;

import java.util.ArrayList;

class ClientView extends VBox {

    private TextField idClientInput, nameInput, idCardInput, pncInput, addressInput;
    private TableView<Client> tableViewClient;

    ClientView() {
        HBox hBox = new HBox(10);
        HBox hBox1 = new HBox(10);
        setSpacing(8);

        tableViewClient = new TableView<>();
        Client client = new Client(-1, null, null, null, null);
        TableGenerator<Client> t = new TableGenerator<>();
        tableViewClient = t.generateTable(client);
        tableViewClient.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewClient.setMaxWidth(900);
        refreshTableClient();

        idClientInput = new TextField();
        idClientInput.setPromptText("Id");
        idClientInput.setMaxWidth(70);

        nameInput = new TextField();
        nameInput.setPromptText("Name");

        pncInput = new TextField();
        pncInput.setPromptText("Personal Numerical Code");

        idCardInput = new TextField();
        idCardInput.setPromptText("Id Card");

        addressInput = new TextField();
        addressInput.setPromptText("Address");

        Button clearClientButton = new Button("Clear");
        clearClientButton.setOnAction(e -> clearAllFields());

        Label chooseLabelClient = new Label("Choose an operation: ");
        Button addClientButton = new Button("Add Client");
        addClientButton.setPrefWidth(100);
        Button editClientButton = new Button("Edit Client");
        editClientButton.setPrefWidth(100);
        Button deleteClientButton = new Button("Delete Client");
        deleteClientButton.setPrefWidth(100);

        addClientButton.setOnAction(e -> {
            try {
                ClientBLL clientBLL = new ClientBLL();
                Client client1 = new Client(
                        Integer.parseInt(idClientInput.getText()),
                        nameInput.getText(),
                        pncInput.getText(),
                        idCardInput.getText(),
                        addressInput.getText());
                ClientBLL.insertClient(client1);
                refreshTableClient();
                clearAllFields();
            } catch (Exception e1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Couldn't add client");
                alert.setContentText("The client could not be added.\nThe input might be missing or incompatible.\n" +
                        "Please try again.");
                alert.showAndWait();
            }
        });

        editClientButton.setOnAction(e -> {
            try {
                ClientBLL clientBLL = new ClientBLL();
                Client client1 = new Client(
                        Integer.parseInt(idClientInput.getText()),
                        nameInput.getText(),
                        pncInput.getText(),
                        idCardInput.getText(),
                        addressInput.getText());
                ClientBLL.updateClient(client1);
                clearAllFields();
                refreshTableClient();
            } catch (Exception e1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Couldn't edit client");
                alert.setContentText("The client could not be edited.\nThe input might be missing or incompatible.\n" +
                        "Please try again.");
                alert.showAndWait();
            }
        });

        deleteClientButton.setOnAction(e -> {
            try {
                Client selectedItem = tableViewClient.getSelectionModel().getSelectedItem();
                int idSelected = selectedItem.getId();
                ClientDAO.delete(idSelected);
                refreshTableClient();
                clearAllFields();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Could not delete the client");
                alert.setContentText("The client could not be deleted.\n They may have an active account.");
                alert.showAndWait();
            }
        });

        hBox1.getChildren().addAll(chooseLabelClient, addClientButton, editClientButton, deleteClientButton);
        hBox.getChildren().addAll(idClientInput, nameInput, pncInput, idCardInput, addressInput, clearClientButton);
        getChildren().addAll(tableViewClient, hBox, hBox1);
    }

    private void refreshTableClient() {
        tableViewClient.getItems().removeAll();
        ArrayList<Client> clients = ClientDAO.getClientsList();
        ObservableList<Client> observableList = FXCollections.observableArrayList(clients);
        tableViewClient.setItems(observableList);
        tableViewClient.refresh();
    }

    private void clearAllFields() {
        idClientInput.clear();
        nameInput.clear();
        pncInput.clear();
        idCardInput.clear();
        addressInput.clear();
    }


}
