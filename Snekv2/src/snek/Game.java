/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snek;

import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;

/**
 *
 * @author ulacit
 */
public class Game extends JPanel implements KeyListener, ActionListener {

    Scanner scan = new Scanner(System.in);

    private String playerName = JOptionPane.showInputDialog("Ingrese su nombre");
    private int boardW = 20 * 40; //ancho
    private int boardH = 20 * 40; //largo
    private int pixel_total = 800; // cuantos pixeles (el area de juego)
    private int pixel = 20;  //Tama;o por celda o "pixel"

    private int playerSize = 6; //Tama;o del jugador, inicia en 3. Esto se va a usar para cuando coma.
    private int[] playerX = new int[pixel_total]; //un array del tama;o de todo el area. (porque es hasta adonde puede crecer el jugador) es como una lista vacia que se llena cada vez que crece.
    private int[] playerY = new int[pixel_total]; //otro array del mismo tama;o (para coordenada y)

    private boolean inGame = true; //marca que el jugador esta en juego, asi se sabe cuando parar de refresh y poner en false cuando se pierde

    int keyPressed; //la direccion hacia adonde va a iniciar
    int lastkeyPressed;

    private boolean isSaving=true;
   
    Game() {
        isSaving=true;
        setBackground(Color.black);

        //de adonde inicia
        for (int i = 0; i < playerSize; i++) {
            playerX[i] = 100;
            playerY[i] = 10 - (i * pixel);

        }

    }

    public void keyPressed(KeyEvent e) {
        if (keyPressed == KeyEvent.VK_A) {

            Board t = new Board();

         
            //AQUI VA LA LINEA QUE CIERRA EL ANTIGUO JUEGO CON LA X
        }

        lastkeyPressed = keyPressed;

        keyPressed = e.getKeyCode();//key code lo que hace es que usa una funcion diferente que depende que letra usa.(de una lista https://docs.oracle.com/javafx/2/api/javafx/scene/input/KeyCode.html)
    }

    public void paintComponent(Graphics graphic) {

        super.paintComponent(graphic);

        if (inGame == true) {

            for (int i = 0; i < playerSize; i++) { // para todo el tama;o del jugador

                if (i == 0) { //si no es posicion 0 (cabeza)
                    graphic.setColor(Color.blue); // <--la cabeza
                } else { //si es cualquier cosa q no sea posicion 0,
                    graphic.setColor(Color.green); //<--cuerpo
                }
                graphic.drawString("Puntos:" + playerSize, 700, 700);
                graphic.fillRect(playerX[i], playerY[i], pixel, pixel);
                //pintar coordenadas del personaje, ancho del objeto a pintar(pixel), largo del objeto a pintar(pixel)

                //PINTAR MANZANA
            }

        } else if (inGame == false) {

            //PANTALLA DE MUERTE 
            graphic.setColor(Color.white);
            graphic.setFont(new Font("Sans serif", Font.ITALIC, 40));
            graphic.drawString("Perdio", 200, 200);
            graphic.drawString("Puntos:" + playerSize, 200, 400);
            graphic.drawString("Presione 'A' para reiniciar ", 200, 600);
            graphic.drawString("Nombre:" + playerName, 200, 300);

            //PARTE DE SALVAR LOS SCORES
            FileWriter fileWriter = null; //para definir fileWriter e iniciarlo
         if (inGame == false && isSaving == true){   
            try {
                fileWriter = new FileWriter("Scores.csv",true); //crea nuevo file y borra lo anterior, el true es para que le agrege y no haga overwrite a lo viejo
                fileWriter.append("Score,Name");
                fileWriter.append("\n");
                fileWriter.append(this.playerName);
                fileWriter.append(",");
                fileWriter.append(String.valueOf(this.playerSize));
                fileWriter.append("\n");
                fileWriter.flush();
                fileWriter.close();
                isSaving = false;
            } catch (Exception e) {
                System.out.print("ERROR GUARDANDO PUNTOS");
            }
         }
        }
    }

    private void movement() {

        for (int i = playerSize; i > 0; i--) {  //empieza desde atras (se mueve de [playersize] a la nueva pos [0]) 
            playerX[i] = playerX[(i - 1)];      //el pedazo de cuerpo que estaba en la ultima posicion sube una posicion
            playerY[i] = playerY[(i - 1)];      //se repite para todas las posiciones una vez por movimiento
        }

        switch (keyPressed) {                   //controles
            case KeyEvent.VK_DOWN:
                if (lastkeyPressed != KeyEvent.VK_UP) {
                    playerY[0] += pixel;
                } else if (lastkeyPressed == KeyEvent.VK_UP) {

                    playerY[0] -= pixel;
                    lastkeyPressed = KeyEvent.VK_UP;

                }

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
        
        isValidMove();
        movement();//viendo si el usuario ha cambiado la direccion
        repaint(); //refresh adonde esta todo (repinta todo y usa las coordenadas nuevas)

    }

    public void keyTyped(KeyEvent e) { //obligatorio poner aunque no haga nada el metodo (por lo de event). //se usaria si usaramos key typed en vez de keycode

    }

    public void keyReleased(KeyEvent e) {//obligatorio poner aunque no haga nada el metodo. (se usa keycode no keytyped ni keyreleased.)
    }

    public void isValidMove() { //incompleta
        if (playerX[0] >= boardW - pixel || playerX[0] < 0) {
            inGame = false;

        } else if (playerY[0] >= boardH - pixel || playerY[0] < 0) {
            inGame = false;

        }
    }
}
