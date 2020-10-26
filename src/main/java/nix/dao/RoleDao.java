package nix.dao;

import nix.bean.Role;
import nix.util.Configs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDao extends Configs implements Dao<Role> {
    private static final Logger logger = LoggerFactory.getLogger(RoleDao.class);


    public RoleDao() {}

    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, dbUser, dbPass);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("connected to database" );

        return connection;
    }

    @Override
    public Role findById(Long id) {
        Role role = null;

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection
                     .prepareStatement(" select * from hospitalmanagement.roles where id =? ;");) {
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long roleId = rs.getLong("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("createdDatetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modifiedDatetime");

                role = new Role(roleId,name, description, createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime);
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        logger.info("RoleDao.java:  method findById is executed");

        return role;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(" select * from roles ;");) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long roleId = rs.getLong("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("createdDatetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modifiedDatetime");

                roles.add(new Role(roleId,name, description, createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime));
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        logger.info("RoleDao.java:  method findAll is executed");

        return roles;
    }



    @Override
    public boolean update(Role role) {
        boolean updated = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(" update hospitalmanagement.roles " +
                             "set name= ?, description =?, createdBy=?," +
                             "modifiedBy=?, createdDatetime=?, modifiedDatetime=? " +
                             "where id = ?; ");) {
            statement.setString(1, role.getName());
            statement.setString(2, role.getDescription());
            statement.setString(3, role.getCreatedBy());
            statement.setString(4, role.getModifiedBy());
            statement.setTimestamp(5, role.getCreatedDatetime());
            statement.setTimestamp(6, role.getModifiedDatetime());
            statement.setLong(7, role.getId());

            updated = statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        logger.info("RoleDao.java:  method update is executed");

        return updated;
    }

    @Override
    public boolean delete(Role role) {
        boolean deleted = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(" delete from hospitalmanagement.roles where id = ?; ");) {
            statement.setLong(1, role.getId());
            deleted = statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        logger.info("RoleDao.java:  method delete is executed");

        return deleted;
    }

    @Override
    public void insert(Role role){
            try (Connection connection = getConnection();
        PreparedStatement statement = connection
                .prepareStatement("INSERT INTO hospitalmanagement.roles (name, description, createdBy,modifiedBy," +
                        "                        createdDatetime,modifiedDatetime) VALUES (?,?,?,?,?,?);")) {
            statement.setString(1, role.getName());
            statement.setString(2, role.getDescription());
            statement.setString(3, role.getCreatedBy());
            statement.setString(4, role.getModifiedBy());
            statement.setTimestamp(5, role.getCreatedDatetime());
            statement.setTimestamp(6, role.getModifiedDatetime());

            statement.executeUpdate();
            logger.info("RoleDao.java:  method insert is executed");
            } catch (SQLException  e) {
            e.printStackTrace();
        }
    }
}
