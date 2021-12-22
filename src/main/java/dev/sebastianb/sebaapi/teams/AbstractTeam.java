package dev.sebastianb.sebaapi.teams;

import net.minecraft.resource.JsonDataLoader;

import java.util.UUID;

public abstract class AbstractTeam {

    private String teamName;
    private UUID teamId;

    public AbstractTeam(String teamName) {
        this.teamName = teamName;
        this.teamId = UUID.randomUUID();
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public UUID getTeamId() {
        return teamId;
    }

    // TODO: implement a method to get a specific team from the database
    public static AbstractTeam getTeam(String teamName) {
        return null;
    }

}
