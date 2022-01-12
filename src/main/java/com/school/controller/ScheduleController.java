package com.school.controller;

import com.school.repository.ScheduleRepository;
import com.school.repository.UserRepository;
import com.school.model.CustomUser;
import com.school.model.Schedule;
import com.school.model.UserRole;
import com.school.service.CodeYear;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;


@Controller
public class ScheduleController {
    
    @Autowired
    private UserRepository userRepository;
        
    @Autowired
    CodeYear codeYear;  
    
    @Autowired
    ScheduleRepository scheduleRepository;
    
    private static int year;
    private static String letter;
    private static String week="НЕ УКАЗАНО";
    private static String tacher="НЕ УКАЗАНО";
    
    public static boolean checkForWord(String line, String word){ //проверка нахождения слова word в строке line 
            return line.contains(word);
    }
    public String period() { //определение текущего полугодия
        String period;
        codeYear=codeYear.loagYears();
        int y=codeYear.getYears(); //запоминаем установленный год
        String year; 
        java.util.Date dt  = new java.util.Date(); //определяем текущую дату
        SimpleDateFormat form = new SimpleDateFormat("yyyy"); 
        String dat = form.format(dt); //выделяем только год
        int d=Integer.parseInt(dat);  //преобразуем в int для дальнейшего сравнения
        if (d>y) { //если текущий год больше установленого
            period="второе полугодие "+y+"-"+(y+1)+" учебного года.";
        } else { //в противном случае
            period="первое полугодие "+y+"-"+(y+1)+" учебного года.";
        }
        return period;
    }
    
    @RequestMapping(value = "/editSchedule", method = RequestMethod.GET) // просмотр расписания занятий запрос из menu.jsp
    public String showSchedules(Principal principal,Model model) {
        codeYear=codeYear.loagYears();
        String name = principal.getName();
        CustomUser cmu = userRepository.getOneByLogin(name);
        String myletter=cmu.getLetter();
        String outpjsp="editSchedules"; //ссылка на editSchedules.jsp
        int yr=codeYear.getYears();
        if (!"ЗАВУЧ".equals(myletter)) {
            if ((!checkForWord(myletter,"Рук"))||("УЧИТЕЛЬ".equals(myletter))) { //если не ученик и не классный руководитель.
                model.addAttribute("message", "У Вас нет доступа к этой странице!!!"); 
                return "welcome"; 
            }  
        }
        if ((!"ЗАВУЧ".equals(myletter))||(checkForWord(myletter,"Рук"))){ //если классные руководители и завуч
            year=cmu.getYear(); //запоминаем вошедшего юзера 
            letter=cmu.getLetter();
            letter = myletter.replaceAll("Рук", ""); //вырезаем "Рук"
            outpjsp="editSchedules_1"; //ссылка на editSchedules_1.jsp
            if ((yr-cmu.getYear())<4) { //если учитель младших классов
                tacher=cmu.getFirstname();
                outpjsp="editSchedules_2"; //ссылка на editSchedules_2.jsp
            } 
        } 
        List<Schedule> list = new ArrayList<>();
        List<Schedule> sch = scheduleRepository.findAll();
        for (Schedule s :sch) {
            if ((s.getYear()==year)&&(s.getLetter().equals(letter))&&(s.getWeekday().equals(week))) {
                String lett= s.getLetter();
                int yra = yr-s.getYear()+1;
                s.setLetter(Integer.toString(yra)+lett);
               
                list.add(s);
            }
        }
        Sort sort = new Sort(Direction.ASC, "firstname");
        List<CustomUser> usr = userRepository.findAll(sort); //прямая сортировка по имени юзера
        List<CustomUser> teach = new ArrayList<>();
        for (CustomUser u: usr) {
            String lett=u.getLetter();
            if (("УЧИТЕЛЬ".equals(lett))||("ЗАВУЧ".equals(lett))||(checkForWord(lett,"Рук"))) {
                if (u.getYear()!=0) { //если объект ученик или классный руководитель
                String lt = Integer.toString(codeYear.getYears()-u.getYear()+1)+u.getLetter();
                u.setLetter(lt); //добавляем  номер класса к букве
            }
                teach.add(u);
            }
        }
        String clas="НЕ УКАЗАНО";
        if ((year!=0)&&(!"".equals(letter))) { //определение класса
            clas=Integer.toString(yr-year+1)+letter+"а";
        } 
        Collections.sort(list, Schedule.COMPARE_BY_NAMBERLESSON); //прямая сортировка по номеру урока
         
        model.addAttribute("thr", teach); 
        model.addAttribute("sce", list); 
        model.addAttribute("wday", week);
        model.addAttribute("cl", clas);
        model.addAttribute("th", tacher);
        model.addAttribute("period", period());
 
        return outpjsp;
    }
    
