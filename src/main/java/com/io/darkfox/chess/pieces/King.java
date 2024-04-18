package com.io.darkfox.chess.pieces;

import com.io.darkfox.boardgame.Board;
import com.io.darkfox.boardgame.Position;
import com.io.darkfox.chess.ChessPiece;
import com.io.darkfox.chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }
    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }
    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0,0);
        //above
        p.setValue(position.getRow()-1, position.getColumn());
        if(getBoard().positionExists(p)&& canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //down
        p.setValue(position.getRow()+1, position.getColumn());
        if(getBoard().positionExists(p)&& canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //left
        p.setValue(position.getRow(), position.getColumn()-1);
        if(getBoard().positionExists(p)&& canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //right
        p.setValue(position.getRow(), position.getColumn()+1);
        if(getBoard().positionExists(p)&& canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //NW
        p.setValue(position.getRow()-1, position.getColumn()-1);
        if(getBoard().positionExists(p)&& canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //NE
        p.setValue(position.getRow()-1, position.getColumn()+1);
        if(getBoard().positionExists(p)&& canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //SW
        p.setValue(position.getRow()+1, position.getColumn()-1);
        if(getBoard().positionExists(p)&& canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //SE
        p.setValue(position.getRow()+1, position.getColumn()+1);
        if(getBoard().positionExists(p)&& canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        return mat;
    }
}
