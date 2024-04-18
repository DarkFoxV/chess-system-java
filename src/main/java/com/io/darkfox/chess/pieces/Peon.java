package com.io.darkfox.chess.pieces;

import com.io.darkfox.boardgame.Board;
import com.io.darkfox.boardgame.Position;
import com.io.darkfox.chess.ChessPiece;
import com.io.darkfox.chess.Color;

public class Peon extends ChessPiece {

    public Peon(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        byte i = 0;
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);
        if (getColor().equals(Color.WHITE)) {
            p.setValue(position.getRow() - 1, position.getColumn());
            while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && i < 2) {
                mat[p.getRow()][p.getColumn()] = true;
                p.setRow(p.getRow() - 1);
                i++;
            }
        } else {
            p.setValue(position.getRow() + 1, position.getColumn());
            while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && i < 2 && getColor().equals(Color.BLACK)) {
                mat[p.getRow()][p.getColumn()] = true;
                p.setRow(p.getRow() + 1);
                i++;
            }
        }
        if (getBoard().positionExists(p) && isThereOppenentPiece(p) && i < 1) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        return mat;
    }

}
