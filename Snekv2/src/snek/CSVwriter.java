/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snek;

import java.io.FileWriter;
import java.util.Date;

/**
 *
 * @author crazyiot
 */
public class CSVwriter {
    FileWriter fileWriter = null;
    

Date date = new Date();

	
    public void CSVwriter(){
   

}
 public void CSVIntro(){
  
    
    

     try {
                fileWriter = new FileWriter("Scores.csv",true); //crea nuevo file y borra lo anterior, el true es para que le agrege y no haga overwrite a lo viejo
                fileWriter.append("Iniciado:"+date+System.lineSeparator()+"Score , Name");
                fileWriter.flush();
                fileWriter.close();
             }catch (Exception e){
                 System.out.print("Error haciendo archivo");
   
}
 }

public void CSVaddscore(String a, int b){
    
    try{   
                fileWriter = new FileWriter("Scores.csv",true);        
                fileWriter.append(System.lineSeparator());                //NO SE GUARDA SI ESTA ABIERTO EL FILE
                fileWriter.append(a);
                fileWriter.append(",");
                fileWriter.append(String.valueOf(b));
                fileWriter.append(System.lineSeparator());                //PASAR A OTRA CLASE
                fileWriter.flush();
                fileWriter.close();
                
            } catch (Exception e) {
                System.out.print("ERROR GUARDANDO PUNTOS");
            }   
}



}
