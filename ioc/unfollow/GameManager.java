package exceptionhandling.classroom.ioc.unfollow;

import org.example.ioc.data.Chess;
import org.example.ioc.data.Game;
import org.example.ioc.data.GameType;
import org.example.ioc.data.TicTacToe;

public class GameManager {

    public void manage(GameType gameType) {
        Game game = null;
        if (gameType.equals(GameType.CHESS)) {
            game = new Chess();// specific obj
        } else if (gameType.equals(GameType.TIC_TAC_TOE)) {
            game = new TicTacToe();
        }
        game.init();
        game.start();
        game.finish();
    }

}
