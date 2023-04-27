package com.insumak.api.model;

import lombok.Getter;
import lombok.Setter;

public class Move {
    
    @Getter
    @Setter
    private int row; 

    @Getter
    @Setter
    private int col;

    @Getter
    @Setter
    private int value;

    public Move () {}

    public Move(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Move [row=" + row + ", col=" + col + ", value=" + value + "]";
    }

    
}
