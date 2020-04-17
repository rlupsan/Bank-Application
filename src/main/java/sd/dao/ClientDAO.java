package sd.dao;

import sd.dao.connection.ConnectionClose;
import sd.dao.connection.ConnectionFactory;
import sd.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientDAO {

    private static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    //Create
    private static final String insertStatementString =
            "INSERT INTO client (id, name, pnc, idCard, address) VALUES (?,?,?,?,?)";
    //Read
    private final static String findStatementString =
            "SELECT * FROM client where id = ?";
    //Update
    private static final String updateStatementString =
            "UPDATE client SET name =?, pnc=?, idCard=? ,address=? WHERE id = ?";
    //Delete
    private static final String deleteStatementString = "DELETE FROM client where id = ?";

    public static ArrayList<Client> getClientsList() {
        ArrayList<Client> clients = new ArrayList<>();
        try {
            Connection dbConnection = ConnectionFactory.getConnection();
            String query = "SELECT * FROM client";
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Client client = new Client(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("pnc"),
                        resultSet.getString("idCard"),
                        resultSet.getString("address")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public static Client findById(int id) {
        Client toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, id);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("name");
            String pnc = rs.getString("pnc");
            String idCard = rs.getString("idCard");
            String address = rs.getString("address");
            toReturn = new Client(name, pnc, idCard, address);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionClose.close(rs);
            ConnectionClose.close(findStatement);
        }
        return toReturn;
    }

    public static int insert(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, client.getId());
            insertStatement.setString(2, client.getName());
            insertStatement.setString(3, client.getPnc());
            insertStatement.setString(4, client.getIdCard());
            insertStatement.setString(5, client.getAddress());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
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
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
        } finally {
            ConnectionClose.close(deleteStatement);
        }
        return deletedId;
    }


    public static int update(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updatedId = -1;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, client.getName());
            updateStatement.setString(2, client.getPnc());
            updateStatement.setString(3, client.getIdCard());
            updateStatement.setString(4, client.getAddress());
            updateStatement.setInt(5, client.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:update" + e.getMessage());
        } finally {
            ConnectionClose.close(updateStatement);
        }
        return updatedId;
    }
}
