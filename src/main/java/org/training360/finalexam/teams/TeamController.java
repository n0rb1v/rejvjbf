package org.training360.finalexam.teams;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.training360.finalexam.players.CreatePlayerCommand;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService teamService;

    @GetMapping
    @Operation(summary = "list of teams")
    public List<TeamDTO> listTeams() {
        return teamService.listTeams();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "creates a team")
    @ApiResponse(responseCode = "201", description = "team created")
    public TeamDTO createTeam(@Valid @RequestBody CreateTeamCommand command) {
        return teamService.createTeam(command);
    }

    @PostMapping("/{id}/players")
    @Operation(summary = "creates new player with team")
    public TeamDTO createPlayerWithTeam(@PathVariable("id") long id, @Valid @RequestBody CreatePlayerCommand command) {
        return teamService.createPlayerWithTeam(id, command);
    }

    @PutMapping("/{id}/players")
    @Operation(summary = "link existing player to team")
    public TeamDTO linkPlayerToTeam(@PathVariable("id") long id, @Valid @RequestBody UpdateWithExistingPlayerCommand command) {
        return teamService.linkPlayerToTeam(id, command);
    }


}
