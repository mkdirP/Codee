package lab.Classes;

public class Coordinates {
    private Integer x;//Максимальное значение поля: 60, Поле не может быть null
    private long y;//Значение поля должно быть больше -646

    public Coordinates(Integer x_coordinate, Float y_coordinate) {
    }

    public Coordinates(Integer x, long y) throws NullException,ValueTooBigException{
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) throws NullException,ValueTooBigException{
        if (x > 60){
            throw new ValueTooBigException("x can't be bigger than 60\n");
        }else if (x == null){
            throw new NullException("x can't be wrong");
        }else{
            this.x = x;
        }
    }

    public long getY() {
        return y;
    }

    public void setY(long y) throws ValueTooSmallException{
        if (y < -646){
            throw new ValueTooSmallException("y can't be smaller than -646\n");
        }else{
            this.y = y;
        }
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
