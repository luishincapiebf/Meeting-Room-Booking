package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.model.dto.BookingRequest;

import java.util.List;

public interface UserService {
  User create(User user);
  User findById(Long id);
  Boolean delete(Long id);
  List<User> findAll();
}
