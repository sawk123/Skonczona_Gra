package gui;

import Gierka.Okno;

import java.awt.*;
import java.awt.event.KeyEvent;

public class tworzenie_menu {

    //zmienne
    private int WybieranieX = 0, WybieranieY = 0;
    private byte Wybieranie = 0;
    private int WybieranieZnacznik = 16;
    private int Wybieranie_rozmiar = 16;
    private Okno o;
    private KeyEvent EventKey;

    public tworzenie_menu(Okno o){
        this.o = o;

    }
    public void Render(Graphics g){
        g.setColor(Color.black);

        String Tytul = "Zbieranie Smieci, bez smieci", StartGry = "Nowa Gra", Opcje = "Opcje", Wyjscie = "Wyjscie";
        g.setFont(new Font("Times New Roman", Font.BOLD, 24));
        FontMetrics font = g.getFontMetrics(g.getFont());
        g.drawString(Tytul, Okno.width/2 - (font.stringWidth(Tytul)/2), 55);

        g.drawString(StartGry, Okno.width/2 - (font.stringWidth(StartGry)/2), 255);
        if(Wybieranie == 0)WybieranieX = Okno.width/2 - (font.stringWidth(StartGry)/2)-Wybieranie_rozmiar-WybieranieZnacznik;
        if(Wybieranie == 0)WybieranieY = 255-Wybieranie_rozmiar/2;

        g.drawString(Opcje, Okno.width/2 - (font.stringWidth(Opcje)/2), 355);
        if(Wybieranie == 1)WybieranieX = Okno.width/2 - (font.stringWidth(Opcje)/2)-Wybieranie_rozmiar-WybieranieZnacznik;
        if(Wybieranie == 1)WybieranieY = 355-Wybieranie_rozmiar/2;

        g.drawString(Wyjscie, Okno.width/2 - (font.stringWidth(Wyjscie)/2), 455);
        if(Wybieranie == 2)WybieranieX = Okno.width/2 - (font.stringWidth(Wyjscie)/2)-Wybieranie_rozmiar-WybieranieZnacznik;
        if(Wybieranie == 2)WybieranieY = 455-Wybieranie_rozmiar/2;

        g.setColor(Color.green);
        if(WybieranieX != 0)g.fillRect(WybieranieX, WybieranieY, Wybieranie_rozmiar, Wybieranie_rozmiar);

    }

    public void tick() {
    }

    public void KeyPress(int key) {
        
        if(key == EventKey.VK_S && Wybieranie < 3)Wybieranie+=1;
        if(key == EventKey.VK_W && Wybieranie > 0)Wybieranie-=1;
        if(key == EventKey.VK_ENTER && Wybieranie == 0){
            o.menu_gra();
        }
        if(key == EventKey.VK_ENTER && Wybieranie == 2){
            System.exit(0);
        }
    }
}
