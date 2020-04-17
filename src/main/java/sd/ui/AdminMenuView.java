package sd.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

class AdminMenuView extends VBox {

    AdminMenuView() {
        setSpacing(8);
        Button toManageEmployeeButton = new Button("Manage Employees");
        toManageEmployeeButton.setPrefWidth(150);
        Button toGenerateReports = new Button("Generate Reports");
        toGenerateReports.setPrefWidth(150);
        getChildren().addAll(toManageEmployeeButton, toGenerateReports);
        setAlignment(Pos.CENTER);
    }
}
