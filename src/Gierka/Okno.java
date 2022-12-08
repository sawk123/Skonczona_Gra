package Gierka;


import gui.tworzenie_menu;
import input.klawiatura;
import input.muzyka;
import level.LevelHandler;
import objects.gracz;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Okno extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;
    private Thread thread;
    private boolean running = false;
    public klawiatura Klistener = new klawiatura(this);

    public stan_gry sg = stan_gry.Menu;
    public tworzenie_menu menu = new tworzenie_menu(this);

    public LevelHandler level;

    Image img = Toolkit.getDefaultToolkit().createImage("res/tlo.jpg");


    public static final int width = 800, height= 600;

    public Okno(String Title){
        JFrame ramka = new JFrame(Title);
        ramka.setVisible(true);
        ramka.setResizable(true);
        ramka.add(this);
        ramka.setSize(width,height);
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        level = new LevelHandler(this);

        muzyka.grana_muzyka("res/music2.mp3"); // muzyka nie chce działać
    }

    public void start(){
        running = true;
        thread = new Thread(this);
        thread.start();

    }

    public void stop() {
        running = false;
        try {
            thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while (running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1)
            {
                tick();
                updates++;
                delta--;
            }
            Render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }

        stop();
    }

    public void tick(){

        if(sg == stan_gry.Gra || sg == stan_gry.KoniecGry)level.tick();
        if(sg == stan_gry.Menu)menu.tick();

    }
    public void Render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, null); // dodanie tła tez nie dziala poprawnie
        g.fillRect(0,0,this.getWidth(), this.getHeight());


        if(sg == stan_gry.Gra || sg == stan_gry.KoniecGry)level.Render(g);
        if(sg == stan_gry.Menu)menu.Render(g);



        bs.show();
        g.dispose();

    }

    public void menu_gra(){
        level.Generuj_poziom();
        sg = stan_gry.Gra;

    }


}
