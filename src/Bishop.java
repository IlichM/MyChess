public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }
    @Override
    public String getColor() {
        return this.color;
    }
    @Override
    public String getSymbol() {
        return "B";
    }
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // check that we can move to position and can't moved out from board or in not empty position
        //начальная точка не равна конечной
        if (line != toLine && column != toColumn &&
                //и ходит по диагонали
                getMax(line, toLine) - getMin(line, toLine) == getMax(column, toColumn) - getMin(column, toColumn) &&
                //и все координаты существуют
                checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn) &&
                //и конечная точка пустая или на ней стоит фигура другого цвет
                (chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].color.equals(this.color)) &&
                //и стартовя клетка не пустая
                chessBoard.board[line][column] != null) {

            if (!chessBoard.board[line][column].equals(this)) {
                return false;
            }

            // from up-left to down-right
            if ((column == getMin(column, toColumn) && line == getMax(line, toLine)) ||
                    (toColumn == getMin(column, toColumn) && toLine == getMax(line, toLine))) {
                //макс и мин нужны чтобы можно было делать обратный ход(т.е. сверху справа - вниз налево)
                int fromL = getMax(line, toLine);
                int fromC = getMin(column, toColumn);
                int toL = getMin(line, toLine);
                int toC = getMax(column, toColumn);
                //позиции, которые слон проходит по пути
                int[][] positions = new int[toC - fromC][1];
                //число колонок = числу линий пройденных слоном
                for (int i = 1; i < toC - fromC; i++) {
                    if (chessBoard.board[fromL - i][fromC + i] == null) {
                        positions[i - 1] = new int[]{fromL - i, fromC + i};
                      //ниже условия, если кто то будет на пути, то слон его срубит
                    } else if (!chessBoard.board[fromL - i][fromC + i].color.equals(this.color) && fromL - i == toLine) {
                        positions[i - 1] = new int[]{fromL - i, fromC + i};
                    } else {
                        return false;
                    }
                }
                return true;
            } else {
                // from down-left to up-right
                int fromL = getMin(line, toLine);
                int fromC = getMin(column, toColumn);
                int toL = getMax(line, toLine);
                int toC = getMax(column, toColumn);
                int[][] positions = new int[toC - fromC][1];
                for (int i = 1; i < toC - fromC; i++) {
                    if (chessBoard.board[fromL + i][fromC + i] == null) {
                        positions[i - 1] = new int[]{fromL + i, fromC + i};
                    } else if (!chessBoard.board[fromL + i][fromC + i].color.equals(this.color) && fromL + i == toLine) {
                        positions[i - 1] = new int[]{fromL + i, fromC + i};
                    } else {
                        return false;
                    }
                }
                return true;
            }
        } else return false;
    }
    public int getMax(int a, int b) {
        return Math.max(a, b);
    }
    public int getMin(int a, int b) {
        return Math.min(a, b);
    }
    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
