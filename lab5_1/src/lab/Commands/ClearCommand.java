package lab.Commands;

import lab.Console;

import java.util.ArrayList;

public class ClearCommand extends Command {
    {
        command_name = new String[]{"clear"};
        description = "clear - clears the collection." +
                "Flags: f - to do all the actions without confirmation" +
                "       s - save the collection after cleaning";
    }
    @Override
    void start_with_arguments(ArrayList<String> args, ArrayList<Character> flags) {
        if(flags.contains('f')) {
            if(flags.contains('s')){
                if (data.clean()) {
                    Console.sendln("The collection is cleared");
                    if (data.save()) {
                        Console.sendln("The collection is saved");
                    }
                    else{
                        Console.sendln("The collection is not savedа");
                    }
                } else {
                    throw new RuntimeException("The collection is not cleared");
                }
            }
            else {
                if (data.clean()) {
                    Console.sendln("The collection is cleared");
                    Console.sendln("Don't forget to save your changes with the save commande");
                } else {
                    throw new RuntimeException("The collection is not cleared");
                }
            }
        }

        else{
            start_without_arguments();
        }
    }

    @Override
    void start_without_arguments() {
        if (Console.getAnswer("Are you sure you want to clear the collection?")) {
            if(data.clean()){
                Console.sendln("The collection is cleared");
                Console.sendln("Don't forget to save your changes with the save command");
            }
            else{
                throw new RuntimeException("The collection is not cleared");
            }
        }
        else{
            Console.sendln("The task is rejected.");
        }
    }
}
