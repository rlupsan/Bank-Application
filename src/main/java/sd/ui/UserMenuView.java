package sd.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

class UserMenuView extends VBox {

    UserMenuView() {
        setSpacing(8);
        Button toManageClientsButton = new Button("Manage Clients");
        toManageClientsButton.setPrefWidth(150);
        Button toManageAccountsButton = new Button("Manage Accounts");
        toManageAccountsButton.setPrefWidth(150);
        Button toTransferMoneyButton = new Button("Transfer Money");
        toTransferMoneyButton.setPrefWidth(150);
        Button toProcessBillsButton = new Button("Process Bills");
        toProcessBillsButton.setPrefWidth(150);
        getChildren().addAll(toManageClientsButton, toManageAccountsButton, toTransferMoneyButton, toProcessBillsButton);
        setAlignment(Pos.CENTER);
    }
}
