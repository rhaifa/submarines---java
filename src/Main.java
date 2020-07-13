import java.util.Scanner;  // Import the Scanner class
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.ArrayList; // import the ArrayList class
import java.util.Arrays; // import the ArrayList class
import java.util.List;

public class Main {

    public static  int R;
    public static  int C;
    private static final int DELAY = 2;

    private static void _print_column_indices(boolean with_indices){
        if (with_indices){
            System.out.print("  ");
            for (int c = 0; c < C; c++)
                System.out.print(c);
            System.out.println();
            System.out.print("  ");
            for (int c = 0; c < C; c++)
                System.out.print("_");
            System.out.println();
        }
    }

    private static void print_board(SubmarineFleet fleet, boolean with_indices, List<Integer> attack_history){
        int current_index;
        String hash_key;
        Submarine current_submarine;
        boolean in_attack_history;

        _print_column_indices(with_indices);
        for (int r=0; r<R; r++) {
            if(with_indices){
                System.out.print(r+"|");
            }
            for (int c = 0; c < C; c++) {
                current_index = cordinates.to_index(r, c);
                //check if this cordinate was attacked
                in_attack_history = false;
                for(int old_index : attack_history)
                    if(current_index == old_index)
                        in_attack_history = true;

                if (!in_attack_history){
                    System.out.print(colors.ANSI_CYAN + "0" + colors.ANSI_RESET);
                }else{
                    //check for submarine
                    hash_key = String.valueOf(current_index);
                    current_submarine = fleet.submarines.get(hash_key);
                    if (current_submarine == null) { // No submarine
                        System.out.print(colors.ANSI_CYAN + "x" + colors.ANSI_RESET);
                    }else{
                        System.out.print(colors.ANSI_YELLOW + "S" + colors.ANSI_RESET);
                    }
                }

            }
            System.out.println();
        }
        System.out.println();
    }

    private static Integer[] get_random_attack_order(int M, int N){
        //init attack arr with the indices
        Integer [] attacks = new Integer[N*M];
        for (int i=0; i<attacks.length; i++)
            attacks[i] = i;

        //t he attack order is random
        int random;
        int temp;
        for (int i=0; i<attacks.length; i++){
            random = (int )(Math.random() * attacks.length);
            //swap
            temp = attacks[i];
            attacks[i] = attacks[random];
            attacks[random] = temp;
        }

        return attacks;
    }

    private static cordinates get_attack_cordinates(Scanner scanner, int R, int C){
        System.out.println("please select cordinates");

        System.out.println("    Enter row (0 to " + (R-1)+")");
        int r = scanner.nextInt();  // Read user input
        while (r<0 || r>=R){
            System.out.println("please enter valid row");
            r = scanner.nextInt();  // Read user input
        }

        System.out.println("    Enter column (0 to " + (C-1)+")");
        int c = scanner.nextInt();  // Read user input
        while (c<0 || c>=C){
            System.out.println("please enter valid column");
            c = scanner.nextInt();  // Read user input
        }

        cordinates cordinate = new cordinates(r, c);
        System.out.println("player attacked " + cordinate.repr());
        return cordinate;
    }

    private static void get_ocean_size(Scanner scanner){
        System.out.println("please select ocean size");

        System.out.println("    Enter #rows (4 or more)");
        int r = scanner.nextInt();  // Read user input
        while (r<4){
            System.out.println("please enter valid #rows");
            r = scanner.nextInt();  // Read user input
        }

        System.out.println("    Enter #columns (4 or more)");
        int c = scanner.nextInt();  // Read user input
        while (c<4){
            System.out.println("please enter valid #columns");
            c = scanner.nextInt();  // Read user input
        }

        Main.R = r;
        Main.C = c;
    }



    private static void computer_turn(SubmarineFleet player_fleet, Integer [] computer_attacks, int round)throws InterruptedException{
        System.out.println(colors.ANSI_RED + "==computer_turn==" + colors.ANSI_RESET);
        TimeUnit.SECONDS.sleep(DELAY);

        int attack_location = computer_attacks[round];
        List<Integer> attack_history = Arrays.asList(Arrays.copyOfRange(computer_attacks, 0, round + 1));
        System.out.println("computer attacked " + cordinates.repr(attack_location));
        TimeUnit.SECONDS.sleep(DELAY);

        player_fleet.were_attacked(attack_location);
        print_board(player_fleet, false, attack_history);
    }


    private static void player_turn(Scanner scanner, SubmarineFleet computer_fleet, List<Integer> player_attack_history)throws InterruptedException{
        System.out.println(colors.ANSI_BLUE + "==player_turn==" + colors.ANSI_RESET);
        TimeUnit.SECONDS.sleep(DELAY);

        print_board(computer_fleet, true, player_attack_history);
        int attack_location = get_attack_cordinates(scanner, R, C).get_index();
        player_attack_history.add(attack_location);

        computer_fleet.were_attacked(attack_location);
        print_board(computer_fleet, false, player_attack_history);
    }

    public static void main(String[] args)throws InterruptedException {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        get_ocean_size(scanner);

        Integer [] computer_attacks = get_random_attack_order(R, C);  // the attacks the computer will do
        List<Integer> player_attack_history = new ArrayList<>();

        SubmarineFleet player_fleet = new SubmarineFleet();
        SubmarineFleet computer_fleet = new SubmarineFleet();

        // game-loop
        int round =0;
        while(true){
            System.out.println(colors.ANSI_YELLOW + "\n====== Round " + (round+1) +" ======" + colors.ANSI_RESET);
            TimeUnit.SECONDS.sleep(DELAY);
            computer_turn(player_fleet, computer_attacks, round);
            if (player_fleet.functioning_submarine_amount == 0){
                System.out.println(colors.ANSI_YELLOW + "\n\nComputer won!!!"+colors.ANSI_RESET);
                System.exit(0);
            }
            player_turn(scanner, computer_fleet, player_attack_history);
            if (computer_fleet.functioning_submarine_amount == 0){
                System.out.println(colors.ANSI_YELLOW + "\n\nplayer won!!!"+colors.ANSI_RESET);
                System.exit(0);
            }
            round+=1;
        }

        }
}
