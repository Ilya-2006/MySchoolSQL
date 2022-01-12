package com.school.controller;

import com.school.repository.ProfileRepository;
import com.school.repository.UserRepository;
import com.school.model.UserRole;
import com.school.model.CustomUser;
import com.school.model.Profile;
import com.school.model.ReportUsers;
import com.school.service.CodeYear;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProfileRepository profileRepository;
    
    @Autowired
    private CodeYear codeYear;
    
    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/")
    public String menu(Model model){
        return "menu";    
    }
    
    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String update(@RequestParam String login,
                         @RequestParam String password,
                         @RequestParam(required = false) String firstname,
                         Model model) {
        if (userRepository.existsByLogin(login)) { //если такой логин уже есть
            model.addAttribute("exists", true); 
            return "register";
        }
        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        String passHash = encoder.encodePassword(password, null);
        CustomUser dbUser = new CustomUser(login, passHash, UserRole.GUEST, firstname);
        dbUser.setLetter("Гость"); 
        userRepository.save(dbUser); 

        return "redirect:/";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(Model model){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        return "unauthorized";
    }
    @RequestMapping("/showUser")
    public String showUsers(Model model) {
      if (codeYear.loagTest()) { 
        codeYear=codeYear.loagYears();
     }
      List<CustomUser> usr = userRepository.findAll();   
      List<ReportUsers> rps = new ArrayList<>(); //создаем коллекцию для объектов ReportUsers
      for(CustomUser ur: usr) {
            String lt=ur.getLetter(); //читаем букву класса
            if (ur.getYear()!=0) { //если объект ученик или классный руководитель
                lt = Integer.toString(codeYear.getYears()-ur.getYear()+1)+ur.getLetter();  //преобразуем год в класс
            }
            ReportUsers ru = new ReportUsers(ur.getLogin(), ur.getRole(), ur.getFirstname(), lt, ur.getScience()); //создаем объект для правильного отбраженния
                rps.add(ru); //добавляем объект ReportUsers в коллекцию rps
       
       }
        model.addAttribute("user", rps); 
        model.addAttribute("begin", codeYear.getYears());
        model.addAttribute("end", codeYear.getYears()+1);
        
        return "showUsers";
    }
    
    @RequestMapping("/addUsr") //добавдение юзера из showUsers.jsp 
    public String addUsers(@RequestParam String login, 
                           @RequestParam String firstname, 
                           @RequestParam String password, 
                           @RequestParam String year, 
                           @RequestParam String letter, 
                           @RequestParam String science, 
                           Model model) {
        if (codeYear.loagTest()) { 
            codeYear=codeYear.loagYears();
         }
        CustomUser ur = userRepository.getOneByLogin(login); 
        if (ur!=null) { //если уже есть такой логин
            model.addAttribute("message", "Already have such a user: " + login); 
            return "welcome";
        }
        int code=0;
        if((!"УЧИТЕЛЬ".equals(letter))&&(!"ЗАВУЧ".equals(letter))) { //если не учитель и не завуч, значит ученик.
            code =codeYear.getYears()-Integer.parseInt(year)+1;  //преобразование номера класса в год поступления
        }
        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        String passHash = encoder.encodePassword(password, null);
        CustomUser us = new CustomUser();
        us.setLogin(login); 
        us.setPassword(passHash); 
        us.setRole(UserRole.USER);  
        us.setFirstname(firstname); //ФИО юзера
        us.setYear(code); //Класс ученика. Если code=0 значит это не ученик и не классный руководитель.
        us.setLetter(letter); //Буква класса
        us.setScience(science);// Предмет обучения для учителей
        userRepository.save(us);

        return showUsers(model); //Вывод таблицы в браузер через метод showUsers
    }
    @RequestMapping("/rnmUsr") //измеменение параметров юзера, запрос из showUsers.jsp
    public String rnmUsers(@RequestParam String login, 
                           @RequestParam (required = false) String newlogin, 
                           @RequestParam (required = false) String password,
                           @RequestParam (required = false) String firstname, 
                           @RequestParam (required = false) String role,
                           @RequestParam (required = false) String year, 
                           @RequestParam (required = false) String letter, 
                           @RequestParam (required = false) String science, 
                           Model model) {
        CustomUser usr = userRepository.getOneByLogin(login);
        if (usr!=null) { //если есть объект с таким логином. защита от дурака
            if (!"".equals(newlogin)) { //если есть запсиь в браузере значения "newlogin"
                usr.setLogin(newlogin); //изменяем login
            }
            if (!"".equals(firstname)) { //если есть запсиь в браузере значения "firstname"
                usr.setFirstname(firstname); //изменяем имя юзера 
            }
            if (!"".equals(password)) { //если есть запсиь в браузере значения "password"
                ShaPasswordEncoder encoder = new ShaPasswordEncoder(); //кодируем пароль
                String passHash = encoder.encodePassword(password, null);
                usr.setPassword(passHash); //изменяем пароль юзера 
            }
            if (!"".equals(year)) { //если есть запсиь в браузере значения "year"
                int code =codeYear.getYears()-Integer.parseInt(year)+1; //Преобразование номера класса в год поступления
                usr.setYear(code); //меняем класс ученика или классного руководителя
            }
            if (!"".equals(letter)) { //если есть запсиь в браузере значения "letter"
                if ((letter.equals("УЧИТЕЛЬ"))||(letter.equals("ЗАВУЧ"))) { //если звучилиучитель ситавим номер класса ноль
                    usr.setYear(0); 
                }
                usr.setLetter(letter); //меняем букву класса ученика или классного руководителя  
            }
            if (!"".equals(science)) { //если есть запсиь в браузере значения "thing"
                usr.setScience(science); //меняем предмета обучения юзера 
            }            
            if (!"".equals(role)) { //если есть запсиь в браузере значения "role"
                switch (role) {
                    case("ADMIN"): usr.setRole(UserRole.ADMIN);
                    break;
                    case("USER"): usr.setRole(UserRole.USER);
                    break;
                    case("GUEST"): usr.setRole(UserRole.GUEST);   
                }   
            }            
            userRepository.save(usr);
         
        }
        
        return showUsers(model);
    }    
    @RequestMapping("/remDate")
    public String renmDate(@RequestParam int year, Model model) {
       codeYear.setYears(year);
       codeYear.saveYears();
    return showUsers(model);
    } 
   @RequestMapping(value = "/del", method = RequestMethod.GET) // удаление юзера из showUsers.jsp
   public String delSend(@RequestParam("log") String Login, Model model) {
     CustomUser dbUser = userRepository.getOneByLogin(Login);
     Profile prof = profileRepository.getOneByIdLogin(dbUser.getId()); 
     userRepository.delete(dbUser.getId());  //удаление юзера
     if (prof!=null) {
        profileRepository.delete(prof); //удаление его профиля.
     }

     return showUsers(model); 
    }
    @RequestMapping("/findLog") //Поиск юзера по его логину, запрос из showUsers.jsp
    public String findLog(@RequestParam("login") String Login, Model model) {
        List<CustomUser> usr = userRepository.findAll(); //составляем коллекцию существующих объектов
        List<ReportUsers> search = new ArrayList<>(); //составляем пустую коллекцию
        for (CustomUser ur : usr) {
            if (ur.getLogin().contains(Login)) { //если объект частично совпадает с логином
                String lt=ur.getLetter(); //читаем букву класса
                    if (ur.getYear()!=0) { //Если объект ученик или классный руководитель
                        lt = Integer.toString(codeYear.getYears()-ur.getYear()+1)+ur.getLetter();  //Преобразуем год в класс
                    }
                ReportUsers ru = new ReportUsers(ur.getLogin(), ur.getRole(), ur.getFirstname(), lt, ur.getScience()); 
                search.add(ru); //добавляем объект ReportUsers в коллкцию search    
            }
        }
        model.addAttribute("begin", codeYear.getYears());
        model.addAttribute("end", codeYear.getYears()+1);
        model.addAttribute("user", search); 
        
        return "showUsers"; 
    }
    @RequestMapping("/findFname") //Поиск юзера по его имени, запрос из showUsers.jsp
    public String findFname(@RequestParam("firstname") String Firstname, Model model) {
        List<CustomUser> usr = userRepository.findAll();
        List<ReportUsers> search = new ArrayList<>(); 
        for (CustomUser ur : usr) {
            if (ur.getFirstname().contains(Firstname)) { //если объект частично совпадает с ФИО юзера
                String lt=ur.getLetter(); //читаем букву класса
                    if (ur.getYear()!=0) { //Если объект ученик или классный руководитель
                        lt = Integer.toString(codeYear.getYears()-ur.getYear()+1)+ur.getLetter();  //Преобразуем год в класс
                    }
                ReportUsers ru = new ReportUsers(ur.getLogin(), ur.getRole(), ur.getFirstname(),lt, ur.getScience()); 
                search.add(ru); //добавляем объект ReportUsers в коллкцию search    
            }
        }
        model.addAttribute("begin", codeYear.getYears());
        model.addAttribute("end", codeYear.getYears()+1);
        model.addAttribute("user", search); 
        
        return "showUsers"; 
    }
    @RequestMapping("/sortFname") //Сотрировка объектов по имени юзера, запрос из showUsers.jsp
    public String sortFname(Model model) {
        List<CustomUser> usr = userRepository.findAll();
        List<ReportUsers> search = new ArrayList<>(); 
        for (CustomUser ur : usr) {
            String lt=ur.getLetter(); //читаем букву класса
                if (ur.getYear()!=0) { //если объект ученик или классный руководитель
                    lt = Integer.toString(codeYear.getYears()-ur.getYear()+1)+ur.getLetter();  //Преобразуем год в класс
                }
            ReportUsers ru = new ReportUsers(ur.getLogin(), ur.getRole(), ur.getFirstname(), lt, ur.getScience()); 
            search.add(ru); //добавляем объект ReportUsers в коллкцию search    
        }
        Collections.sort(search, ReportUsers.COMPARE_BY_FIRSTNAME); //Прямая сортировка по имени юзера
        model.addAttribute("begin", codeYear.getYears());
        model.addAttribute("end", codeYear.getYears()+1);
        model.addAttribute("user", search); 
        
        return "showUsers"; 
    }
    @RequestMapping("/sortLogin") //Сотрировка объектов по логину юзера, запрос из showUsers.jsp
    public String sortLogin(Model model) {
        List<CustomUser> usr = userRepository.findAll();
        List<ReportUsers> search = new ArrayList<>(); 
        for (CustomUser ur : usr) {
            String lt=ur.getLetter(); //читаем букву класса
                if (ur.getYear()!=0) { //если объект ученик или классный руководитель
                    lt = Integer.toString(codeYear.getYears()-ur.getYear()+1)+ur.getLetter();  //Преобразуем год в класс
                }
            ReportUsers ru = new ReportUsers(ur.getLogin(), ur.getRole(), ur.getFirstname(), lt, ur.getScience()); 
            search.add(ru); //добавляем объект ReportUsers в коллкцию search    
        }        
        Collections.sort(search, ReportUsers.COMPARE_BY_LOGIN);
        model.addAttribute("begin", codeYear.getYears());
        model.addAttribute("end", codeYear.getYears()+1);        
        model.addAttribute("user", search); 
        
        return "showUsers"; 
    }
    @RequestMapping("/sortClass") //сотрировка объектов по классам юзеров, запрос из showUsers.jsp
    public String sortClass(Model model) {
        List<CustomUser> usr = userRepository.findAll();
        List<ReportUsers> search = new ArrayList<>(); 
        for (CustomUser ur : usr) {
            String lt=ur.getLetter(); //читаем букву класса
                if (ur.getYear()!=0) { //если объект ученик или классный руководитель
                    lt = Integer.toString(codeYear.getYears()-ur.getYear()+1)+ur.getLetter();  //Преобразуем год в класс
                } 
            ReportUsers ru = new ReportUsers(ur.getLogin(), ur.getRole(), ur.getFirstname(), lt, ur.getScience()); 
            search.add(ru); //добавляем объект ReportUsers в коллкцию search    
        }        
        Collections.sort(search, ReportUsers.COMPARE_BY_LETTER);
        model.addAttribute("begin", codeYear.getYears());
        model.addAttribute("end", codeYear.getYears()+1);        
        model.addAttribute("user", search); 
        
        return "showUsers"; 
    }
    
}
