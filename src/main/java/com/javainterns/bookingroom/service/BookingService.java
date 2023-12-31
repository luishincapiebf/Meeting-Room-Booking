package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.model.dto.TimeSlot;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

public interface BookingService {
  BookingRequest create(BookingRequest bookingRequest, Principal principal);
  BookingRequest findById(Long id, Principal principal);
  Boolean delete(Long id);
  List<BookingRequest> findAll();
  List<BookingRequest> findBookingsByUsername(String username);
  Boolean deleteByUser(Long id, Principal principal);
  List<TimeSlot> findAvailableTimeSlots(Long id, LocalDate date);
}
