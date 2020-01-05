/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nucleiassign4;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Nilesh Gajwani
 */
public class SqlImport {

    static Connection con;
    private static ArrayList<Item> items = new ArrayList<>();
    private static ArrayList<Item> itemsWithTax = new ArrayList<>();
    static boolean flag = true;

    public static void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/NucleiAssign4", "root", "Nilesh@123");
            Statement s = con.createStatement();

            int res = s.executeUpdate("CREATE TABLE IF NOT EXISTS ITEMS(NAME VARCHAR(20) PRIMARY KEY, TYPE ENUM('raw','imported','manufactured') NOT NULL, QUANTITY INT, PRICE FLOAT);");
            System.out.println("Created table");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Sucessfully connected to the database!");
    }

    public static void insert(Item item) {
        String query = "Insert into items values('" + item.item_name + "','" + item.item_type + "'," + item.item_quantity + "," + item.item_price + ");";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Inserted item successfully");
        } catch (SQLException e) {
            System.out.println("Error in SQL query for inserting");
        }

    }

    public void select() throws InterruptedException {
        System.out.println("select called");
        String query = "Select * from items";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int maxCapacity = 4;
            while (rs.next()) {
                synchronized (this) {
                    while (items.size() == maxCapacity) {
                        System.out.println("producer queue full...");
                        wait();
                    }
                    Item newItem = null;
                    String type = rs.getString("type");
                    switch (type) {
                        case "raw":
                            newItem = new raw_Item(rs.getString("name"), rs.getFloat("price"), rs.getInt("quantity"));
                            break;
                        case "imported":
                            newItem = new imported_Item(rs.getString("name"), rs.getFloat("price"), rs.getInt("quantity"));
                            break;
                        case "manufactured":
                            newItem = new manufactured_Item(rs.getString("name"), rs.getFloat("price"), rs.getInt("quantity"));
                            break;
                    }
                    items.add(newItem);
                    System.out.println("Added new item of type: " + type);
                    notify();
                    Thread.sleep(1000);
                }

            }
        } catch (SQLException e) {
            System.out.println("Error while producing into the queue");
            e.printStackTrace();
            flag = false;
        }
        flag = false;
    }

    public void updateTax() throws InterruptedException {
        System.out.println("Update called");
        while (flag || items.size() != 0) {
            synchronized (this) {
                System.out.println("In loop of consumer");
                while (items.size() == 0) {
                    System.out.println("Waiting for producer.....");

                    wait();
                    if (items.size() != 0) {
                        break;
                    }
                }
                System.out.println("starting consumer...");
                Item retrievedItem = items.get(0);
               // System.out.println(retrievedItem.getType());
                retrievedItem.getSalesTax();
                itemsWithTax.add(retrievedItem);
                System.out.println("Consumed one item of type: " + retrievedItem.getType());
                items.remove(0);
                notify();
                Thread.sleep(1000);

            }
        }
    }

}
