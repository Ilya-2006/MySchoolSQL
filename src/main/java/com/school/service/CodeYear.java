package com.school.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.springframework.stereotype.Service;


@Service
public class CodeYear implements Serializable {
    private static final long serialVersionUID = 1L; //код сериализации
    int years; //текущий учебный год
    
    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public void saveYears() { //Запись в файл  
    try (ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream("scholyear"))) {
            OOS.writeObject(this);
        } catch (IOException e) {
        }   
    }
    public CodeYear loagYears(){ //Чтение из файла
     CodeYear gp=null;
        try (ObjectInputStream OIS = new ObjectInputStream(new FileInputStream("scholyear"))) {
            gp = (CodeYear) OIS.readObject();
            
        } catch (IOException | ClassNotFoundException e) {
        }
       return gp;
    }
    public boolean loagTest(){ //проверка наличия файла
     boolean bl=true;
        try (ObjectInputStream OIS = new ObjectInputStream(new FileInputStream("scholyear"))) {
            CodeYear gp = (CodeYear) OIS.readObject();
            
        } catch (IOException | ClassNotFoundException e) {
            bl=false;
        }
       return bl;
    }
    
}
