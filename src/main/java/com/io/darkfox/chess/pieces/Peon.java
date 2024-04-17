package com.io.darkfox.chess.pieces;

import com.io.darkfox.boardgame.Board;
import com.io.darkfox.chess.ChessPiece;
import com.io.darkfox.chess.Color;

public class Peon extends ChessPiece {

    public Peon(Board board, Color color) {
        super(board,color);
    }

    @Override
    public String toString(){
        return "P";
    }

}
