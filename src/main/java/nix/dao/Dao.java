package nix.dao;

import java.sql.*;
import java.util.List;

public abstract class Dao<T> {
    protected String dbHost = "localhost";
    protected String dbPort = "3306";
    protected String dbName = "hospitalmanagement";
    protected String dbUser = "root";
    protected String dbPass = "root";
    /*url:  jdbc:mysql://localhost:3306/hospitalmanagement?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC*/

    protected String URL = "jdbc:mysql://localhost:3306/hospitalmanagement?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC";

    protected  static Connection connection = null;
    protected static PreparedStatement preparedStatement = null;
    protected static ResultSet rs = null;

    protected Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, dbUser, dbPass);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
//        logger.info("connected to database" );
        return connection;
    }

    protected void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        try {
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void closeResources(Connection connection, PreparedStatement preparedStatement) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    abstract List<T> findAll();
    abstract T findById(Long id);
    abstract void insert(T entity);
    abstract boolean update(T entity);
    abstract boolean delete(T entity);




}
