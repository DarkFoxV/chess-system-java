package com.io.darkfox.application;

import com.io.darkfox.chess.ChessException;
import com.io.darkfox.chess.ChessMatch;
import com.io.darkfox.chess.ChessPiece;
import com.io.darkfox.chess.ChessPosition;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChessMatch match = new ChessMatch();
        List<ChessPiece> pieces = new ArrayList<>();
        while (match.getCheckMate()) {
            try {
                UI.cleanUI();
                UI.printMatch(match,pieces);
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
                if (capturedPiece != null) {
                    pieces.add(capturedPiece);
                }
                if (match.getPromoted() != null) {
                    System.out.print("Enter piece for promotion (B/N/R/Q): ");
                    String type = scanner.nextLine().toUpperCase();
                    while (!type.equals("B") && !type.equals("N") && !type.equals("R") & !type.equals("Q")) {
                        System.out.print("Invalid value! Enter piece for promotion (B/N/R/Q): ");
                        type = scanner.nextLine().toUpperCase();
                    }
                    match.replacePromotedPiece(type);
                }

            } catch (ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
        UI.cleanUI();
        UI.printMatch(match,pieces);
    }
}