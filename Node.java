/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

/**
 *
 * @author kouel
 */
public class Node {
    
    public Node left, right;
    public String data;
    
    public Node(String expr){
        left = null;
        right = null;
        this.data = expr;
    }
    
    String getString(){
        return (data);
    }
}
