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
        ChessMatch match = new ChessMatch();
        while (true) {
            try {
                UI.cleanUI();
                UI.printMatch(match);
                System.out.println();
                System.out.println("Source: ");
                ChessPosition source = UI.readChessPosition(scanner);
                boolean[][] possibleMoves = match.possibleMoves(source);
                UI.cleanUI();
                UI.printBoard(match.getPieces(), possibleMoves);
                System.out.println();
                System.out.println("Destination: ");
                ChessPosition destination = UI.readChessPosition(scanner);
                ChessPiece capturedPiece = match.performChessMove(source, destination);
            } catch (ChessException | InputMismatchException che) {
                System.out.println(che.getMessage());
            }
        }
    }
}