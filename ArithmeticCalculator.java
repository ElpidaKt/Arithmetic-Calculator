/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;
import java.util.Scanner;
/**
 *
 * @author kouel
 */
public class ArithmeticCalculator {
    String expression;
    BinaryTree tree;
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        System.out.print("Expression: ");
        String line = sc.nextLine();
        
        ArithmeticCalculator object1;
        double res;
        int i;
       
        object1 = new ArithmeticCalculator(line);
        
        //java.util.Scanner scan = new java.util.Scanner(System.in);
        //if(sc.hasNextLine()){
        String choices = sc.nextLine();
        choices = choices.replaceAll(" ","");

        for(i=0; i<choices.length(); i++){
            if(choices.charAt(i) == '-'){
                if(choices.charAt(i+1) == 'd'){
                    System.out.println(object1.toDotString());
                }
                else if (choices.charAt(i+1) == 's'){
                    System.out.println("Postfix: " +object1.toString());
                }
                else if(choices.charAt(i+1) == 'c'){
                    System.out.println("Result: " +object1.calculate());
                }
            }
        }
        //}
    }
    
    
    public ArithmeticCalculator(String expr){
        int i, j, closed_par=0, opened_par=0;
        int extent, new_pos;
        String exp_substring, op_par, cl_par, sub_string;
        int backslash = 0;
        
        op_par = "(";
        cl_par = ")";
        expression = expr;
        expression = expression.replaceAll(" ","");  // afairesh kenwn
        
        // Elegxoi Orthotitas
        
        for(i=0; i<expression.length(); i++){
            if(expression.charAt(i) == '\\'){
                if(expression.charAt(i+1) != '+' && expression.charAt(i+1) != '-' && expression.charAt(i+1) != '*'&& expression.charAt(i+1) != 'x' && expression.charAt(i+1) != '/' && expression.charAt(i+1) != '^'){
                    System.out.println("[ERROR] Invalid expansion expression\n");
                    System.exit(0);
                }
                backslash = 1;
            }
        } 
        
        operator_check(expression);
        operator_parenthesis(expression);
        parenthesis_check(expression);
        symbols_check(expression);
        
        // Backslash
        if (backslash == 1){
            for(i = 0; i < expression.length(); i++){
                if(expression.charAt(i) == '\\'){
                    extent = extention(expression);
                    if(extent <= 1){
                        System.out.println("[ERROR] Invalid expansion expression\n");
                        System.exit(0);
                    }

                    new_pos = position_of_rest(expression);
                    
                    switch(expression.charAt(i+1)){
                        case '^' : 
                            closed_par = 0;
                            opened_par = 0;
                            for (j = i-1; j >= 0 ; j--) {
                                if(closed_par == opened_par){
                                    if(expression.charAt(j) == '(' || expression.charAt(j) == '+' || expression.charAt(j) == '-' || expression.charAt(j) == '*'|| expression.charAt(j) == 'x' || expression.charAt(j) == '/' || expression.charAt(j) == '^'){
                                        exp_substring = expression.charAt(i+1) + expression.substring(j+1, i); 
                                        sub_string = my_repeat(exp_substring, extent);
                                        if(new_pos != 0){
                                            expression = expression.substring(0, j+1)+ op_par + expression.substring(j+1, i)+ sub_string +cl_par + expression.substring(new_pos, expression.length());
                                        }
                                        else{
                                            expression = expression.substring(0, j+1)+ op_par + expression.substring(j+1, i)+ sub_string +cl_par;
                                        }   
                                        break;

                                    }
                                }

                                if(expression.charAt(j) == ')'){
                                    closed_par = closed_par + 1;
                                }
                                if(expression.charAt(j) == '('){
                                    opened_par = opened_par + 1;
                                }

                            }
                            break;
                        case '*' :
                            closed_par = 0;
                            opened_par = 0;
                            for (j = i-1; j >= 0 ; j--) {
                                if(closed_par == opened_par){
                                    if(expression.charAt(j) == '(' || expression.charAt(j) == '+' || expression.charAt(j) == '-' || expression.charAt(j) == '*'|| expression.charAt(j) == 'x' || expression.charAt(j) == '/' || expression.charAt(j) == '^'){
                                        exp_substring = expression.charAt(i+1) + expression.substring(j+1, i); 
                                        sub_string = my_repeat(exp_substring, extent);
                                        if(new_pos != 0){
                                            expression = expression.substring(0, j+1)+ op_par + expression.substring(j+1, i)+ sub_string +cl_par + expression.substring(new_pos, expression.length());
                                        }
                                        else{
                                            expression = expression.substring(0, j+1)+ op_par + expression.substring(j+1, i)+ sub_string +cl_par;
                                        }   
                                        break;
                                    }
                                }
                                if(expression.charAt(j) == ')'){
                                    closed_par = closed_par + 1;
                                }
                                if(expression.charAt(j) == '('){
                                    opened_par = opened_par + 1;
                                }
                            }
                            break;
                        case 'x' : 
                            closed_par = 0;
                            opened_par = 0;
                            for (j = i-1; j >= 0 ; j--) {

                                if(closed_par == opened_par){
                                    if(expression.charAt(j) == '(' || expression.charAt(j) == '+' || expression.charAt(j) == '-' || expression.charAt(j) == '*'|| expression.charAt(j) == 'x' || expression.charAt(j) == '/' || expression.charAt(j) == '^'){
                                        exp_substring = expression.charAt(i+1) + expression.substring(j+1, i); 
                                        sub_string = my_repeat(exp_substring, extent);
                                        if(new_pos != 0){
                                            expression = expression.substring(0, j+1)+ op_par + expression.substring(j+1, i)+ sub_string +cl_par + expression.substring(new_pos, expression.length());
                                        }
                                        else{
                                            expression = expression.substring(0, j+1)+ op_par + expression.substring(j+1, i)+ sub_string +cl_par;
                                        }   
                                        break;
                                    }
                                }

                                if(expression.charAt(j) == ')'){
                                    closed_par = closed_par + 1;
                                }
                                if(expression.charAt(j) == '('){
                                    opened_par = opened_par + 1;
                                }
                            }
                            break;
                        case '/' : 
                            closed_par = 0;
                            opened_par = 0;
                            for (j = i-1; j >= 0 ; j--) {

                                if(closed_par == opened_par){
                                    if(expression.charAt(j) == '(' || expression.charAt(j) == '+' || expression.charAt(j) == '-' || expression.charAt(j) == '*'|| expression.charAt(j) == 'x' || expression.charAt(j) == '/' || expression.charAt(j) == '^'){
                                        exp_substring = expression.charAt(i+1) + expression.substring(j+1, i); 
                                        sub_string = my_repeat(exp_substring, extent);
                                        if(new_pos != 0){
                                            expression = expression.substring(0, j+1)+ op_par + expression.substring(j+1, i)+ sub_string +cl_par + expression.substring(new_pos, expression.length());
                                        }
                                        else{
                                            expression = expression.substring(0, j+1)+ op_par + expression.substring(j+1, i)+ sub_string +cl_par;
                                        }   
                                        break;
                                    }
                                }
                                if(expression.charAt(j) == ')'){
                                    closed_par = closed_par + 1;
                                }
                                if(expression.charAt(j) == '('){
                                    opened_par = opened_par + 1;
                                }

                            }
                            break;
                        case '+' : 
                            closed_par = 0;
                            opened_par = 0;
                            for (j = i-1; j >= 0 ; j--) {

                                if(closed_par == opened_par){
                                    if(expression.charAt(j) == '(' || expression.charAt(j) == '+' || expression.charAt(j) == '-' || expression.charAt(j) == '*'|| expression.charAt(j) == 'x' || expression.charAt(j) == '/' || expression.charAt(j) == '^'){
                                        exp_substring = expression.charAt(i+1) + expression.substring(j+1, i); 
                                        sub_string = my_repeat(exp_substring, extent);
                                        if(new_pos != 0){
                                            expression = expression.substring(0, j+1)+ op_par + expression.substring(j+1, i)+ sub_string +cl_par + expression.substring(new_pos, expression.length());
                                        }
                                        else{
                                            expression = expression.substring(0, j+1)+ op_par + expression.substring(j+1, i)+ sub_string +cl_par;
                                        }   
                                        break;
                                    }
                                }
                                if(expression.charAt(j) == ')'){
                                    closed_par = closed_par + 1;
                                }
                                if(expression.charAt(j) == '('){
                                    opened_par = opened_par + 1;
                                }

                            }
                            break;
                        case '-' : 
                            closed_par = 0;
                            opened_par = 0;
                            for (j = i-1; j >= 0 ; j--) {

                                if(closed_par == opened_par){
                                    if(expression.charAt(j) == '(' || expression.charAt(j) == '+' || expression.charAt(j) == '-' || expression.charAt(j) == '*'|| expression.charAt(j) == 'x' || expression.charAt(j) == '/' || expression.charAt(j) == '^'){
                                        exp_substring = expression.charAt(i+1) + expression.substring(j+1, i); 
                                        sub_string = my_repeat(exp_substring, extent);
                                        if(new_pos != 0){
                                            expression = expression.substring(0, j+1)+ op_par + expression.substring(j+1, i)+ sub_string +cl_par + expression.substring(new_pos, expression.length());
                                        }
                                        else{
                                            expression = expression.substring(0, j+1)+ op_par + expression.substring(j+1, i)+ sub_string +cl_par;
                                        }   
                                        break;
                                    }
                                }
                                if(expression.charAt(j) == ')'){
                                    closed_par = closed_par + 1;
                                }
                                if(expression.charAt(j) == '('){
                                    opened_par = opened_par + 1;
                                }
                            }
                            break;
                    }  
                }
            }
        }
        
        operator_check(expression);
        operator_parenthesis(expression);
        parenthesis_check(expression);
        symbols_check(expression);
        
        
        tree = new BinaryTree();
        tree.root = tree.Add_recur(tree.root, expression);
    }
    
    double calculate(){
        double fin_result;
        
        fin_result = internal_calculate(tree.root);
    
        return fin_result;
    }
    
    private double internal_calculate(Node current){
        double left_res;
        double right_res;
        double final_result=0;
        
        if(current.left == null && current.right == null){
            return(Double.parseDouble(current.data)); 
        }
        left_res = internal_calculate(current.left);
        right_res = internal_calculate(current.right);
     
        switch (current.data){
            case "^" : final_result = Math.pow(left_res, right_res); 
                break;
            case "*" : final_result = left_res * right_res; 
                break;
            case "x" : final_result = left_res * right_res; 
                break;
            case "/" : final_result = left_res / right_res;
                break;
            case "+" : final_result = left_res + right_res; 
                break;
            case "-" : final_result = left_res - right_res;
                break;
            }
        
        return final_result;
    }
    
    public String toDotString(){
        StringBuilder final_str = new StringBuilder();
        final_str.append("graph ArithmeticExpressionTree {\n");
        String middle_str = internal_toDotString(tree.root);
        final_str.append(middle_str);
        final_str.append("}");
        //final_str.append("\n");
        //System.out.println(final_str);
        
        return final_str.toString();
    }
    
    private String internal_toDotString(Node current){
        StringBuilder middle_str = new StringBuilder();
        String str1 = " [label=\"";
        String str2 = "\"]";
        String str3 =  " -- ";
        String left, right;
        int cur_hash, left_hash, right_hash;
        
        cur_hash = current.hashCode();
        if(current.left == null && current.right == null){
            middle_str.append(cur_hash);
            middle_str.append(str1);
            middle_str.append(current.data);
            middle_str.append(str2);
            middle_str.append("\n");
            return(middle_str.toString()); 
        }
        left = internal_toDotString(current.left);
        right = internal_toDotString(current.right);
    
        left_hash = current.left.hashCode();
        right_hash = current.right.hashCode();
        
        middle_str.append(cur_hash);
        middle_str.append(str1);
        middle_str.append(current.data);
        middle_str.append(str2);
        middle_str.append("\n");
        middle_str.append(cur_hash);
        middle_str.append(str3);
        middle_str.append(left_hash);
        middle_str.append("\n");
        middle_str.append(cur_hash);
        middle_str.append(str3);
        middle_str.append(right_hash);
        middle_str.append("\n");
        
        middle_str.append(left);
        middle_str.append(right);
        
        return (middle_str.toString());
        
    }
    
    @Override
    public String toString(){
        String post_order_string = internal_toString(tree.root);
 
        return(post_order_string.toString());
    
    }
    
    private String internal_toString(Node current){
        StringBuilder post_String = new StringBuilder();
        String left, right;
        
        if(current.left == null && current.right == null){
            post_String.append("(");
            post_String.append(current.data);
            post_String.append(")");
            return(post_String.toString()); 
        }
        left = internal_toString(current.left);
        right = internal_toString(current.right);
        
        if(current != tree.root){
            post_String.append("(");
        }
        post_String.append(left);
        
        post_String.append(right);
        
        post_String.append(current.data);
        if(current != tree.root){
            post_String.append(")");
        }
        
        return (post_String.toString());
    }
    
    private int extention(String expr){
        int i, k;
        String new_extender;
        StringBuilder extender = new StringBuilder();
        
        for(i=0; i<expr.length(); i++){
            if(expr.charAt(i) == '\\'){
                extender.append(expr.charAt(i+2));
                if(expr.length() > i+2 +1){
                    for(k=i+3; k<expr.length(); k++){
                        if(expr.charAt(k) != ')' && expr.charAt(k) != '(' && expr.charAt(k) != '+' && expr.charAt(k) != '-' && expr.charAt(k) != '*' && expr.charAt(k) != '/' && expr.charAt(k) != 'x' && expr.charAt(k) != '^'){
                            extender.append(expr.charAt(k));
                        }
                        else{
                            break;
                        }
                    }
                }
                break;
            }
        }
        new_extender = extender.toString();
        for(i=0; i<new_extender.length(); i++){
            if (!Character.isDigit(new_extender.charAt(i))){
                 System.out.println("[ERROR] Invalid expansion expression\n");
                 System.exit(0);
            }
        }
        
        return(Integer.parseInt(new_extender));
    }
    
    private int position_of_rest(String expr){
        int i, k, position=0;
    
         for(i=0; i<expr.length(); i++){
            if(expr.charAt(i) == '\\'){
                if(expr.length() > i+2  +1){
                    for(k=i+3; k<expr.length(); k++){
                        if(expr.charAt(k) == ')' || expr.charAt(k) == '(' || expr.charAt(k) == '+' || expr.charAt(k) == '-' || expr.charAt(k) == '*' || expr.charAt(k) == '/' || expr.charAt(k) == 'x' || expr.charAt(k) == '^'){
                            position = k;
                            break;
                        }
                    }
                }
                else{
                    position = 0;
                }
                break;
            }
         }
         return(position);
    }
    
    private void parenthesis_check(String expr){
        int i, opened=0, closed=0;
        
        for (i=0; i<expr.length(); i++){
            if(expr.charAt(i) == '('){
                opened = opened +1 ;
            }
            
            if(expr.charAt(i) == ')'){
                closed = closed +1 ;
            }
        }
        
        if(closed < opened){
            System.out.println("[ERROR] Not closing opened parenthesis\n");
            System.exit(0);
        }
        
        if(closed > opened){
            System.out.println("[ERROR] Closing unopened parenthesis\n");
            System.exit(0);
        }
    }
    
    private void symbols_check(String expr){
        int i;
        
        for(i=0; i<expr.length(); i++){
            if(expr.charAt(i) != ')' && expr.charAt(i) != '(' && expr.charAt(i) != '+' && expr.charAt(i) != '-' && expr.charAt(i) != '*' && expr.charAt(i) != '/' && expr.charAt(i) != 'x' && expr.charAt(i) != '^' && (!Character.isDigit(expr.charAt(i))) && expr.charAt(i) != '\\' && expr.charAt(i) != '.'){
                System.out.println("[ERROR] Invalid character\n");
                System.exit(0);
            }
        }
    }
    
    private void operator_check(String expr){
        int i;
        
        for(i=0; i<expr.length(); i++){
            if(expr.charAt(i) == '+' || expr.charAt(i) == '-' || expr.charAt(i) == '*' || expr.charAt(i) == '/' || expr.charAt(i) == 'x' || expr.charAt(i) == '^' ){
                if(expr.charAt(i+1) == '+' || expr.charAt(i+1) == '-' || expr.charAt(i+1) == '*' || expr.charAt(i+1) == '/' || expr.charAt(i+1) == 'x' || expr.charAt(i+1) == '^' ){
                    System.out.println("[ERROR] Two consecutive operands\n");
                    System.exit(0);
                }
            }
        }
    }
    
    private void operator_parenthesis(String expr){
        int i;
        
        for(i=0; i<expr.length(); i++){
            if(expr.charAt(i) == '('){
                if(expr.charAt(i+1) == '+' || expr.charAt(i+1) == '-' || expr.charAt(i+1) == '*' || expr.charAt(i+1) == '/' || expr.charAt(i+1) == 'x' || expr.charAt(i+1) == '^' ){
                    System.out.println("[ERROR] Operand appears after opening parenthesis\n");
                    System.exit(0);
                }
            }
            
            if(expr.charAt(i) == '+' || expr.charAt(i) == '-' || expr.charAt(i) == '*' || expr.charAt(i) == '/' || expr.charAt(i) == 'x' || expr.charAt(i) == '^' ){
                if(expr.charAt(i+1) == ')'){
                    System.out.println("[ERROR] Operand appears before closing parenthesis\n");
                    System.exit(0);
                }
            }
        }
    }
    
    private String my_repeat(String expr, int num){
        int i;
        StringBuilder sub_string = new StringBuilder();
        
        for(i=0; i<num-1; i++){
            sub_string.append(expr);
        }
        
        return(sub_string.toString());
    }
}
