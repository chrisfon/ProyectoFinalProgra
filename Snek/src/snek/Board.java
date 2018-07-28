/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author crazyiot
 */
//falta:
//pantalla de muerte, choque contra pared, choque contra uno mismo, puntos

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
    
    private int playerSize = 3; //Tama;o del jugador, inicia en 3. Esto se va a usar para cuando coma.
    private int[] playerX = new int[pixel_total]; //un array del tama;o de todo el area. (porque es hasta adonde puede crecer el jugador) es como una lista vacia que se llena cada vez que crece.
    private int[] playerY = new int[pixel_total]; //otro array del mismo tama;o (para coordenada y)
    

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
         
    }

    public class Game extends JPanel implements KeyListener, ActionListener {

        Game() {
           
            setBackground(Color.black);

            
            //de adonde inicia
            for (int i = 0; i < playerSize; i++) {
                playerX[i] = 100;
                playerY[i] = 0 - (i * pixel);

            }

        }

        public void keyPressed(KeyEvent e) {
            lastkeyPressed = keyPressed;
            keyPressed = e.getKeyCode();//key code lo que hace es que usa una funcion diferente que depende que letra usa.(de una lista https://docs.oracle.com/javafx/2/api/javafx/scene/input/KeyCode.html)
        }  

        public void paintComponent(Graphics graphic) {

            super.paintComponent(graphic);

            if (inGame) {

                for (int i = 0; i < playerSize; i++) { // para todo el tama;o del jugador

                    if (i == 0) { //si no es posicion 0 (cabeza)
                        graphic.setColor(Color.blue); // <--la cabeza
                    } else { //si es cualquier cosa q no sea posicion 0,
                        graphic.setColor(Color.green); //<--cuerpo
                    }

                    graphic.fillRect(playerX[i], playerY[i], pixel, pixel);
                    //pintar coordenadas del personaje, ancho del objeto a pintar(pixel), largo del objeto a pintar(pixel)
                }

            } else {
                System.out.print("Aqui va algo cuando ingame se pone false"); //pantalla para indicar que perdio el jugador
            }
        }

        private void movement() {

            for (int i = playerSize; i > 0; i--) {  //empieza desde atras (se mueve de [playersize] a la nueva pos [0]) 
                playerX[i] = playerX[(i - 1)];      //el pedazo de cuerpo que estaba en la ultima posicion sube una posicion
                playerY[i] = playerY[(i - 1)];      //se repite para todas las posiciones una vez por movimiento
            }
            
            switch (keyPressed) {                   //controles
                case KeyEvent.VK_DOWN:
                    playerY[0] += pixel;    
                    break;
                case KeyEvent.VK_UP:
                    playerY[0] -= pixel;
                    break;
                case KeyEvent.VK_LEFT:
                    playerX[0] -= pixel;
                    break;
                case KeyEvent.VK_RIGHT:
                    playerX[0] += pixel;
                    break;
                
            }
            
        }

        public void actionPerformed(ActionEvent e) {  //constantemente va a estar updating (despues de cada moviemiento)

            //funcion para checkear si choco va aqui
            
            movement();//viendo si el usuario ha cambiado la direccion
            repaint(); //refresh adonde esta todo (repinta todo y usa las coordenadas nuevas)
            
        }

        public void keyTyped(KeyEvent e) { //obligatorio poner aunque no haga nada el metodo (por lo de event). //se usaria si usaramos key typed en vez de keycode
        }

        public void keyReleased(KeyEvent e) {//obligatorio poner aunque no haga nada el metodo. (se usa keycode no keytyped ni keyreleased.)
        }

    }
}
