import java.util.ArrayList;

public class Player {
    private String name, description;
    private ArrayList<Item> items;
    Level.Room currentRoom;

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
        items=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<Item> getItems(){
        return items;
    }

    public void displayInventory(){
        for(int i=0; i<items.size(); i++){
            System.out.print(items.get(i).getName()+ " ");
        }
    }

    public Level.Room getCurrentRoom(){
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room newRoom){
        currentRoom=newRoom;
    }

}
