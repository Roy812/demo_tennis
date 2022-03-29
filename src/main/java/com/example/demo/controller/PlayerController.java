package com.example.demo.controller;

import com.example.demo.exceptions.PlayerNotFoundException;
import com.example.demo.model.MatchResults;
import com.example.demo.model.Player;
import com.example.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tennis")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping(value = "player")
    public void addNewPlayer(@RequestBody Player player) throws PlayerNotFoundException {
        playerService.addNewPlayer(player);
    }

    @PatchMapping(value = "match")
    public void registerMatchResults(@RequestBody MatchResults matchResults) throws PlayerNotFoundException {
        playerService.registerMatchResults(matchResults);
    }

    @GetMapping(value = "/player/{id}")
    public Player getPlayerStats(@PathVariable("id") Long id) throws PlayerNotFoundException {
        return playerService.getPlayerStats(id);
    }
}
