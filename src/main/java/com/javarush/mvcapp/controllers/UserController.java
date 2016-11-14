package com.javarush.mvcapp.controllers;

import com.javarush.mvcapp.domain.User;
import com.javarush.mvcapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by Andriana_Yarmoliuk on 11/11/2016.
 */
@Controller
public class UserController {
//
//    private int visitorCount = 0;
//
//    @RequestMapping("/index.html")
//    public String index(Model model) {
//        model.addAttribute("visitorCount", visitorCount++);
//        return "WEB-INF/jsp/index.jsp";
//    }

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String listContacts(Map<String, Object> map) {

        map.put("user", new User());
        map.put("userList", userService.listUser());

        return "user";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user,
                             BindingResult result) {

        userService.addUser(user);

        return "redirect:/index";
    }

    @RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {

        userService.removeUser(userId);

        return "redirect:/index";
    }
}