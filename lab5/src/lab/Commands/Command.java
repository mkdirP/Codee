package lab.Commands;

import lab.Console;
import lab.Database;

import java.util.ArrayList;

public abstract class Command implements ICommand{
    protected Database data;
    protected Console user;
    protected String[] command_name = {"command_test"};
    protected String origin_line;
    protected String description = "No description";

    Command() {}

    /**
     * 一个方法，如果输入了带参数的命令，将被执行。
     * A method that will be executed if a command is entered with arguments.
     * @param args ArrayList<String> arguments
     * @param flags ArrayList<Character> flags
     */
    abstract void start_with_arguments(ArrayList<String> args, ArrayList<Character> flags);

    /**
     * 一个方法，如果输入一个没有参数的命令，将被执行
     * A method that will be executed if a command is entered without arguments
     */
    abstract void start_without_arguments();

    @Override
    public void initialization(Database db, Console user) {
        this.data = db;
        this.user = user;
    }

    /**
     * 运行命令的方法  The method that runs the command
     * @param line
     * @return
     */
    @Override
    public boolean execute(String line) {
        line = line.trim();
        origin_line = line;
        for(String cname : command_name) {
            if (line.indexOf(cname) == 0) {
                data.log(cname);
                if (line.equals(cname)) {
                    start_without_arguments();
                } else {
                    char[] temp = line.toCharArray();
                    ArrayList<String> args = new ArrayList<>();
                    ArrayList<Character> flags = new ArrayList<>();
                    ArrayList<Character> tmp = new ArrayList<>();
                    StringBuilder b = new StringBuilder();

                    int mod = 1;
                    boolean isString = false;

                    for (char c : temp){
                        switch (mod) {
                            case 1:
                                if(c == ' '){
                                    mod = 2;
                                }
                                break;
                            case 2:
                                if(isString){
                                    if(c == ' '){
                                        isString = false;
                                        mod = 3;
                                    }
                                    else{
                                        flags.add(c);
                                    }
                                }
                                else {
                                    if (c == '-') {
                                        isString = true;
                                    }
                                    else{
                                        mod = 4;
                                        tmp.add(c);
                                    }
                                }
                                break;
                            case 3:
                                if(c == '-'){
                                    mod = 2;
                                    isString = true;
                                }
                                else{
                                    mod = 4;
                                    tmp.add(c);
                                }
                                break;
                            case 4:
                                tmp.add(c);
                                break;
                        }
                    }
                    tmp.add(' ');
                    isString = false;
                    for(Character c : tmp){
                        if(isString) {
                            if (c == '"') {
                                isString = false;
                            } else {
                                b.append(c);
                            }
                        } else{
                            if (c == '"') {
                                isString = true;
                            } else if(c == ' '){
                                args.add(b.toString());
                                b = new StringBuilder();
                            } else{
                                b.append(c);
                            }
                        }
                    }
                    start_with_arguments(args, flags);
                }
                return true;
            }
        }
        return false;
    }

    public String[] getCommandName() {
        return command_name;
    }

    public String getDescription() {
        return description;
    }
}

