public class TakeCommand implements Command {
    Level level;
    String itemName;

    public TakeCommand(Level level) {
        this.level= level;
    }

    @Override
    public void init(String userString) {
        this.itemName= getLastWordIn(userString);
    }

    private String getLastWordIn(String userString) {
        String [] words= userString.split(" ");
        return words[1];
    }

    @Override
    public boolean execute() {
        Player p= level.getPlayer();
        Level.Room playersRoom= p.getCurrentRoom();
        Item itemForPlayer= playersRoom.removeItem(itemName);
        p.addItem(itemForPlayer);
        System.out.println(itemForPlayer.getName()+ " was removed from "+ playersRoom.getName() + ". Is with player now.");
        if(itemForPlayer==null){
            return false;
        }else{
            return true;
        }
    }
}