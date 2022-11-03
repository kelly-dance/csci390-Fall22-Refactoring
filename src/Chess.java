import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chess {

    Board board;
    Player white, black;

    public Chess() {
        board = new Board();
        white = new ConsolePlayer();
        black = new ConsolePlayer();
    }

    public void play() throws IOException {
        while(!board.gameIsOver()) {
            Player active = board.getTurnIsWhite() ? white : black;

            Move move = active.getMove(this);

            MoveStatus result;
            if(!move.getCapture()) {
                result = board.movePiece(move);
            } else {
                result = board.capturePiece(move);
            }
            switch (result){
                case InvalidMove:
                    System.out.println("Invalid move, try again.");
                    break;
                case WrongTeam:
                    System.out.println("Select a piece that belongs to your team.");
                    break;
                default:
            }
        }

        System.out.println("Game over.");
        System.out.println("Thanks for playing!");
    }


}
