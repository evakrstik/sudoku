package com.insumak.api.model;

import lombok.Getter;
import lombok.Setter;

public class SudokuBoard {
    @Getter
    @Setter
    private int[][] board;

    @Getter
    @Setter
    private int difficulty;  // 1 - easy, 2 - medium, 3 - hard

    @Getter
    @Setter
    private int gridSize = 9;   // the board size 9x9
    
    @Getter
    @Setter
    private int boxSize = 3;    // the size of the subgrids there are 9 3x3 subgrids

    public SudokuBoard() {}

    public SudokuBoard(int[][] board, int difficulty) {
        this.board = board;
        this.difficulty = difficulty;
        this.gridSize = 9;
        this.boxSize = 3;
    } 

    // generate a random board
    public void populateBoard() {
        fillDiagonal();
        solve();
        setDiff(getDifficulty());
    }

    // solve the board using backtracking algorithm
    public boolean solve() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= gridSize; num++) {
                        if (isValid(row, col, num)) {
                            board[row][col] = num;
                            if (solve()) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // generate a random number
    public int randomGenerator(int num) {
        return (int) Math.floor((Math.random() * num + 1));
    }

    // fill each of the diagonal 3x3 subgrids with random numbers from 1 to 9 
    public void fillDiagonal() {
        for (int i = 0; i < gridSize; i = i + boxSize) {
            fillBox(i, i);
        }
    }

    public void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                do {
                    num = randomGenerator(gridSize);
                } while (!isValid(row, col, num));
                board[row+i][col+j] = num;
            }
        }
    }

    // validates if the number is applicable in the provided row and column 
    public boolean isValid(int row, int col, int num) {
        // this for loop checks if the number is in the vertical or horizontal line
        for (int i = 0; i < gridSize; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int startRow = row - row % boxSize;
        int startCol = col - col % boxSize;

        // this nested for loop checks if the number is in the provided 3x3 subgrid
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // depending on the chosen difficulty remove X cell values from the sudoku board
    // the more values are removed the harder the game is to beat
    public void setDiff(int level) {

        int diffNum; 
        switch(level) {
            case 1:
                diffNum = 15;
                break;
            case 2:
                diffNum = 25;
                break;
            case 3:
                diffNum = 35;
                break;
            default:
                diffNum = 20;
        }

        while (diffNum != 0) {
            int cellId = randomGenerator(gridSize * gridSize) - 1;
 
            int i = (cellId / gridSize);
            int j = cellId % gridSize;
            if (j != 0) {
                j = j - 1;
            }

            if (board[i][j] != 0)
            {
                diffNum--;
                board[i][j] = 0;
            }
        }
    }

    // undo previous move
    public void undoMove(Move move) {
        this.board[move.getRow()][move.getCol()] = 0; 
    }

    // checks if the puzzle is solved
    public boolean isSolved(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(board[i][j] == 0) {
                    return false;
                }
            }
        }
        
        return true;
    }
} 