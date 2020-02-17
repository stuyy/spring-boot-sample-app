package com.ansonfoong.controllers;

import com.ansonfoong.models.User;
import com.ansonfoong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/")
    public List<User> getUsers() 
    {
        System.out.println("Get All Users.");
        return this.userService.getUsers();
    }
    @PostMapping(value="/")
    public User addUser(@RequestBody User user) 
    {
        return this.userService.addUser(user);
    }
    @GetMapping(value="/{id}")
    public User getUser(@PathVariable(value = "id") Long id) 
    {
        Optional<User> user = this.userService.getUser(id);
        System.out.println(this.userService.compare(user.get(), "mynamejeff"));
        return user.isPresent() ? user.get() : null;
    }
    @PutMapping(value="/update/username/{id}")
    public void updateUsername(@PathVariable(value="id") Long id, @RequestParam(value="username") String username) {
        this.userService.updateUsername(id, username);
    }

    @DeleteMapping(value="/delete/{id}")
    public HashMap<String, Object> deleteUser(@PathVariable(value="id") Long id) {
        try {
            System.out.println(id);
            this.userService.delete(id);
            HashMap<String, Object> m = new HashMap<>();
            m.put("status", 200);
            return m;
        }
        catch(Exception ex) {
            System.out.println(ex);
            HashMap<String, Object> m = new HashMap<>();
            m.put("status", 200);
            return m;
        }
    }
}
