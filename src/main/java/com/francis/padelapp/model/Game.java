package com.francis.padelapp.model;

import com.francis.padelapp.enums.GameStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity(name = "tbl_game")
public class Game {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private LocalDateTime time;
    private String Address;
    private GameStatus status = GameStatus.BOOKING;
    private String comments;

}


