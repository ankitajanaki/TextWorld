import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Level level = new Level();
        level.initLevel();
        HashMap<String, Command> commands= new HashMap<>();
        initCommands(commands, level, level.getPlayer());

        while(true) {
            Scanner in = new Scanner(System.in);

            System.out.println(">");
            String response = in.nextLine();
            Command command = lookUpCommand(response, commands);
            if(command.equals("quit")){
                break;
            }

            command.execute();
        };

    }

    private static Command lookUpCommand(String response, HashMap<String, Command> commands) {
        String commandWord= getFirstWordIn(response);
        Command c= commands.get(commandWord);
        /*if(c==null){
            return new EmptyCommand();
        }*/
        c.init(response);
        //System.out.println("c"+ c.toString());
        return c;
    }

    private static String getFirstWordIn(String response) {
        String [] words= response.split(" ");
        return words[0];
    }

    private static void initCommands(HashMap<String, Command> commands, Level level, Player p){
        commands.put("take", new TakeCommand(level));
        commands.put("look", new LookCommand(p));
        commands.put("add-room", new AddRoomCommand(p, level));
        commands.put("drop", new DropItemCommand(level, p));
        commands.put("go", new GoCommand(level, p));
    }


}

