package objects;

import java.awt.*;

public abstract class object {

    public static byte platform = Byte.MIN_VALUE;

    protected byte ID;
    protected double x, y;
    int width, height;

    public object(byte ID, double x, double y, int width, int height){

        this.ID=ID;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    public abstract void Render(Graphics g);
    public abstract void tick();

}
