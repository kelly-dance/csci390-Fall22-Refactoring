import java.util.Objects;

public class Square {
    private int rankIndex;
    private int fileIndex;

    public Square(int rank, int file){
        rankIndex = rank;
        fileIndex = file;
    }

    public int getRankIndex() {
        return rankIndex;
    }

    public void setRankIndex(int rankIndex) {
        this.rankIndex = rankIndex;
    }

    public int getFileIndex() {
        return fileIndex;
    }

    public void setFileIndex(int fileIndex) {
        this.fileIndex = fileIndex;
    }

    @Override
    public String toString(){
        return "("+rankIndex + ", " + fileIndex + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return rankIndex == square.rankIndex && fileIndex == square.fileIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rankIndex, fileIndex);
    }
}
