package lab.Commands;

import lab.Console;

import java.util.ArrayList;

public class UpdateIdCommand extends Command{
    {
        command_name = new String[]{"update"};
        description = "update id name x y numberOfParticipants - standard command with primitive data entry\n" +
                "Флаги: i - ID\n" +
                "       n - Name\n" +
                "       x - X\n" +
                "       y - Y\n" +
                "       p - Number of participants\n" +
                "       d - Description\n" +
                "       c - Creation date\n" +
                "       g - Genre\n" +
                "       l - Label name\n" +
                "       b - Label bands\n" +
                "The flags above are displayed in the same order as the arguments.\n" +
                "       m - input in xml format. If present, other flags are ignored.";
    }
    @Override
    void start_with_arguments(ArrayList<String> args, ArrayList<Character> flags) {
        if(flags.contains('f')) {
            try {
                String[] find_id = origin_line.split(" ");
                char[] chars_old = origin_line.toCharArray();
                boolean isString;
                boolean isMas;
                ArrayList<Character> chars = new ArrayList<Character>();
                isMas = false;
                isString = false;
                boolean nameORvalue = false;
                StringBuilder tmpName = new StringBuilder();
                StringBuilder tmpValue = new StringBuilder();

                for (char word : chars_old) {
                    //if(word == '"'){isString = !isString;}
                    if (word == ':') {
                        isString = true;
                    }
                    if (word == ',') {
                        isString = false;
                    }
                    if (word == '{') {
                        isMas = true;
                    }
                    if ((word != ' ' || isString) && isMas && word != '{' && word != '}' && word != '[' && word != '"' && word != ']') {
                        chars.add(word);
                    }
                }
                chars.add(',');

                String name = null;
                String x = null;
                String y = null;
                String numberOfParticipants = null;
                String description = null;
                String creationDate = null;
                String genre = null;
                String labelName = null;
                String labelBands = null;

                //add {name:"name", coordinates:{x:100.0,y:12.0}, numberOfParticipants:1000, description:"description", creationDate:1986-04-08 12:30, genre:POP, label:[name:label_name, label_bands]}
                for (char word : chars) {
                    if (word == ':') {
                        nameORvalue = true;
                        Console.sendln("name: " + tmpName);
                        if (tmpName.toString().equals("coordinates")) {
                            nameORvalue = false;
                            tmpValue = new StringBuilder();
                        }
                        if (tmpName.toString().equals("bestAlbum")) {
                            nameORvalue = false;
                            tmpValue = new StringBuilder();
                        }
                        if (tmpName.toString().equals("establishmentDate")) {
                            if (!tmpValue.toString().equals("")) {
                                tmpValue.append(":");
                            }
                        }
                        continue;
                    } else if (word == ',') {
                        Console.sendln("value: " + tmpValue);
                        nameORvalue = false;
                        if (tmpName.toString().equals("name")) {
                            name = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().indexOf("coordinates") == 0 && tmpName.toString().endsWith("x")) {
                            x = tmpValue.toString();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().indexOf("coordinates") == 0 && tmpName.toString().endsWith("y")) {
                            y = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            nameORvalue = false;
                            continue;
                        }
                        if (tmpName.toString().equals("numberOfParticipants")) {
                            numberOfParticipants = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().equals("description")) {
                            description = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().equals("creationDate")) {
                            creationDate = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().equals("genre")) {
                            genre = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().indexOf("label") == 0 && tmpName.toString().endsWith("name")) {
                            labelName = tmpValue.toString();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().indexOf("label") == 0 && tmpName.toString().endsWith("tracks")) {
                            labelBands = tmpValue.toString();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                    }
                    if (!nameORvalue) {
                        tmpName.append(word);
                    } else {
                        tmpValue.append(word);
                    }
                }
                if (data.update(find_id[1], name, x, y, numberOfParticipants, description, creationDate, genre, labelName, labelBands)) {
                    Console.sendln("The object has been updated. id: " + find_id[1]);
                } else {
                    Console.sendln("There is no object.");
                }
            } catch (Exception e) {
                Console.sendln("Example command: add {name:\"name\", coordinates:[x:100.0,y:12.0], numberOfParticipants:1000, description:\"description\", creationDate:1986-04-08 12:30, genre:POP, label:[name:label_name, label_bands]}");
            }
        }
        else if(flags.contains('i') || flags.contains('n') || flags.contains('x') || flags.contains('y') || flags.contains('p') || flags.contains('d') || flags.contains('c') || flags.contains('g') || flags.contains('l') || flags.contains('b')){
            String id = null;
            String name = null;
            String x = null;
            String y = null;
            String numberOfParticipants = null;
            String description = null;
            String creationDate = null;
            String genre = null;
            String labelName = null;
            String labelBands = null;
            for(int i = 0; i < args.size(); i++){
                if (flags.size() >= i) {
                    if (flags.get(i).equals('i')){
                        id = args.get(i);
                    }
                    else if (flags.get(i).equals('n')){
                        name = args.get(i);
                    }
                    else if (flags.get(i).equals('x')){
                        x = args.get(i);
                    }
                    else if (flags.get(i).equals('y')){
                        y = args.get(i);
                    }
                    else if (flags.get(i).equals('p')){
                        numberOfParticipants = args.get(i);
                    }
                    else if (flags.get(i).equals('d')){
                        description = args.get(i);
                    }
                    else if (flags.get(i).equals('c')){
                        creationDate = args.get(i);
                    }
                    else if (flags.get(i).equals('g')){
                        genre = args.get(i);
                    }
                    else if (flags.get(i).equals('l')){
                        labelName = args.get(i);
                    }
                    else if (flags.get(i).equals('b')){
                        labelBands = args.get(i);
                    }
                }
            }
            if(data.update(id, name, x, y, numberOfParticipants, description, creationDate, genre, labelName, labelBands)){ Console.sendln("The object has been updated.");}
            else{ Console.sendln("The object has not been added. Check if the entered command is correct."); }
        }
        else if(!flags.isEmpty()){
            Console.sendln("Incorrect flags. Lead: help update");
        }
        else if(!args.isEmpty() && args.size()>= 5){
            if(data.update(args.get(0),args.get(1), args.get(2), args.get(3), args.get(4), null, null, null, null, null)){ Console.sendln("The object has been updated.");}
            else{ Console.sendln("The object has not been added. Check if the entered command is correct."); }
        }
        else if(args.size() > 0){
            if(data.update(args.get(0),null, null, null, null, null, null, null, null, null)){ Console.sendln("The object has been updated.");}
            else{ Console.sendln("The object has not been added. Check if the entered command is correct."); }
        }
        else{
            start_without_arguments();
        }
    }

    @Override
    void start_without_arguments() {
        Console.sendln("Example command: update id name x y numberOfParticipants\n");
        Console.sendln("Example command: update 1 -f {name:\"Twenty One Pilots\", coordinates:[x:38.0,y:97.0], numberOfParticipants:2, description:\"американский дуэт из Колумбуса, штат Огайо. Группа образовалась в 2009 году и на данный момент состоит из Тайлера Джозефа и Джоша Дана.\", creationDate:2009-12-29 12:30, genre:ROCK, label:[name:\"..\", bands:\"..\"]}\n");
        Console.sendln("Or use the command: help update");
    }
}