    @RequestMapping(value = "/addPlan", method = RequestMethod.POST) // добавление записи в таблицу Schedule, запрос из editSchedules.jsp
    public String addPlan(@RequestParam String lesson, 
                          @RequestParam String cabinet, 
                          @RequestParam String science,
                          @RequestParam String notes, 
                          Principal principal,
                          Model model) {
        
        if (("НЕ УКАЗАНО".equals(week))||("НЕ УКАЗАНО".equals(tacher))) {
            model.addAttribute("message", "Укажите день недели и фамилию преподавателя!");
            return "welcome"; 
        }
        String title="";
        String wk="";
        List<CustomUser> usr = userRepository.findAll();
        for (CustomUser u: usr) {
            if(u.getFirstname().equals(tacher)) {
               title=u.getScience();
            }
        }
        if (("".equals(title))||(!"".equals(science))) { //если у юзера не задан предмет обучения, берем его из раписания.
            title=science;
        }  
        switch (week){ //определение номера дня недели для дальнейшей сотртировки по возрастанию
                    case "понедельнк" : wk="1";
                            break; 
                    case "вторник" : wk="2";
                            break;         
                    case "среда" : wk="3";
                            break;         
                    case "четверг" : wk="4";
                            break;         
                    case "пятница" : wk="5";
                            break;         
                    case "суббота" : wk="6";       
        }
        Schedule shd = new Schedule (tacher, title, year, letter, cabinet, lesson, wk, week, notes);
        scheduleRepository.save(shd);
        return showSchedules(principal,model);
    }
    
    @RequestMapping(value = "/choiceTeacher", method = RequestMethod.POST) // установка преподавателя, запорос из editSchedules.jsp
    public String addTacher( @RequestParam("contact") String Tacher, Principal principal, Model model) {
        tacher=Tacher;
        return showSchedules(principal, model);
    }
    
    @RequestMapping(value = "/ext", method = RequestMethod.POST) // установка класса и дня недели, запорос из editSchedules.jsp
        public String addEx(@RequestParam("week") String Week, 
                            @RequestParam("year") int Year, 
                            @RequestParam("let") String Letter, 
                            Principal principal,
                            Model model) {
        int yr=codeYear.getYears();
        week=Week;
        year=yr-Year+1;
        letter=Letter;
        return showSchedules(principal, model);
        
    }
    @RequestMapping(value = "/ext_1", method = RequestMethod.POST) // установка класса и дня недели, запрос из editSchedules_1_2.jsp
    public String addEx_1(@RequestParam("week") String Week, Principal principal, Model model) {
        week=Week;
        return showSchedules(principal, model);
    }
    
