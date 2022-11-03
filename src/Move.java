public class Move {
    private final Square fromSquare;
    private final Square toSquare;
    private final String promotion;
    private final boolean capture;

    public Move(Square fromSquare, Square toSquare, String promotion, boolean capture){
        this.fromSquare = fromSquare;
        this.toSquare = toSquare;
        this.promotion = promotion;
        this.capture = capture;
    }

    public Square getFromSquare() {
        return fromSquare;
    }

    public Square getToSquare() {
        return toSquare;
    }

    public String getPromotion() {
        return promotion;
    }

    public boolean getCapture() {
        return capture;
    }
}
