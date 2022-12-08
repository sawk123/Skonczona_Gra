package objects;

import Gierka.Okno;

import java.awt.*;

public class punkt extends object {

    private object stoi = null;
    private Okno o;

    private boolean lewo = false;
    private double predkosc = 1;


    public punkt(Okno o, byte ID, int x, int y, int width, int height, double predkosc) {
        super(ID, x, y, width, height);
        this.o = o;
        this.predkosc = predkosc;
    }

    public void Render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y, width, height);

    }


    public void tick() {
        if (stoi == null) {
            y+=o.level.Gravity;
            for(object ob : o.level.obiekty){
                if(ob.ID == objectIDs.platform){
                    if(new Rectangle((int)x, (int)y, width, height).intersects(new Rectangle((int)ob.x, (int)ob.y, ob.width, ob.height))){
                        stoi = ob;
                    }
                }
            }
        }else{
            y = stoi.y - height;
            if(x+width >= stoi.x+stoi.width || x <= stoi.x)lewo = !lewo;
            if(lewo){
                x-=predkosc;
            }else{
                x+=predkosc;
            }


        }
    }
}

