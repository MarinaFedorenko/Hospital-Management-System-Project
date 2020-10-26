package nix.dao;

import nix.bean.Patient;
import nix.util.Configs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDao extends Configs implements Dao<Patient>{
    private static final Logger logger = LoggerFactory.getLogger(PatientDao.class);

    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet rs = null;

    public PatientDao() {}

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

    @Override
    public Patient findById(Long id) {
        Patient patient = null;

        try {
            connection = getConnection();

            preparedStatement = connection
                     .prepareStatement(" select * from hospitalmanagement.patients where id =? ");
            preparedStatement.setLong(1, id);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long patientId = rs.getLong("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String birthdate = rs.getString("birthDate");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String maritalStatus = rs.getString("maritalStatus");
                String city = rs.getString("city");
                String address = rs.getString("address");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("created_Datetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modified_Datetime");
                patient = new Patient(patientId,firstName,lastName,gender,age,
                        birthdate, phoneNumber,email, maritalStatus,city,address,createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeResources(connection, preparedStatement, rs);
        }
        logger.info("PatientDao.java:  method findById is executed");

        return patient;
    }

    public List<Patient> findAllByLastnamePhonenumber(String lastname, String phonenumber) {
        List<Patient> patients = new ArrayList<>();

        try {
            connection = getConnection();

            preparedStatement = connection
                    .prepareStatement(" select * from patients where lastName = ? and " +
                            " phoneNumber = ? " );
            preparedStatement.setString(1, lastname);
            preparedStatement.setString(2, phonenumber);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long patientId = rs.getLong("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String birthdate = rs.getString("birthDate");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String maritalStatus = rs.getString("maritalStatus");
                String city = rs.getString("city");
                String address = rs.getString("address");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("created_Datetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modified_Datetime");
                patients.add(new Patient(patientId,firstName,lastName,gender,age,
                        birthdate, phoneNumber,email, maritalStatus,city, address, createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeResources(connection, preparedStatement, rs);
        }
        logger.info("PatientDao.java:  method findAllByLastnamePhonenumber is executed");

        return patients;
    }

    public List<Patient> findAllByLastname(String lastname) {
        List<Patient> patients = new ArrayList<>();
        try {
            connection = getConnection();


            preparedStatement = connection
                    .prepareStatement(" select * from patients where lastName = ? " );
            preparedStatement.setString(1, lastname);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long patientId = rs.getLong("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String birthdate = rs.getString("birthDate");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String maritalStatus = rs.getString("maritalStatus");
                String city = rs.getString("city");
                String address = rs.getString("address");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("created_Datetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modified_Datetime");
                patients.add(new Patient(patientId, firstName,lastName,gender,age,
                        birthdate, phoneNumber,email, maritalStatus,city,address,createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, rs);
        }
        logger.info("PatientDao.java:  method findAllByLastname is executed");

        return patients;
    }

    public List<Patient> findAllByPhonenumber(String phonenumber) {
        List<Patient> patients = new ArrayList<>();
        try {
            connection = getConnection();

            preparedStatement = connection
                    .prepareStatement(" select * from patients where phoneNumber = ? " );
            preparedStatement.setString(1, phonenumber);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long userId = rs.getLong("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String birthdate = rs.getString("birthDate");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String maritalStatus = rs.getString("maritalStatus");
                String city = rs.getString("city");
                String address = rs.getString("address");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("created_Datetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modified_Datetime");
                patients.add(new Patient(userId, firstName,lastName,gender,age,
                        birthdate, phoneNumber,email, maritalStatus,city,address,
                        createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, rs);

        }
        logger.info("PatientDao.java:  method findAllByPhonenumber is executed");

        return patients;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement(" select * from patients ;" );
             rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long patientId = rs.getLong("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String birthdate = rs.getString("birthDate");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String maritalStatus = rs.getString("maritalStatus");
                String city = rs.getString("city");
                String address = rs.getString("address");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("created_Datetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modified_Datetime");
                patients.add(new Patient(patientId,firstName,lastName,gender,age,
                        birthdate, phoneNumber,email, maritalStatus,city,address,createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeResources(connection, preparedStatement, rs);
        }
        logger.info("PatientDao.java:  method findAll is executed");

        return patients;
    }


    @Override
    public void insert(Patient patient) {
        try {
            connection = getConnection();
            preparedStatement = connection
                     .prepareStatement(" INSERT INTO hospitalmanagement.patients (firstName, lastName," +
                             " gender, age, birthDate, phoneNumber," +
                             "email, maritalStatus, city,address, createdBy, modifiedBy," +
                             "created_Datetime, modified_Datetime) VALUES "
                             + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            preparedStatement.setString(1, patient.getFirstName());
            preparedStatement.setString(2, patient.getLastName());
            preparedStatement.setString(3, patient.getGender());
            preparedStatement.setInt(4, patient.getAge());
            preparedStatement.setString(5, patient.getBirthDate());
            preparedStatement.setString(6, patient.getPhoneNumber());
            preparedStatement.setString(7, patient.getEmail());
            preparedStatement.setString(8, patient.getMaritalStatus());
            preparedStatement.setString(9, patient.getCity());
            preparedStatement.setString(10, patient.getAddress());
            preparedStatement.setString(11, patient.getCreatedBy());
            preparedStatement.setString(12, patient.getModifiedBy());
            preparedStatement.setTimestamp(13, patient.getCreatedDatetime());
            preparedStatement.setTimestamp(14, patient.getModifiedDatetime());

            preparedStatement.executeUpdate();
            logger.info("PatientDao.java:  method insert is executed");

        } catch (SQLException  e) {
            e.printStackTrace();
        }
        finally {
            closeResources(connection, preparedStatement);
        }
    }

    @Override
    public boolean update(Patient patient) {
        boolean updated = false;
        try {
            connection = getConnection();
            if(!patient.getFirstName().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.patients " +
                                " set firstName =? where id = ? ;");
                preparedStatement.setString(1, patient.getFirstName());
                preparedStatement.setLong(2, patient.getId());
                updated = preparedStatement.executeUpdate() > 0;

            }
            if(!patient.getLastName().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.patients " +
                                " set lastName =? where id = ? ;");
                preparedStatement.setString(1, patient.getLastName());
                preparedStatement.setLong(2, patient.getId());
                updated = preparedStatement.executeUpdate() > 0;

            }
            if(!patient.getGender().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.patients " +
                                " set gender =? where id = ? ;");
                preparedStatement.setString(1, patient.getGender());
                preparedStatement.setLong(2, patient.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(patient.getAge()!=0){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.patients " +
                                " set age =? where id = ? ;");
                preparedStatement.setInt(1, patient.getAge());
                preparedStatement.setLong(2, patient.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(!patient.getBirthDate().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.patients " +
                                " set birthDate =? where id = ? ;");
                preparedStatement.setString(1, patient.getBirthDate());
                preparedStatement.setLong(2, patient.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(!patient.getPhoneNumber().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.patients " +
                                " set phoneNumber =? where id = ? ;");
                preparedStatement.setString(1, patient.getPhoneNumber());
                preparedStatement.setLong(2, patient.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(!patient.getEmail().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.patients " +
                                " set email =? where id = ? ;");
                preparedStatement.setString(1, patient.getEmail());
                preparedStatement.setLong(2, patient.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(!patient.getMaritalStatus().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.patients " +
                                " set maritalStatus =? where id = ? ;");
                preparedStatement.setString(1, patient.getMaritalStatus());
                preparedStatement.setLong(2, patient.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(!patient.getCity().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.patients " +
                                " set city =? where id = ? ;");
                preparedStatement.setString(1, patient.getCity());
                preparedStatement.setLong(2, patient.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(!patient.getAddress().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.patients " +
                                " set address =? where id = ? ;");
                preparedStatement.setString(1, patient.getAddress());
                preparedStatement.setLong(2, patient.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }




//            preparedStatement = connection
//                     .prepareStatement(" update hospitalmanagement.patients " +
//                             "set firstName=?, lastName=?," +
//                             "gender=?, age=?, birthDate=?, phoneNumber=?," +
//                             "email=?, maritalStatus=?, city=?,address=?, createdBy=?, modifiedBy=?," +
//                             "created_Datetime=?, modified_Datetime=?" +
//                             "where id = ?;");
//            preparedStatement.setString(1, patient.getFirstName());
//            preparedStatement.setString(2, patient.getLastName());
//            preparedStatement.setString(3, patient.getGender());
//            preparedStatement.setInt(4, patient.getAge());
//            preparedStatement.setDate(5, (java.sql.Date) patient.getBirthDate());
//            preparedStatement.setString(6, patient.getPhoneNumber());
//            preparedStatement.setString(7, patient.getEmail());
//            preparedStatement.setString(8, patient.getMaritalStatus());
//            preparedStatement.setString(9, patient.getCity());
//            preparedStatement.setString(10, patient.getAddress());
//            preparedStatement.setString(11, patient.getCreatedBy());
//            preparedStatement.setString(12, patient.getModifiedBy());
//            preparedStatement.setTimestamp(13, patient.getCreatedDatetime());
//            preparedStatement.setTimestamp(14, patient.getModifiedDatetime());
//            preparedStatement.setLong(15, patient.getId());
//
//            updated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement);
        }
        logger.info("PatientDao.java:  method update is executed");

        return updated;
    }

    @Override
    public boolean delete(Patient patient) {
        boolean deleted = false;
        try {
            connection = getConnection();
            preparedStatement = connection
                     .prepareStatement(" delete from hospitalmanagement.patients where id = ?; ");
            preparedStatement.setLong(1, patient.getId());
            deleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement);
        }
        logger.info("PatientDao.java:  method delete is executed");

        return deleted;
    }
}

