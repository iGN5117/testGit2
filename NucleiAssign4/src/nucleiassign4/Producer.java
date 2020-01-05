/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nucleiassign4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nilesh Gajwani
 */
public class Producer implements Runnable {

    SqlImport s;

    Producer(SqlImport s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            System.out.println("Called producer");
            SqlImport sqlStuff = new SqlImport();
            sqlStuff.select();
        } catch (InterruptedException ex) {
            System.out.println("Error in producer");
        }
    }

}
