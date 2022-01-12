package com.school.controller;

import com.school.repository.UserRepository;
import com.school.model.CustomUser;
import com.school.model.ReportUsers;
import com.school.model.UserRole;
import com.school.service.CodeYear;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Controller
public class SchollController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CodeYear codeYear; 
    
    public static boolean checkForWord(String line, String word){ //Проверка нахождения слова word в строке line 
            return line.contains(word);
    }
    
    @RequestMapping("/myClass") //Просмотр одноклассников, запрос из menu.jsp
    public String myClass(Principal principal,Model model) {
        String name = principal.getName();
        CustomUser dbUser = userRepository.getOneByLogin(name);
        int cont=0;
        int year=dbUser.getYear();
        String lett=dbUser.getLetter();
        
        if (year==0) { //если не ученик и не классный руководитель.
            model.addAttribute("message", "У Вас не доступа к этой странице!!!"); 
            return "welcome"; 
        }  
        
        if (lett.length()>6) { //Если классный руковолитель, удаляем слово "Рук" 
            lett = lett.replaceAll("Рук", "");
        }
        String ya=Integer.toString(codeYear.getYears()-year+1)+lett;
        Sort sort = new Sort(Direction.ASC, "firstname"); //прямая сотриковка по значению "firstname"
        List<CustomUser> usr = userRepository.findAll(sort);
        List<CustomUser> clas = new ArrayList<>();
        List<CustomUser> teacher = new ArrayList<>();
        for (CustomUser u: usr) {
            if ((u.getYear()==year)&&((u.getLetter().equals(lett))&&(!checkForWord(u.getLetter(),"Рук")))) { //Если ученик
                clas.add(u);
                cont++;
            }
        }
        for (CustomUser u: usr) {
             if ((year==u.getYear())&&(u.getLetter().contains(lett))&&(checkForWord(u.getLetter(),"Рук"))) { //Если клрук
                    teacher.add(u);               
            }
        }
        
        model.addAttribute("myc", clas); 
        model.addAttribute("cnt", cont); 
        model.addAttribute("yr", ya); 
        model.addAttribute("tyc", teacher); 
        
        return "myClass"; 
    }  

    @RequestMapping("/allClass") //просмотр всех ученикиов и учителей, запрос из menu.jsp
    public String allClass(Principal principal, Model model) {
        String name = principal.getName();
        CustomUser dbUser = userRepository.getOneByLogin(name);
        if ((dbUser.getRole()==null)||(dbUser.getRole()==UserRole.GUEST)) { //если гость или чужой
            model.addAttribute("message", "У Вас не доступа к этой странице!!!"); 
            return "welcome"; 
        } 
        codeYear=codeYear.loagYears();
        int st=0; //счетчик учеников
        int th=0; //счечик учителей
        List<CustomUser> usr = userRepository.findAll();
        List<ReportUsers> rps = new ArrayList<>(); //создвем коллекцию для объектов ReportUsers
        for(CustomUser ur: usr) {
            String lt=ur.getLetter(); //читаем букву класса
            if (ur.getYear()!=0) { //если объект ученик или классный руководитель
                lt = Integer.toString(codeYear.getYears()-ur.getYear()+1)+ur.getLetter();  //преобразуем год в класс
                if ((!checkForWord(lt,"Рук"))) { //если ученик
                      st++; //считаем учеников
                } else { 
                    th++; //считаем учителей
                }
            } else {
                th++; //считаем учителей
            }
            ReportUsers ru = new ReportUsers(ur.getLogin(), ur.getRole(), ur.getFirstname(), lt, ur.getScience()); //создаем объект для правильного отображенния класса
            rps.add(ru); //добавляем объект ReportUsers в коллекцию rps
        }
        Collections.sort(rps, ReportUsers.COMPARE_BY_FIRSTNAME); //сортировка по имени
     //   Collections.sort(rps, ReportUsers.COMPARE_BY_LETTER); //сотрировка по классам
        model.addAttribute("cls", rps);
        model.addAttribute("std", st);
        model.addAttribute("teth", th);
        return "allClass"; 
        
    }

}
