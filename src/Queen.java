import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Queen extends Piece {

    public Queen(boolean isWhite) {
        super(isWhite);
        this.face = "q";
    }

    @Override
    public Set<Square> getActionLocations(Square location, Board board) {
        List<Point> directions = List.of(new Point(0,1), new Point(1,0), new Point(0,-1), new Point(0, -1),
                new Point(1, -1), new Point(-1, -1), new Point(-1, 1), new Point(1, 1));
        Set<Square> options = new HashSet<>();
        for(Point direction : directions){
            for(int i = 1; i < 8; i++){
                Square loc = new Square(location.getRankIndex() + direction.x, location.getFileIndex() + direction.y);
                options.add(loc);
                if(board.getBoardPiece(loc) != null) break;
            }
        }
        return options;
    }
}
