package lab.Commands;

import java.util.ArrayList;

public class RemoveByIdCommand extends Command{
    {
        command_name = new String[]{"remove_by_id"};
        description = "remove_by_id id - remove an item from the collection by its id. You can specify more than one id.\n" +
                "For example: remove_by_id 1 2 3\n" +
                "Then the command to remove elements from 1, 2 и 3 ID";
    }

    @Override
    void start_with_arguments(ArrayList<String> args, ArrayList<Character> flags) {
        try {
            for (String arg : args) {
                data.remove(Integer.parseInt(arg));
            }
            System.out.println("Deletion is successful");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Item with this ID is not on the list");
        }catch (Exception e) {
            System.out.println("Something very scary has happened.");
        }
    }

    @Override
    void start_without_arguments() {
        System.out.println("Enter the command with the id argument. For example: remove_by_id 1");
    }
}
