/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nucleiassign3;

/**
 *
 * @author Nilesh Gajwani
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class NucleiAssign3 {

    /**
     * @param args the command line arguments
     */
    
       // TODO code application logic here

        //Menu Driven Program was created for easy user interface and handling of all operations at one place	
    public static void main(String[] args) {

        ArrayList<Node> root = new ArrayList<>();
        HashMap<Integer, Node> hm = new HashMap<>();
        String res = "8";
        int pid, cid, response;

        DependencyGraph graph = new DependencyGraph(root, hm);
        Scanner sc = new Scanner(System.in);

        while (!res.equals("9")) {
            System.out.println("Press 1 to add dependency");
            System.out.println("Press 2 to get immediate children");
            System.out.println("Press 3 to get immediate parents");
            System.out.println("Press 4 to find all ancestors");
            System.out.println("Press 5 to find all Descendants");
            System.out.println("Press 6 to add a new node");
            System.out.println("Press 7 to delete a node");
            System.out.println("Press 8 to delete a dependency");
            System.out.println("Press 9 to exit");
            res = sc.nextLine();
            switch (res) {
                case "1":
                    System.out.println("Enter Parent id");
                    try {
                        pid = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Entry While Entering Parent ID");
                        break;
                    }
                    System.out.println("Enter Child ID");
                    try {
                        cid = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Entry While Entering Parent ID");
                        break;
                    }
                    if (graph.add(pid, cid, sc) == 1) {
                        System.out.println("Inserted Node Successfully");
                    } else {
                        System.out.println("Error While Inserting");
                    }
                    break;
                case "2":
                    System.out.println("Enter Node id");
                    try {
                        response = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Entry While Entering for Immediate Children");
                        break;
                    }
                    graph.get_immediate_children(response);
                    break;
                case "3":
                    System.out.println("Enter Node id");
                    try {
                        response = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Entry While Entering for Immediate Parents");
                        break;
                    }
                    graph.get_immediate_parents(response);
                    break;
                case "4":
                    System.out.println("Enter Node id");
                    try {
                        response = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Entry While Entering for Ancestors");
                        break;
                    }
                    graph.get_ancestors(response);
                    break;
                case "5":
                    System.out.println("Enter Node id");
                    try {
                        response = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Entry While Entering for Descendants");
                        break;
                    }
                    graph.getDescendants(response);
                    break;
                case "6":
                    System.out.println("Enter Node id");
                    try {
                        response = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Entry While Entering for Adding Single Node");
                        break;
                    }
                    System.out.println("Enter Node name");
                    String name = sc.nextLine();
                    graph.addNode(name, response);
                    break;
                case "7":
                    System.out.println("Enter Node id");
                    try {
                        response = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Entry While Entering for Adding Single Node");
                        break;
                    }
                    if (hm.containsKey(response)) {
                        Node n = hm.get(response);
                        if (n.getParentSet().size() == 0) {
                            graph.deleteNode(n.getNodeId());
                        } else if (n.getChildrenSet().size() == 0) {
                            n.getParentSet().forEach((bn) -> bn.getChildrenSet().remove(n));
                            n.getParentSet().clear();
                            n.getChildrenSet().clear();
                            hm.remove(n.getNodeId());
                        } else {
                            n.getParentSet().forEach((bn) -> bn.getChildrenSet().remove(n));
                            graph.deleteNode(n.getNodeId());
                        }
                        System.out.println("Deleted");
                    } else {
                        System.out.println("Node Not Present in Graph");
                    }
                case "8":
                    System.out.println("Enter Parent id");
                    try {
                        pid = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Entry!Try Again");
                        break;
                    }
                    System.out.println("Enter Child id");
                    try {
                        cid = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Entry!Try Again");
                        break;
                    }
                    graph.delete_dependency(pid, cid);
                case "9":
                    break;
                default:
                    System.out.println("Invalid Entry!Try Again.");
            }

        }
        System.out.println("Deallocating Resources");
        hm.clear();
        sc.close();
        System.out.println("Terminating");
    }



}
