package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("MyManager")
public class GameManager {

    private final Game game; // named autowiring

    private final Logger logger;

    // 1 drawback of field autowiring

    @Autowired
    public GameManager(@Qualifier("chess") Optional<Game> game,
                       Optional<Logger> logger) {
        this.game = game.isPresent() ? game.get() : null;
        this.logger = logger.isPresent() ? logger.get() : null;
    }

    public void manage() {
        game.play();
    }

    // great if you've optional dependencies
    /**@Autowired
    public void setGame(@Qualifier("ludo") Game game) {
        this.game = game;
    }

    @Autowired(required = false)
    public void setLogger(Logger logger) {
        this.logger = logger;
    }**/

}
