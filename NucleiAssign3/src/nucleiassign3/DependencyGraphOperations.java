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
public interface DependencyGraphOperations {
    public Set<Node> getParents(int nodeId);
    public Set<Node> getChildren(int nodeId);
    public Set<Node> getAncestors(int nodeId);
    public Set<Node> getDescendants(int nodeId);
    public void deleteDependency(int parentId, int childId);
    public Node deleteNode(int nodeId);
    public void addDependency(int parentId, int childId);
    public void addNode(Node node);
    
}
