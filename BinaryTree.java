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
public class BinaryTree {
    public Node root;
    
    public BinaryTree(){
        root = null;
    }
     
    Node Add_recur(Node current, String expr){
        int oper_pos = 0;
        int i;
        char operator;
        int num_of_par;
        String left_substring = null;
        String right_substring;

        if(root == null){
            oper_pos = oper_position(expr);
            num_of_par = num_of_parenthesis(expr);
            if (oper_pos == 0 && num_of_par == 0){  // an einai mono 1 arithmos
                root = new Node(expr);
                return root;
            }
            if(oper_pos == 0 && num_of_par > 0){    // bgazei parentheseis
                expr = parenthesis_removal(expr);
                oper_pos = oper_position(expr);
                if(oper_pos == 0){                  // an einai mono 1 arithmos
                    root = new Node(expr);
                    return root;
                }
            }
            root = new Node(Character.toString(expr.charAt(oper_pos)));
            left_substring = expr.substring(0,oper_pos);
            right_substring = expr.substring(oper_pos +1, expr.length());

            root.left = Add_recur(root.left, left_substring);
            root.right = Add_recur(root.right, right_substring);
            return root;
        }
        
        if(current == null){
            
            oper_pos = oper_position(expr);
            num_of_par = num_of_parenthesis(expr);
            if (oper_pos == 0 && num_of_par == 0){  // an einai mono 1 arithmos
                current = new Node(expr);
                return current;
            }
            if(oper_pos == 0 && num_of_par > 0){    // bgazei parentheseis
                expr = parenthesis_removal(expr);
                oper_pos = oper_position(expr);
                if(oper_pos == 0){                  // an einai mono 1 arithmos
                    current = new Node(expr);
                    return current;
                }
            }
            current = new Node(Character.toString(expr.charAt(oper_pos)));
            left_substring = expr.substring(0,oper_pos);
            right_substring = expr.substring(oper_pos +1, expr.length());
            
            current.left = Add_recur(current.left, left_substring);
            current.right = Add_recur(current.right, right_substring);
            return current;
        }
        return root;
    }
    
    int oper_position(String expr){
        int i;
        int position = 0;
        char current;
        int par_closed = 0;
        int par_opened = 0;
        
        
        for(i=expr.length()-1; i >= 0; i--){
            current = expr.charAt(i);
            if(current == ')'){
                par_closed = par_closed+1;
            } 
            if(current == '('){
                par_opened = par_opened+1;
            }
            if ( (current == '+' || current == '-') && (par_closed == par_opened) ){
                return i;
            }
        }
        
        par_closed = 0;
        par_opened = 0;
         
        for(i=expr.length()-1; i >= 0; i--){
            current = expr.charAt(i);
            if(current == ')'){
                par_closed = par_closed+1;
            } 
            if(current == '('){
                par_opened = par_opened+1;
            }
            if (  (current == '*' || current == '/' || current == 'x')&& (par_closed == par_opened)  ){
                return i;
            }
        }
        
        par_closed = 0;
        par_opened = 0;
         
        for(i=expr.length()-1; i >= 0; i--){    /*to koitame apo deksia prow ta aristera*/
            current = expr.charAt(i); 
            if(current == ')'){
                par_closed = par_closed+1;
            } 
            if(current == '('){
                par_opened = par_opened+1;
            }
            if ((current == '^')&& (par_closed == par_opened)){
                return i;
            }
        }
        
        return position;
    }
    
    int num_of_parenthesis(String expr){
        int par_closed = 0;
        int par_opened = 0;
        int i;
        char current;
        
        for(i=expr.length()-1; i >= 0; i--){
            current = expr.charAt(i);
            if(current == ')'){
                par_closed = par_closed+1;
            } 
            if(current == '('){
                par_opened = par_opened+1;
            }
        
        }
        
        if(par_closed == par_opened && par_closed > 0){
            return par_closed;
        }
        else{
            return 0;
        }
    
    }
    
    String parenthesis_removal(String expr){
        int oper_pos = 0;
        int i;
        char operator;
        int num_of_par;
        
        oper_pos = oper_position(expr);
        num_of_par = num_of_parenthesis(expr);
            
        while(oper_pos == 0 && num_of_par > 0){    //bgazei parentheseis
        
            for(i=0; i<expr.length(); i++){
                if(expr.charAt(i) == '('){
                    expr = expr.substring(i+1,expr.length());
                    break;
                }
            } 
            for(i=expr.length()-1; i>=0; i--){
                if(expr.charAt(i) == ')'){
                    expr = expr.substring(0, i);
                    break;
                }
            }
            
            oper_pos = oper_position(expr);
            num_of_par = num_of_parenthesis(expr);
        }
        return expr;
    }
}
