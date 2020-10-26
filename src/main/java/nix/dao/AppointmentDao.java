package nix.dao;

import nix.bean.Appointment;
import nix.util.Configs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao extends Configs implements Dao<Appointment> {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentDao.class);

    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet rs = null;

    public AppointmentDao() {}

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

    @Override
    public Appointment findById(Long id) {
        Appointment appointment = null;

        try {
            connection = getConnection();

            preparedStatement = connection
                     .prepareStatement(" select * from hospitalmanagement.appointments where id =? ");
            preparedStatement.setLong(1, id);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long appointmentId = rs.getLong("id");
                Long doctorId = rs.getLong("doctorId");
                Long patientId = rs.getLong("patientId");
                String time = rs.getString("time");
                String date = rs.getString("date");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("createdDatetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modifiedDatetime");
                appointment = new Appointment(appointmentId, doctorId,patientId,
                        time,date,createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime);
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, rs);
        }
        logger.info("AppointmentDao.java:  method findById is executed");

        return appointment;
    }

    public List<Appointment> findByDoctorId(Long doctorId) {
        List<Appointment> appointments = new ArrayList<>();

        try {
            connection = getConnection();

            preparedStatement = connection
                    .prepareStatement(" select * from hospitalmanagement.appointments where doctorId =? ");
            preparedStatement.setLong(1, doctorId);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long appointmentId = rs.getLong("id");
                Long doctorId1 = rs.getLong("doctorId");
                Long patientId = rs.getLong("patientId");
                String time = rs.getString("time");
                String date = rs.getString("date");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("createdDatetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modifiedDatetime");
                appointments.add(new Appointment(appointmentId, doctorId1,patientId,
                        time,date,createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime));
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, rs);
        }
        logger.info("AppointmentDao.java:  method findById is executed");

        return appointments;
    }

    public List<Appointment> findByPatientId(Long patientId) {
        List<Appointment> appointments = new ArrayList<>();

        try {
            connection = getConnection();

            preparedStatement = connection
                    .prepareStatement(" select * from hospitalmanagement.appointments where patientId =? ");
            preparedStatement.setLong(1, patientId);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long appointmentId = rs.getLong("id");
                Long doctorId = rs.getLong("doctorId");
                Long patientId1 = rs.getLong("patientId");
                String time = rs.getString("time");
                String date = rs.getString("date");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("createdDatetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modifiedDatetime");
                appointments.add(new Appointment(appointmentId, doctorId,patientId1,
                        time,date,createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime));
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, rs);
        }
        logger.info("AppointmentDao.java:  method findById is executed");

        return appointments;
    }

    @Override
    public List<Appointment> findAll() {
        List<Appointment> appointments = new ArrayList<>();
        try {
            connection = getConnection();

            preparedStatement = connection
                     .prepareStatement(" select * from hospitalmanagement.appointments " );
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long appointmentId = rs.getLong("id");
                Long doctorId = rs.getLong("doctorId");
                Long patientId = rs.getLong("patientId");
                String time = rs.getString("time");
                String date = rs.getString("date");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("createdDatetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modifiedDatetime");
                appointments.add(new Appointment(appointmentId, doctorId,patientId,
                        time,date,createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime));
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, rs);
        }
        logger.info("AppointmentDao.java:  method findAll is executed");

        return appointments;
    }


    public List<Appointment> findAllByDoctorIdPatientId(Long doctorId, Long patientId) {
        List<Appointment> appointments = new ArrayList<>();
        try {
            connection = getConnection();

            preparedStatement = connection
                    .prepareStatement(" select * from hospitalmanagement.appointments where doctorId=? and " +
                            " patientId = ? ;" );
            preparedStatement.setLong(1, doctorId);
            preparedStatement.setLong(2, patientId);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long appointmentId = rs.getLong("id");
                Long doctorId1 = rs.getLong("doctorId");
                Long patientId1 = rs.getLong("patientId");
                String time = rs.getString("time");
                String date = rs.getString("date");
                String createdBy = rs.getString("createdBy");
                String modifiedBy = rs.getString("modifiedBy");
                Timestamp createdDatetime = rs.getTimestamp("createdDatetime");
                Timestamp modifiedDatetime = rs.getTimestamp("modifiedDatetime");
                appointments.add(new Appointment(appointmentId, doctorId1,patientId1,
                        time,date,createdBy,modifiedBy,
                        createdDatetime,modifiedDatetime));
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, rs);
        }
        logger.info("AppointmentDao.java:  method findAll is executed");

        return appointments;
    }

    @Override
    public void insert(Appointment appointment) {
        try {
            connection = getConnection();
            preparedStatement = connection
                     .prepareStatement(" INSERT INTO hospitalmanagement.appointments (doctorId, patientId," +
                             "time, date,  createdBy, modifiedBy," +
                             "createdDatetime, modifiedDatetime) VALUES "
                             + " (?,?,?,?,?,?,?,?);");
            preparedStatement.setLong(1, appointment.getDoctorId());
            preparedStatement.setLong(2, appointment.getPatientId());
            preparedStatement.setString(3, appointment.getTime());
            preparedStatement.setString(4,  appointment.getDate());
            preparedStatement.setString(5, appointment.getCreatedBy());
            preparedStatement.setString(6, appointment.getModifiedBy());
            preparedStatement.setTimestamp(7, appointment.getCreatedDatetime());
            preparedStatement.setTimestamp(8, appointment.getModifiedDatetime());

            preparedStatement.executeUpdate();
            logger.info("AppointmentDao.java: method insert is executed");

        } catch (SQLException  e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    @Override
    public boolean update(Appointment appointment) {
        boolean updated = false;
        try {
            connection = getConnection();

            if(appointment.getDoctorId()!=0){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.appointments " +
                                " set doctorId =? where id = ? ;");
                preparedStatement.setLong(1, appointment.getDoctorId());
                preparedStatement.setLong(2, appointment.getId());
                updated = preparedStatement.executeUpdate() > 0;

            }

            if(appointment.getPatientId()!=0){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.appointments " +
                                " set patientId =? where id = ? ;");
                preparedStatement.setLong(1, appointment.getPatientId());
                preparedStatement.setLong(2, appointment.getId());
                updated = preparedStatement.executeUpdate() > 0;

            }
            if(!appointment.getTime().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.appointments " +
                                " set time =? where id = ? ;");
                preparedStatement.setString(1, appointment.getTime());
                preparedStatement.setLong(2, appointment.getId());
                updated = preparedStatement.executeUpdate() > 0;

            }
            if(!appointment.getDate().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.appointments " +
                                " set date =? where id = ? ;");
                preparedStatement.setString(1, appointment.getDate());
                preparedStatement.setLong(2, appointment.getId());
                updated = preparedStatement.executeUpdate() > 0;

            }
            if(!appointment.getModifiedBy().equals("")){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.appointments " +
                                " set modifiedBy =? where id = ? ;");
                preparedStatement.setString(1, appointment.getModifiedBy());
                preparedStatement.setLong(2, appointment.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }
            if(appointment.getModifiedDatetime()!=null){
                preparedStatement =  connection
                        .prepareStatement(" update hospitalmanagement.appointments " +
                                " set modifiedDatetime =? where id = ? ;");
                preparedStatement.setTimestamp(1, appointment.getModifiedDatetime());
                preparedStatement.setLong(2, appointment.getId());
                updated = preparedStatement.executeUpdate() > 0;
            }

