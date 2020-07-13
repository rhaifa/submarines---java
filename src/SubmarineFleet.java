import java.util.Arrays;
import java.util.HashMap;

public class SubmarineFleet {
    int functioning_submarine_amount = 3;
    HashMap<String, Submarine> submarines;

    public SubmarineFleet() {
        this.submarines = init_submarine_map();
    }

    private HashMap<String, Submarine> init_submarine_map(){
        this.submarines = new HashMap<>();

        //create submarine objects
        // TODO make this random
        new BigSubmarine(new cordinates(0,0),true, this.submarines);
        new MediumSubmarine(new cordinates(1,0), true, this.submarines);
        new SmallSubmarine(new cordinates(2,0), false, this.submarines);

        return submarines;
    }

    public void were_attacked(int attack_location){
        String hash_key = String.valueOf(attack_location);
        Submarine attacked_submarine = this.submarines.get(hash_key);
        if (attacked_submarine == null) { // No such key
            System.out.println("missed!");
            return;
        }

        System.out.println("hit!");
        attacked_submarine.were_attacked(attack_location);
        if (attacked_submarine.hitpoint==0){
            System.out.println("submarine drawn");
            attacked_submarine.make_drawn_sound();
            functioning_submarine_amount--;
        }


    }



}
