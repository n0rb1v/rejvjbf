package org.training360.finalexam.players;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    public List<PlayerDTO> listPlayers() {
        return playerRepository.findAll().stream()
                .map(player -> modelMapper.map(player,PlayerDTO.class))
                .collect(Collectors.toList());
    }

    public PlayerDTO createPlayer(CreatePlayerCommand command) {
        Player player = new Player(
                command.getName(),
                command.getBirthDate(),
                command.getPosition());
        playerRepository.save(player);
        return modelMapper.map(player,PlayerDTO.class);
    }

    public void deletePlayer(long id) {
        playerRepository.deleteById(id);
    }

}
