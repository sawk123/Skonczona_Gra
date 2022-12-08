package objects;

import java.awt.*;

public class platform extends object{

    public Color c;


    public platform(byte ID, int x, int y, int width, int height,Color c) {
        super(ID, x, y, width, height);
        this.c = c;
    }

    public void Render(Graphics g) {
        g.setColor(c);
        g.fillRect((int)x, (int)y, width, height);

    }


    public void tick() {

    }
}
