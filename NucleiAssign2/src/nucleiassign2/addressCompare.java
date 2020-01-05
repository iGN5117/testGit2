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
public class addressCompare implements Comparator<User> {

    @Override
    public int compare(User u1, User u2) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return u1.address.compareTo(u2.address);
    }
    
}
