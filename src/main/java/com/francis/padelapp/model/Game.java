package com.francis.padelapp.model;

import com.francis.padelapp.enums.GameStatus;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDateTime;

@Audited
@Data
@Entity(name = "tbl_game")
public class Game {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private LocalDateTime time;
    private String address;
    @Enumerated
    private GameStatus status = GameStatus.OPEN;
    private String comments;
    private String playerOne;
    private String playerTwo;
    private String playerThree;
    private String playerFour;


}


