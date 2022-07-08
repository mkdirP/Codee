package lab;

import lab.Classes.MusicBand;

import javax.xml.bind.*;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class Database {
    /** The field that stores the path to the database file */
    private final String file_path;
    /** A collection that is used to represent data in a running program.*/
    private ArrayList<MusicBand> data = new ArrayList<>();
    /** A sheet that stores the history of the commands entered.*/
    private ArrayList<String> history = new ArrayList<>();

    public Database(String filePath) {
        this.file_path = filePath;
        if (load()){
            Console.sendln("Loading is successful.");
        }else{
            Console.sendln("Past data is not loaded.");
            Console.sendln("Creating a data file.");
            if (save()){
                Console.sendln("File created");
                System.exit(0);
            }else{
                Console.sendln("No access to file");
                System.exit(0);
            }
        }
    }

    /**
     * Method, allows to get the id for a new object.
     * @return id：add new id
     */
    private int giveId(){
        boolean is = false;
        for (int result = 1; result <= data.size(); result++){
            for (MusicBand musicBand : data){
                if (musicBand.getId() == result) {
                    is = true;
                }
            }if (is = false){
                is = false;
            }else{
                return result;
            }
        }
        return data.size() + 1;
    }

    /**
     * Mark an object as its ID.
     * @param id    ID объекта, который мы хотим поменять.
     * @param name  Имя объекта. Поле не может быть null, Строка не может быть пустой
     * @param x Координата X. Максимальное значение поля: 60, Поле не может быть null
     * @param y Координата Y. Значение поля должно быть больше -646
     * @param numberOfParticipants  Количество людей в музыкальной группе. Значение поля должно быть больше 0
     * @param description   Описание музыкальной группы. Поле не может быть null
     * @param creationDate  Дата создания музыкальной группы. Формат: yyyy-MM-dd. Поле не может быть null.
     * @param genre   Жанр(PSYCHEDELIC_ROCK, HIP_HOP, PSYCHEDELIC_CLOUD_RAP, POP, POST_PUNK;). Поле не может быть null
     * @param labelName     название этикетки
     * @param labelBands    этикетки групп  标签乐队
     * @return true - success, false - exception
     */
    public boolean update(String id, String name, String x, String y,String numberOfParticipants,
                          String description, String creationDate, String genre,
                          String labelName, String labelBands){
        try{
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Saves the collection to a file, the name of which is specified when the object is created.
     * @return  true - success, false - exception
     */
    public boolean save(){
        try {
            JAXBContext context = JAXBContext.newInstance(ClassWrapper.class);
            ClassWrapper cw = new ClassWrapper();
            cw.setTheCollection(data);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File fileWrite = new File(file_path);
            FileWriter fw = new FileWriter(fileWrite);
            BufferedWriter bw = new BufferedWriter(fw);
            marshaller.marshal(cw, bw);
            return true;
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean load(){
        try {
            File fileRead = new File(file_path);
            FileReader fr = new FileReader(fileRead);
            BufferedReader br = new BufferedReader(fr);
            ClassWrapper returnedHS = JAXB.unmarshal(br, ClassWrapper.class);
            data = returnedHS.getTheCollection();
            return true;
        }catch (FileNotFoundException e){
            return false;
        }
    }

    /**
     * A method to add an object to a collection.
     * @param name  Имя объекта. Поле не может быть null, Строка не может быть пустой
     * @param x Координата X. Максимальное значение поля: 60, Поле не может быть null
     * @param y Координата Y. Значение поля должно быть больше -646
     * @param numberOfParticipants  Количество людей в музыкальной группе. Значение поля должно быть больше 0
     * @param description   Описание музыкальной группы. Поле не может быть null
     * @param creationDate  Дата создания музыкальной группы. Формат: yyyy-MM-dd. Поле не может быть null.
     * @param genre   Жанр(PSYCHEDELIC_ROCK, HIP_HOP, PSYCHEDELIC_CLOUD_RAP, POP, POST_PUNK;). Поле не может быть null
     * @param labelName     название этикетки
     * @param labelBands    этикетки групп
     * @return  true - success, false - exception
     */
    public boolean add(String name, String x, String y, String numberOfParticipants, String description, String creationDate, String genre, String labelName, String labelBands){
        try {
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * A method that allows you to remove objects from a collection by ID.
     * (ID в коллекции начинаються с 1, а не с 0.)
     * @param id ID файла, который хотим удалить.
     * @return true - success, false - exception
     */
    public boolean remove(int id) {
        try {
            data.removeIf(m -> m.getId() == id);
            return true;
        }
        catch (Exception ignored){}
        return false;
    }

    /**
     * Method, allows to clear the collection.
     * @return true - success, false - exception
     */
    public boolean clean(){
        try {
            data = new ArrayList<MusicBand>();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    /**
     *  method, allows you to get the date when the file was created.
     * @return String - date created
     */
    public String getCreateTime(){
        try {
            //return data.get(0).getCreateTime().toString();
            BasicFileAttributes attr = Files.readAttributes(Paths.get(file_path), BasicFileAttributes.class);
            return attr.creationTime().toString();
        }
        catch (Exception e){
            return "The collection has no items.";
        }
    }

    /**
     * A method that allows you to retrieve an object by its ID.
     * (ID в коллекции начинаються с 1, а не с 0.)
     * @param id ID объекта.
     *
     * @return Объект MusicBand.
     */
    public MusicBand get(int id){
        return data.get(id-1);
    }

    /**
     *  method, allows you to get the number of items in the collection.
     * @return int - колличество элементов.
     */
    public int size(){
        return data.size();
    }

    /**
     * A method that allows you to record a command in the history.
     * @param command Команда, которую надо записать.
     */
    public void log(String command) {
        history.add(command);
    }

    public void sort(boolean reverse){
        if(!reverse){
            Collections.sort(data, (player2, player1) -> {
                if (player1.getId() < player2.getId()) {
                    return 1;
                } else if (player1.getId() > player2.getId()) {
                    return -1;
                } else {
                    return 0;
                }
            });
        }
        else{
            Collections.sort(data, (player2, player1) -> {
                if (player1.getId() > player2.getId()) {
                    return 1;
                } else if (player1.getId() < player2.getId()) {
                    return -1;
                } else {
                    return 0;
                }
            });
        }
    }
}
