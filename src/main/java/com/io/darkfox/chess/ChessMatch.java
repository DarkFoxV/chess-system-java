package com.io.darkfox.chess;

import com.io.darkfox.boardgame.Board;
import com.io.darkfox.boardgame.Piece;
import com.io.darkfox.boardgame.Position;
import com.io.darkfox.chess.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {
    private byte turn;
    private Color currentPlayerColor;
    private static Board board;
    private boolean check;
    private boolean checkMate;
    private final List<Piece> pieceOnTheBoard = new ArrayList<>();
    private final List<Piece> captured = new ArrayList<>();

    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayerColor = Color.WHITE;
        initialSetup();
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    public byte getTurn() {
        return turn;
    }

    public Color getCurrentPlayerColor() {
        return currentPlayerColor;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean isCheckMate() {
        return checkMate;
    }

    public boolean[][] possibleMoves(ChessPosition source) {
        Position pos = source.toPosition();
        validateSourcePosition(pos);
        return board.piece(pos).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        if (testCheck(currentPlayerColor)) {
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }
        check = testCheck(opponent(currentPlayerColor));
        if (testCheckMate(opponent(currentPlayerColor))) {
            checkMate = true;
        } else nextTurn();
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        ChessPiece p = (ChessPiece) board.removePiece(source);
        p.increaseMoveCount();
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        if (capturedPiece != null) {
            pieceOnTheBoard.remove(capturedPiece);
            captured.add(capturedPiece);
        }
        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturedPiece) {
        ChessPiece p = (ChessPiece) board.removePiece(target);
        p.decreaseMoveCount();
        board.placePiece(p, source);
        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            captured.remove(capturedPiece);
            pieceOnTheBoard.add(capturedPiece);
        }
    }

    private void validateSourcePosition(Position sourcePosition) {
        if (!board.thereIsAPiece(sourcePosition)) {
            throw new ChessException("There is no piece at position " + sourcePosition);
        }
        if (currentPlayerColor != (((ChessPiece) board.piece(sourcePosition)).getColor())) {
            throw new ChessException("The chosen piece is not yours");
        }
        if (!board.piece(sourcePosition).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible moves for the chosen piece " + sourcePosition);
        }
    }

    private static void validateTargetPosition(Position sourcePosition, Position targetPosition) {
        if (!board.piece(sourcePosition).possibleMove(targetPosition)) {
            throw new ChessException("There is no possible moves for the chosen piece " + sourcePosition);
        }
    }

    private void nextTurn() {
        turn++;
        currentPlayerColor = (currentPlayerColor == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private Color opponent(Color color) {
        return color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color) {
        List<Piece> list = pieceOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).toList();
        for (Piece p : list) {
            if (p instanceof King) {
                return (ChessPiece) p;
            }
        }
        throw new IllegalStateException("There is no " + color + " king on the board");
    }

    private boolean testCheck(Color color) {
        Position king = king(color).getChessPosition().toPosition();
        List<Piece> opponentPiece = pieceOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color)).toList();
        for (Piece piece : opponentPiece) {
            boolean[][] mat = piece.possibleMoves();
            if (mat[king.getRow()][king.getColumn()]) {
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(Color color) {
        if (!testCheck(color)) {
            return false;
        }
        List<Piece> list = pieceOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).toList();
        for (Piece p : list) {
            boolean[][] mat = p.possibleMoves();
            for (int i=0; i<board.getRows(); i++) {
                for (int j=0; j<board.getColumns(); j++) {
                    if (mat[i][j]) {
                        Position source = ((ChessPiece)p).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(source, target);
                        boolean testCheck = testCheck(color);
                        undoMove(source, target, capturedPiece);
                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        pieceOnTheBoard.add(piece);
    }

    private void initialSetup() {
        placeNewPiece('b', 1, new Horse(board, Color.WHITE));
        placeNewPiece('g', 1, new Horse(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));
        placeNewPiece('e', 1, new Queen(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));

        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 8, new Horse(board, Color.BLACK));
        placeNewPiece('g', 8, new Horse(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
        placeNewPiece('e', 8, new Queen(board, Color.BLACK));
    }
}
