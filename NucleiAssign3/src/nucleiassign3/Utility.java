/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nucleiassign3;

import java.util.Set;

/**
 *
 * @author Nilesh Gajwani
 */
public class Utility {
    public static Set<Node> ancestorsUtil(Set<Node> ancestors,Set<Node> parents)
    {
        for(Node n:parents)
        {
            ancestors.add(n);
            ancestorsUtil(ancestors,n.getParentSet());
        }
        return ancestors;
    }
    public static Set<Node> descendantsUtil(Set<Node> descendants, Set<Node> children)
    {
        for(Node n:children)
        {
            descendants.add(n);
            descendantsUtil(descendants, n.getChildrenSet());
        }
        
        return descendants;
    }
    
}
