package com.io.darkfox.chess;

import com.io.darkfox.boardgame.Position;

public class ChessPosition {
    private final char column;
    private final int row;

    public ChessPosition(char column, int row) {
        if (column < 'a' || column > 'h' || row < 1 || row > 8) {
            throw new ChessException("Invalid chess position");
        }
        this.column = column;
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Position getPosition() {
        return new Position(column, row);
    }

    protected Position toPosition() {
        return new Position(8 - row, column - 'a');
    }

    protected static ChessPosition fromPosition(Position pos) {
        return new ChessPosition((char) ('a' + pos.getColumn()), 8 - pos.getRow());
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}
