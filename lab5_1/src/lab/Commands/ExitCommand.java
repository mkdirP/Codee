package lab.Commands;

import lab.Console;
import java.util.ArrayList;

public class ExitCommand extends Command{
    {
        command_name = new String[]{"exit"};
        description = "exit -exit without saving\n" +
                "Флаги: s - exit with saving\n" +
                "       f - exit without confirmation";
    }
    @Override
    void start_with_arguments(ArrayList<String> args, ArrayList<Character> flags) {
        if(flags.contains('s')){
            if(data.save()){
                Console.sendln("The data is arranged");
            }
            else {
                Console.sendln("The data could not be saved");
            }
        }
        if(flags.contains('f')){
            System.exit(0);
        }
        else{
            start_without_arguments();
        }
    }

    @Override
    void start_without_arguments() {
        if(Console.getAnswer("Are you sure you want to get out without saving?")){
            System.exit(0);
        }
        else{
            if(Console.getAnswer("Do you want to save the data?")){
                data.save();
                if(Console.getAnswer("Data saved, want to get out?")){
                    System.exit(0);
                }
            }
        }
    }
}
