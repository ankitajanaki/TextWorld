import java.util.ArrayList;

public class PopStar extends Creature {
    Player p;

    public PopStar(Level.Room currentRoom, String name, String description, Player p) {
        super(currentRoom, name, description);
        this.p=p;
    }

    @Override
    public void move() {
        Level.Room playersRoom= p.getCurrentRoom();
        String playerNeighbors= playersRoom.getNeighborNames();
        String popStarNeighbors= this.getCurrentRoom().getNeighborNames();

        while(checkConditions(playerNeighbors, popStarNeighbors)){
            Level.Room newRoom=getRandomCommonNeighbor(getCommonNeighbors(playerNeighbors,popStarNeighbors));
            this.moveToNeighboringRoom(newRoom);
        }
    }

    private boolean checkConditions(String playerNeighbors, String popStarNeighbors) {
        String [] popStarNeighborNames=popStarNeighbors.split(" ");
        for(int i=0; i<popStarNeighborNames.length; i++){
            if(popStarNeighborNames[i].equals(p.currentRoom.getName())){
                return true;
            }
        }
        ArrayList<String> commonNeighbors= getCommonNeighbors(playerNeighbors, popStarNeighbors);
        if(commonNeighbors.size()>=1){
            return true;
        }
        return false;
    }


    private Level.Room getRandomCommonNeighbor(ArrayList<String> commonNeighbors) {
        int randIndex= (int)Math.random()*commonNeighbors.size();
        return getCurrentRoom().getNeighbor(commonNeighbors.get(randIndex));
    }

    private ArrayList<String> getCommonNeighbors(String playerNeighbors, String popStarNeighbors) {
        ArrayList<String> commonNeighbors=new ArrayList<>();
        String [] playerNeighborValues= playerNeighbors.split(" ");
        String [] popStarNeighborValues= popStarNeighbors.split(" ");
        for(int i=0; i<playerNeighborValues.length; i++ ){
            for (int j=0; j<popStarNeighborValues.length; j++){
                if(playerNeighborValues[i].equals(popStarNeighborValues[j])){
                    commonNeighbors.add(playerNeighborValues[i]);
                }
            }
        }
        return commonNeighbors;
    }
}
