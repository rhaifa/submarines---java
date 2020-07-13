import java.util.Arrays;
import java.util.HashMap;

public class Submarine {
    private cordinates upper_left_cordinate;
    private boolean [] destroyed_part;
    int hitpoint;
    private boolean horizontal_direction;

    public Submarine(cordinates upper_left_cordinate, int length, boolean horizontal_direction, HashMap<String, Submarine> map) {
        this.upper_left_cordinate = upper_left_cordinate;
        this.destroyed_part = new boolean[length];  //init automatically to false
        this.hitpoint = length;
        this.horizontal_direction = horizontal_direction;

        //update fleet map
        if (horizontal_direction)
            for (int i=0; i<length; i++)
                map.put(String.valueOf(upper_left_cordinate.get_index() + i), this);
        else
            for (int i=0; i<length; i++)
                map.put(String.valueOf(upper_left_cordinate.get_index() + (i * Main.C)), this);
    }

    public void print_submarine_status(){
        System.out.println("hitpoint left: " + hitpoint + " , destroyed parts:");
        System.out.println(Arrays.toString(this.destroyed_part));
    }


    public void were_attacked(int attack_index){
        int destroyed_part_index;
        if (this.horizontal_direction)
            destroyed_part_index = attack_index - this.upper_left_cordinate.get_index();
        else
            destroyed_part_index = (attack_index - this.upper_left_cordinate.get_index())/Main.C;

        if (destroyed_part[destroyed_part_index]){  // we already attacked this part
            System.out.println("this point has already been attacked");
        }else{
            destroyed_part[destroyed_part_index] = true;
            hitpoint--;
        }
    }

    public void make_drawn_sound(){
        System.out.println("blab");
    }


}