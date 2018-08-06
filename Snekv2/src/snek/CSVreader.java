/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snek;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
/**
 *
 * @author ulacit
 */
public class CSVreader {
    
   String csvFile = "/Users/ulacit/Desktop/Snekv2/Scores.scv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
public void Reader(){
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] name = line.split(cvsSplitBy);

                System.out.println("Country [code= " + name[4] + " , name=" + name[5] + "]");

            }

        
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}
        
    }

    




