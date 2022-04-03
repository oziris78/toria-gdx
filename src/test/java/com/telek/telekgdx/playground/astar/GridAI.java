package com.telek.telekgdx.playground.astar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;


public class GridAI {

    private final GridCell[][] gridCells;

    public void draw(ShapeRenderer shapeRenderer) {
        for (int i = 0; i < gridCells.length; i++) {
            for (int j = 0; j < gridCells[0].length; j++) {
                GridCell cell = getCell(i, j);
                shapeRenderer.setColor(cell.color);
                shapeRenderer.rect(cell.box.x, cell.box.y, cell.box.width, cell.box.height);
            }
        }

    }

    public static class GridCell{
        Rectangle box;
        Color color;
        int row, col;
        float G, H, F;
        boolean isSolid = false;
        public GridCell(float x, float y, int tileSize, int row, int col){
            box = new Rectangle(x, y, tileSize, tileSize);
            color = Color.LIGHT_GRAY; // beginning color
            this.row = row;
            this.col = col;
            G = 0f; H = 0f; F = 0f;
        }
        public void setSolid(){
            this.isSolid = true;
            this.color = Color.DARK_GRAY;
        }
        public boolean isSameAs(GridCell cell){
            return cell.row == this.row && cell.col == this.col;
        }
    }

    public GridAI(int leftBottomX, int leftBottomY, int rowSize, int colSize, int tileSize){
        gridCells = new GridCell[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                gridCells[i][j] = new GridCell(leftBottomX + i * tileSize, leftBottomY + j * tileSize, tileSize, i, j);
            }
        }
    }


    public void makeCellSolid(int row, int col){
        gridCells[row][col].setSolid();
    }

    public void makeCellsSolid(int... rowsAndColsIterating){
        for (int i = 0; i < rowsAndColsIterating.length; i+=2)
            makeCellSolid( rowsAndColsIterating[i], rowsAndColsIterating[i+1] );
    }

    public void colorShortestPath(int startRow, int startCol, int endRow, int endCol){
        GridCell startCell = getCell(startRow, startCol), endCell = getCell(endRow, endCol);
        // set G, H and F for the entire grid
        for (int i = 0; i < gridCells.length; i++) {
            for (int j = 0; j < gridCells[0].length; j++) {
                GridCell cell = getCell(i, j);
                cell.G = getDistance(startCell, cell);
                cell.H = getDistance(endCell, cell);
                cell.F = cell.G + cell.H;
            }
        }
        // start searching
        ArrayList<GridCell> open = new ArrayList<>(); // the set of nodes to be evaluated
        ArrayList<GridCell> closed = new ArrayList<>(); // the set of nodes already evaluated
        open.add(startCell);
        while(true){
                GridCell current = getWithLowestF(open);
                open.remove(current);
                closed.add(current);

                if( current.isSameAs(endCell) ) break;

                ArrayList<GridCell> neighbours = new ArrayList<>();
                GridCell c1, c2, c3, c4, c5, c6, c7, c8;
                c1 = getCell(current.row + 1, current.col);
                c2 = getCell(current.row + 1, current.col + 1);
                c3 = getCell(current.row + 1, current.col - 1);
                c4 = getCell(current.row - 1, current.col);
                c5 = getCell(current.row - 1, current.col + 1);
                c6 = getCell(current.row - 1, current.col - 1);
                c7 = getCell(current.row + 1, current.col);
                c8 = getCell(current.row - 1, current.col);
                if( c1 != null) neighbours.add(c1);
                if( c2 != null) neighbours.add(c2);
                if( c3 != null) neighbours.add(c3);
                if( c4 != null) neighbours.add(c4);
                if( c5 != null) neighbours.add(c5);
                if( c6 != null) neighbours.add(c6);
                if( c7 != null) neighbours.add(c7);
                if( c8 != null) neighbours.add(c8);
                for( GridCell neighbour : neighbours ){
                    if( neighbour.isSolid || closed.contains(neighbour) ) continue;

                    if( !open.contains(neighbour) ) open.add(neighbour);
                }
                for(GridCell cell : open) cell.color = Color.GREEN;
        }
        startCell.color = Color.YELLOW;
        endCell.color = Color.YELLOW;
    }

    public GridCell[][] getGridCells() {
        return gridCells;
    }

    /*  HELPERS  */

    private GridCell getWithLowestF(ArrayList<GridCell> arr){
        GridCell cell = arr.get(0);
        for(int i = 1; i < arr.size(); i++)
            if(arr.get(i).F < cell.F) cell = arr.get(i);
        return cell;
    }

    private float getDistance(GridCell start, GridCell cell){
        int rowDiff = Math.abs(cell.row - start.row);
        int colDiff = Math.abs(cell.col - start.col);
        return (float) Math.sqrt( rowDiff * rowDiff + colDiff * colDiff );
    }

    private GridCell getCell(int row, int col){
        if( row >= gridCells.length || row < 0 || col >= gridCells[0].length || col < 0) return null;
        return gridCells[row][col];
    }


}
