package com.school.model;

import java.util.Comparator;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Profile {
    @Id
    @GeneratedValue
    private long id; //идентификатор записи
    
    private long idlogin; //логин
    private String surname; // фамилия
    private String firstname; //имя
    private String lastname; //отчество
    private String sex; //пол
    private Date datebirth; //день рождения
    private String adres; //адрес
    private String education; //образование
    private String positions; //должность
    private String phone1; // телефон 1
    private String phone2; // телефон 2
    private String email1; //емаил 1
    private String email2; //емаил 2
    private String notes; //примечание
    private String photo; //фотография

    public Profile() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdlogin() {
        return idlogin;
    }

    public void setIdlogin(long idlogin) {
        this.idlogin = idlogin;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDatebirth() {
        return datebirth;
    }

    public void setDatebirth(Date datebirth) {
        this.datebirth = datebirth;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public static Comparator<Profile> COMPARE_BY_SURNAME = new Comparator<Profile>() {
        @Override
        public int compare(Profile one, Profile other) {
            return one.surname.compareToIgnoreCase(other.surname);
        }
    };
    
}
