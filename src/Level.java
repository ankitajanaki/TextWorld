import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Level {
    private HashMap<String, Room> allRooms;
    private ArrayList<Creature> allCreatures;
    private Player player;


    public Level(){
        allRooms= new HashMap<>();
        allCreatures=new ArrayList<>();
        player= new Player("Player 1", "new current player");
    }

    public Player getPlayer(){
        return player;
    }


    public void moveAllCreatures(){
        for(Creature c: allCreatures){
            c.move();
        }
    }

    public void initLevel(){
        addRooms();
        addUndirectedEdge("hall", "dungeon");
        addUndirectedEdge("closet", "hall");
        addUndirectedEdge("closet", "dungeon");
        addUndirectedEdge("hall", "backyard");
        addUndirectedEdge("backyard", "kitchen");
        player.setCurrentRoom(getRoom("hall"));
        addCreatures();
        addItems();
    }

    private void addRooms() {
        addRoom("hall", "a long pathway");
        addRoom("closet", "a storage area");
        addRoom("dungeon", "a scary basement");
        addRoom("kitchen", "where food is kept");
        addRoom("backyard", "grass and tables");
    }

    private void addItems() {
        Item jacket=new Item("jacket", "for the rain");
        Item lamp=new Item("lamp", "used for studying");
        Item hat=new Item("hat", "a baseball cap");
        getRoom("closet").addItem(jacket);
        getRoom("dungeon").addItem(lamp);
        getRoom("hall").addItem(hat);
    }

    private void addCreatures(){
        Creature fancyChicken= new Chicken(getRoom("hall"), "fancy chicken", "a very fancy chicken");
        Creature smartChicken= new Chicken(getRoom("hall"), "smart chicken", "a very smart chicken");
        Creature happyChicken= new Chicken(getRoom("hall"), "happy chicken", "a very happy chicken");
        Creature wumpusOne= new Wumpus(getRoom("backyard"), "wumpusOne", "wumpus", player);
        Creature popStarOne= new PopStar(getRoom("dungeon"), "popStarOne", "popStar", player);
        allCreatures.add(fancyChicken);
        allCreatures.add(smartChicken);
        allCreatures.add(happyChicken);
        allCreatures.add(wumpusOne);
        allCreatures.add(popStarOne);
        getRoom("hall").addCreature(fancyChicken);
        getRoom("hall").addCreature(smartChicken);
        getRoom("hall").addCreature(happyChicken);
        getRoom("backyard").addCreature(wumpusOne);
        getRoom("dungeon").addCreature(popStarOne);
    }

    public void addRoom(String name, String description){
        Room n= new Room(name, description);
        allRooms.put(name, n);
    }

    public void addDirectedEdge(String name1, String name2){
        Room one=allRooms.get(name1);
        Room two=allRooms.get(name2);
        if(one !=null && two!= null){
            one.addNeighbor(name2, two);
        }
    }

    public void addUndirectedEdge(String name1, String name2){
        addDirectedEdge(name1, name2);
        addDirectedEdge(name2, name1);
    }

    public Room getRoom(String name){
       return allRooms.get(name);
    }


    //------------------------------------------------------------------------------------------------------------------

    public class Room{
        private String name;
        private HashMap<String, Room> neighbors;
        private String description;
        private ArrayList<Item> items;
        private ArrayList<Creature> creatures;

        private Room(String name, String desciption) {
            neighbors=new HashMap<>();
            this.name = name;
            this.description = desciption;
            items=new ArrayList<>();
            creatures= new ArrayList<>();
        }

        private void addNeighbor(String name, Room n){
            neighbors.put(name, n);
        }

        public Room getNeighbor(String name){
            return neighbors.get(name);
        }

        public String getNeighborNames(){
            String names = "";
            for (String name: neighbors.keySet()){
                names+=name + " ";
            }
            return names;
        }

        public ArrayList<Item> getItems(){
            return items;
        }

        public void addItem(Item item){
            items.add(item);
        }

        public Item removeItem(String name){
            Item itemToRemove=null;
            for(int i=0; i<items.size(); i++){
                if(items.get(i).getName().equals(name)){
                    itemToRemove=items.remove(i);
                }
            }
            return itemToRemove;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void addCreature(Creature creature){
            creatures.add(creature);
        }

        public Creature removeCreature(String name){
            Creature creatureToRemove=null;
            for(int i=0; i<creatures.size(); i++){
                if(creatures.get(i).getName().equals(name)){
                    creatureToRemove=creatures.remove(i);
                }
            }
            return creatureToRemove;
        }

        public ArrayList<Creature> getCreatures(){
            return creatures;
        }

        public Player getPlayer(){
            return player;
        }
    }

}
