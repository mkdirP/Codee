package lab.Commands;

import lab.Console;
import java.util.ArrayList;

public class ShowCommand extends Command{
    {
        command_name = new String[]{"show"};
        description = "show - outputs to the standard output stream all elements of the collection in string representation";
    }

    @Override
    void start_with_arguments(ArrayList<String> args, ArrayList<Character> flags) {
        Console.sendln("This command hasn't arguments.");
        start_without_arguments();
    }

    @Override
    void start_without_arguments() {
        if (data.size() != 0){
            for (int i = 1; i <= data.size(); i++){
                Console.sendln(data.get(i).toString());
            }
        }else {
            Console.sendln("The elements are missing");
        }
    }
}
