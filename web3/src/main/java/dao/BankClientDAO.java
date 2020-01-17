package dao;

import com.sun.deploy.util.SessionState;
import model.BankClient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BankClientDAO {

    private Connection connection;

    public BankClientDAO(Connection connection) {
        this.connection = connection;
    }

    public List<BankClient> getAllBankClient() throws SQLException {
        try {
            List<BankClient> bankClients = new ArrayList<>();
            Statement statement = connection.createStatement();
            //language=sql
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bank_client");
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                Long money = resultSet.getLong("money");

                BankClient bankClient = new BankClient(id, name, password, money);
                bankClients.add(bankClient);

            }
            resultSet.close();
            statement.close();
            return bankClients;
        } catch (SQLException ignore) {

        }
        return null;
    }



    public boolean validateClient(String name, String password) throws SQLException {
        BankClient bankClient = getClientByName(name);


        if (bankClient != null && bankClient.getName().equals(name) && bankClient.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public void updateClientsMoney(String name, String password, Long transactValue) throws SQLException {
        if (validateClient(name, password)) {
            PreparedStatement statement = connection.prepareStatement("UPDATE bank_client SET money = money +? WHERE name =?");
            statement.setLong(1, transactValue);
            statement.setString(2, name);
            statement.executeUpdate();
            statement.close();
        }
    }

    public BankClient getClientById(long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM bank_client WHERE id=?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            Long money = resultSet.getLong("money");
            resultSet.close();
            statement.close();
            return new BankClient(id, name, password, money);

        }
        resultSet.close();
        statement.close();
        return null;
    }

    public boolean isClientHasSum(String name, Long expectedSum) throws SQLException {
        BankClient bankClient = getClientByName(name);
        if (bankClient != null) {
            return bankClient.getMoney() >= expectedSum;
        }
        return false;
    }


    public long getClientIdByName(String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM bank_client WHERE name=?");
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        Long id = resultSet.getLong(1);
        resultSet.close();
        statement.close();
        return id;
    }

    public BankClient getClientByName(String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM bank_client WHERE name=?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String password = resultSet.getString("password");
            Long money = resultSet.getLong("money");
            resultSet.close();
            statement.close();
            return new BankClient(id, name, password, money);
        }
        resultSet.close();
        statement.close();
        return null;
    }


    public void addClient(BankClient client) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO bank_client(name,password,money) VALUES (?,?,?)");
//        statement.setLong(1, client.getId());
        statement.setString(1, client.getName());
        statement.setString(2, client.getPassword());
        statement.setLong(3, client.getMoney());
        statement.execute();
        statement.close();
    }

    public void deleteClient(String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM bank_client WHERE name =?");
        statement.setString(1, name);
        statement.execute();
        statement.close();
    }


    public void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE if not exists  bank_client (id BIGSERIAL PRIMARY KEY UNIQUE, name varchar(256), password varchar(256), money BIGINT)");
        statement.close();
    }

    public void dropTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS bank_client");
        statement.close();
    }
}