//            preparedStatement = connection
//                     .prepareStatement(" update hospitalmanagement.appointments " +
//                             "set doctorId= ?, patientId =?, time=?, date=?," +
//                             " createdBy=?, modifiedBy=?, createdDatetime=?, modifiedDatetime=? " +
//                             "where id = ?;");
//            preparedStatement.setLong(1, appointment.getDoctorId());
//            preparedStatement.setLong(2, appointment.getPatientId());
//            preparedStatement.setString(3, appointment.getTime());
//            preparedStatement.setString(4, appointment.getDate());
//            preparedStatement.setString(5, appointment.getCreatedBy());
//            preparedStatement.setString(6, appointment.getModifiedBy());
//            preparedStatement.setTimestamp(7, appointment.getCreatedDatetime());
//            preparedStatement.setTimestamp(8, appointment.getModifiedDatetime());
//            preparedStatement.setLong(9, appointment.getId());
//
//            updated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement);
        }
        logger.info("AppointmentDao.java: method update is executed");

        return updated;
    }

    @Override
    public boolean delete(Appointment appointment) {
        boolean deleted = false;
        try {
            connection = getConnection();
             preparedStatement = connection
                     .prepareStatement(" delete from hospitalmanagement.appointments where id = ?; ");
            preparedStatement.setLong(1, appointment.getId());
            deleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement);
        }
        logger.info("AppointmentDao.java: method delete is executed");

        return deleted;
    }


}
