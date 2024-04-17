package com.io.darkfox.application;

import com.io.darkfox.chess.ChessMatch;
import com.io.darkfox.chess.ChessPiece;
import com.io.darkfox.chess.ChessPosition;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        while (true){
            UI.printBoard(chessMatch.getPieces());
            System.out.println();
            System.out.println("Source: ");
            ChessPosition source = UI.readChessPosition(scanner);
            System.out.println();
            System.out.println("Destination: ");
            ChessPosition destination = UI.readChessPosition(scanner);
            ChessPiece capturedPiece = ChessMatch.performChessMove(source, destination);
        }

    }
}