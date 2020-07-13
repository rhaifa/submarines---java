import java.util.Arrays;
import java.util.HashMap;

public class SmallSubmarine extends Submarine {
    private static final int length = 2;


    public SmallSubmarine(cordinates upper_left_cordinate, boolean horizontal_direction, HashMap<String, Submarine> map){
        super(upper_left_cordinate, SmallSubmarine.length, horizontal_direction, map);
    }

    @Override
    public void make_drawn_sound(){
        System.out.println("blob");
    }

}