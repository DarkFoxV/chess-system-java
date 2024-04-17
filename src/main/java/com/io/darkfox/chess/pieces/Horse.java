package com.io.darkfox.chess.pieces;

import com.io.darkfox.boardgame.Board;
import com.io.darkfox.chess.ChessPiece;
import com.io.darkfox.chess.Color;

public class Horse extends ChessPiece {

    public Horse(Board board, Color color) {
        super(board,color);
    }

    @Override
    public String toString(){
        return "H";
    }

    @Override
    public boolean[][] possibleMoves() {
        return new boolean[getBoard().getRows()][getBoard().getColumns()];

    }

}
