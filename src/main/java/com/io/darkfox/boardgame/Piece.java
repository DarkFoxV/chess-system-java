package com.io.darkfox.boardgame;

public abstract class Piece {
    private Board board;
    protected Position position;

    public Piece(Board board) {
        this.board = board;
    }

    protected Board getBoard() {
        return board;
    }
    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }
    public boolean isThereAnyPossibleMove() {
        boolean[][] possibleMoves = possibleMoves();
        for (boolean[] possibleMove : possibleMoves) {
            if (possibleMove[position.getRow()]) {
                return true;
            }
        }
        return false;
    }
}
