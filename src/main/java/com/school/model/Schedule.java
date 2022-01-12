package com.school.model;

import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private long id;
    private String nameteacher;
    private String science;
    private int year; 
    private String letter;
    private String nambercabinet;
    private String namberlesson;
    private String intweek;
    private String weekday;
    private String notes;

    public Schedule(String nameteacher, String science, int year, String letter, String nambercabinet, String namberlesson, String intweek, String weekday, String notes) {
        this.nameteacher = nameteacher;
        this.science = science;
        this.year = year;
        this.letter = letter;
        this.nambercabinet = nambercabinet;
        this.namberlesson = namberlesson;
        this.intweek = intweek;
        this.weekday = weekday;
        this.notes = notes;
    }

    public Schedule() {
    }
    
    public long getId() {
        return id; 
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getScience() {
        return science;
    }

    public void setScience(String science) {
        this.science = science;
    }

    public String getNameteacher() {
        return nameteacher;
    }

    public void setNameteacher(String nameteacher) {
        this.nameteacher = nameteacher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getIntweek() {
        return intweek;
    }

    public void setIntweek(String intweek) {
        this.intweek = intweek;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getNambercabinet() {
        return nambercabinet;
    }

    public void setNambercabinet(String nambercabinet) {
        this.nambercabinet = nambercabinet;
    }

    public String getNamberlesson() {
        return namberlesson;
    }

    public void setNamberlesson(String namberlesson) {
        this.namberlesson = namberlesson;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public static Comparator<Schedule> COMPARE_BY_NAMBERLESSON = new Comparator<Schedule>() {
    @Override
    public int compare(Schedule one, Schedule other) {
        return one.namberlesson.compareToIgnoreCase(other.namberlesson);
    }
    };
    
    public static Comparator<Schedule> COMPARE_BY_WEEKDAY = new Comparator<Schedule>() {
    @Override
    public int compare(Schedule one, Schedule other) {
        
        return one.intweek.compareTo(other.intweek); 
    }
    };
    public static Comparator<Schedule> COMPARE_BY_LETTER = new Comparator<Schedule>() {
    @Override
    public int compare(Schedule one, Schedule other) {
        
        return one.letter.compareTo(other.letter); 
    }
    };
    
    
}
