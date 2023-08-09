package com.javainterns.bookingroom.utils;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.Room;

@Component
public class TimeValidation {
    public Boolean isValidTimeRange(LocalTime endTime, LocalTime startTime){
        return endTime.isAfter(startTime);
    }

    public Boolean bookingHourValidation(Booking booking, List<Booking> booked){
        return booked.stream().allMatch(x ->
                (booking.getEndTime()<=x.getStartTime())
                        ||
                        (booking.getStartTime()>=x.getEndTime())
                        ||
                        (booking.getStartTime()>=x.getEndTime() && booking.getEndTime() ==0));
    }

    public Boolean bookingRoomHourValidation(Booking booking, Room room){
        return (booking.getStartTime()>=room.getStartTime() && booking.getEndTime()<=room.getFinishTime());
    }
}
