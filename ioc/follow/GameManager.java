package exceptionhandling.classroom.ioc.follow;

import org.example.ioc.data.Game;

public class GameManager {

    private Game game;

    public GameManager(Game game) { // constr. dep inj
        // IoC
        this.game = game;
    }

    public void manage() {
        game.init();
        game.start();
        game.finish();
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
