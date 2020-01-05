/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nucleiassign3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import static nucleiassign3.Utility.*;
import java.util.HashSet;

/**
 *
 * @author Nilesh Gajwani
 */
public class DependencyGraph implements DependencyGraphOperations {

    private HashMap<Integer, Node> nodes;
    private ArrayList<Node> root;

    public ArrayList<Node> getRoots() {
        
        return this.root;
    }

    public DependencyGraph(ArrayList<Node> root, HashMap<Integer, Node> nodes) {
        this.root = root;
        this.nodes = nodes;
    }
    

    @Override
    public Set<Node> getParents(int nodeId) {
        return nodes.get(nodeId).getParentSet();
    }

    @Override
    public Set<Node> getChildren(int nodeId) {
        return nodes.get(nodeId).getChildrenSet();
    }

    @Override
    public Set<Node> getAncestors(int nodeId) {
        Set<Node> ancestors = new HashSet<Node>();
        Set<Node> parents = nodes.get(nodeId).getParentSet();
        return ancestorsUtil(ancestors, parents);
    }

    @Override
    public Set<Node> getDescendants(int nodeId) {
        Set<Node> descendants = new HashSet<>();
        Set<Node> children = nodes.get(nodeId).getChildrenSet();
        return descendantsUtil(descendants, children);
    }

    @Override
    public void deleteDependency(int parentId, int childId) {
        Node parent = nodes.get(parentId);
        Node child = nodes.get(childId);
        parent.getChildrenSet().remove(child);
        child.getParentSet().remove(parent);
        System.out.println("Deleted dependency between " + parent.getName() + " and " + child.getName());
    }

    @Override
    public Node deleteNode(int nodeId) {
        Set<Node> parents = nodes.get(nodeId).getParentSet();
        for (Node n : parents) {
            deleteDependency(n.getNodeId(), nodeId);
        }
        Node deletedNode = nodes.get(nodeId);
        nodes.remove(nodeId);
        return deletedNode;
    }

    @Override
    public void addDependency(int parentId, int childId) {
        Node parent = nodes.get(parentId);
        Node child = nodes.get(childId);
        if (getAncestors(parentId).contains(child)) {

        } else if (getDescendants(childId).contains(parentId)) {

        } else {
            parent.getChildrenSet().add(child);
            child.getParentSet().add(parent);
            System.out.println("Added dependency between " + parent.getName() + " and " + child.getName());
        }

    }

    @Override
    public void addNode(Node node) {
        nodes.put(node.getNodeId(), node);
    }

}
