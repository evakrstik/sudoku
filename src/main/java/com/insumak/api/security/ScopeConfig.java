package com.insumak.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.insumak.api.model.SudokuSession;
import com.insumak.api.model.SudokuBoard;

@Configuration
public class ScopeConfig {
    
    @Bean
    @Scope(
        value= WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SudokuSession storeDifficulty() {
        return new SudokuSession();
    }

    @Bean
    @Scope(
        value= WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SudokuBoard game() {
        return new SudokuBoard();
    }
}
