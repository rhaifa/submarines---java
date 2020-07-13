import java.util.HashMap;

public class BigSubmarine extends Submarine {
    private static final int length = 4;


    public BigSubmarine(cordinates upper_left_cordinate, boolean horizontal_direction, HashMap<String, Submarine> map){
        super(upper_left_cordinate, BigSubmarine.length, horizontal_direction, map);
    }

    @Override
    public void make_drawn_sound(){
        System.out.println("blooooooooob");
    }

}