package com.javarush.mvcapp.web;

import com.javarush.mvcapp.domain.User;
import com.javarush.mvcapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
/**
 * Created by Andriana_Yarmoliuk on 11/11/2016.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home() {
        return "redirect:/usersview";
    }

    /*It displays a form to input data, here "command" is a reserved request attribute
     *which is used to display object data into form
     */
    @RequestMapping("userform")
    public ModelAndView showform(){
        return new ModelAndView("userform","command", userService.getUser());
    }


    /*It saves object into database. The @ModelAttribute puts request data
     *  into model object. You need to mention RequestMethod.POST method
     *  because default request is GET*/
    @RequestMapping(value="save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("user") User user){
        userService.addUser(user);
        return new ModelAndView("redirect:/usersview");//will redirect to usersview request mapping
    }
    /* It provides list of users in model object */
//    @RequestMapping("/usersview")
//    public ModelAndView viewusers(){
//        List<User> list=userService.listUser();
//        return new ModelAndView("usersview","list",list);
//    }


    @RequestMapping("usersview")
    public String list(Model model, Integer offset, Integer maxResults){

        model.addAttribute("users", userService.listUser(offset, maxResults));
        model.addAttribute("count", userService.count());
        model.addAttribute("offset", offset);

        return "usersview";

    }



    /* It displays object data into form for the given id.
     * The @PathVariable puts URL data into variable.*/
    @RequestMapping(value="edit/{id}")
    public ModelAndView edit(@PathVariable int id){
        User user = userService.getUser(id);
        return new ModelAndView("usereditform","command", user);
    }
    /* It updates model object. */
    @RequestMapping(value="editsave",method = RequestMethod.POST)
    public ModelAndView editsave(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return new ModelAndView("redirect:/usersview");
    }
    /* It deletes record for the given id in URL and redirects to /usersview */
    @RequestMapping(value="delete/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id){
        userService.removeUser(id);
        return new ModelAndView("redirect:/usersview");
    }

}