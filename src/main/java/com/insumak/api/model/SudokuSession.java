package com.insumak.api.model;

public class SudokuSession {
    
    private int level;
    private boolean isFirst;
    private String username;
    private int[][] board;
    private int gamesWon;

    public SudokuSession() {}

    public SudokuSession(int level, boolean isFirst, String username, int[][] board, int gamesWon) {
        this.level = level;
        this.isFirst = isFirst;
        this.username = username;
        this.board = board;
        this.gamesWon = gamesWon;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    
}
