package org.training360.finalexam.teams;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class TeamNotFoundException extends AbstractThrowableProblem {
    public TeamNotFoundException() {
        super(URI.create("teams/not-found"),
                "Not found",
                Status.NOT_FOUND,
                "Team not found");
    }
}
