import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsolePlayer implements Player {

    @Override
    public Move getMove(Chess game) {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

        game.board.printBoardToConsole();

        // We'll be doing a simpler notation for our chess game. Notation will be a 5 or 6 character length. Form
        //       will take the shape of {a-h}{1-8}(\-x}{a-h}{1-8}{rnbqRNBQ}.
        //       First character is the from file
        //       Second character is the from rank
        //       Third character is - for move, x for capture
        //       Forth character is the to file
        //       Fifth character is the to rank
        //       Sixth character is the promotion of a pawn to a piece type. This is optional
        String move;
        try{
            move = inputReader.readLine();
        } catch (Exception e){
            move = "";
        }

        Pattern movePattern = Pattern.compile("^[a-h][1-8][-x][a-h][1-8][rnbqRNBQ]?");
        Matcher moveMatcher = movePattern.matcher(move);
        if(!moveMatcher.find()) {
            //Move is invalid;
            System.out.println("Move is invalid. Please input a valid move.");
            return getMove(game);
        }

        Square fromSquare = new Square(Integer.parseInt(move.substring(1,2)), Board.calcFileIndex(move.charAt(0)));
        boolean capture = (move.charAt(2) == 'x');
        Square toSquare = new Square(Integer.parseInt(move.substring(4,5)), Board.calcFileIndex(move.charAt(3)));

        String pawnPromotionPiece = null;
        if(move.length() == 6) {
            pawnPromotionPiece = move.substring(5,6);
        }

        return new Move(fromSquare, toSquare, pawnPromotionPiece, capture);
    }
}
