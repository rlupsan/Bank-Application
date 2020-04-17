package sd.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sd.bll.EmployeeBLL;


public class LoginView extends Application {
    private TextField usernameInput;
    private PasswordField passwordInput;
    private EmployeeBLL businessLogic;
    private Scene sceneLogin, sceneUserMenu, sceneAdminMenu;

    @Override
    public void start(Stage primaryStage) {
        businessLogic = new EmployeeBLL();
        primaryStage.setTitle("Bank Application");

        GridPane gridPaneLogin = new GridPane();
        gridPaneLogin.setPadding(new Insets(30, 30, 30, 30));
        gridPaneLogin.setVgap(8);
        gridPaneLogin.setHgap(10);

        Label label1 = new Label("Username");
        GridPane.setConstraints(label1, 0, 1);

        usernameInput = new TextField();
        GridPane.setConstraints(usernameInput, 1, 1);

        Label label2 = new Label("Password");
        GridPane.setConstraints(label2, 0, 2);

        passwordInput = new PasswordField();
        GridPane.setConstraints(passwordInput, 1, 2);

        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 4);
        loginButton.setOnAction(this::performLogin);

        gridPaneLogin.getChildren().addAll(label1, label2, usernameInput, passwordInput, loginButton);
        gridPaneLogin.setAlignment(Pos.CENTER);
        sceneLogin = new Scene(gridPaneLogin);
        //END OF SCENE 0 (LoginView)

        //START OF SCENE1 (USER VIEW)
        primaryStage.setTitle("User Menu");
        VBox vBoxUserMenu = new VBox(8);

        Button toManageClientsButton = new Button("Manage Clients");
        toManageClientsButton.setPrefWidth(150);
        Button toManageAccountsButton = new Button("Manage Accounts");
        toManageAccountsButton.setPrefWidth(150);
        Button toTransferMoneyButton = new Button("Transfer Money");
        toTransferMoneyButton.setPrefWidth(150);
        Button toProcessBillsButton = new Button("Process Bills");
        toProcessBillsButton.setPrefWidth(150);
        vBoxUserMenu.getChildren().addAll(toManageClientsButton, toManageAccountsButton, toTransferMoneyButton, toProcessBillsButton);

        vBoxUserMenu.setAlignment(Pos.CENTER);

        toManageClientsButton.setOnAction(e -> sceneUserMenu.setRoot(new ClientView()));
        toManageAccountsButton.setOnAction(e -> sceneUserMenu.setRoot(new AccountsView()));
        toTransferMoneyButton.setOnAction(e -> sceneUserMenu.setRoot(new TransferMoneyView()));
        toProcessBillsButton.setOnAction(e -> sceneUserMenu.setRoot(new PayBillsView()));

        sceneUserMenu = new Scene(vBoxUserMenu, 1100, 550);
        //END SCENE 1 (USER MENU VIEW)

        //START SCENE 2 (ADMIN MENU VIEW)
        primaryStage.setTitle("Admin Menu");
        VBox vBoxAdminMenu = new VBox(8);
        Button toManageEmployeeButton = new Button("Manage Employees");
        toManageEmployeeButton.setPrefWidth(150);
        Button toGenerateReports = new Button("Generate Reports");
        toGenerateReports.setPrefWidth(150);
        vBoxAdminMenu.getChildren().addAll(toManageEmployeeButton, toGenerateReports);

        vBoxAdminMenu.setAlignment(Pos.CENTER);
        toManageEmployeeButton.setOnAction(e -> sceneAdminMenu.setRoot(new EmployeeView()));
        toGenerateReports.setOnAction(e -> sceneAdminMenu.setRoot(new GenerateReportsView()));
        sceneAdminMenu = new Scene(vBoxAdminMenu, 1100, 550);
        //END SCENE 2 (ADMIN MENU VIEW)

        //BUTTON LOGIN
        loginButton.setOnAction(e -> {
            String target = businessLogic.login(usernameInput.getText(), passwordInput.getText());
            switch (target) {
                case "admin":
                    primaryStage.setTitle("Admin Menu");
                    primaryStage.setScene(sceneAdminMenu);
                    //scene.setRoot(new AdminMenuView());
                    break;
                case "user":
                    primaryStage.setTitle("User Menu");
                    primaryStage.setScene(sceneUserMenu);
                    //scene.setRoot(new UserMenuView());
                    break;
                default:
                    showAlert();
                    usernameInput.clear();
                    passwordInput.clear();
            }
        });

        primaryStage.setTitle("Login");
        primaryStage.setScene(sceneLogin);
        primaryStage.show();


    }

    private void performLogin(ActionEvent actionEvent) {
        String target = businessLogic.login(usernameInput.getText(), passwordInput.getText());
        switch (target) {
            case "admin":
                sceneLogin.setRoot(new AdminMenuView());
                break;
            case "user":
                sceneLogin.setRoot(new UserMenuView());
                break;
            default:
                showAlert();
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Login");
        alert.setHeaderText("Your credentials are invalid!");
        alert.showAndWait();
    }


}
