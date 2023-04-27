package com.insumak.api.controller;

import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.insumak.api.model.SudokuSession;
import com.insumak.api.model.Move;
import com.insumak.api.model.SudokuBoard;
import com.insumak.api.repository.UserRepository;


@Controller
public class SudokuController {

    @Autowired
    SudokuBoard sudokuGlobal;

    @Autowired
    SudokuSession sudokuSession;

    @Autowired
    UserRepository userRepository;
    
    private Stack<Move> moves = new Stack<>();

    @GetMapping("/sudoku")
    public String showBoard(Model model) {
        if (sudokuSession.isFirst()) {                              // check if the request is coming from the home controller
            sudokuGlobal.setBoard(sudokuSession.getBoard());        // set the empty sudoku board 9x9 grid with zeros
            sudokuGlobal.setDifficulty(sudokuSession.getLevel());   // set the difficulty from the previous page
            sudokuGlobal.populateBoard();                           // generate a board
        }
        model.addAttribute("diff", sudokuSession.getLevel());
        model.addAttribute("board", sudokuGlobal.getBoard());
        model.addAttribute("username", sudokuSession.getUsername());
        model.addAttribute("gamesWon", sudokuSession.getGamesWon());
        return "index";
    }

    @PostMapping("/sudoku")
    public String submitBoard(@RequestParam int row, @RequestParam int col, @RequestParam int val, Model model) {

        if (sudokuGlobal.isValid(row,col,val)) {
            sudokuGlobal.getBoard()[row][col] = val;
            Move latestMove = new Move(row, col, val);      
            moves.push(latestMove);
        } else {
            model.addAttribute("error", "Invalid value for cell.");
        }

        if (sudokuGlobal.isSolved(sudokuGlobal.getBoard())) {
            userRepository.updateGamesWon(sudokuSession.getUsername());

            model.addAttribute("success", "Congratulations! You won!");
        }

        model.addAttribute("board", sudokuGlobal.getBoard());
        return "index";
    }
    
    @GetMapping("/reset")
    public String reset(Model model) {
        return "redirect:/home";
    }

    @GetMapping("/solve")
    public String solve(Model model) {
        sudokuSession.setFirst(false);
        sudokuGlobal.solve();
        model.addAttribute("success", "Congratulations! You won!");
        model.addAttribute("board", sudokuGlobal.getBoard());
        return "redirect:/sudoku";
    }

    @GetMapping("/undo")
    public String undo(Model model) {
        sudokuSession.setFirst(false);
        if (!moves.empty()) {   
            sudokuGlobal.undoMove(moves.pop());
        }
        model.addAttribute("board", sudokuGlobal.getBoard());
        return "redirect:/sudoku";
    }
}
