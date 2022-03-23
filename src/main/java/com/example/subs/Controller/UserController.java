package com.example.subs.Controller;
import com.example.subs.Services.UserServices;
import com.example.subs.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//configure this class as controller
@RestController
//to map web requests of subscriber to this class
@RequestMapping(path="/subscriber")
public class UserController {

    // link between the Services and controller to allow the implementation of the services base on the web request
    @Autowired
    UserServices userServices;

    //map the HTTP POST requests with the certain path to the associate method
    @PostMapping(path="/add")

    /*@RequestBody: bound the value of the HTTP request body to specified parameter.
     * addNewUser: add new user to the user table
     */
    public User addNewUser (@RequestBody User user) {
        return userServices.addNewUser(user);
    }

    //map the HTTP GET requests with the certain path to the associate method
    @GetMapping(path="/users")

    // return all the users in the user table
    public Iterable<User> getUsers() {
        return userServices.getAllUsers();
    }

    @GetMapping(path="users/{id}")

    //indicate that the result would be written straight in the response body
    @ResponseBody

    /*@PathVariable: extract the value from the URI and bind it to parameter.
     * return  user from user table
     */
    public User getUser(@PathVariable ("id") Long id) {
        return userServices.getUser(id);
    }

    @PostMapping(path="/update/{id}")

    //update the user information based on the send data
    public User updateUser (@PathVariable ("id")Long id, @RequestBody User user) {
        return userServices.updateUser(id, user) ;
    }

    @PostMapping(path="/depositBalance/{id}/{balanceValue}")
    //update the user balance based on the send data
    public User deposit (@PathVariable ("id")Long id, @PathVariable ("balanceValue")double numb) {
        return userServices.deposit(id,numb) ;
    }

    @PostMapping(path="/withdrawBalance/{id}/{balanceValue}")
    //update the user balance based on the send data
    public User withdrawUser (@PathVariable ("id")Long id, @PathVariable ("balanceValue")double numb) {
        return userServices.withdraw(id,numb) ;
    }

    @PostMapping(path="/deleteAll")

    //delete all the users in the user table
    public  void deleteAllUsers () {
         userServices.deleteAllUsers();
    }

    @PostMapping(path="/delete/{id}")

    //delete user from the user table
    public  void deleteUser (@PathVariable ("id")Long id) {
         userServices.deleteUser(id);
    }
}
