import java.util.ArrayList;

public class DropItemCommand implements Command {
    Level level;
    String itemName;
    ArrayList<Item>playerItems;
    Level.Room playerRoom;

    public DropItemCommand(Level level, Player p) {
        this.level = level;
        playerItems=p.getItems();
        playerRoom=p.getCurrentRoom();
        //System.out.println("drop item" +"player room "+ playerRoom.getName());
    }

    @Override
    public void init(String userString) {
        this.itemName = getLastWordIn(userString);
    }

    private String getLastWordIn(String userString) {
        String[] words = userString.split(" ");
        return words[1];
    }

    @Override
    public boolean execute() {
        Item itemToDrop=getItemToDrop(itemName, playerItems);
        playerRoom.getPlayer().getCurrentRoom().addItem(itemToDrop);
        System.out.println(itemToDrop.getName()+ " was added to "+ playerRoom.getName()+ ". Is no longer with player.");
        return true;
    }

    private Item getItemToDrop(String itemName, ArrayList<Item> playerItems) {
        Item itemToDrop=null;
        for(Item i:playerItems){
            if(i.getName().equals(itemName)){
                itemToDrop=i;
            }
        }
        return itemToDrop;
    }


}

