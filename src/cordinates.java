public class cordinates {
    private int r;
    private int c;

    public cordinates(int r, int c){
        this.r = r;
        this.c = c;
    }

    public int get_index(){
        return to_index(this.r, this.c);
    }

    public String repr(){
        return "[" + this.r + ", " + this.c + "]";
    }


    public static String repr(int i){
        return "[" + (i/Main.C) + ", " + (i%Main.C) + "]";
    }

    public static int to_index(int r, int c){return r*Main.C + c;}




}
