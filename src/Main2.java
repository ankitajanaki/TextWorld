import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Level g=new Level();

        g.initLevel();

        Item itemOne=new Item("hat", "a baseball cap");
        Item itemTwo=new Item("jacket", "for the rain");
        Item itemThree=new Item("lamp", "used for studying");

        g.addRoom("hall", "a long pathway");
        g.addRoom("closet", "a storage area");
        g.addRoom("dungeon", "a scary basement");

        Creature creatureOne= new Chicken(g.getRoom("hall"), "chicken", "an randomly moving animal");

        g.getRoom("closet").addItem(itemTwo);
        g.getRoom("dungeon").addItem(itemThree);
        g.getRoom("hall").addItem(itemOne);


        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");

        Player player=new Player("Player 1", "An APCS student");
        player.setCurrentRoom(g.getRoom("hall"));

        String response= "";
        Scanner s= new Scanner(System.in);

        System.out.println("These are the commands you can type.");
        System.out.println("go <roomname>");
        System.out.println("look for items");
        System.out.println("add room <roomname>");
        System.out.println("take <itemname>");
        System.out.println("drop <itemname>");
        System.out.println("quit");


        do{
            System.out.println("You are in the "+ player.getCurrentRoom().getName());
            System.out.println("What do you want to do? >");
            response=s.nextLine();
            String [] words= response.split(" ");
            String firstWord= words[0];
            String secondWord, thirdWord;
            creatureOne.move();
            System.out.println(creatureOne.getName() +" has moved.");

            if(firstWord.equals("go")){
                secondWord=words[1];
                Level.Room roomToMoveTo= g.getRoom(secondWord);
                player.setCurrentRoom(roomToMoveTo);
            }else if(firstWord.equals("look")){
                Level.Room current=player.getCurrentRoom();
                System.out.println(current.getDescription());
                ArrayList<Item> itemsInRoom=current.getItems();
                for(Item i:itemsInRoom){
                   System.out.print(i.getName()+ " ");
                }
            }else if(firstWord.equals("add")){
                secondWord=words[1];
                if(secondWord.length()==4){
                    thirdWord=words[2];
                    String descriptionOfRoom=JOptionPane.showInputDialog("type description of room");
                    g.addRoom(thirdWord, descriptionOfRoom);
                    g.addUndirectedEdge(player.getCurrentRoom().getName(), thirdWord);
                }
            }else if(firstWord.equals("quit")){
                break;
            }else if(firstWord.equals("take")){
                secondWord=words[1];
                Level.Room currentRoom=player.currentRoom;
                Item itemForPlayer= currentRoom.removeItem(secondWord);
                player.addItem(itemForPlayer);
                System.out.println(itemForPlayer.getName()+ " was removed from "+ currentRoom.getName() + ". Is with player now.");
            }else if(firstWord.equals("drop")){
                secondWord=words[1];
                Item itemToDrop=player.removeItem(secondWord);
                player.getCurrentRoom().addItem(itemToDrop);
                System.out.println(itemToDrop.getName()+ " was added to "+ player.getCurrentRoom().getName()+ ". Is no longer with player.");
            }else{
                System.out.println("These are the commands you can type.");
                System.out.println("go <roomname>");
                System.out.println("look for items");
                System.out.println("add room <roomname>");
                System.out.println("take <itemname>");
                System.out.println("drop <itemname>");
                System.out.println("quit");
            }

        }while(!response.equals("quit"));

    }

    private static Item getItemWithName(String name, ArrayList<Item>items) {
        Item itemToReturn=null;
        for(Item i:items){
            if(i.getName().equals(name)){
                itemToReturn=i;
            }
        }
        return itemToReturn;
    }
}


