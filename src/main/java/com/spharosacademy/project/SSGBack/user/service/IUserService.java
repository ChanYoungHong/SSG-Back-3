package com.spharosacademy.project.SSGBack.user.service;

import com.spharosacademy.project.SSGBack.order.dto.output.OrderOutputDto;
import com.spharosacademy.project.SSGBack.user.domain.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);

    User getUserById(Long id);

    User editUser(User user);

    List<User> getAll();

    void deleteUser(Long id);

    List<OrderOutputDto> getOrderList(Long userId);
}
