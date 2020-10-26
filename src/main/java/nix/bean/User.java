package nix.bean;

import java.sql.Timestamp;
import java.sql.Date;

public class User extends AbstractEntity{

    private String login;
    private String password;

    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String birthDate;
    private String phoneNumber;
    private String email;
    private String maritalStatus;
    private String city;

    private String specialization;
    private String qualification;
    private String hiringDate;
    private Long roleId;


    public User(Long id, String login, String password, String firstName, String lastName, String gender, int age,
                String birthDate, String phoneNumber, String email, String maritalStatus, String city,
                String specialization, String qualification, String hiringDate, Long roleId,
                String createdBy, String modifiedBy, Timestamp createdDatetime,
                Timestamp modifiedDatetime) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.maritalStatus = maritalStatus;
        this.city = city;
        this.specialization = specialization;
        this.qualification = qualification;
        this.hiringDate = hiringDate;
        this.roleId = roleId;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdDatetime = createdDatetime;
        this.modifiedDatetime  = modifiedDatetime;
    }

    public User(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(String hiringDate) {
        this.hiringDate = hiringDate;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
