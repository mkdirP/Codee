package lab.Commands;

import lab.Console;
import java.util.ArrayList;

public class AddCommand extends Command{
    {
        command_name = new String[]{"add"};
        description = "add name x y numberOfParticipants - стандартная команда с вводом примитивных данных\n" +
                "Флаги: n - Name\n" +
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
        if(flags.contains('m')){
            try {
                char[] chars_old = origin_line.toCharArray();
                boolean isString;
                boolean isMas;
                ArrayList<Character> chars = new ArrayList<>();
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
                    if ((word != ' ' || isString) && isMas && word != '{' && word != '}' && word != '[' && word != ']') {
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

                isString = false;
                for (char word : chars) {
                    if(word == '"'){isString = !isString;}
                    if (word == ':') {
                        nameORvalue = true;
                        Console.sendln("name: "+tmpName);
                        if (tmpName.toString().equals("coordinates")){
                            nameORvalue = false;
                            tmpValue = new StringBuilder();
                        }
                        if (tmpName.toString().equals("label")){
                            nameORvalue = false;
                            tmpValue = new StringBuilder();
                        }
                        if(tmpName.toString().equals("creationDate")){ if(!tmpValue.toString().equals("")){tmpValue.append(":");} }
                        continue;
                    } else if (word == ',' && !isString) {
                        Console.sendln("value: "+tmpValue);
                        nameORvalue = false;
                        if (tmpName.toString().equals("name")) {
                            name = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().indexOf("coordinates") == 0 && tmpName.toString().endsWith("x")){
                            x = tmpValue.toString();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().indexOf("coordinates") == 0 && tmpName.toString().endsWith("y")){
                            y = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            nameORvalue = false;
                            continue;
                        }
                        if (tmpName.toString().equals("numberOfParticipants")){
                            numberOfParticipants = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().equals("description")){
                            description = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().equals("creationDate")){
                            creationDate = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().equals("genre")){
                            genre = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().indexOf("label") == 0 && tmpName.toString().endsWith("name")){
                            labelName = tmpValue.toString();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().indexOf("label") == 0 && tmpName.toString().endsWith("tracks")){
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
                Console.sendln(name + "," + x + "," + y + "," + numberOfParticipants + "," + description + "," + creationDate + "," + genre + "," + labelName + "," + labelBands);
                if(data.add(name, x, y, numberOfParticipants, description, creationDate, genre, labelName, labelBands)){ Console.sendln("Object added");}
                else{ Console.sendln("The object has not been added."); }
            }
            catch (Exception e){ Console.sendln("Example command: add {name:\"name\", coordinates:[x:100.0,y:12.0], numberOfParticipants:1000, description:\"description\", creationDate:1986-04-08 12:30, genre:POP, label:[name:label_name, bands:label_bands]}"); }
        }else if(flags.contains('n') || flags.contains('x') || flags.contains('y') || flags.contains('p') || flags.contains('d') || flags.contains('c') || flags.contains('g') || flags.contains('l') || flags.contains('b')){
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
                    if (flags.get(i).equals('n')){
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
            if(data.add(name, x, y, numberOfParticipants, description, creationDate, genre, labelName, labelBands)){ Console.sendln("Object added");}
            else{ Console.sendln("The object has not been added."); }
        }
        else if(!flags.isEmpty()){
            Console.sendln("Incorrect flags. Lead: help add");
        }
        else if(!args.isEmpty() && args.size()>= 4){
            if(data.add(args.get(0), args.get(1), args.get(2), args.get(3), null, null, null, null, null)){
                Console.sendln("Object added");
            }
            else{ Console.sendln("The object has not been added."); }
        }
        else{
            start_without_arguments();
        }
    }

    @Override
    void start_without_arguments() {
        if(data.add(null, null, null, null, null, null, null, null, null)){ Console.sendln("Object added");}
        else{ Console.sendln("The object has not been added."); }
    }
}
