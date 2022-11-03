import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Piece {
    protected String face;
    protected boolean isWhite;

    public Piece(boolean isWhite){
        this.face = "?";
        this.isWhite = isWhite;
    }

    // returns locations of valid moves or valid captures
    // useful in most pieces
    public Set<Square> getActionLocations(Square location, Board board){
        return new HashSet<>();
    }

    public Set<Square> getMoveLocations(Square location, Board board){
        return boundCheckActions(getActionLocations(location, board)
                .stream()
                .filter(loc -> board.getBoardPiece(loc) == null)
                .collect(Collectors.toSet()));
    }

    public Set<Square> getCaptureLocations(Square location, Board board){
        return boundCheckActions(getActionLocations(location, board)
                .stream()
                .filter(loc -> board.getBoardPiece(loc) != null && board.getBoardPiece(loc).isWhite != isWhite)
                .collect(Collectors.toSet()));
    }

    protected static Set<Square> boundCheckActions(Set<Square> options){
        return options.stream().filter(loc -> {
            if(loc.getRankIndex() < 1 || loc.getRankIndex() > 8) return false;
            if(loc.getFileIndex() < 1 || loc.getFileIndex() > 8) return false;
            return true;
        }).collect(Collectors.toSet());
    }

    public String getDisplay(){
        if(isWhite) return face.toUpperCase();
        else return face.toLowerCase();
    }
}
