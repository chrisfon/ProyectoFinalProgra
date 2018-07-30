/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snek;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.util.Scanner;
/**
 *
 * @author crazyiot
 */
//falta:
// choque contra uno mismo, puntos, highscores, e imagenes.

//tiene:
//board, jugador, movimiento, listeners(accion y input),y graficas
public class Board extends JFrame { //se puede implementar esto por los componentes que estoy usando (imports)

    //Cosas/config del board y tama;os
    private int boardW = 20 * 40; //ancho
    private int boardH = 20 * 40; //largo
    private int pixel = 20;  //Tama;o por celda o "pixel"
    private int pixel_total = 800; // cuantos pixeles (el area de juego)
   
    private int speed = 100; //velocidad. es el tiempo entre movimientos. entre mas bajo mas rapido el juego
    
    
    private boolean inGame = true; //marca que el jugador esta en juego, asi se sabe cuando parar de refresh y poner en false cuando se pierde
    
    //Coordenadas de las cosas y config de jugador
    //como funciona es que hay un array que es el gusano+hacia adonde va y la direccion si cambia, cambiando el array que se usa de la posicion
    
   
    

    int keyPressed; //la direccion hacia adonde va a iniciar
    int lastkeyPressed;

    public Board() {
        
        this.setSize(boardW, boardH);
        Game game = new Game(); //Inicia el juego
        addKeyListener(game); //agrega funcion para leer que estripa el jugador
        add(game);
        setVisible(true); //hace el cuadro adonde esta el jugador y el tablero visible
        Timer t = new Timer(speed, game); //timer que hace que el tiempo avanze
       
        t.start();
        
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                t.stop();
                System.exit(0);
            }
        });
         
    }

   
}
