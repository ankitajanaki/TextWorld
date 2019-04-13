import java.util.ArrayList;

public class Wumpus extends Creature {
    Player p;

    public Wumpus(Level.Room currentRoom, String name, String description, Player p) {
        super(currentRoom, name, description);
        this.p=p;
    }

    @Override
    public void move() {
        Level.Room newRoom;
        String newRoomName;
        Level.Room playersRoom=p.getCurrentRoom();
        String playerNeighbors= playersRoom.getNeighborNames();
        String wumpusNeighbors= this.getCurrentRoom().getNeighborNames();

        while(checkConditions(playerNeighbors, wumpusNeighbors)){
            newRoomName=getRandomUnCommonNeighbor(wumpusNeighbors,getCommonNeighbors(playerNeighbors,wumpusNeighbors));
            this.moveToNeighboringRoom(this.getCurrentRoom().getNeighbor(newRoomName));
        }

    }

    private String getRandomUnCommonNeighbor(String wumpusNeighbors, ArrayList<String> commonNeighbors) {
        String[] wumpusNeighborNames= wumpusNeighbors.split(" ");
        ArrayList<String>unCommon=new ArrayList<>();
        for(int i=0; i<wumpusNeighborNames.length; i++){
            unCommon.add(wumpusNeighborNames[i]);
        }
        for(int j=0; j<unCommon.size(); j++){
            for(int k=0; k<commonNeighbors.size(); k++){
                if(unCommon.get(j).equals(commonNeighbors.get(k))){
                    unCommon.remove(j);
                }
            }
        }
        int rand=(int)Math.random()*unCommon.size();
        return unCommon.get(rand);
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

    private Level.Room getRandomNeighbor(Level.Room currentRoom) {
        String neighbors= currentRoom.getNeighborNames();
        String[] neighborNames= neighbors.split(" ");
        int randInt=(int)Math.random()*neighborNames.length;
        String randName= neighborNames[randInt];
        return currentRoom.getNeighbor(randName);
    }




}
