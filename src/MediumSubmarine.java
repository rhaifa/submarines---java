import java.util.HashMap;

public class MediumSubmarine extends Submarine {
    private static final int length = 3;


    public MediumSubmarine(cordinates upper_left_cordinate, boolean horizontal_direction, HashMap<String, Submarine> map){
        super(upper_left_cordinate, MediumSubmarine.length, horizontal_direction, map);
    }

    @Override
    public void make_drawn_sound(){
        System.out.println("blooob");
    }

}