package sd.dao;

import sd.dao.connection.ConnectionClose;
import sd.dao.connection.ConnectionFactory;
import sd.model.Transaction;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionDAO {
    private static final Logger LOGGER = Logger.getLogger(EmployeeDAO.class.getName());
    private static final String insertStatementString =
            "INSERT INTO transaction (dateTransaction, fromAccount, toAccount, amountMoneyTransferred, description) VALUES (?,?,?,?,?)";

    public static int insert(Transaction transaction) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setDate(1, transaction.getDateTransaction());
            insertStatement.setInt(2, transaction.getFromAccount());
            insertStatement.setInt(3, transaction.getToAccount());
            insertStatement.setDouble(4, transaction.getAmountMoneyTransferred());
            insertStatement.setString(5, transaction.getDescription());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "TransactionDAO:insert " + e.getMessage());
        } finally {
            ConnectionClose.close(insertStatement);
        }
        return insertedId;
    }
}
