package lab.Classes;

import lab.Console;

import java.time.LocalDateTime;

public class MusicBand implements Comparable<MusicBand>{
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int numberOfParticipants; //Значение поля должно быть больше 0
    private String description; //Поле может быть null
    private MusicGenre genre; //Поле может быть null
    private Label label; //Поле может быть null

    /**
     *  @param id Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
     * @param name Имя объекта. Поле не может быть null, Строка не может быть пустой
     * @param coordinates Поле не может быть null
     * @param numberOfParticipants Количество людей в музыкальной группе. Значение поля должно быть больше 0
     * @param description Описание музыкальной группы. Поле не может быть null
     * @param genre Жанр(PSYCHEDELIC_ROCK, HIP_HOP, PSYCHEDELIC_CLOUD_RAP, POP, POST_PUNK;). Поле не может быть null
     * @param label Поле может быть null
     */
    public MusicBand(Long id, String name, Coordinates coordinates, int numberOfParticipants, String description, MusicGenre genre, Label label) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.numberOfParticipants = numberOfParticipants;
        this.description = description;
        this.genre = genre;
        this.label = label;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public static MusicBand parseMusicBand(Long id){
        return parseMusicBand(id, null, null, null , null, null, null, null, null);
    }


    //TODO переписать под новые поля
    public static MusicBand parseMusicBand(Long id, String name, String x, String y, String numberOfParticipants, String description, String genre, String label_name, String label_bands) {
        if (name == null || name.isEmpty()) {
            while (true) {
                Console.sendln("Имя не может быть пустым. Укажите имя:");
                Console.send("?");
                String l = Console.receive();
                if (l != null && !l.equals("")) {
                    name = l;
                    break;
                }
            }
        }
        Integer x_coordinate;
        try {
            x_coordinate = Integer.parseInt(x);
        }
        catch (Exception e){
            while (true) {
                Console.sendln("X является числом. Укажите X:");
                Console.send("?");
                try {
                    x_coordinate = Integer.parseInt(Console.receive());
                    break;
                }
                catch (NullPointerException | NumberFormatException ignored){ }
            }
        }

        Float y_coordinate;
        try{
            y_coordinate = Float.parseFloat(y);
            if(Float.isNaN(y_coordinate)){throw new Exception();}
        }
        catch (Exception e){
            while (true) {
                Console.sendln("Y является числом. Укажите Y:");
                Console.send("?");
                try {
                    y_coordinate = Float.parseFloat(Console.receive());
                    if (!Float.isNaN(y_coordinate)) {
                        break;
                    }
                }
                catch (NullPointerException | NumberFormatException ignored){ }
            }
        }


        int numberOfParticipants_int;
        try {
            numberOfParticipants_int = Integer.parseInt(numberOfParticipants);
            if (numberOfParticipants_int <= 0 || numberOfParticipants == null || numberOfParticipants.isEmpty()) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e){
            while (true) {
                Console.sendln("Количество участников должно быть числом и должно быть больше нуля. Укажите количество участников:");
                Console.send("?");
                try {
                    numberOfParticipants_int = Integer.parseInt(Console.receive());
                    if (numberOfParticipants_int > 0) {
                        break;
                    }
                }
                catch (NullPointerException | NumberFormatException ignored){ }
            }
        }
        if((description == null) && Console.getAnswer("Хотите добавить описание?")) {
            while (true) {
                Console.sendln("Введите описание:");
                Console.send("?");
                description = Console.receive();
                if (description != null) {
                    break;
                }
            }
        }
        //TODO поменять жанры на свои
        MusicGenre mgenre = null;
        if((genre == null) && Console.getAnswer("Хотите добавить жанр?")) {
            while (true) {
                if (genre == null || genre.isEmpty()) {
                    Console.sendln("Укажите жанр(PSYCHEDELIC_ROCK, RAP, POP, POST_ROCK, POST_PUNK):");
                    Console.send("?");
                    genre = Console.receive();
                }else {

                    Console.sendln("Укажите жанр(PSYCHEDELIC_ROCK, RAP, POP, POST_ROCK, POST_PUNK):");
                    Console.send("?");
                    genre = Console.receive();
                }
            }
        }
        if (label_name == null || label_name.isEmpty()) {
            while (true) {
                Console.sendln("Имя не может быть пустым. Укажите имя:");
                Console.send("?");
                String l = Console.receive();
                if (l != null && !l.equals("")) {
                    label_name = l;
                    break;
                }
            }
        }

        Long bands_Long;
        try{
            bands_Long = Long.parseLong(label_bands);
            if(bands_Long <= 0){new Exception();}
        }
        catch (Exception e){
            while (true) {
                Console.sendln("weight является числом и должно быть больше 0. Укажите weight:");
                Console.send("?");
                try {
                    bands_Long = Long.parseLong(Console.receive());
                    if (bands_Long > 0) {
                        break;
                    }
                }
                catch (NullPointerException | NumberFormatException ignored){ }
            }
        }
        return new MusicBand(id, name, new Coordinates(x_coordinate, y_coordinate), numberOfParticipants_int, description, mgenre, new Label(label_name, bands_Long));
    }
    /** Переопределенный метод toString
     *
     * @return возвращает объект в виде текста
     */
    //TODO переписать под новые поля
    @Override
    public String toString() {
        return  "________________________________________________________________" + "\n" +
                "|ID: " + id + "\n" +
                "|Name: (" + name + ")\n" +
                "|Coordinates: " + coordinates + "\n" +
                "|Creation Date: " + creationDate + " \n" +
                "|Number Of Participants: " + numberOfParticipants + "\n" +
                "|Description: (" + description + ")\n" +
                "|Genre: " + genre + "\n" +
                "|Label: "  + label +"\n" +
                "________________________________________________________________";
    }
    @Override
    public int compareTo(MusicBand anotherMusicBand)
    {
        if (this.id.equals(anotherMusicBand.id)) {
            return 0;
        } else if (this.id < anotherMusicBand.id) {
            return -1;
        } else {
            return 1;
        }
    }
}

