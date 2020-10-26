package nix.bean;

import java.sql.Timestamp;
import java.sql.Date;

public class Patient extends AbstractEntity{

    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String birthDate;
    private String phoneNumber;
    private String email;
    private String maritalStatus;
    private String city;
    private String address;

    public Patient(Long id, String firstName, String lastName, String gender, int age, String birthDate, String phoneNumber,
                   String email, String maritalStatus, String city, String address,
                   String createdBy, String modifiedBy, Timestamp createdDatetime,
                   Timestamp modifiedDatetime) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.maritalStatus = maritalStatus;
        this.city = city;
        this.address = address;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdDatetime = createdDatetime;
        this.modifiedDatetime = modifiedDatetime;
    }

    public Patient(){}

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
