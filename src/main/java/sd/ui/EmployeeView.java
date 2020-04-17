package sd.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sd.bll.EmployeeBLL;
import sd.dao.EmployeeDAO;
import sd.model.Employee;
import sd.model.Role;

import java.util.ArrayList;

class EmployeeView extends VBox {

    private TextField idInput, usernameInput, passwordInput;
    private ComboBox<String> roleInput;
    private TableView<Employee> tableViewEmployee;

    EmployeeView() {
        HBox hBox = new HBox(10);
        //VBox vBox = new VBox(8);
        HBox hBox1 = new HBox(10);

        tableViewEmployee = new TableView<>();
        Employee employee = new Employee(-1, null, null, null);
        TableGenerator<Employee> tableGenerator = new TableGenerator<>();
        tableViewEmployee = tableGenerator.generateTable(employee);
        tableViewEmployee.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewEmployee.setMaxWidth(900);
        refreshEmployeeTable();

        idInput = new TextField();
        idInput.setPromptText("Id");
        idInput.setMaxWidth(70);

        usernameInput = new TextField();
        usernameInput.setPromptText("Username");

        passwordInput = new TextField();
        passwordInput.setPromptText("Password");

        roleInput = new ComboBox<>();
        roleInput.getItems().addAll("ADMIN", "USER");
        roleInput.setPromptText("ADMIN/USER");

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clearAllEmployee());

        Label chooseLabel = new Label("Choose an operation: ");
        Button addButton = new Button("Add Employee");
        addButton.setPrefWidth(100);
        Button editButton = new Button("Edit Employee");
        editButton.setPrefWidth(100);
        Button deleteButton = new Button("Delete Employee");
        deleteButton.setPrefWidth(100);

        addButton.setOnAction(e -> {
            try {
                EmployeeBLL employeeBLL = new EmployeeBLL();
                Employee employee1 = new Employee(
                        usernameInput.getText(),
                        passwordInput.getText(),
                        Role.valueOf(roleInput.getValue())
                );
                employeeBLL.insertEmployee(employee1);
                refreshEmployeeTable();
                clearAllEmployee();
            } catch (Exception e1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Couldn't add employee");
                alert.setContentText("The employee could not be added.\nThe input might be missing or incompatible.\n" +
                        "Please try again.");
                alert.showAndWait();
            }
        });

        editButton.setOnAction(e -> {
            Employee employee1 = new Employee(
                    Integer.parseInt(idInput.getText()),
                    usernameInput.getText(),
                    passwordInput.getText(),
                    Role.valueOf(roleInput.getValue()));
            EmployeeDAO.update(employee1);
            clearAllEmployee();
            refreshEmployeeTable();
        });

        deleteButton.setOnAction(e -> {
            try {
                Employee selectedItem = tableViewEmployee.getSelectionModel().getSelectedItem();
                int idSelected = selectedItem.getId();
                EmployeeDAO.delete(idSelected);
                refreshEmployeeTable();
                clearAllEmployee();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Couldn't delete employee");
                alert.setContentText("The employee could not be deleted.");
                alert.showAndWait();
            }
        });

        hBox1.getChildren().addAll(chooseLabel, addButton, editButton, deleteButton);
        hBox.getChildren().addAll(idInput, usernameInput, passwordInput, roleInput);
        getChildren().addAll(tableViewEmployee, hBox, hBox1);
    }

    private void refreshEmployeeTable() {
        tableViewEmployee.getItems().removeAll();
        ArrayList<Employee> employees = EmployeeDAO.getEmployeeList();
        ObservableList<Employee> observableList = FXCollections.observableArrayList(employees);
        tableViewEmployee.setItems(observableList);
        tableViewEmployee.refresh();
    }

    private void clearAllEmployee() {
        idInput.clear();
        usernameInput.clear();
        passwordInput.clear();
        roleInput.valueProperty().set(null);
    }


}
