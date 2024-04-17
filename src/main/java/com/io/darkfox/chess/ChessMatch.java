package com.io.darkfox.chess;

import com.io.darkfox.boardgame.Board;
import com.io.darkfox.boardgame.Piece;
import com.io.darkfox.boardgame.Position;
import com.io.darkfox.chess.pieces.King;
import com.io.darkfox.chess.pieces.Peon;
import com.io.darkfox.chess.pieces.Rook;

public class ChessMatch {
    private static Board board;

    public ChessMatch() {
        this.board = new Board(8, 8);
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

    public static ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        Piece capturedPiece = makeMove(source, target);
        return (ChessPiece) capturedPiece;
    }

    private static Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        return capturedPiece;
    }

    private static void validateSourcePosition(Position sourcePosition) {
        if (!board.thereIsAPiece(sourcePosition)) {
            throw new ChessException("There is no piece at position " + sourcePosition);
        }
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup() {
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));
        placeNewPiece('a', 2, new Peon(board, Color.WHITE));
        placeNewPiece('b', 2, new Peon(board, Color.WHITE));
        placeNewPiece('c', 2, new Peon(board, Color.WHITE));
        placeNewPiece('d', 2, new Peon(board, Color.WHITE));
        placeNewPiece('e', 2, new Peon(board, Color.WHITE));
        placeNewPiece('f', 2, new Peon(board, Color.WHITE));
        placeNewPiece('g', 2, new Peon(board, Color.WHITE));
        placeNewPiece('h', 2, new Peon(board, Color.WHITE));

        placeNewPiece('a', 7, new Peon(board, Color.BLACK));
        placeNewPiece('b', 7, new Peon(board, Color.BLACK));
        placeNewPiece('c', 7, new Peon(board, Color.BLACK));
        placeNewPiece('d', 7, new Peon(board, Color.BLACK));
        placeNewPiece('e', 7, new Peon(board, Color.BLACK));
        placeNewPiece('f', 7, new Peon(board, Color.BLACK));
        placeNewPiece('g', 7, new Peon(board, Color.BLACK));
        placeNewPiece('h', 7, new Peon(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
}
