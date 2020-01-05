/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nucleiassign3;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author Nilesh Gajwani
 */
public class Node {
    private static int id = 0;
    private int nodeId;
    private String name;
    private Set<Node> parents,children;
    private Map<String,Object> extraInfo;
    Node(String name)
    {
        this.name = name;
        this.nodeId = id;
        id++;
    }
    
    public int getNodeId()
    {
        return this.nodeId;
    }
    Node(String name, Map<String,Object> extraInfo)
    {
        this.name = name;
        this.extraInfo = extraInfo;
        this.nodeId = id;
        id++;
    }
    public void putExtraInfo(Map<String,Object> extraInfo)
    {
        this.extraInfo = extraInfo;
    }
    public Set<Node> getParentSet()
    {
        return this.parents;
    }
    public Set<Node> getChildrenSet()
    {
        return this.children;
    }
    public String getName()
    {
        return this.name;
    }
    
}
