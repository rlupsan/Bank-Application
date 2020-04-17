package sd.dao;

import sd.dao.connection.ConnectionClose;
import sd.dao.connection.ConnectionFactory;
import sd.model.Account;
import sd.model.AccountType;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDAO {
    private static final Logger LOGGER = Logger.getLogger(AccountDAO.class.getName());
    //Create
    private static final String insertStatementString =
            "INSERT INTO account (id, type ,amountMoney, dateCreation, clientId) VALUES (?,?,?,?,?)";
    //Read
    private final static String findStatementString =
            "SELECT * FROM account where id = ?";
    //Update
    private static final String updateStatementString =
            "UPDATE account SET type = ?, amountMoney =? WHERE id = ?";
    //UpdateMoneyOnly
    private static final String updateMoneyOnlyStatementString =
            "UPDATE account SET amountMoney =? WHERE id = ?";
    //Delete
    private static final String deleteStatementString =
            "DELETE FROM account where id = ?";

    public static Account findById(int id) {
        Account returnAccount = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, id);
            rs = findStatement.executeQuery();
            rs.next();

            String type = rs.getString("type");
            double amountMoney = rs.getDouble("amountMoney");
            Date dateCreation = rs.getDate("dateCreation");
            int clientId = rs.getInt("clientId");
            returnAccount = new Account(id, AccountType.valueOf(type), amountMoney, dateCreation, clientId);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AccountDAO:findById " + e.getMessage());
        } finally {
            ConnectionClose.close(rs);
            ConnectionClose.close(findStatement);
        }
        return returnAccount;
    }

    public static int insert(Account account) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, account.getId());
            insertStatement.setString(2, account.getType().toString());
            insertStatement.setDouble(3, account.getAmountMoney());
            insertStatement.setDate(4, account.getDateCreation());
            insertStatement.setInt(5, account.getClientId());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AccountDAO:insert " + e.getMessage());
        } finally {
            ConnectionClose.close(insertStatement);
        }
        return insertedId;
    }

    public static int delete(int id) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        int deletedId = -1;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AccountDAO:delete " + e.getMessage());
        } finally {
            ConnectionClose.close(deleteStatement);
        }
        return deletedId;
    }


    public static int update(Account account) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updatedId = -1;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, String.valueOf(account.getType()));
            updateStatement.setDouble(2, account.getAmountMoney());
            updateStatement.setInt(3, account.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AccountDAO:update" + e.getMessage());
        } finally {
            ConnectionClose.close(updateStatement);
        }
        return updatedId;
    }

    public static int updateMoneyOnly(Account account) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updatedId = -1;
        try {
            updateStatement = dbConnection.prepareStatement(updateMoneyOnlyStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setDouble(1, account.getAmountMoney());
            updateStatement.setInt(2, account.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AccountDAO:updateMoneyOnly" + e.getMessage());
        } finally {
            ConnectionClose.close(updateStatement);
        }
        return updatedId;
    }

    public static ArrayList<Account> getAccountsList() {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            Connection dbConnection = ConnectionFactory.getConnection();
            String query = "SELECT * FROM account";
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Account account = new Account(
                        resultSet.getInt("id"),
                        AccountType.valueOf(resultSet.getString("type")),
                        resultSet.getDouble("amountMoney"),
                        resultSet.getDate("dateCreation"),
                        resultSet.getInt("clientId")
                );
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
