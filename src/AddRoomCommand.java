import javax.swing.*;

public class AddRoomCommand implements Command {
    Level l;
    Level.Room playerRoom;

    public AddRoomCommand(Player p, Level l) {
        this.l= l;
        playerRoom=p.getCurrentRoom();
    }

    @Override
    public void init(String userString) {
        String [] words= userString.split(" ");
        String roomName= words[words.length-1];
        String descriptionOfRoom= JOptionPane.showInputDialog("type description of room");
        l.addRoom(roomName, descriptionOfRoom);
        l.addUndirectedEdge(playerRoom.getName(), roomName);
        System.out.println("You added a room, "+ roomName);
    }

    @Override
    public boolean execute() {
        return true;
    }

}
