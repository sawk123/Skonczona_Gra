package input;

import Gierka.Okno;
import Gierka.stan_gry;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class klawiatura implements KeyListener {

    private Okno o;

    public boolean Wdol = false;

    public klawiatura(Okno o) {
        this.o = o;
        o.addKeyListener(this);

    }

    public void keyTyped(KeyEvent e) {

    }

    boolean kierunek_lewy = false;

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (o.sg == stan_gry.Gra) {
            if(key == KeyEvent.VK_ESCAPE){
                o.sg = stan_gry.Menu;
                o.level.Restart();
            }

            if (key == KeyEvent.VK_D && o.level.Gracz.poruszanie_prawo) {
                o.level.Gracz.velx = o.level.Gracz.szybkosc;
                kierunek_lewy = false;
            }
            if (key == KeyEvent.VK_A && o.level.Gracz.poruszanie_lewo) {
                o.level.Gracz.velx = -o.level.Gracz.szybkosc;
                kierunek_lewy = true;
            }
            if (key == KeyEvent.VK_W) {
                Wdol = true;
            }
            if (key == KeyEvent.VK_S) {
                if (o.level.Gracz.spadanie) {
                    if (o.level.Gracz.vely < 0) {
                        o.level.Gracz.vely = 0;
                    } else {
                        o.level.Gracz.opadanie = true;

                    }
                }
            }
        }else if(o.sg == stan_gry.Menu){
            o.menu.KeyPress(key);

        }else if(o.sg == stan_gry.KoniecGry){
            if(key == KeyEvent.VK_ESCAPE){
                o.sg = stan_gry.Menu;
                o.level.Restart();
            }
        }


    }


    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (o.sg == stan_gry.Gra) {
            if (key == KeyEvent.VK_D && !kierunek_lewy) {
                o.level.Gracz.velx = 0;
            }
            if (key == KeyEvent.VK_A && kierunek_lewy) {
                o.level.Gracz.velx = 0;
            }
            if (key == KeyEvent.VK_W) {
                Wdol = false;
            }


        }else if(o.sg == stan_gry.Menu){

        }
    }
}
