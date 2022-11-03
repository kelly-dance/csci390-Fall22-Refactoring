import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {

    public Knight(boolean isWhite) {
        super(isWhite);
        this.face = "n";
    }

    @Override
    public Set<Square> getActionLocations(Square location, Board board){
        Set<Square> options = new HashSet<>();
        options.add(new Square(location.getRankIndex()+1, location.getFileIndex()+2));
        options.add(new Square(location.getRankIndex()-1, location.getFileIndex()+2));
        options.add(new Square(location.getRankIndex()+1, location.getFileIndex()-2));
        options.add(new Square(location.getRankIndex()-1, location.getFileIndex()-2));
        options.add(new Square(location.getRankIndex()+2, location.getFileIndex()+1));
        options.add(new Square(location.getRankIndex()-2, location.getFileIndex()+1));
        options.add(new Square(location.getRankIndex()+2, location.getFileIndex()-1));
        options.add(new Square(location.getRankIndex()-2, location.getFileIndex()-1));
        return options;
    }
}
