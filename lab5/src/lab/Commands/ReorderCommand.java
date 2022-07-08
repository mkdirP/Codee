package lab.Commands;

import lab.Console;
import java.util.ArrayList;

public class ReorderCommand extends Command {
    private boolean reverse = false;
    {
        command_name = new String[]{"reorder"};
        description = "reorder - sort the collection in reverse order of the current one\n" +
                "Флаги: n - sort in ascending order\n" +
                "       r - sort by decreasing order";
    }
    @Override
    void start_with_arguments(ArrayList<String> args, ArrayList<Character> flags) {
        if(flags.contains('r')) {
            data.sort(true);
            Console.sendln("The collection is sorted in descending order");
        }
        else if(flags.contains('n')){
            data.sort(false);
            Console.sendln("The collection is sorted in ascending order");
        }
        else{
            Console.sendln("The command is not entered correctly. Enter: help reorder");
        }
    }

    @Override
    void start_without_arguments() {
        reverse = !reverse;
        data.sort(reverse);
        Console.sendln("Коллекция отсортирована");
    }
}

