package org.matejko06.vesek.anarchycore;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Party {

    private UUID leader;
    private List<UUID> members = new ArrayList<>();

    public Party (Player leader){
        this.leader = leader.getUniqueId();
    }

    public void invitePlayer(Player member){
        members.add(member.getUniqueId());
    }
}