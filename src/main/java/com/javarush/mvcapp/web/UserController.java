package com.javarush.mvcapp.web;

import com.javarush.mvcapp.domain.User;
import com.javarush.mvcapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Andriana_Yarmoliuk on 11/11/2016.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
//
//    @RequestMapping("/")
//    public String home() {
//        return "redirect:/usersview";
//    }
//
//


    /* It provides list of users in model object */
//    @RequestMapping("/usersview")
//    public ModelAndView viewusers(){
//        List<User> list=userService.listUser();
//        return new ModelAndView("usersview","list",list);
//    }


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

    /*It saves object into database. The @ModelAttribute puts request data
     *  into model object. You need to mention RequestMethod.POST method
     *  because default request is GET*/

//    @RequestMapping(value="save", method = RequestMethod.POST)
//    public ModelAndView save(@ModelAttribute("user") User user){
//        userService.addUser(user);
//        return new ModelAndView("redirect:/usersview");//will redirect to usersview request mapping
//    }


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

        /*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new

String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }*/

        userService.updateUser(user);

        model.addAttribute("success", "User " + user.getName() + " "+ user.getIsAdmin() + " updated successfully");
        return "registrationsuccess";
    }

    /* It displays object data into form for the given id.
     * The @PathVariable puts URL data into variable.*/
//    @RequestMapping(value="edit/{id}")
//    public ModelAndView edit(@PathVariable int id){
//        User user = userService.getUser(id);
//        return new ModelAndView("usereditform","command", user);
//    }
//    /* It updates model object. */
//    @RequestMapping(value="editsave",method = RequestMethod.POST)
//    public ModelAndView editsave(@ModelAttribute("user") User user){
//        userService.updateUser(user);
//        return new ModelAndView("redirect:/usersview");
//    }
    /* It deletes record for the given id in URL and redirects to /usersview */
    @RequestMapping(value="/delete-user-{id}",method = RequestMethod.GET)
    public String delete(@PathVariable String id){
        userService.removeUser(id);
        return "redirect:/usersview";
    }

    @RequestMapping(value = "/search-user{text}", method = RequestMethod.GET)
    public String search(String q, Model model, Integer offset, Integer maxResults) {
        List searchResults = null;
        try {
            searchResults = userService.searchUser(q, offset, maxResults);
        }
        catch (Exception ex) {
            // here you should handle unexpected errors
            // ...
            // throw ex;
        }
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("count", userService.count());
        model.addAttribute("offset", offset);
        return "redirect:/searchresults";
    }

    @RequestMapping(value = "/search-user{text}", method = RequestMethod.POST)
    public String searchList(Model model){
        return "";
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchPage()
    {
        ModelAndView mav = new ModelAndView("search");
        return mav;
    }

    @RequestMapping(value = "/doSearch", method = RequestMethod.POST)
    public ModelAndView search(
            @RequestParam("searchText")
                    String searchText
    ) throws Exception
    {
        List<User> allFound = _repo.searchForBook(searchText);
        List<User> bookModels = new ArrayList<User>();

        for (User b : allFound)
        {
            User bm = new User();
//            bm.setBookAuthor(b.getAuthor());
//            bm.setBookDescription(b.getDescription());
//            bm.setBookTitle(b.getTitle());

            bookModels.add(bm);
        }

        ModelAndView mav = new ModelAndView("foundBooks");
        mav.addObject("foundBooks", bookModels);
        return mav;
    }
}
}