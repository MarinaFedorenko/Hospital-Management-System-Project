package nix.dao;

import nix.bean.Role;
import nix.util.Configs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDao extends  Dao<Role> {
    private static final Logger logger = LoggerFactory.getLogger(RoleDao.class);

//    private static Connection connection = null;
//    private static PreparedStatement preparedStatement = null;
//    private static ResultSet rs = null;

    public RoleDao() {}

//    public Connection getConnection() {
//        Connection connection = null;
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(URL, dbUser, dbPass);
//
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        logger.info("connected to database" );
//
//        return connection;
//    }
//
//    private void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
//        try {
//            rs.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
//            preparedStatement.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
//            connection.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    private void closeResources(Connection connection, PreparedStatement preparedStatement) {
//        try {
//            rs.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
//            preparedStatement.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    @Override
    public Role findById(Long id) {
        Role role = null;

        try  {
            connection = getConnection();

            preparedStatement = connection
                    .prepareStatement(" select * from hospitalmanagement.roles where id =? ;");
            preparedStatement.setLong(1, id);

            rs = preparedStatement.executeQuery();

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
        }finally {
            closeResources(connection, preparedStatement, rs);
        }
        logger.info("RoleDao.java:  method findById is executed");

        return role;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        try  {
            connection = getConnection();

            preparedStatement = connection.prepareStatement(" select * from roles ;");
            rs = preparedStatement.executeQuery();

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
        }finally {
            closeResources(connection, preparedStatement, rs);
        }
        logger.info("RoleDao.java:  method findAll is executed");

        return roles;
    }



    @Override
    public boolean update(Role role) {
        boolean updated = false;
        try  {
            connection = getConnection();
            preparedStatement = connection
                    .prepareStatement(" update hospitalmanagement.roles " +
                            "set name= ?, description =?, createdBy=?," +
                            "modifiedBy=?, createdDatetime=?, modifiedDatetime=? " +
                            "where id = ?; ");
            preparedStatement.setString(1, role.getName());
            preparedStatement.setString(2, role.getDescription());
            preparedStatement.setString(3, role.getCreatedBy());
            preparedStatement.setString(4, role.getModifiedBy());
            preparedStatement.setTimestamp(5, role.getCreatedDatetime());
            preparedStatement.setTimestamp(6, role.getModifiedDatetime());
            preparedStatement.setLong(7, role.getId());

            updated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeResources(connection, preparedStatement);
        }
        logger.info("RoleDao.java:  method update is executed");

        return updated;
    }

    @Override
    public boolean delete(Role role) {
        boolean deleted = false;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(" delete from hospitalmanagement.roles where id = ?; ");
            preparedStatement.setLong(1, role.getId());
            deleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeResources(connection, preparedStatement);
        }
        logger.info("RoleDao.java:  method delete is executed");

        return deleted;
    }

    @Override
    public void insert(Role role){
            try  {
                connection = getConnection();
                preparedStatement = connection
                        .prepareStatement("INSERT INTO hospitalmanagement.roles (name, description, createdBy,modifiedBy," +
                                "                        createdDatetime,modifiedDatetime) VALUES (?,?,?,?,?,?);");
                preparedStatement.setString(1, role.getName());
                preparedStatement.setString(2, role.getDescription());
                preparedStatement.setString(3, role.getCreatedBy());
                preparedStatement.setString(4, role.getModifiedBy());
                preparedStatement.setTimestamp(5, role.getCreatedDatetime());
                preparedStatement.setTimestamp(6, role.getModifiedDatetime());

                preparedStatement.executeUpdate();
            logger.info("RoleDao.java:  method insert is executed");
            } catch (SQLException  e) {
            e.printStackTrace();
        } finally {
                closeResources(connection, preparedStatement);
            }
    }
}
