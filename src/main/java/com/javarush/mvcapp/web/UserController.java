package com.javarush.mvcapp.web;

import com.javarush.mvcapp.domain.User;
import com.javarush.mvcapp.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
/**
 * Created by Andriana_Yarmoliuk on 11/11/2016.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping(value = { "/","usersview"}, method = RequestMethod.GET)
    public String list(Model model, Integer offset, Integer maxResults){
        model.addAttribute("users", userService.listUser(offset, maxResults));
        model.addAttribute("count", userService.count());
        model.addAttribute("offset", offset);
        return "usersview";

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

    /* It deletes record for the given id in URL and redirects to /usersview */
    @RequestMapping(value="/delete-user-{id}",method = RequestMethod.GET)
    public String delete(@PathVariable String id){
        userService.removeUser(id);
        return "redirect:/usersview";
    }

    @RequestMapping(value="/delete-{searched}-user-{id}",method = RequestMethod.GET)
    public String deleteSearchedUser(@PathVariable String id, String text, Model model){
        userService.removeUser(id);
        model.addAttribute("searchedtext", text);
        return "searchresults";
    }

    @RequestMapping(value = "/doSearch", method = RequestMethod.POST)
    public String search(@RequestParam("searchText") String searchText, Model model) throws Exception
    {
        List<User> allFound = userService.searchUser(searchText);
        model.addAttribute("searchresults", allFound);
        model.addAttribute("searchedtext", searchText);

//        ModelAndView mav = new ModelAndView("searchresults");
//        mav.addObject("searchresults", allFound);
//    mav.addObject("searchedtext", searchText);
        return "searchresults";
    }
}

