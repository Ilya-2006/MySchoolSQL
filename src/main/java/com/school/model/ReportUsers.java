package com.school.model;


import java.util.Comparator;

public class ReportUsers {
    private String login;
    private UserRole role;
    private String firstname; //ФИО
    private String letter; //Класс
    private String science; //Предмет обучения для учителя

    public ReportUsers() {
    }

    public ReportUsers(String login, UserRole role, String firstname, String letter, String science) {
        this.login = login;
        this.role = role;
        this.firstname = firstname;
        this.letter = letter;
        this.science = science;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getScience() {
        return science;
    }

    public void setScience(String science) {
        this.science = science;
    }

    
    public static Comparator<ReportUsers> COMPARE_BY_FIRSTNAME = new Comparator<ReportUsers>() {
        @Override
        public int compare(ReportUsers one, ReportUsers other) {
            return one.firstname.compareToIgnoreCase(other.firstname);
        }
    };   
    public static Comparator<ReportUsers> COMPARE_BY_LOGIN = new Comparator<ReportUsers>() {
        @Override
        public int compare(ReportUsers one, ReportUsers other) {
            return one.login.compareToIgnoreCase(other.login);
        }
    };
    public static Comparator<ReportUsers> COMPARE_BY_LETTER = new Comparator<ReportUsers>() {
        @Override
        public int compare(ReportUsers one, ReportUsers other) {
            return one.letter.compareToIgnoreCase(other.letter);
        }
    };    
    
}
