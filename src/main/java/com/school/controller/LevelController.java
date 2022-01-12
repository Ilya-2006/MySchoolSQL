package com.school.controller;

import com.school.model.CustomUser;
import com.school.model.Level;
import com.school.model.Schedule;
import com.school.repository.LevelRepository;
import com.school.repository.ScheduleRepository;
import com.school.repository.UserRepository;
import com.school.service.CodeYear;
import java.security.Principal;
import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LevelController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    ScheduleRepository scheduleRepository;
    
    @Autowired
    CodeYear codeYear;  
    
    @Autowired
    LevelRepository levelRepository;
    
    private static long idSchedule; //запоминаем ID расписания занятий
    private static String name; //запоминаем ФИО вбранного ученика
    
    public static boolean checkForWord(String line, String word){ //проверка нахождения слова word в строке line 
            return line.contains(word);
    }    

    @RequestMapping(value = "/lvl", method = RequestMethod.GET) // выбор ученика для оценки запрос из setLevel.jsp
    public String levlName(@RequestParam("name") String Name, Principal principal, Model model) {
        name = Name; //запоминаем ФИО вбранного ученика
        model.addAttribute("nme", Name);  
      return showLevelTetcherSt(principal, model);  
    }
    @RequestMapping(value = "/levlShd", method = RequestMethod.GET) // выбор класса для оценки учеников запрос из tetchSchedule.jsp
    public String levlShd(@RequestParam("id") long id, Model model) {
        idSchedule=id; //запоминаем ID расписания занятий
        codeYear=codeYear.loagYears();
        
        Schedule sch = scheduleRepository.getOne(id); 
        List<CustomUser> usr = userRepository.findAll();
        List<CustomUser> list = new ArrayList<>();
        for (CustomUser ur : usr) {
            if ((ur.getLetter().equals(sch.getLetter())&&(ur.getYear()==sch.getYear()))) {
                list.add(ur);
            }
        }
        model.addAttribute("cls", codeYear.getYears()-sch.getYear()+1+sch.getLetter()+", "+sch.getWeekday()+", аудитория №"+sch.getNambercabinet()+" "+sch.getScience());  
        model.addAttribute("thc", list);  
        return "tetcherClass"; 
    
    }   
    @RequestMapping(value = "/dellvl", method = RequestMethod.GET) // удаление оценки учеников запрос из setLevel.jsp
    public String delLvl(@RequestParam("id") long id, Principal principal, Model model) {
        levelRepository.delete(id); 
        return showLevelTetcherSt(principal, model);
    }
    @RequestMapping(value = "/levelTetcherSt") // просмотр учителем своих оценок одного ученика после метода String levelStudent
    public String showLevelTetcherSt(Principal principal,Model model){
        String nam = principal.getName();
        CustomUser cmu = userRepository.getOneByLogin(nam);
        List<Level> lev = levelRepository.findAll();
        List<Level> list = new ArrayList();
        for (Level lv : lev) {
            if ((lv.getNameteacher().equals(cmu.getFirstname()))&&(lv.getNamestudent().equals(name))) {
                list.add(lv);
            }
        }
        model.addAttribute("nme", name);
        model.addAttribute("lvl", list); 
        return "setLevel";
    }
    @RequestMapping(value = "/levelStudent") // запись оценки ученику, запрос из setLevel.jsp
    public String levelStudent(@RequestParam("datelevel") Date dt, 
                              @RequestParam String level, 
                              @RequestParam String note, 
                              Principal principal,
                              Model model){
        codeYear=codeYear.loagYears();
        Schedule sch = scheduleRepository.getOne(idSchedule);
        String cls = Integer.toString(codeYear.getYears()-sch.getYear()+1)+sch.getLetter(); //определяем класс ученика
        Level lv = new Level();
        lv.setDat(dt); 
        lv.setCls(cls); 
        lv.setLevel(Integer.parseInt(level));  
        lv.setNote(note);
        lv.setNamestudent(name); 
        lv.setNameteacher(sch.getNameteacher());  
        lv.setScience(sch.getScience());  
        levelRepository.save(lv);
        
        return showLevelTetcherSt(principal,model);  
    }
    @RequestMapping(value = "/myLevel") // просмотр своих оценок, запрос из menu.jsp 
    public String myLevel(Principal principal, Model model){
        codeYear=codeYear.loagYears();
        int y=codeYear.getYears(); //запоминаем установленный год
        String name = principal.getName();
        CustomUser cmu = userRepository.getOneByLogin(name); //запомнаем объект вошедшего юзера
        String year; 
        java.util.Date dt  = new java.util.Date(); //определяем текущую дату
        SimpleDateFormat form = new SimpleDateFormat("yyyy"); 
        String dat = form.format(dt); //выделяем только год
        int d=Integer.parseInt(dat);  //преобразуем в int для дальнейшего сравнения
        String period;
        if (d>y) { //если текущий год больше установленого
            period="второе полугодие "+y+"-"+(y+1)+" учебного года.";
            year=Integer.toString(y+1); 
        } else { //в противном случае
            period="первое полугодие "+y+"-"+(y+1)+" учебного года.";
            year=Integer.toString(y); 
        }
        String nme=cmu.getFirstname(); //запоминаем имя вошедшего юзера
        String jsp="myLevel"; // устанавливаем jsp страницу
        List<Level> lev = levelRepository.findAll();
        List<Level> list = new ArrayList();
        if ((cmu.getYear()!=0)&&(!checkForWord(cmu.getLetter(),"Рук"))) { //если ученик, только оценки ученика
            for (Level lv : lev) {
                if (lv.getNamestudent().equals(nme)) {//если совпадает имя
                    String str = form.format(lv.getDat());
                    if (year.equals(str)) { //если совпадает полугодие
                        list.add(lv); //добавляем в список для публикации
                    }
                }
            }
        } else { //если учитель, только оценки учителя
            for (Level lv : lev) {
                if (lv.getNameteacher().equals(nme)) { //если совпадает имя
                    String str = form.format(lv.getDat());
                    if (year.equals(str)) { //если совпадает полугодие
                        list.add(lv); //добавляем в список для публикации
                    }
                }
            }
        }
        if ("ЗАВУЧ".equals(cmu.getLetter())) { //если завуч, просмотр всех оценок
            list=lev; //публикуем весь список
            jsp="generalLevel"; //меняем jsp страницу
        }
        Collections.sort(list, Level.COMPARE_BY_CLS); //прямая сортировка по классам
        Collections.sort(list, Level.COMPARE_BY_STUDENT); //прямая сортировка по ученикам
        Collections.sort(list, Level.COMPARE_BY_TEATCHER); //прямая сортировка по учителям
        model.addAttribute("nm", nme+" за "+period);
        model.addAttribute("myl", list); 
        return jsp;
    }
    @RequestMapping(value = "/delLevel", method = RequestMethod.GET) // удаление оценки учеников, запрос из generalLevel.jsp
    public String delLvel(@RequestParam("id") long id, Principal principal, Model model) {
        levelRepository.delete(id); 
        return myLevel(principal, model);
    }
}
