/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nucleiassign4;

import java.util.ArrayList;

/**
 *
 * @author Nilesh Gajwani
 */
public class NucleiAssign4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        SqlImport.connect();
        SqlImport connect = new SqlImport();
        // Producer p = new Producer(new Runnable());
        //Consumer c = new Consumer(connect);
        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
            System.out.println("Called producer");
            connect.select();
        } catch (InterruptedException ex) {
            System.out.println("Error in producer");
        }
            }
        }
        );
        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Called consumer");
                    connect.updateTax();
                } catch (InterruptedException ex) {
                    System.out.println("Error in consumer");
                }
            }
        });
        producerThread.start();
        consumerThread.start();
        producerThread.join();
        consumerThread.join();

    }

}
