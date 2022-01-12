package com.school.controller;

import com.school.repository.ProfileRepository;
import com.school.repository.UserRepository;
import com.school.model.CustomUser;
import com.school.model.Profile;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.Date;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProfileController {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProfileRepository profileRepository;
    
    private static long idprofile=0;
    
    @RequestMapping(value = "/showProfile", method = RequestMethod.GET) // просмотр аккаунтов, запрос из menu.jsp
    public String showProfile(Principal principal, Model model) { //только для юзера ЗАВУЧ
        String name = principal.getName();
        CustomUser cmu = userRepository.getOneByLogin(name);
        if (!"ЗАВУЧ".equals(cmu.getLetter())) { //если не завуч
            model.addAttribute("message", "У Вас не доступа к этой странице!!!"); 
            return "welcome"; 
        }
        Sort sort = new Sort(Sort.Direction.ASC, "surname"); //прямая сотриковка по значению "surname"
        List<Profile> list = profileRepository.findAll(sort);
        model.addAttribute("acc", list);
        return "showProfile";

    }

    @RequestMapping(value = "/delacc", method = RequestMethod.GET) // удаление профиля, запрос из showProfile.jsp
    public String delProfile(Principal principal, @RequestParam long id, Model model) {
        profileRepository.delete(id);

        return showProfile(principal, model);
    }

    @RequestMapping("/renameProfile") //изменение профиля юзера, запрос из myProfile.jsp
    public String renameAccaunt(@RequestParam String adres, 
                                @RequestParam String phone1,
                                @RequestParam String phone2, 
                                @RequestParam String email1, 
                                @RequestParam String email2,
                                @RequestParam MultipartFile photo, 
                                Principal principal, Model model) throws IOException {
        String name = principal.getName();
        CustomUser cmu = userRepository.getOneByLogin(name);
        Profile acc = profileRepository.getOne(cmu.getId());
        if (acc != null) {
            byte[] encodeBase64 = Base64.encodeBase64(photo.getBytes()); //преобразование формата фото в String
            String base64Encoded = new String(encodeBase64, "UTF-8");
            if (!"".equals(base64Encoded)) {
                acc.setPhoto(base64Encoded);
            }
            if (!"".equals(adres)) {
                acc.setAdres(adres);
            }
            if (!"".equals(phone1)) {
                acc.setPhone1(phone1);
            }
            if (!"".equals(phone2)) {
                acc.setPhone2(phone2);
            }
            if (!"".equals(email1)) {
                acc.setEmail1(email1);
            }
            if (!"".equals(email2)) {
                acc.setEmail2(email2);
            }
            profileRepository.save(acc);
        }
        return myProfile(principal, model);
    }

    @RequestMapping("/addProfile") //добавление анекты юзера, запрос из newProfile.jsp 
    public String addAccount(@RequestParam String surname, 
                             @RequestParam String firstname,
                             @RequestParam String lastname, 
                             @RequestParam String sex, 
                             @RequestParam Date datebirth,
                             @RequestParam String adres, 
                             @RequestParam String education, 
                             @RequestParam String phone1,
                             @RequestParam String phone2, 
                             @RequestParam String email1, 
                             @RequestParam String email2,
                             @RequestParam MultipartFile photo, 
                             Principal principal, Model model) throws IOException {
        
        String name = principal.getName();
        CustomUser cmu = userRepository.getOneByLogin(name);
        byte[] encodeBase64 = Base64.encodeBase64(photo.getBytes()); //преобразование формата фото в String
        String base64Encoded = new String(encodeBase64, "UTF-8");
        Profile acc = new Profile(); //создаем новый объект Profile
        acc.setIdlogin(cmu.getId()); 
        acc.setSurname(surname);
        acc.setFirstname(firstname);
        acc.setLastname(lastname);
        acc.setSex(sex);
        acc.setDatebirth(datebirth);
        acc.setAdres(adres);
        acc.setEducation(education);
        acc.setPositions("Не определено");
        acc.setPhone1(phone1);
        acc.setPhone2(phone2);
        acc.setEmail1(email1);
        acc.setEmail2(email2);
        acc.setPhoto(base64Encoded);

        profileRepository.save(acc);

        return "menu";
    }

    @RequestMapping("/myProfile")
    public String myProfile(Principal principal, Model model) throws UnsupportedEncodingException { //порсмотр таблицы Profile, запрос из menu.jsp
        String name = principal.getName();
        CustomUser cmu = userRepository.getOneByLogin(name);
        Profile pr = profileRepository.getOneByIdLogin(cmu.getId()); 
        if (pr==null) { //если нет профиля, нужно его создать.
            return "newProfile";
        }
        idprofile = pr.getId();
        if (pr != null) {
            model.addAttribute("surname", pr.getSurname());
            model.addAttribute("firstname", pr.getFirstname());
            model.addAttribute("lastname", pr.getLastname());
            model.addAttribute("sex", pr.getSex());
            model.addAttribute("datebirth", pr.getDatebirth());
            model.addAttribute("adres", pr.getAdres());
            model.addAttribute("education", pr.getEducation());
            model.addAttribute("positions", pr.getPositions());
            model.addAttribute("phone1", pr.getPhone1());
            model.addAttribute("phone2", pr.getPhone2());
            model.addAttribute("email1", pr.getEmail1());
            model.addAttribute("email2", pr.getEmail2());
            model.addAttribute("photo", pr.getPhoto());
        }
        return "myProfile";
    }
    @RequestMapping("/rnmProfile") //изменение юзера, запрос  из myProfile.jsp
    public String RenmProf(@RequestParam String adres, 
                            @RequestParam String phone1, 
                            @RequestParam String phone2,
                            @RequestParam String email1, 
                            @RequestParam String email2,
                            @RequestParam MultipartFile photo,
                            Principal principal,
                            Model model) throws UnsupportedEncodingException, IOException {  
        
        Profile acc = profileRepository.getOne(idprofile); 
        byte[] encodeBase64 = Base64.encodeBase64(photo.getBytes()); //преобразование формата фото в String
        String base64Encoded = new String(encodeBase64, "UTF-8");
        acc.setAdres(adres);
        acc.setPhone1(phone1);
        acc.setPhone2(phone2);
        acc.setEmail1(email1);
        acc.setEmail2(email2);
        if (!"".equals(base64Encoded)) {
            acc.setPhoto(base64Encoded); 
        }
        profileRepository.save(acc); 
        
        return myProfile(principal, model);
    }
    @RequestMapping("/renameProf") //изменение юзера, запрос  из infoProfile.jsp
    public String RenameProf(@RequestParam String education, 
                            @RequestParam String positions, 
                            @RequestParam String text, 
                            Model model) {
        
        Profile acc = profileRepository.getOne(idprofile); 
        acc.setPositions(positions);
        acc.setEducation(education); 
        acc.setNotes(text); 
        profileRepository.save(acc); 
        
        return editProfile(idprofile,  model);
    }
    @RequestMapping(value = "/info", method = RequestMethod.GET) // выбор одельног юзера, запрос из showProfile.jsp
    public String editProfile(@RequestParam long id, Model model) {
        if (idprofile==0) {
            idprofile=id;
        }
        Profile pr = profileRepository.findOne(id); 
        if (pr != null) {
            model.addAttribute("surname", pr.getSurname());
            model.addAttribute("firstname", pr.getFirstname());
            model.addAttribute("lastname", pr.getLastname());
            model.addAttribute("sex", pr.getSex());
            model.addAttribute("datebirth", pr.getDatebirth());
            model.addAttribute("adres", pr.getAdres());
            model.addAttribute("education", pr.getEducation());
            model.addAttribute("positions", pr.getPositions());
            model.addAttribute("phone1", pr.getPhone1());
            model.addAttribute("phone2", pr.getPhone2());
            model.addAttribute("email1", pr.getEmail1());
            model.addAttribute("email2", pr.getEmail2());
            model.addAttribute("photo", pr.getPhoto());
            model.addAttribute("note", pr.getNotes());
           
        }
        return "infoProfile";
    }

}
