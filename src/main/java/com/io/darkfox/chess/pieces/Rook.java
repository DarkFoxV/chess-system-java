package com.io.darkfox.chess.pieces;

import com.io.darkfox.boardgame.Board;
import com.io.darkfox.chess.ChessPiece;
import com.io.darkfox.chess.Color;

public class Rook  extends ChessPiece {

    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "R";
    }
}
