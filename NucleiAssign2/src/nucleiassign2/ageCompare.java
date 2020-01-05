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
public class ageCompare implements Comparator<User>{

    @Override
    public int compare(User u1, User u2) {
        if(u1.age == u2.age)
        {
            return new rollCompare().compare(u1, u2);
        }
        return u1.age - u2.age;
    }
    
}
