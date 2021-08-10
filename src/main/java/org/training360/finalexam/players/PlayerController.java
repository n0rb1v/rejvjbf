package org.training360.finalexam.players;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping
    @Operation(summary = "list of players")
    public List<PlayerDTO> listPlayers() {
        return playerService.listPlayers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create player")
    @ApiResponse(responseCode = "201",description = "player created")
    public PlayerDTO createPlayer(@Valid @RequestBody CreatePlayerCommand command) {
        return playerService.createPlayer(command);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete player")
    public void deletePlayer(@PathVariable("id") long id) {
        playerService.deletePlayer(id);
    }
}
