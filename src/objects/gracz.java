package objects;

import Gierka.Okno;

import java.awt.*;

public class gracz {

    public Okno o;
    public int width,height;

    public double x, y;
    public double velx, vely;
    public double Skok_szybkosc = 4;
    public double szybkosc = 4;

    public boolean spadanie = true;
    public boolean skakanie = false;
    public boolean opadanie = false;
    public boolean doublejump = false;
    public int czas_skoku = 0;
    public boolean poruszanie_lewo = false;
    public boolean poruszanie_prawo = false;

    Image img = Toolkit.getDefaultToolkit().createImage("kosz.png");

    public gracz(Okno o, double x, double y, int width, int height){
        this.o=o;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    public void tick(){
        x+=velx;
        y+=vely;

        if(vely < o.level.Gravity && spadanie){
            if(!opadanie)vely+=0.1;
            if(opadanie)vely+=0.5;
        }else if(!spadanie && vely > 0){
            vely = 0;
        }

        if(o.Klistener.Wdol ){

            if(skakanie){
                vely = -Skok_szybkosc;
            }else if(!doublejump && czas_skoku > 20){
                vely = -Skok_szybkosc;
                doublejump = true;
            }

        }
        //
        if(spadanie){
            czas_skoku += 1;
        }

        if(y > Okno.height){
            o.level.przegrana();
        }

        kolizje();
    }

    public void kolizje(){
        spadanie = true;
        skakanie = false;
        poruszanie_lewo = true;
        poruszanie_prawo = true;
        for(object ob : o.level.obiekty){
            if( ob.ID == objectIDs.platform){
                platform p = (platform)ob;
                if(new Rectangle((int)(x+velx),(int)y+(int)vely, width, height).intersects(p.x,p.y,p.width,p.height)) {
                    //vely
                    if (y + height <= p.y + 1) {
                        spadanie = false;
                        opadanie = false;
                        doublejump = false;
                        czas_skoku = 0;
                        if (vely > 0) {
                            vely = 0;
                            y = p.y - height;
                        }
                    } else if (y < p.y){
                        velx = 0;
                }
                    if(vely < 0 && y > p.y){
                        spadanie = true;
                        y-=(vely+1);
                        vely = -1*vely;
                    }
                    if(new Rectangle((int)(x+velx),(int)y+(int)vely, width, height).intersects(p.x,p.y,p.width,p.height)){
                        if(p.x+p.width <= x){
                            poruszanie_lewo = false;
                            velx = 0;
                        }
                        if(p.x >= x+width){
                            poruszanie_prawo = false;
                            velx = 0;
                        }
                    }
                }
                float detekcja_kolizji = 20;
                if(!spadanie || (Math.abs(y+height-p.y) < 20 && new Rectangle((int)(x+velx*detekcja_kolizji),(int)(y+vely*detekcja_kolizji), width, height).intersects(p.x,p.y,p.width,p.height))){
                    skakanie = true;

                }


            }
            //spike
            if(ob.ID == objectIDs.Spike){
                Spike s = (Spike)ob;
                if(new Rectangle((int)s.x, (int)s.y, s.width, s.height).intersects(new Rectangle((int)x, (int)y, width, height))){
                    o.level.przegrana();
                }
            }
            //punkt
            if(ob.ID == objectIDs.punkt){
               punkt p = (punkt)ob;

              if(new Rectangle((int)p.x, (int)p.y, p.width, p.height).intersects(new Rectangle((int)x, (int)y, width, height))){
                  o.level.obiekty.remove(ob);
              }
           }

        }

    }

    public double predkosc(){
        return szybkosc*o.level.kamera_predkosc;

    }

    public void Render(Graphics g){
        //g.drawImage(img, 0, 0, null); opcja nie dziala
        g.setColor(Color.green);
        g.fillRect((int)x, (int)y, width, height);
    }
}
