import java.util.ArrayList;

public class LookCommand implements Command {
    //Player p;
    Level.Room playersRoom;

    public LookCommand(Player p) {
        //this.p=p;
        //System.out.println("p: "+ p);
        //System.out.println("player name: "+ p.getName());
        this.playersRoom=p.getCurrentRoom();
        //System.out.println("players room: "+ p.getCurrentRoom());

    }

    @Override
    public void init(String userString) {

    }

    @Override
    public boolean execute() {
        ArrayList<Item> itemsInRoom=playersRoom.getItems();
        ArrayList<Creature>creaturesInRoom= playersRoom.getCreatures();
        //System.out.println("Creatures: " + creaturesInRoom);
        for(Item i:itemsInRoom){
            System.out.println("Items: ");
            System.out.println(i.getName()+ " ");
        }
        for(Creature c: creaturesInRoom){
            System.out.println("Creatures: ");
            System.out.print(c.getName()+ " ");
        }
        return true;
    }
}


