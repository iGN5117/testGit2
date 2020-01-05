/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nucleiassign2;

import java.util.Comparator;

/**
 *
 * @author Nilesh Gajwani
 */
public class rollCompare implements Comparator<User>{

    @Override
    public int compare(User u1, User u2) {
        return u1.rollNumber - u2.rollNumber;
    }
    
}
