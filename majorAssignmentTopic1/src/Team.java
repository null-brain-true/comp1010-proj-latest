import java.util.ArrayList;

public class Team {
    String name;
    ArrayList<Character> members;

    Team(String name, ArrayList<Character>members){
        this.name = name;
        this.members = members;
    }

    public boolean isActive(){
        for(Character c : members){
            if(c.HP > 0){
                return true;
            }
        }
        return false;
    }

    public void addMember(Character c){
        members.add(c);
    }

}
