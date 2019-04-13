public abstract class Creature {
    private Level.Room currentRoom;
    private String name, description;

    public Creature(Level.Room currentRoom, String name, String description) {
        this.currentRoom = currentRoom;
        this.name = name;
        this.description = description;
    }

    public abstract void move();

    public void moveToNeighboringRoom(Level.Room nextRoom) {
        Creature c=getCurrentRoom().removeCreature(this.getName());  //remove from current room
        nextRoom.addCreature(c);                                    //add to new room
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
