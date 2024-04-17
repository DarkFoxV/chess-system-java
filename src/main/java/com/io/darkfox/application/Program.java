package com.io.darkfox.application;

import com.io.darkfox.boardgame.Board;
import com.io.darkfox.boardgame.Position;
import com.io.darkfox.chess.ChessMatch;

public class Program {
    public static void main(String[] args) {
        ChessMatch chessMatch = new ChessMatch();
        UI.printBoard(chessMatch.getPieces());

    }
}