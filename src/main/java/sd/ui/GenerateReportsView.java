package sd.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class GenerateReportsView extends GridPane {

    private DatePicker beginningDateInput, endingDateInput;

    GenerateReportsView() {
        setPadding(new Insets(30, 30, 30, 30));
        setVgap(8);
        setHgap(10);

        Label label = new Label("Select beginning date:");
        GridPane.setConstraints(label, 0, 0);
        Label label1 = new Label("Select ending date:");
        GridPane.setConstraints(label1, 0, 1);

        beginningDateInput = new DatePicker();
        endingDateInput = new DatePicker();
        beginningDateInput.setValue(LocalDate.now());
        endingDateInput.setValue(beginningDateInput.getValue().plusDays(1));
        GridPane.setConstraints(beginningDateInput, 1, 0);
        GridPane.setConstraints(endingDateInput, 1, 1);

        Button button = new Button("Generate");
        button.setPrefWidth(100);
        GridPane.setConstraints(button, 1, 4);
        getChildren().addAll(label, label1, beginningDateInput, endingDateInput, button);

        button.setOnAction(e -> {
            try {
                String beginningDate = beginningDateInput.getValue().toString();
                String endingDate = endingDateInput.getValue().toString();
                Connection connection;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root2018");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    return;
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                    return;
                }

                JasperReportBuilder report = DynamicReports.report();
                report
                        .columns(
                                Columns.column("Date Transaction", "dateTransaction", DataTypes.dateType())
                                        .setHorizontalAlignment(HorizontalAlignment.CENTER),
                                Columns.column("From Account", "fromAccount", DataTypes.integerType())
                                        .setHorizontalAlignment(HorizontalAlignment.CENTER),
                                Columns.column("To Account", "toAccount", DataTypes.integerType())
                                        .setHorizontalAlignment(HorizontalAlignment.CENTER),
                                Columns.column("Amount Money Transferred", "amountMoneyTransferred", DataTypes.doubleType())
                                        .setHorizontalAlignment(HorizontalAlignment.CENTER),
                                Columns.column("Description", "description", DataTypes.stringType())
                                        .setHorizontalAlignment(HorizontalAlignment.CENTER)
                        )
                        .title(
                                Components.text("ActivitiesReport")
                                        .setHorizontalAlignment(HorizontalAlignment.CENTER))
                        .pageFooter(Components.pageXofY())
                        .setDataSource("SELECT * FROM bank.transaction " +
                                "WHERE dateTransaction >= '" + beginningDate +
                                "' AND dateTransaction <= '" + endingDate + "';", connection);

                try {
                    report.show();//show the report
                    report.toPdf(new FileOutputStream("E:/SD2019/Git/2019-30434-RoxanaLupsan/BankApplication/BankActivitiesReport.pdf"));
                } catch (DRException e1) {
                    e1.printStackTrace();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            } catch (Exception e1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert message");
                alert.setContentText("Cannot generate report");
                alert.showAndWait();
            }

        });
    }
}
