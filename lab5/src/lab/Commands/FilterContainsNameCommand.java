package lab.Commands;

import lab.Console;
import java.util.ArrayList;

/**
 * 输出名称字段值包含给定子串的项目。
 */
public class FilterContainsNameCommand extends Command {
    {
        command_name = new String[]{"filter_contains_name"};
        description = "filter_contains_name name - outputs items whose name field values contain the specified substring";
    }
    @Override
    void start_with_arguments(ArrayList<String> args, ArrayList<Character> flags) {
        if(args.isEmpty()){
            start_without_arguments();
        }
        else {
            int j = 0;
            for (int i = 1; i <= data.size(); i++) {
                if (data.get(i).getName().contains(args.get(0))) {
                    Console.sendln(data.get(i).toString());
                    j++;
                }
            }
            if (j == 0) {
                Console.sendln("There are no such elements");
            }
        }
    }

    @Override
    void start_without_arguments() {
        Console.sendln("У команды должен быть аргумент name. Например: filter_contains_name имя");
    }
}
