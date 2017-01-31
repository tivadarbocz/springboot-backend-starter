package com.tb.web;

import com.tb.domain.User;
import com.tb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Tivadar Bocz on 2017.01.31..
 */

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll() {
        return userService.finAll();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }
}
