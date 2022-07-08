package lab;
import lab.Commands.*;

public class Main {
    public static void main(String[] args) {

        Database database = null;
//        if (args.length > 0){
//            database = new Database(args[0]);
//        }else {
//            System.out.println("The file name is not entered in the command line argument!");
//        }
        Console user = new Console(database);
        user.addCommand(new AddCommand(), new ClearCommand(), new ExecuteScriptCommand(), new ExitCommand());
        user.addCommand( new HelpCommand(), new InfoCommand(), new RemoveAllByDescriptionCommand(), new ReorderCommand());
        user.addCommand( new RemoveByIdCommand(), new RemoveLowerCommand(), new HistoryCommand());
        user.addCommand(new SaveCommand(), new ShowCommand(), new UpdateIdCommand(), new FilterContainsNameCommand());
        user.start();
    }
}
