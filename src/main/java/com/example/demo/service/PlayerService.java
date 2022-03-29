package com.example.demo.service;

import com.example.demo.exceptions.PlayerNotFoundException;
import com.example.demo.model.MatchResults;
import com.example.demo.model.Player;
import com.example.demo.repository.MatchResultsRepository;
import com.example.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final MatchResultsRepository matchResultsRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, MatchResultsRepository matchResultsRepository) {
        this.playerRepository = playerRepository;
        this.matchResultsRepository = matchResultsRepository;
    }

    public void addNewPlayer(Player player) throws PlayerNotFoundException {
        try {
            playerRepository.save(player);
        } catch (Exception e) {
            throw new PlayerNotFoundException("Player with id: " + player.getId() + " was not found");
        }
    }

    public void registerMatchResults(MatchResults matchResults) throws PlayerNotFoundException {
        Optional<Player> player = playerRepository.findById(matchResults.getPlayerId());
        Optional<Player> opponent = playerRepository.findById(matchResults.getOpponentId());
        if (player.isEmpty() && opponent.isEmpty()) {
            throw new PlayerNotFoundException("Player with id: " + player.get().getId() + " was not found");
        }

        int[] earningsAllPlayers = calculateEarningsPerMatch(matchResults);
        player.get().setSalary(player.get().getSalary() + earningsAllPlayers[0]);
        opponent.get().setSalary(opponent.get().getSalary() + earningsAllPlayers[1]);

        List listOfMatchResults = new ArrayList<MatchResults>();
        listOfMatchResults.add(matchResults);
        player.get().setMatchResults(listOfMatchResults);
        opponent.get().setMatchResults(listOfMatchResults);

        matchResultsRepository.save(matchResults);
        playerRepository.save(player.get());
        playerRepository.save(opponent.get());
    }

    public Player getPlayerStats(Long playerId) throws PlayerNotFoundException {
        Optional<Player> player = Optional.ofNullable(playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id: " + playerId + " was not found")));
        return player.get();
    }

    public int[] calculateEarningsPerMatch(MatchResults matchResults) {
        int earningsPlayer = 500;
        int earningsOpponent = 500;
        int setsWonPlayer = 0;
        int setsWonOpponent = 0;

        for (int[] result : matchResults.getResults()) {
            earningsPlayer += result[0] * 200;
            earningsOpponent += result[1] * 200;
            if (result[0] > result[1]) {
                earningsPlayer += 750;
                setsWonPlayer++;
            } else {
                earningsOpponent += 750;
                setsWonOpponent++;
            }
            if (setsWonPlayer == 3) {
                earningsPlayer += 2500;
            }
            if (setsWonOpponent == 3) {
                earningsOpponent += 2500;
            }
        }

        earningsPlayer += matchResults.getAces()[0] * 100;
        earningsOpponent += matchResults.getAces()[1] * 100;
        earningsPlayer -= matchResults.getSmashedRackets()[0] * 500;
        earningsOpponent -= matchResults.getSmashedRackets()[1] * 500;
        earningsPlayer -= matchResults.getDoubleFaults()[0] * 100;
        earningsOpponent -= matchResults.getDoubleFaults()[1] * 100;

        int[] listOfEarnings = new int[2];
        listOfEarnings[0] = earningsPlayer;
        listOfEarnings[1] = earningsOpponent;
        return listOfEarnings;
    }
}
