package model;

import java.util.ArrayList;

public class Team {
    public String name;
    public ArrayList<Character> members;

    Team(String name, ArrayList<Character> members) {
        this.name = name;
        this.members = members;
    }

    // check to see if team is still active
    public boolean isActive() {
        for (Character c : members) {
            if (c.HP > 0) {
                return true;
            }
        }
        return false;
    }

    // adding characters to teams
    public void addMember(Character c) {
        members.add(c);
    }

}