    @RequestMapping(value = "/delIds", method = RequestMethod.GET) // удаление предмета из расписания, запрос из editSchedules.jsp
    public String delSchedule(@RequestParam long id, Principal principal, Model model) {
        scheduleRepository.delete(id); 
        
        return showSchedules(principal, model);
    }
    @RequestMapping(value = "/delShd", method = RequestMethod.GET) // удаление предмета из расписания, запрос из generalSchedule.jsp
    public String delGeneralSchedule(@RequestParam long id, Principal principal, Model model) {
        scheduleRepository.delete(id); 
        return mySchedule(principal, model);
    }
    @RequestMapping(value = "/delPlan", method = RequestMethod.POST) // удаление всего расписаия, запрос из generalSchedule.jsp
    public String delPlan(Model model) {
        return "warning";
    }
    
    @RequestMapping(value = "/warning_NO", method = RequestMethod.POST) // ответ NO предупреждения удаления всего расписания, запрос из warning.jsp
    public String wNo(Principal principal, Model model) {
        return mySchedule(principal, model);
    }
    
    @RequestMapping(value = "/warning_YES", method = RequestMethod.POST) // ответ YES удаления всего расписаия, запрос из warning.jsp
    public String wYes(Principal principal,Model model) {
        String name = principal.getName();
        CustomUser cmu = userRepository.getOneByLogin(name);
        if("ЗАВУЧ".equals(cmu.getLetter())) { //только если завуч
            scheduleRepository.deleteAll();
        }
        return mySchedule(principal, model);
        
    }

    @RequestMapping(value = "/mySchedule", method = RequestMethod.GET) // просмотр всего расписания, запрос из menu.jsp
    public String mySchedule(Principal principal,Model model) {
        codeYear=codeYear.loagYears();
        String name = principal.getName();
        CustomUser cmu = userRepository.getOneByLogin(name);
        String lett=cmu.getLetter();
        String outpjsp="tetchSchedule"; //устанавливаем jsp страницу
        List<Schedule> list = new ArrayList<>();
        List<Schedule> sch = scheduleRepository.findAll();
        if (!lett.equals("ЗАВУЧ")) { //если не завуч
           if ((checkForWord(lett,"Рук")||("УЧИТЕЛЬ".equals(lett)))) { //если классный руководитель или учитель
                for (Schedule s: sch) {
                    if (s.getNameteacher().equals(cmu.getFirstname())) { //выбираем по имени учителя
                        String lt = Integer.toString(codeYear.getYears()-s.getYear()+1)+s.getLetter();
                        s.setLetter(lt); //добавляем  номер класса к букве
                        list.add(s);
                    }
                }
            } else {
                if (cmu.getRole()==UserRole.USER) { //если ученик
                    outpjsp="studeSchedule"; //меняем jsp страницу
                    for (Schedule s: sch) {
                        if ((s.getYear()==cmu.getYear())&&(s.getLetter().equals(lett))) { //выбираем по номеру класса
                            String lt = Integer.toString(codeYear.getYears()-s.getYear()+1)+s.getLetter();
                            s.setLetter(lt); //добавляем номер класса к букве
                            list.add(s);
                        }
                    }
                }
            }
        } else { //если завуч, выводить все
            outpjsp="generalSchedule"; //меняем jsp страницу
            for (Schedule s: sch) {
                String lt = Integer.toString(codeYear.getYears()-s.getYear()+1)+s.getLetter();
                s.setLetter(lt); //добавляем  номер класса к букве
                list.add(s);
            }
        }
        Collections.sort(list, Schedule.COMPARE_BY_NAMBERLESSON); //прямая сортировка по номеру урока
        Collections.sort(list, Schedule.COMPARE_BY_WEEKDAY); //прямая сортировка по дням недели
        Collections.sort(list, Schedule.COMPARE_BY_LETTER); //прямая сортировка по классам
        model.addAttribute("sce", list);
        model.addAttribute("period", period());

        return outpjsp; //выводим установленную jsp страницу
    }
    @RequestMapping(value = "/remDate", method = RequestMethod.POST) // изменение учебного года, запорос из generalSchedule.jsp
    public String renmDate(Principal principal, @RequestParam int year, Model model) {
        codeYear.setYears(year); 
        codeYear.saveYears();
        return mySchedule(principal,model);
    }
       

}
