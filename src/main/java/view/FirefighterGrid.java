package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import model.Board;
import model.Box;
import model.Fire;
import model.Immovable;
import model.Item;
import util.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirefighterGrid extends Canvas implements Grid{
    private int squareWidth;
    private int squareHeight;
    private int columnCount;
    private int rowCount;

    public void repaint(List<Item> itemList, List<Position> clearList, Board board) {
        clear(clearList, board);
        paintItems(itemList);
        paintLines();
    }

    private void clear(List<Position> positions, Board board) {
        Immovable immovable;
        for (Position position : positions) {
            clearSquare(position.row(), position.column());
            immovable = board.getBoxByPosition(position);
            if (immovable != null){
                immovable.paint(this);
            }else {
                paintSquare(position.row(), position.column(), Color.WHITE);
            }
        }
    }

    private void paintItems(List<Item> itemList) {
        for (Item item : itemList) {
            item.paint(this);
        }
    }

    private void paintBox(Board board){
        Immovable immovable;
        for (int row = 0; row < rowCount; row++){
            for (int column = 0; column < columnCount; column++){
                immovable = board.getBoxByPosition(new Position(row, column));
                if (immovable != null){
                    immovable.paint(this);
                }else {
                    paintSquare(row, column, Color.WHITE);
                }
            }
        }
    }

    public void initialize(Board board) {
        paintBox(board);
        paintLines();
        paintItems(board.itemList());
    }

    public int columnCount() {
        return columnCount;
    }

    public int rowCount() {
        return rowCount;
    }

    @Override
    public void setDimensions(int columnCount, int rowCount, int squareWidth, int squareHeight) {
        this.squareWidth = squareWidth;
        this.squareHeight = squareHeight;
        this.columnCount = columnCount;
        this.rowCount = rowCount;
        super.setWidth(squareWidth*columnCount);
        super.setHeight(squareHeight*rowCount);

    }

    private void paintLines(){
        paintHorizontalLines();
        paintVerticalLines();
    }

    private void paintVerticalLines() {
        for(int column = 0; column < columnCount; column++)
            getGraphicsContext2D().strokeLine(column*squareWidth, 0,column*squareWidth, getHeight());
    }

    private void paintHorizontalLines() {
        for(int row = 0; row < rowCount; row++)
            getGraphicsContext2D().strokeLine(0, row*squareHeight, getWidth(), row*squareHeight);
    }

    public void paintSquare(int row, int column, Color color){
        getGraphicsContext2D().setFill(color);
        getGraphicsContext2D().fillRect(column*squareWidth,row*squareHeight, squareWidth, squareHeight);
    }

    public void paintTriangle(int row, int column, Color color){
        getGraphicsContext2D().setFill(color);
        getGraphicsContext2D().fillPolygon(new double[]{column*squareWidth , column*squareWidth + squareWidth/2, (column+1)*squareWidth},
                new double[]{(row+1)*squareHeight , row*squareHeight, (row+1)*squareHeight},
                3);
    }

    public void paintCircle(int row, int column, Color color){
        getGraphicsContext2D().setFill(color);
        getGraphicsContext2D().fillOval(column*squareWidth, row*squareHeight, squareHeight, squareWidth);
    }

    private void clearSquare(int row, int column){
        getGraphicsContext2D().clearRect(column*squareWidth,row*squareHeight, squareWidth, squareHeight);
    }
}
