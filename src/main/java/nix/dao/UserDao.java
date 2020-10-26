package nix.dao;

import nix.bean.User;
import nix.util.Configs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends Configs implements Dao<User>{
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    public UserDao() {}

    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet rs = null;

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

    private void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
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

    private void closeResources(Connection connection, PreparedStatement preparedStatement) {
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
    }

    public User login(String username, String password){
        User user = null;
        try {
             connection = getConnection();

              preparedStatement = connection
                     .prepareStatement(" select * from hospitalmanagement.users " +
                             "where login=? and password=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long userId = rs.getLong("id");
                String login = rs.getString("login");
                String password1 = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String birthdate = rs.getString("birthDate");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String maritalStatus = rs.getString("maritalStatus");
                String city = rs.getString("city");
                String specification = rs.getString("specification");
                String qualification = rs.getString("qualification");
                String hiringDate = rs.getString("hiringDate");
                Long roleId = rs.getLong("roleId");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("createdDatetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modifiedDatetime");
                user = new User(userId, login,password1,firstName,lastName,gender,age,
                        birthdate, phoneNumber,email, maritalStatus,city,specification,
                        qualification,hiringDate,roleId,createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
           closeResources(connection, preparedStatement, rs);
        }
        logger.info("UserDao.java:  method login is executed");

        return user;
    }

    @Override
    public User findById(Long id) {
        User user = null;

        try {
            connection = getConnection();

             preparedStatement = connection
                     .prepareStatement(" select * from hospitalmanagement.users where id =? ");
            preparedStatement.setLong(1, id);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long userId = rs.getLong("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String birthdate = rs.getString("birthDate");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String maritalStatus = rs.getString("maritalStatus");
                String city = rs.getString("city");
                String specification = rs.getString("specification");
                String qualification = rs.getString("qualification");
                String hiringDate = rs.getString("hiringDate");
                Long roleId = rs.getLong("roleId");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("createdDatetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modifiedDatetime");
                user = new User(userId, login,password,firstName,lastName,gender,age,
                        birthdate, phoneNumber,email, maritalStatus,city,specification,
                        qualification,hiringDate,roleId,createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, rs);

        }
        logger.info("UserDao.java:  method findById is executed");

        return user;
    }


    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement(" select * from users " );

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long userId = rs.getLong("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String birthdate = rs.getString("birthDate");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String maritalStatus = rs.getString("maritalStatus");
                String city = rs.getString("city");
                String specification = rs.getString("specification");
                String qualification = rs.getString("qualification");
                String hiringDate = rs.getString("hiringDate");
                Long roleId = rs.getLong("roleId");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("createdDatetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modifiedDatetime");
                users.add(new User(userId, login,password,firstName,lastName,gender,age,
                        birthdate, phoneNumber,email, maritalStatus,city,specification,
                        qualification,hiringDate,roleId,createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeResources(connection, preparedStatement, rs);
        }
        logger.info("UserDao.java:  method findAll is executed");

        return users;
    }

    public List<User> findAllByRole(Long id) {
        List<User> users = new ArrayList<>();
        try {
            connection = getConnection();


            preparedStatement = connection.prepareStatement(" select * from users where roleId = ? " );
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long userId = rs.getLong("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String birthdate = rs.getString("birthDate");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String maritalStatus = rs.getString("maritalStatus");
                String city = rs.getString("city");
                String specification = rs.getString("specification");
                String qualification = rs.getString("qualification");
                String hiringDate = rs.getString("hiringDate");
                Long roleId = rs.getLong("roleId");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("createdDatetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modifiedDatetime");
                users.add(new User(userId, login,password,firstName,lastName,gender,age,
                        birthdate, phoneNumber,email, maritalStatus,city,specification,
                        qualification,hiringDate,roleId,createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, rs);

        }
        logger.info("UserDao.java:  method findAllByRole is executed");

        return users;
    }

    public List<User> findAllByLastnamePhonenumberRoleId(String lastname, String phonenumber, Long roleId) {
        List<User> users = new ArrayList<>();

        try {
            connection = getConnection();

            preparedStatement = connection
                     .prepareStatement(" select * from users where lastName = ? and " +
                             " phoneNumber = ? and roleId = ? " );
            preparedStatement.setString(1, lastname);
            preparedStatement.setString(2, phonenumber);
            preparedStatement.setLong(3, roleId);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long userId = rs.getLong("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String birthdate = rs.getString("birthDate");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String maritalStatus = rs.getString("maritalStatus");
                String city = rs.getString("city");
                String specification = rs.getString("specification");
                String qualification = rs.getString("qualification");
                String hiringDate = rs.getString("hiringDate");
                Long roleId1 = rs.getLong("roleId");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("createdDatetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modifiedDatetime");
                users.add(new User(userId, login,password,firstName,lastName,gender,age,
                        birthdate, phoneNumber,email, maritalStatus,city,specification,
                        qualification,hiringDate,roleId1,createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeResources(connection, preparedStatement, rs);
        }
        logger.info("UserDao.java:  method findAllByLastnamePhonenumberRoleId is executed");

        return users;
    }

    public List<User> findAllByLastnameRoleId(String lastname, Long roleId) {
        List<User> users = new ArrayList<>();

        try {
            connection = getConnection();


             preparedStatement = connection
                     .prepareStatement(" select * from users where lastName = ? and " +
                             " roleId = ? " );
            preparedStatement.setString(1, lastname);
            preparedStatement.setLong(2, roleId);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long userId = rs.getLong("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String birthdate = rs.getString("birthDate");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String maritalStatus = rs.getString("maritalStatus");
                String city = rs.getString("city");
                String specification = rs.getString("specification");
                String qualification = rs.getString("qualification");
                String hiringDate = rs.getString("hiringDate");
                Long roleId1 = rs.getLong("roleId");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("createdDatetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modifiedDatetime");
                users.add(new User(userId, login,password,firstName,lastName,gender,age,
                        birthdate, phoneNumber,email, maritalStatus,city,specification,
                        qualification,hiringDate,roleId1,createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, rs);
        }
        logger.info("UserDao.java:  method findAllByLastnameRoleId is executed");

        return users;
    }

    public List<User> findAllByPhonenumberRoleId(String phonenumber, Long roleId) {
        List<User> users = new ArrayList<>();
        try {
            connection = getConnection();

            preparedStatement = connection
                     .prepareStatement(" select * from users where phoneNumber = ? and " +
                             " roleId = ? " );
            preparedStatement.setString(1, phonenumber);
            preparedStatement.setLong(2, roleId);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long userId = rs.getLong("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String birthdate = rs.getString("birthDate");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String maritalStatus = rs.getString("maritalStatus");
                String city = rs.getString("city");
                String specification = rs.getString("specification");
                String qualification = rs.getString("qualification");
                String hiringDate = rs.getString("hiringDate");
                Long roleId1 = rs.getLong("roleId");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("createdDatetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modifiedDatetime");
                users.add(new User(userId, login,password,firstName,lastName,gender,age,
                        birthdate, phoneNumber,email, maritalStatus,city,specification,
                        qualification,hiringDate,roleId1,createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, rs);

        }
        logger.info("UserDao.java:  method findAllByPhonenumberRoleId is executed");

        return users;
    }




    @Override
    public void insert(User user) {

        try {
            connection = getConnection();
            preparedStatement = connection
                     .prepareStatement(" INSERT INTO hospitalmanagement.users (login, password," +
                             "firstName, lastName, gender, age, birthDate, phoneNumber," +
                             "email, maritalStatus, city, specification, qualification," +
                             "hiringDate, roleId, createdBy, modifiedBy," +
                             "createdDatetime, modifiedDatetime) VALUES "
                     + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getGender());
            preparedStatement.setInt(6, user.getAge());
            preparedStatement.setString(7, user.getBirthDate());
            preparedStatement.setString(8, user.getPhoneNumber());
            preparedStatement.setString(9, user.getEmail());
            preparedStatement.setString(10, user.getMaritalStatus());
            preparedStatement.setString(11, user.getCity());
            preparedStatement.setString(12, user.getSpecialization());
            preparedStatement.setString(13, user.getQualification());
            preparedStatement.setString(14,  user.getHiringDate());
            preparedStatement.setLong(15, user.getRoleId());
            preparedStatement.setString(16, user.getCreatedBy());
            preparedStatement.setString(17, user.getModifiedBy());
            preparedStatement.setTimestamp(18, user.getCreatedDatetime());
            preparedStatement.setTimestamp(19, user.getModifiedDatetime());

            preparedStatement.executeUpdate();
            logger.info("UserDao.java:  method insert is executed");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeResources(connection, preparedStatement);
        }
    }




    @Override
    public boolean update(User user) {
        boolean updated = false;
        try {
            connection = getConnection();
            if(!user.getLogin().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.users " +
                                " set login =? where id = ? ;");
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setLong(2, user.getId());
                updated = preparedStatement.executeUpdate() > 0;

            }
            if(!user.getFirstName().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.users " +
                                " set firstName = ? where id = ? ;");
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setLong(2, user.getId());
                updated = preparedStatement.executeUpdate() > 0;

            }
            if(!user.getLastName().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.users " +
                                " set lastName = ? " +
                                " where id = ? ;");
                preparedStatement.setString(1, user.getLastName());
                preparedStatement.setLong(2, user.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(!user.getGender().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.users " +
                                " set gender = ? " +
                                " where id = ? ;");
                preparedStatement.setString(1, user.getGender());
                preparedStatement.setLong(2, user.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(user.getAge()!=0){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.users " +
                                "set age = ? " +
                                "where id = ? ;");
                preparedStatement.setInt(1, user.getAge());
                preparedStatement.setLong(2, user.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(!user.getBirthDate().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.users " +
                                "set birthDate = ? " +
                                "where id = ? ;");
                preparedStatement.setString(1,  user.getBirthDate());
                preparedStatement.setLong(2, user.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(!user.getPhoneNumber().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.users " +
                                "set phoneNumber = ? " +
                                "where id = ? ;");
                preparedStatement.setString(1, user.getPhoneNumber());
                preparedStatement.setLong(2, user.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(!user.getEmail().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.users " +
                                "set email = ? " +
                                "where id = ? ;");
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setLong(2, user.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(!user.getMaritalStatus().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.users " +
                                "set maritalStatus = ? " +
                                "where id = ? ;");
                preparedStatement.setString(1, user.getMaritalStatus());
                preparedStatement.setLong(2, user.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(!user.getCity().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.users " +
                                "set city = ? " +
                                "where id = ? ;");
                preparedStatement.setString(1, user.getCity());
                preparedStatement.setLong(2, user.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(!user.getSpecialization().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.users " +
                                "set specification = ? " +
                                "where id = ? ;");
                preparedStatement.setString(1, user.getSpecialization());
                preparedStatement.setLong(2, user.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(!user.getQualification().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.users " +
                                "set qualification = ? " +
                                "where id = ? ;");
                preparedStatement.setString(1, user.getQualification());
                preparedStatement.setLong(2, user.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(!user.getHiringDate().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.users " +
                                "set hiringDate = ? " +
                                "where id = ? ;");
                preparedStatement.setString(1, user.getHiringDate());
                preparedStatement.setLong(2, user.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }


//             PreparedStatement st1 = connection
//                     .prepareStatement(" update hospitalmanagement.users " +
//                             "set login = ? , firstName=?, lastName=?," +
//                             "gender=?, age=?, birthDate=?, phoneNumber=?," +
//                             "email=?, maritalStatus=?, city=?, specification=?," +
//                             "qualification=?, hiringDate=? " +
//                             "where id = ?;");
//
//
//            statement.setString(1, user.getLogin());
//            statement.setString(2, user.getFirstName());
//            statement.setString(3, user.getLastName());
//            statement.setString(4, user.getGender());
//            statement.setInt(5, user.getAge());
//            statement.setDate(6, (java.sql.Date) user.getBirthDate());
//            statement.setString(7, user.getPhoneNumber());
//            statement.setString(8, user.getEmail());
//            statement.setString(9, user.getMaritalStatus());
//            statement.setString(10, user.getCity());
//            statement.setString(11, user.getSpecialization());
//            statement.setString(12, user.getQualification());
//            statement.setDate(13,(java.sql.Date) user.getHiringDate());
//            statement.setLong(14, user.getId());
//
//            updated = statement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement);
        }
        logger.info("UserDao.java:  method update is executed");

        return updated;
    }

    public boolean changePassword(String username, String newPassword) {
        boolean updated = false;
        try {
            connection = getConnection();
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.users " +
                                " set password =? where login = ? ;");
                preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, username);
                updated = preparedStatement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement);
        }
        logger.info("UserDao.java:  method changePassword is executed");

        return updated;
    }

    @Override
    public boolean delete(User user) {
        boolean deleted = false;
        try {
            connection = getConnection();

             preparedStatement = connection
                     .prepareStatement(" delete from hospitalmanagement.users where id = ?; ") ;
            preparedStatement.setLong(1, user.getId());
            deleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement);
        }
        logger.info("UserDao.java:  method delete is executed");

        return deleted;
    }

}
