package com.io.darkfox.application;

import com.io.darkfox.chess.ChessException;
import com.io.darkfox.chess.ChessMatch;
import com.io.darkfox.chess.ChessPiece;
import com.io.darkfox.chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        while (true) {
            try {
                UI.cleanUI();
                UI.printBoard(chessMatch.getPieces());
                System.out.println();
                System.out.println("Source: ");
                ChessPosition source = UI.readChessPosition(scanner);
                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.cleanUI();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);
                System.out.println();
                System.out.println("Destination: ");
                ChessPosition destination = UI.readChessPosition(scanner);
                ChessPiece capturedPiece = ChessMatch.performChessMove(source, destination);
            } catch (ChessException | InputMismatchException che) {
                System.out.println(che.getMessage());
            }
        }
    }
}