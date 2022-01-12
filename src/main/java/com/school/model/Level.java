package com.school.model;

import java.util.Comparator;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Level {
    @Id
    @GeneratedValue
    private long id;
    private String cls;
    private int level;
    private String science;
    private Date dat;
    private String namestudent;
    private String nameteacher;
    private String note;

    public Level() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getScience() {
        return science;
    }

    public void setScience(String science) {
        this.science = science;
    }

    public Date getDat() {
        return dat;
    }

    public void setDat(Date dat) {
        this.dat = dat;
    }

    public String getNamestudent() {
        return namestudent;
    }

    public void setNamestudent(String namestudent) {
        this.namestudent = namestudent;
    }

    public String getNameteacher() {
        return nameteacher;
    }

    public void setNameteacher(String nameteacher) {
        this.nameteacher = nameteacher;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public static Comparator<Level> COMPARE_BY_CLS = new Comparator<Level>() {
        @Override
        public int compare(Level one, Level other) {
            return one.cls.compareToIgnoreCase(other.cls);
        }
    };    
    public static Comparator<Level> COMPARE_BY_STUDENT = new Comparator<Level>() {
        @Override
        public int compare(Level one, Level other) {
            return one.namestudent.compareToIgnoreCase(other.namestudent);
        }
    };     
    public static Comparator<Level> COMPARE_BY_TEATCHER = new Comparator<Level>() {
        @Override
        public int compare(Level one, Level other) {
            return one.nameteacher.compareToIgnoreCase(other.nameteacher);
        }
    };  
}
