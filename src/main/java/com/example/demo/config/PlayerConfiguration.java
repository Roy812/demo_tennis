package com.example.demo.config;

import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PlayerConfiguration implements CommandLineRunner {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerConfiguration(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... args) {
        playerRepository.save(new Player("Novak Djokovic", 0));
        playerRepository.save(new Player("Daniil Medvedev", 0));
        playerRepository.save(new Player("Rafael Nadal", 0));
        playerRepository.save(new Player("Stefanos Tsitsipas", 0));
    }
}
