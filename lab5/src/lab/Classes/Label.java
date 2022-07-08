package lab.Classes;

public class Label implements Comparable<Label>{
    private String name;
    private Long bands;

    public Label() {
    }

    public Label(String name, long bands) {
        this.name = name;
        this.bands = bands;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBands() {
        return bands;
    }

    public void setBands(long bands) {
        this.bands = bands;
    }

    @Override
    public String toString() {
        return "Label{" +
                "name='" + name + '\'' +
                ", bands=" + bands +
                '}';
    }

    @Override
    public int compareTo(Label otherLabel) {
        if(this.bands.equals(otherLabel.bands)){
            return 0;
        }
        else if(this.bands < otherLabel.bands){
            return -1;
        }
        else{
            return 1;
        }
    }
}
