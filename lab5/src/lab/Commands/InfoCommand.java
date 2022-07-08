package lab.Commands;
import lab.Console;

import java.util.ArrayList;

public class InfoCommand extends Command{
    {
        command_name = new String[]{"info"};
        description = "info - displays information about the collection.";
    }

    @Override
    void start_with_arguments(ArrayList<String> args, ArrayList<Character> flags) {
        Console.sendln("Collection type: ArrayList\nDate of initialization: " + data.getCreateTime()
        + "\nNumber of elements: " + data.size());
    }

    @Override
    void start_without_arguments() {
        Console.sendln("Collection type: ArrayList\nDate of initialization: " + data.getCreateTime()
        + "\nNumber of elements: " + data.size());
    }
}
