package com.javainterns.bookingroom.model.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BookingRequest {
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private Long roomId;
    @NotNull
    @FutureOrPresent(message = "Date error")
    private LocalDate date;
    @NotNull
    @Size(min = 0, max = 23)
    private Integer startTime;
    @NotNull
    @Size(min = 0, max = 23)
    private Integer endTime;

    public BookingRequest(){}

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }
}