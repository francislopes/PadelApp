package com.francis.padelapp.model.response;

import com.francis.padelapp.enums.GameStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GameResponse {

    private Long id;
    private LocalDateTime date;
    private LocalDateTime time;
    private String Address;
    private GameStatus status;
    private String comments;

}
