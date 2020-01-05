/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nucleiassign2;

import java.util.ArrayList;

/**
 *
 * @author Nilesh Gajwani
 */
public class User implements Comparable<User>, java.io.Serializable {

    String name, address;
    int age, rollNumber;
    ArrayList<Course> courses;

    User() {
    }

    public int compareTo(User u) {
        if (this.name.equals(u.name)) {
            rollCompare r = new rollCompare();
            return r.compare(this, u);
        }

        return this.name.compareTo(u.name);

    }

    User(String name, String address, int age, int rollNumber, ArrayList<Course> courses) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.rollNumber = rollNumber;
        this.courses = courses;
    }

    public String toString() {
        String output;
        output = this.name + "     " + this.address
                + "     " + this.age + "     " + this.rollNumber
                + "     " ;
        for (Course c : courses) {
            output += c.toString() + " ";
        }
        //output += "\n\n";
        return output;
    }

}
