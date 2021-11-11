package com.francis.padelapp.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class GameRequest {

    @NotNull(message = "Date is mandatory.")
    private LocalDate date;
    @NotNull(message = "Time is mandatory.")
   // private LocalTime time;
    @NotBlank(message = "Address is mandatory.")
    private String address;
    private String comments;
    private String playerOne;
    private String playerTwo;
    private String playerThree;
    private String playerFour;





}
