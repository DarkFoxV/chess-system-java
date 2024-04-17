package com.io.darkfox.chess;

import com.io.darkfox.boardgame.Board;
import com.io.darkfox.boardgame.Piece;

public abstract class ChessPiece extends Piece {
    private Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
