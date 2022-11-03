import java.util.HashSet;
import java.util.Set;

public class King extends Piece {

    public King(boolean isWhite) {
        super(isWhite);
        this.face = "k";
    }

    @Override
    public Set<Square> getActionLocations(Square location, Board board) {
        Set<Square> options = new HashSet<>();
        options.add(new Square(location.getRankIndex()+1, location.getFileIndex()+1));
        options.add(new Square(location.getRankIndex()-1, location.getFileIndex()+1));
        options.add(new Square(location.getRankIndex()+1, location.getFileIndex()-1));
        options.add(new Square(location.getRankIndex()-1, location.getFileIndex()-1));
        options.add(new Square(location.getRankIndex(), location.getFileIndex()+1));
        options.add(new Square(location.getRankIndex(), location.getFileIndex()-1));
        options.add(new Square(location.getRankIndex()+1, location.getFileIndex()));
        options.add(new Square(location.getRankIndex()-1, location.getFileIndex()));
        return options;
    }
}
