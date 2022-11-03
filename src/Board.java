import java.util.Set;

public class Board {
    private Piece[][] board = new Piece[8][8];
    private boolean playerTurnIsWhite;

    public Board(){
        setupBlackPieces();
        setupWhitePieces();
        playerTurnIsWhite = true;
    }

    private void setupBlackPieces() {
        board[6][0] = new Pawn(false);
        board[6][1] = new Pawn(false);
        board[6][2] = new Pawn(false);
        board[6][3] = new Pawn(false);
        board[6][4] = new Pawn(false);
        board[6][5] = new Pawn(false);
        board[6][6] = new Pawn(false);
        board[6][7] = new Pawn(false);
        board[7][0] = new Rook(false);
        board[7][1] = new Knight(false);
        board[7][2] = new Bishop(false);
        board[7][3] = new Queen(false);
        board[7][4] = new King(false);
        board[7][5] = new Bishop(false);
        board[7][6] = new Knight(false);
        board[7][7] = new Rook(false);
    }

    private void setupWhitePieces() {
        board[1][0] = new Pawn(true);
        board[1][1] = new Pawn(true);
        board[1][2] = new Pawn(true);
        board[1][3] = new Pawn(true);
        board[1][4] = new Pawn(true);
        board[1][5] = new Pawn(true);
        board[1][6] = new Pawn(true);
        board[1][7] = new Pawn(true);
        board[0][0] = new Rook(true);
        board[0][1] = new Knight(true);
        board[0][2] = new Bishop(true);
        board[0][3] = new King(true);
        board[0][4] = new Queen(true);
        board[0][5] = new Bishop(true);
        board[0][6] = new Knight(true);
        board[0][7] = new Rook(true);
    }

    public MoveStatus movePiece(Square fromSquare, Square toSquare) {
        int fromFileIndex = fromSquare.getFileIndex();
        int fromRankIndex = fromSquare.getRankIndex();
        int toFileIndex = toSquare.getFileIndex();
        int toRankIndex = toSquare.getRankIndex();
        Piece fromPiece = getBoardPiece(fromSquare);


        if(correctPlayerNotMovingTheirPiece(fromPiece)) return MoveStatus.WrongTeam;

        Set<Square> options = fromPiece.getMoveLocations(fromSquare, this);
        if(!options.contains(toSquare)) return MoveStatus.InvalidMove;

        //If we have gotten here, that means the move is valid and update the board position
        board[toRankIndex-1][toFileIndex-1] = fromPiece;
        board[fromRankIndex-1][fromFileIndex-1] = null;

        //Change the player's turn
        playerTurnIsWhite = !playerTurnIsWhite;

        return MoveStatus.Accepted;
    }

    public MoveStatus movePiece(Square fromSquare, Square toSquare, String pawnPromotionPiece) {
        if(movePiece(fromSquare, toSquare) == MoveStatus.Accepted){
            if(getBoardPiece(fromSquare) instanceof Pawn && (fromSquare.getRankIndex() == 1 || fromSquare.getRankIndex() == 8))
                board[toSquare.getRankIndex()-1][toSquare.getFileIndex()-1] = getPieceFromChar(pawnPromotionPiece, !playerTurnIsWhite);
            return MoveStatus.Accepted;
        }
        return MoveStatus.InvalidMove;
    }

    public MoveStatus movePiece(Move move) {
        if(move.getPromotion() == null) return movePiece(move.getFromSquare(), move.getToSquare());
        else return movePiece(move.getFromSquare(), move.getToSquare(), move.getPromotion());
    }

    public boolean correctPlayerNotMovingTheirPiece(Piece fromPiece) {
        if(fromPiece == null) return true;

        //Check that the piece is owned by the correct player.
        return fromPiece.toString().toLowerCase().equals(fromPiece.toString());
    }

    // TODO: Homework - Refactor this method to use a single parameter

    public MoveStatus capturePiece(Square fromSquare, Square toSquare) {
        int fromFileIndex = fromSquare.getFileIndex();
        int fromRankIndex = fromSquare.getRankIndex();
        int toFileIndex = toSquare.getFileIndex();
        int toRankIndex = toSquare.getRankIndex();
        Piece fromPiece = getBoardPiece(fromSquare);


        if(correctPlayerNotMovingTheirPiece(fromPiece)) return MoveStatus.WrongTeam;

        Set<Square> options = fromPiece.getCaptureLocations(fromSquare, this);
        if(!options.contains(toSquare)) return MoveStatus.InvalidMove;

        //If we have gotten here, that means the move is valid and update the board position
        board[toRankIndex-1][toFileIndex-1] = fromPiece;
        board[fromRankIndex-1][fromFileIndex-1] = null;

        //Change the player's turn
        playerTurnIsWhite = !playerTurnIsWhite;

        return MoveStatus.Accepted;
    }

    public MoveStatus capturePiece(Square fromSquare, Square toSquare, String pawnPromotionPiece) {
        if(capturePiece(fromSquare, toSquare) == MoveStatus.Accepted){
            if(getBoardPiece(fromSquare) instanceof Pawn && (fromSquare.getRankIndex() == 1 || fromSquare.getRankIndex() == 8))
                board[toSquare.getRankIndex()-1][toSquare.getFileIndex()-1] = getPieceFromChar(pawnPromotionPiece, !playerTurnIsWhite);
            return MoveStatus.Accepted;
        }
        return MoveStatus.InvalidMove;
    }

    public MoveStatus capturePiece(Move move) {
        if(move.getPromotion() == null) return capturePiece(move.getFromSquare(), move.getToSquare());
        else return capturePiece(move.getFromSquare(), move.getToSquare(), move.getPromotion());
    }

    public void printBoardToConsole() {
        StringBuilder sb = new StringBuilder();
        for (int rankNum = 8; rankNum > 0; rankNum--) {
            sb.append(rankNum + " ");
            for(Piece piece : board[rankNum-1]) {
                if(piece != null) {
                    sb.append(piece.getDisplay());
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        sb.append("  abcdefgh");
        System.out.println(sb);
    }

    public Piece getBoardPiece(Square loc){
        if(loc.getRankIndex() < 1 || loc.getRankIndex() > 8 || loc.getFileIndex() < 1 || loc.getFileIndex() > 8) return null;
        return board[loc.getRankIndex() - 1][loc.getFileIndex() - 1];
    }

    public boolean gameIsOver() {
        return isPositionCheckmate() || isPositionStalemate();
    }

    private boolean isPositionStalemate() {
        return false;
    }

    private boolean isPositionCheckmate() {
        return false;
    }

    public static int calcFileIndex(Character file) {
        return file - 'a' + 1;
    }

    private static Piece getPieceFromChar(String p, boolean isWhite){
        return switch (p) {
            case "n" -> new Knight(isWhite);
            case "r" -> new Rook(isWhite);
            case "b" -> new Bishop(isWhite);
            case "q" -> new Queen(isWhite);
            default -> null;
        };
    }

    boolean getTurnIsWhite(){
        return playerTurnIsWhite;
    }
}
