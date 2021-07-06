public class Pawn extends ChessPiece{
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }
    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //проверяем все ли координаты существуют
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn) &&
                //есть ли в этой клетке фигура
                chessBoard.board[line][column] != null) { // check that position in board
            //не ходим на искасок, то есть никого не едим
            if (column == toColumn) { // check that we don't want to eat
                int dir; //на сколько клеток мы продвигаемся
                int start;

                if (color.equals("White")) {  // for white piece
                    dir = 1; //направление хода для белых вверх по доске
                    start = 1; //стартовая линия на которой находится пешка
                } else { // for black piece
                    dir = -1; //направление хода для черных вниз по доске
                    start = 6; //стартовая линия на которой находится пешка
                }
                //проверяем, можно ли сходить в конечную клетку
                if (line + dir == toLine) {  //check direction
                    //если клетка свободна, функция вернет true
                    return chessBoard.board[toLine][toColumn] == null;
                }
                //если линия стартовая и ходим на 2 клетки
                if (line == start && line + 2 * dir == toLine) {
                    //если конечная клетка свободна и на пути нет фигур, функция вернет true
                    return chessBoard.board[toLine][toColumn] == null && chessBoard.board[line + dir][column] == null; // check that positions is null
                }

            } else { // want to eat piece(если рубим)
                //если по колонке и линии сдвигаемся на один
                if ((column - toColumn == 1 || column - toColumn == -1) && (line - toLine == 1 || line - toLine == -1) && // check that piece another color
                        //и на этой клетке есть фигура
                        chessBoard.board[toLine][toColumn] != null) {
                    //возвращаем true,если цвет съеденной фигуры не равен текущему
                    return !chessBoard.board[toLine][toColumn].getColor().equals(color);
                } else return false;
            }
        }
        return false;
    }
    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
