package com.javarush.mvcapp.web;

import com.javarush.mvcapp.domain.User;
import com.javarush.mvcapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
/**
 * Created by Andriana_Yarmoliuk on 11/11/2016.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String init(){
        return "redirect:/users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, Model model){
        model.addAttribute("page", page);
        int endpage = userService.amountOfPagesAllUsers();
        model.addAttribute("endpage", endpage);
        model.addAttribute("users", userService.listUser(page));
        return "usersview";
    }

    @RequestMapping(value = {"search"}, method = RequestMethod.GET)
    public String search(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, Model model,@RequestParam(value = "searchText") String searchText) throws Exception {
        model.addAttribute("page", page);
        int endpage = userService.amountOfFoundedPages(searchText);
        model.addAttribute("endpage", endpage);
        model.addAttribute("found", userService.searchUser(page, searchText));
        model.addAttribute("searchText", searchText);
        return "searchresults";
    }

    /*It displays a form to input data, here "command" is a reserved request attribute
       *which is used to display object data into form
       */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        java.sql.Timestamp sqlNow=new java.sql.Timestamp(new java.util.Date().getTime());
        model.addAttribute("date", sqlNow);
        return "userform";
    }
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
    public String saveUser(/*@Valid*/ User user, BindingResult result, ModelMap model) {
        userService.addUser(user);
        model.addAttribute("success", "User " + user.getName() + " "+ user.getIsAdmin() + " registered successfully");
                //return "success";
        return "registrationsuccess";
    }

    @RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String id, ModelMap model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "userform";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.POST)
    public String updateUser(/*@Valid*/ User user, BindingResult result, ModelMap model, @PathVariable String id) {
        userService.updateUser(user);
        model.addAttribute("success", "User " + user.getName() + " "+ user.getIsAdmin() + " updated successfully");
        return "registrationsuccess";
    }

    /* It deletes record for the given id in URL and redirects to /usersview */
    @RequestMapping(value="/delete-user-{id}",method = RequestMethod.GET)
    public String delete(@PathVariable String id){
        userService.removeUser(id);
        return "redirect:/users";
    }
}

