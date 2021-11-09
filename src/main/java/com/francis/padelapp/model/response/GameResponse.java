package com.francis.padelapp.model.response;

import com.francis.padelapp.enums.GameStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GameResponse {

    private Long id;
    private LocalDateTime date;
    private LocalDateTime time;
    private String address;
    private GameStatus status;
    private String comments;
    private String playerOne;
    private String playerTwo;
    private String playerThree;
    private String playerFour;

}
