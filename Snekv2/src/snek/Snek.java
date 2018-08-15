/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snek;

/**
 *
 * @author crazyiot
 */
public class Snek {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CSVwriter writer= new CSVwriter(); //writer para los puntos
        writer.CSVIntro();
        Board b=new Board();
    }
    
}
