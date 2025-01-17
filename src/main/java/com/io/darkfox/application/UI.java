package com.io.darkfox.application;

import com.io.darkfox.chess.ChessMatch;
import com.io.darkfox.chess.ChessPiece;
import com.io.darkfox.chess.ChessPosition;
import com.io.darkfox.chess.Color;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UI {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void cleanUI(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner scanner) {
        try {
            String line = scanner.nextLine();
            char firstChar = line.charAt(0);
            int row = Integer.parseInt(line.substring(1));
            return new ChessPosition(firstChar, row);
        }
        catch (Exception e) {
            throw new InputMismatchException("Invalid chess position");
        }
    }
    public static void printBoard(ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPieces(pieces[i][j],false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printBoard(ChessPiece[][] pieces, boolean [][] possibleMoves) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPieces(pieces[i][j],possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printMatch(ChessMatch match, List<ChessPiece> pieces) {
        printBoard(match.getPieces());
        System.out.println();
        printCapturedPiece(pieces);
        System.out.println("Turn: " + match.getTurn());
        if(match.getCheckMate()){
            System.out.println("Waiting players: " + match.getCurrentPlayerColor());
            if (match.getCheck()){
                System.out.println("Check");
            }

        }
        else {
            System.out.println("CHECKMATE");
            System.out.println("Winner is: " + match.getCurrentPlayerColor());
        }

    }

    private static void printPieces(ChessPiece piece, boolean background) {
        if (background) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (piece == null) {
            System.out.print("-"+ ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }
    private static void printCapturedPiece(List<ChessPiece> capturedChessPiece) {
        List<ChessPiece> white = capturedChessPiece.stream().filter(x -> x.getColor() == Color.WHITE).toList();
        List<ChessPiece> black = capturedChessPiece.stream().filter(x -> x.getColor() == Color.BLACK).toList();
        System.out.println("Captured Piece:");
        System.out.print("White:");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("BLACK:");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);
    }
}
