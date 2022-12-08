package level;

import Gierka.Okno;
import Gierka.stan_gry;
import objects.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class LevelHandler {

    private Okno o;

    public double Gravity = 4;

    public LinkedList<object> obiekty = new LinkedList<object>();

    private int seed;

    public double kameraX = 0, kameraY= 0;
    public double kamera_predkosc = 1;
    public int ostatni_sektorX = 0;
    public int sektor_width = 0;




    public int startX= 100, startY = 200;

    public gracz Gracz = null;

    public LevelHandler(Okno o){
        this.o = o;
        this.sektor_width = Okno.width*2;

    }

    public void Generuj_poziom(){
        obiekty = new LinkedList<object>();
        Random r = new Random();
        seed = r.nextInt();

        //gracz
        Gracz = new gracz(o, startX,startY,42,42);
        GenerujSektor(0, sektor_width);

        // obiekty


    }
    public void GenerujSektor(int SektorX, int Sektor_width){
        ostatni_sektorX = SektorX + sektor_width;

        // platformy
        for(int x = SektorX; x < SektorX + Sektor_width; x += 1){
            int maxW = 800, minW = 300;

            Random r = new Random();

            int W = r.nextInt(maxW - minW) + minW;
            int y = 400-r.nextInt(100);
            obiekty.add(new platform(objectIDs.platform, x, y, W, 2, Color.black ));



            //spike
            int szansa_spike = 3;
            if(r.nextInt(szansa_spike) == 0){
                int spikeW = 32;
                int max_spike = W/spikeW;
                int ilosc_spike = r.nextInt(max_spike);

                if(ilosc_spike != 0){
                    for(int spikeX = x+spikeW/2; spikeX < x+W-spikeW/2; spikeX += W/ilosc_spike){
                        if(r.nextBoolean())obiekty.add(new Spike(objectIDs.Spike, spikeX, y-32, 32, 32, Color.black));
                    }
                }

            }

            //punkt wywala cala gre
            //int szansa_punkt = 1;
            //if(r.nextInt(szansa_punkt) == 0){
             //   int punktW = 50;
            //    int max_punkt = W/punktW;
             //   int ilosc_punkt = r.nextInt(max_punkt);

//                if(ilosc_punkt != 0){
  //                  for(int punktX = x+punktW/2; punktX < x+W-punktW/2; punktX += W/max_punkt){
    //                    if(r.nextBoolean())obiekty.add(new punkt(o, objectIDs.punkt, punktX, y+2, 16, 16, 2));
      //              }
        //        }
//
  //          }


            int maxP = 200, minP = 20;
            int przerwa = W + r.nextInt(maxP - minP) + minP;
            x+=przerwa;





        }

    }

    public void Render(Graphics g){
        g.translate(-(int)kameraX, -(int)kameraY);

        if(Gracz !=null)Gracz.Render(g);

        for(object ob : obiekty){
            ob.Render(g);
        }

        g.translate((int)kameraX,(int)kameraY);



        if(o.sg == stan_gry.KoniecGry) {
            g.setColor(Color.white);
            FontMetrics font = g.getFontMetrics(g.getFont());
            g.drawString("Koniec gry", Okno.width/2-font.stringWidth("Koniec Gry"), Okno.height/2);
            g.drawString("Kliknij Esc, by wrócić do Menu", Okno.width/2-font.stringWidth("Kliknij Esc, by wrócić do Menu")+50, Okno.height/2+50);

        }
    }
    public void tick(){
        kamera_predkosc+=0.0005;
        kameraX+=kamera_predkosc;
        if(kameraX+sektor_width > ostatni_sektorX){
            GenerujSektor(ostatni_sektorX, sektor_width);
        }
        for(object ob : obiekty){
            ob.tick();
        }
        if(Gracz != null)Gracz.tick();
    }
    public void przegrana(){
        o.sg = stan_gry.KoniecGry;
    }

    // restart
    public void Restart(){
        Generuj_poziom();
        Gracz.x = startX;
        Gracz.y = startY;
        kameraX = 0;
        kameraY = 0;
        kamera_predkosc=1;

    }
}
