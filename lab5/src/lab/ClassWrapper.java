package lab;

import lab.Classes.MusicBand;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/** 以xml格式保存集合
 * Save collections in xml format */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ClassWrapper {

    private ArrayList<MusicBand> theCollection;

    public ClassWrapper() {
        theCollection = new ArrayList<MusicBand>();
    }

    public ArrayList<MusicBand> getTheCollection() {
        return theCollection;
    }

    public void setTheCollection(ArrayList<MusicBand> theCollection) {
        this.theCollection = theCollection;
    }
}
