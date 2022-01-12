package com.school.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowController {
    private static String info;
    
    private void writeFiles(byte[] files, String filename){
    Path file = Paths.get(filename);
    try(FileOutputStream fos=new FileOutputStream(file.toFile())) {  
        fos.write(files, 0, files.length);
    }
    catch(IOException ex){

    }   
    }
    
    private byte[] readFile(String filename){ //чтение файла
    Path file = Paths.get(filename);
    byte[] buffer = null;
     try(FileInputStream fin=new FileInputStream(file.toFile())){
        buffer = new byte[fin.available()];
        fin.read(buffer, 0, buffer.length);   
    }
    catch(IOException ex){
    } 
     return buffer;
    }    
    
@RequestMapping("/myShcool")
public String index() throws UnsupportedEncodingException{
    String copyright ="<br/><p><small>Copyright 2020 Ilja Belov Ukraine Dnipro School 71</small></p>";
    byte[] files = readFile("MySchool.html"); //читаем файл
    info = new String(files); //преобразуем в String
    info+=copyright;
    String test="Привет";
    String newString = new String(info.getBytes(),"UTF-8"); 
    return newString; //отправляем в браузер
}    
    
}
