package lab;

import lab.Commands.Command;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public class Console {
    /**
     * An array that stores the commands available to the user. */
    private ArrayList<Command> commands = new ArrayList<>();
    /**
     *  A field that stores a class that allows access to the collection.*/
    private Database data;
    //Нужно для защиты от рекурсии
    private ArrayList<String> level_list = new ArrayList<>();
    private int level = -1;


    /**
     * Console Constructs
     * @param data Указываем базу данных, которую будем менять.
     *  @param commands Указываем команды. Их может быть несколько.*/
    public Console(Database data, Command... commands){
        this.data = data;
        Collections.addAll(this.commands, commands);
    }

    /**
     * Method, allows you to add a command for the user.
     * @param commands Команды для добавления
     */
    public void addCommand(Command ... commands){
        Collections.addAll(this.commands, commands);
    }

    /**
     * method, allows to delete the command and the user will not be able to call it
     * @param command экземпляр команды
     */
    public void removeCommand(Command command){
        commands.removeIf(c -> c.equals(command));
    }

    /**
     * A method that is used to check the string entered by the user
     * @param line String - строка пользователя
     */
    public void update(String line){
        boolean wasСalled = false;
        for (Command command : commands) {
            if (command.execute(line)) {
                wasСalled = true;
            }
        }
        if (!wasСalled) {
            Console.sendln("The command is not correct. Enter help for help.");
        }
    }
    /**
     * The method used to start the console. */
    public void start(){
        try {
            for(Command command : commands){
                command.initialization(data, this);
            }
            Scanner scanner = new Scanner(System.in);
            sendln("Enter help for a list of available commands:");
            send(">");
            while (scanner.hasNextLine()) {
                update(scanner.nextLine());
                send(">");
            }
            sendln("End of program");
            System.exit(0);
        }
        catch (Exception e){
            try {
                Console.sendln(e.getCause().toString());
                Console.sendln(e.getMessage());
                Console.sendln(e.getLocalizedMessage());
                sendln("A critical error has occurred. Restarting the command line...");
                if (Console.getAnswer("Do you want to save the data? They can damage the data.")) {
                    data.save();
                    start();
                }
            } catch (Exception ex) {
                sendln("End of program");
                Console.sendln(e.getMessage());
                System.exit(0);
            }
        }
    }

    /**
     * Method, allows you to get a string from the user
     * @return String кракозябры пользователя
     */
    public static String receive(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    /**
     *  A method that outputs information on a new line to the console
     * @param info String - информация
     */
    public static void sendln(String info){
        System.out.println(info);
    }
    /**
     * Method, outputs to the console
     * @param info String - информация
     */
    public static void send(String info){ System.out.print(info); }

    /**
     * method that allows to read the file
     * @param FILE_PATH String - название файла
     * @return String - содержимое файла
     */
    public static String readFile(String FILE_PATH) throws IOException {
        StringBuffer data = new StringBuffer();
        // Create an InputStreamReader object for a File object
        InputStreamReader fr = new InputStreamReader(new FileInputStream(FILE_PATH), "UTF-8");
        //Create a BufferedReader from the existing InputStreamReader for line-by-line reading
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        while (line != null) {
            data.append(line).append("\n");
            line = reader.readLine();
        }
        reader.close();
        fr.close();
        return data.toString();
    }

    public static boolean getAnswer(String info){
        while (true){
            Console.sendln(info+"(Yes/No)");
            Console.send(">");
            String line = Console.receive();
            if (line.equals("Yes") || line.equals("Y") || line.equals("y") || line.equals("yes") || line.equals("да") || line.equals("Да") || line.equals("д")|| line.equals("Д")) {
                return true;
            } else if (line.equals("No") || line.equals("N") || line.equals("n") || line.equals("no") || line.equals("нет") || line.equals("Нет") || line.equals("н") || line.equals("Н")) {
                return false;
            }
        }
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void addLevel_list(String s) {
        level_list.add(s);
        level += 1;
    }

    public void remove_list(){
        level_list.remove(level);
        level -= 1;
    }

    public boolean check_list(String s){
        return level_list.contains(s);
    }
}


