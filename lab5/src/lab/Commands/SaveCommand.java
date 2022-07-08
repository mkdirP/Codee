package lab.Commands;

import lab.Console;
import java.util.ArrayList;

public class SaveCommand extends Command{
    {
        command_name = new String[]{"save"};
        description = "save - simply saves the collection to a file.\n" +
                "Flags: there is no flag here that changes the location of the file, otherwise you could do backups.\n" +
                "No more backups, only hardcore.";
    }
    @Override
    void start_with_arguments(ArrayList<String> args, ArrayList<Character> flags) {
        start_without_arguments();
    }

    @Override
    void start_without_arguments() {
        if (data.save()) {
            Console.sendln("Saving is successful.");
        } else {
            Console.sendln("An error occurred while saving the file");
            Console.sendln("Try using the save file command, where file is the name of the file in which the data is saved. For example: save data1.xml");
        }
    }
}
