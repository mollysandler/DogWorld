import java.util.ArrayList;
import java.util.List;

/**
 * @author Saylor Benes
 */

//TODO:
    //THE DIAMONDS DO NOT GET DELETED FROM THE SCREEN WHEN THEY ARE DRAGGED TO THE TRASH SO THEY SHOW UP WHEN THE LEVEL IS SAVED

public class DiamondList {
    private static DiamondList instance;
    private static List<Diamond> diamondList;

    private DiamondList() {
        diamondList = new ArrayList<>();
    }

    static DiamondList getInstance(){
        if (instance == null){
            instance = new DiamondList();
        }
        return instance;
    }

    public void addDiamond(Diamond diamond){
        diamondList.add(diamond);
    }
    public void setDiamond(List<Diamond> diamondList){
        DiamondList.diamondList = diamondList;
    }

}
