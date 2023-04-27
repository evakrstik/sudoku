package com.insumak.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.insumak.api.model.SudokuSession;

@Controller
@SessionAttributes("difficulty")
public class HomeController {
    
    @Autowired
    SudokuSession sudokuSession;

    @GetMapping("/home")
    public String showHomepage(Model model) {
        sudokuSession.setFirst(false);
        model.addAttribute("difficulty", 0);
        return "home";
    }

    @PostMapping("/home")
    public String toGame(@RequestParam int difficulty, Model model) {
        sudokuSession.setLevel(difficulty);
        sudokuSession.setFirst(true);
        int[][] startingBoard = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}};
        sudokuSession.setBoard(startingBoard);
        return "redirect:/sudoku";
    }
}
