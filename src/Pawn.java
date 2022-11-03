import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    private static final int whiteStart = 2;
    private static final int blackStart = 7;

    public Pawn(boolean isWhite) {
        super(isWhite);
        this.face = "p";
    }

    @Override
    public Set<Square> getCaptureLocations(Square location, Board board) {
        Set<Square> options = new HashSet<>();
        if(isWhite){
            Square right = new Square(location.getRankIndex()+1, location.getFileIndex()+1);
            Square left = new Square(location.getRankIndex()+1, location.getFileIndex()-1);
            if(board.getBoardPiece(right) != null) options.add(right);
            if(board.getBoardPiece(left) != null) options.add(left);
        }else{
            Square right = new Square(location.getRankIndex()-1, location.getFileIndex()+1);
            Square left = new Square(location.getRankIndex()-1, location.getFileIndex()-1);
            if(board.getBoardPiece(right) != null) options.add(right);
            if(board.getBoardPiece(left) != null) options.add(left);
        }
        return boundCheckActions(options);
    }

    @Override
    public Set<Square> getMoveLocations(Square location, Board board) {
        Set<Square> options = new HashSet<>();
        if(isWhite){
            Square oneForward = new Square(location.getRankIndex() + 1, location.getFileIndex());
            if(board.getBoardPiece(oneForward) == null) {
                options.add(oneForward);
                Square twoForward = new Square(location.getRankIndex() + 2, location.getFileIndex());
                if (location.getRankIndex() == whiteStart && board.getBoardPiece(twoForward) == null)
                    options.add(twoForward);
            }
        }else{
            Square oneForward = new Square(location.getRankIndex() - 1, location.getFileIndex());
            if(board.getBoardPiece(oneForward) == null) {
                options.add(oneForward);
                Square twoForward = new Square(location.getRankIndex() - 2, location.getFileIndex());
                if (location.getRankIndex() == blackStart && board.getBoardPiece(twoForward) == null)
                    options.add(twoForward);
            }
        }
        return boundCheckActions(options);
    }
}
