package lab.Commands;

import lab.Database;
import lab.Console;

public interface ICommand {
    void initialization(Database db, Console user);
    boolean execute(String line);
}
