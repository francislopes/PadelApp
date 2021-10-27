package com.francis.padelapp.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class GameRequest {

    @NotNull(message = "Date is mandatory.")
    private LocalDateTime date;
    @NotNull(message = "Time is mandatory.")
    private LocalDateTime time;
    @NotBlank(message = "Address is mandatory.")
    private String address;
    private String comments;

}
