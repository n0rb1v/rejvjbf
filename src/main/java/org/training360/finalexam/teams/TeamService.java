package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.training360.finalexam.players.CreatePlayerCommand;
import org.training360.finalexam.players.Player;
import org.training360.finalexam.players.PlayerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    public List<TeamDTO> listTeams() {
        return teamRepository.findAll().stream()
                .map(team -> modelMapper.map(team,TeamDTO.class))
                .collect(Collectors.toList());
    }
    public TeamDTO createTeam(CreateTeamCommand command) {
        Team team = new Team(
                command.getName());
        teamRepository.save(team);
        return modelMapper.map(team,TeamDTO.class);
    }
    @Transactional
    public TeamDTO createPlayerWithTeam(long id, CreatePlayerCommand command) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException());
        Player player = new Player(
                command.getName(),
                command.getBirthDate(),
                command.getPosition());
        team.addPlayer(player);
        return modelMapper.map(team,TeamDTO.class);
    }
    @Transactional
    public TeamDTO linkPlayerToTeam(long id, UpdateWithExistingPlayerCommand command) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException());
        Player player = playerRepository.findById(command.getPlayerId()).orElseThrow(() -> new IllegalArgumentException());
        List<Player> result = team.getPlayers().stream()
                .filter(p -> p.getPosition().equals(player.getPosition()))
                .collect(Collectors.toList());
        if ((player.getTeam() == null)&&(result.size() < 2)) {
            team.addPlayer(player);
        }
        return modelMapper.map(team,TeamDTO.class);
    }
}
