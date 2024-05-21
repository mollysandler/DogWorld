import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
        this.diamondList = diamondList;
    }



}
