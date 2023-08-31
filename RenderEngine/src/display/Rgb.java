package display;
public class Rgb {

    public int r;
    public int g;
    public int b;
    public int a;

    public Rgb(int r, int g, int b, int a) {

        // force the values of r,g,b to be between 0->255
        this.r = Math.max(Math.min(255, r), 0);
        this.b = Math.max(Math.min(255, b), 0);
        this.g = Math.max(Math.min(255, g), 0);
        this.a = Math.max(Math.min(255, a), 0);

    }
}