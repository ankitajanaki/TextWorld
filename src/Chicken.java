public class Chicken extends Creature {

    public Chicken (Level.Room currentRoom, String name, String description){
        super(currentRoom, name, description);
    }

    public void move() {
        Level.Room nextRoom= getRandomNeighbor(getCurrentRoom());
        this.moveToNeighboringRoom(nextRoom);
    }

    private Level.Room getRandomNeighbor(Level.Room currentRoom) {
        String neighbors= currentRoom.getNeighborNames();
        String[] neighborNames= neighbors.split(" ");
        int randInt=(int)Math.random()*neighborNames.length;
        String randName= neighborNames[randInt];
        return currentRoom.getNeighbor(randName);
    }

}
