package com.school.controller;

import com.school.repository.MessageRepository;
import com.school.repository.UserRepository;
import com.school.model.CustomUser;
import com.school.model.Message;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {

    @Autowired
    private UserRepository userRepository;
        
    @Autowired
    private MessageRepository messageRepository;
    
    private static String sendname=null;    
    private static int year=-1; 
    private static String letter=null;   
    
    @RequestMapping(value = "/snd", method = RequestMethod.GET) // прием сообщения от showTeacher.jsp
    public String sendUser(@RequestParam("name") String Name, Model model) {
        sendname=Name;
        model.addAttribute("send", Name);
        return "sengMessage"; 
    }
    
    @RequestMapping(value = "/sn", method = RequestMethod.GET) // прием сообщения от myClass.jsp
    public String sedUser(@RequestParam("name") String Name, Model model) {
        sendname=Name;     
        model.addAttribute("send", Name);
        return "sengMessage"; 
    }
    @RequestMapping(value = "/sen", method = RequestMethod.GET) // прием сообщения от allClass.jsp
    public String sdUser(@RequestParam("name") String Name, Model model) {
        sendname=Name;
        model.addAttribute("send", Name);
        return "sengMessage"; 
    }
    @RequestMapping(value = "/sengMessag", method = RequestMethod.POST) // отправка сообщения
    public String sengMessag(Principal principal,@RequestParam("send") String Send, Model model) {
        String name = principal.getName(); //логин текущего юзера
        CustomUser dbUser = userRepository.getOneByLogin(name);
        Date date = new Date();
        Message mess = new Message(); //создание нового сообщения
        mess.setDat(date); 
        mess.setUsername(dbUser.getFirstname()); 
        mess.setSendname(sendname); 
        mess.setSend(Send); 
        messageRepository.save(mess); //запись в БД
        return myMessag(principal, model);
    }
    @RequestMapping(value = "/showMessages", method = RequestMethod.GET) // просмотр сообщений, запрос из menu.jsp
    public String showMessage(Principal principal,Model model) {
        String name = principal.getName();
        CustomUser cmu = userRepository.getOneByLogin(name);
        if (!"ЗАВУЧ".equals(cmu.getLetter())) { //если не завуч
            model.addAttribute("message", "У Вас не доступа к этой странице!!!"); 
            return "welcome"; 
        }
        List<Message> msge = messageRepository.findAll();
        model.addAttribute("msg", msge); 
        return "showMessage";
    }
    @RequestMapping(value = "/delsend", method = RequestMethod.GET) // удаление сообщения
    public String delSend(@RequestParam("id") long id , Model model) {
        messageRepository.delete(id);  
        return "menu";
             
    }
    @RequestMapping(value = "/myMessag", method = RequestMethod.GET) // просмотр своих сообщений
    public String myMessag(Principal principal,Model model) {   
        String nm = principal.getName(); //логин текущего юзера
        CustomUser dbUser = userRepository.getOneByLogin(nm);
        String name = dbUser.getFirstname();
        List<Message> listout = messageRepository.findByUsername(name);  //отправленные сообщения
        List<Message> listin = messageRepository.findBySendname(name); //принятые сообщения.
        model.addAttribute("msin", listin);
        model.addAttribute("msout", listout);
        return "myMessage";
    }
   
}
