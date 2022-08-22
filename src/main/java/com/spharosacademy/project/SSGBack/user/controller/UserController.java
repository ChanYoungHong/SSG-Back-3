package com.spharosacademy.project.SSGBack.user.controller;

import com.spharosacademy.project.SSGBack.order.dto.output.OrderOutputDto;
import com.spharosacademy.project.SSGBack.user.domain.User;
import com.spharosacademy.project.SSGBack.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final IUserService iUserService;
    @PostMapping("/user/add")
    public User addUser(@RequestBody User user) {
        return iUserService.addUser(user);
    }

    @GetMapping("/user/get/{id}")
    public User getUser(@PathVariable Long id) {
        return iUserService.getUserById(id);
    }

    @GetMapping("/user/getAll")
    public List<User> getAll() {
        return iUserService.getAll();
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        iUserService.deleteUser(id);
    }

    @PutMapping("/user/edit")
    public User editUser(@RequestBody User user) {
        return iUserService.editUser(user);
    }

    @GetMapping("/user/{userId}")
    public List<OrderOutputDto> getOrderList(@PathVariable Long userId){
        return iUserService.getOrderList(userId);
    }
}
