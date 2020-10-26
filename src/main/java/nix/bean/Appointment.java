package nix.bean;

import java.sql.Timestamp;
import java.sql.Date;

public class Appointment extends AbstractEntity{

    private Long doctorId;
    private Long patientId;
    private String time;
    private String date;

    public Appointment(Long id, Long doctorId, Long patientId, String time, String date,
                       String createdBy, String modifiedBy, Timestamp createdDatetime,
                       Timestamp modifiedDatetime) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.time = time;
        this.date = date;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdDatetime = createdDatetime;
        this.modifiedDatetime = modifiedDatetime;
    }

    public Appointment(){}

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
