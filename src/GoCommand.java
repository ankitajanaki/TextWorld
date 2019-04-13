import java.util.ArrayList;

public class GoCommand implements Command {
    String roomToGoTo;
    Level level;
    String neighbors;
    Level.Room playerRoom;


    public GoCommand(Level level, Player p) {
        this.level = level;
        playerRoom=p.getCurrentRoom();

    }

    @Override
    public void init(String userString) {
        this.roomToGoTo=getLastWordIn(userString);
        neighbors=playerRoom.getNeighborNames();
    }

    private String getLastWordIn(String userString) {
        String[] words = userString.split(" ");
        return words[1];
    }

    @Override
    public boolean execute() {
        Player p= playerRoom.getPlayer();
        System.out.println(neighbors);
        if(checkIfNeighbors(roomToGoTo, neighbors)){
            Level.Room roomToMoveTo= level.getRoom(roomToGoTo);
            System.out.println(roomToMoveTo.getName());
            p.setCurrentRoom(roomToMoveTo);
        }
        System.out.println("Player is now in "+ p.getCurrentRoom().getName());
        return true;
    }

    private boolean checkIfNeighbors(String roomToGoTo, String neighbors) {
        String[] neighborsNames= neighbors.split(" ");
        for(int i=0; i<neighborsNames.length; i++){
            if(neighborsNames[i].equals(roomToGoTo)){
                return true;
            }
        }
        return false;
    }
}
