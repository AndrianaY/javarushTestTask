package com.javarush.mvcapp.web;

import com.javarush.mvcapp.domain.User;
import com.javarush.mvcapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Andriana_Yarmoliuk on 11/11/2016.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping(value = { "/","usersview", "usersview?{page}="}, method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", required = false) Integer page, Model model){
        int pageNumber=1;
        if(page != null) {
            model.addAttribute("page", page);
            pageNumber = page;
        } else {
            model.addAttribute("page", 1);
        }
        model.addAttribute("users", userService.listUser(pageNumber));
        String myUrl = "usersview?page=";
        model.addAttribute("myUrl", myUrl);
        int startpage = 1;
        int endpage = userService.amountOfPagesAllUsers();
        model.addAttribute("startpage", startpage);
        model.addAttribute("endpage", endpage);
        model.addAttribute("pagesize", userService.listUser(pageNumber).size());
        return "usersview";

    }

    @RequestMapping(value = {"/doSearch", "searchresults", "searchresults?{searchText}=?{page}="}, method = RequestMethod.GET)
    public String search(@RequestParam(value = "page", required = false) Integer page, Model model,String searchText) throws Exception
    {
        int pageNumber=1;
        if(page != null) {
            model.addAttribute("page", page);
            pageNumber = page;
        } else {
            model.addAttribute("page", 1);
        }
        model.addAttribute("found", userService.searchUser(pageNumber, searchText));
        model.addAttribute("searchText", searchText);
        String myUrl = "searchresults?searchText=" + searchText + "?page=";
        model.addAttribute("myUrl", myUrl);
        int startpage = 1;
        int endpage = userService.amountOfFoundedPages(searchText);
        model.addAttribute("startpage", startpage);
        model.addAttribute("endpage", endpage);
        model.addAttribute("pagesize", userService.searchUser(searchText).size());

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
    public String saveUser(/*@Valid*/ User user, BindingResult result,
                           ModelMap model) {
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
    public String updateUser(/*@Valid*/ User user, BindingResult result,
                             ModelMap model, @PathVariable String id) {
        userService.updateUser(user);
        model.addAttribute("success", "User " + user.getName() + " "+ user.getIsAdmin() + " updated successfully");
        return "registrationsuccess";
    }

    @RequestMapping(value = { "/edit-{searchedtext}-user-{id}" }, method = RequestMethod.GET)
    public String editSearchedUser(@PathVariable String id, ModelMap model, String searchedtext) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("searched", true);
        model.addAttribute("searchedtext", searchedtext);
        return "userform";
    }

//    @RequestMapping(value = {"/edit-{searchedtext}-user-{id}" }, method = RequestMethod.POST)
//    public String updateSearchedUser(@PathVariable String searchedtext, Model model, User user){
//        userService.updateUser(user);
//        model.addAttribute("searchedtext", searchedtext);
//        model.addAttribute("success", "User " + user.getName() + " "+ user.getIsAdmin() + " updated successfully");
//        try {
//            return search(searchedtext, model);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "usersview";
//    }

    /* It deletes record for the given id in URL and redirects to /usersview */
    @RequestMapping(value="/delete-user-{id}",method = RequestMethod.GET)
    public String delete(@PathVariable String id){
        userService.removeUser(id);
        return "redirect:/usersview";
    }
//
//    @RequestMapping(value="/delete-{searched}-user-{id}",method = RequestMethod.GET)
//    public String deleteSearchedUser(@PathVariable String id, String searchedtext, Model model){
//        userService.removeUser(id);
//        model.addAttribute("searchedtext", searchedtext);
//        try {
//            return search(searchedtext, model);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "usersview";
//    }


}

