package com.nighthawk.spring_portfolio.mvc.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import java.lang.Math;

public class Calculator {
    // Key instance variables
    private final String expression;
    private ArrayList<String> tokens;
    private ArrayList<String> reverse_polish;
    private Double result;


    // Create a 1 argument constructor providing a mathematical expression
    public Calculator(String expression) {
        // original input
        this.expression = expression;

        // check imbalance in number of parentheses
        this.parenCheck();

        // parse expression into terms
        this.termTokenizer();

        // place terms into reverse polish notation
        this.tokensToReversePolishNotation();

        // calculate reverse polish notation
        this.rpnToResult();
    }

    private void parenCheck() {
        long count = this.expression.chars().filter(ch -> ch == '(').count();
        long count2 = this.expression.codePoints().filter(ch -> ch == ')').count();
        if (count != count2) {
            throw new RuntimeException("Unequal number of Parentheses");
        }
    }

    // Print the expression, terms, and result
    public String toString() {
        return ("Original expression: " + this.expression + "\n" +
                "Tokenized expression: " + this.tokens.toString() + "\n" +
                "Reverse Polish Notation: " +this.reverse_polish.toString() + "\n" +
                "Final result: " + String.format("%.2f", this.result));
    }

    // Helper definition to define operators, lookup in MAP are fast and easy O(1) versus ArrayList O(n)
    private final Map<String, Integer> OPERATORS = new HashMap<>();
    {
        // Map<"token", precedence>
        OPERATORS.put("*", 3);
        OPERATORS.put("/", 3);
        OPERATORS.put("%", 3);
        OPERATORS.put("+", 4);
        OPERATORS.put("-", 4);
        OPERATORS.put("^", 2);
    }
    
    // Helper definition for supported separators
    private final Map<String, Integer> SEPARATORS = new HashMap<>();
    {
        // Map<"separator", not_used>
        SEPARATORS.put(" ", 0);
        SEPARATORS.put("(", 0);
        SEPARATORS.put(")", 0);
        SEPARATORS.put(",", 0);
        SEPARATORS.put(";", 0);
        SEPARATORS.put("=", 0);
    }

    // Helper definition for supported single variable functions
    private final Map<String, Integer> FUNCTIONSSINGLE = new HashMap<>();
    {
        // Map<"function", not_used>
        FUNCTIONSSINGLE.put("SQRT", 0);
        FUNCTIONSSINGLE.put("SIN", 0);
        FUNCTIONSSINGLE.put("COS", 0);
    }

    
    // Helper definition for supported double variable functions
    private final Map<String, Integer> FUNCTIONSDOUBLE = new HashMap<>();
    {
        // Map<"function", not_used>
        FUNCTIONSDOUBLE.put("MAX", 0);
        FUNCTIONSDOUBLE.put("MIN", 0);
    }

    private final Map<String, Double> VARIABLES = new HashMap<>();
    {
        // Map<"variable", value>
        VARIABLES.put("G", 9.8);
        VARIABLES.put("PI", 3.14159265359);
    }

    // Test if token is an operator
    private boolean isOperator(String token) {
        // find the token in the hash map
        return OPERATORS.containsKey(token);
    }

    // Test if token is an separator
    private boolean isSeparator(String token) {
        // find the token in the hash map
        return SEPARATORS.containsKey(token);
    }

    // Test if token is a single variable function
    private boolean isFunctionSingle(String token) {
        // find the token in the hash map
        return FUNCTIONSSINGLE.containsKey(token);
    }
    
    // Test if token is a double variable function
    private boolean isFunctionDouble(String token) {
        // find the token in the hash map
        return FUNCTIONSDOUBLE.containsKey(token);
    }

    // Test if token is a double variable function
    private boolean isVariable(String token) {
        // find the token in the hash map
        return VARIABLES.containsKey(token);
    }


    // Compare precedence of operators.
    private Boolean isPrecedent(String token1, String token2) {
        // token 1 is precedent if it is greater than token 2
        return (OPERATORS.get(token1) - OPERATORS.get(token2) >= 0) ;
    }

    // Term Tokenizer takes original expression and converts it to ArrayList of tokens
    private void termTokenizer() {
        // contains final list of tokens
        this.tokens = new ArrayList<>();

        int start = 0;  // term split starting index
        StringBuilder multiCharTerm = new StringBuilder();    // term holder
        for (int i = 0; i < this.expression.length(); i++) {
            Character c = this.expression.charAt(i);
            if ( isOperator(c.toString() ) || isSeparator(c.toString())  ) {
                // 1st check for working term and add if it exists
                if (multiCharTerm.length() > 0) {
                    tokens.add(this.expression.substring(start, i).toUpperCase());
                }
                // Add operator or parenthesis term to list
                if (c != ' ' && c != ',' && c != '=') {
                    tokens.add(c.toString());
                }
                // Clear previous entries in tokens if semicolon and assign variable with corresponding value in VARIABLES hashmap
                if (c == ';') {
                    VARIABLES.put(this.tokens.get(0), Double.valueOf(this.tokens.get(1)));
                    this.tokens = new ArrayList<>();
                }
                // Get ready for next term
                start = i + 1;
                multiCharTerm = new StringBuilder();
            } else {
                // multi character terms: numbers, functions, perhaps non-supported elements
                // Add next character to working term
                multiCharTerm.append(c);
            }

        }
        // Add last term
        if (multiCharTerm.length() > 0) {
            tokens.add(this.expression.substring(start));
        }
    }

    // Takes tokens and converts to Reverse Polish Notation (RPN).
    private void tokensToReversePolishNotation () {
        // contains final list of tokens in RPN
        this.reverse_polish = new ArrayList<>();

        // stack is used to reorder for appropriate grouping and precedence
        Stack<String> tokenStack = new Stack<String>();
        for (String token : tokens) {
            switch (token) {
                // If left bracket push token on to stack
                case "MAX":
                case "MIN":
                case "SQRT":
                case "SIN":
                case "COS":
                case "(":
                    tokenStack.push(token);
                    break;
                case ")":
                    while (tokenStack.peek() != null && !tokenStack.peek().equals("("))
                    {
                        reverse_polish.add( (String)tokenStack.pop() );
                    }
                    tokenStack.pop();
                    if (isFunctionSingle(tokenStack.peek()) || isFunctionDouble(tokenStack.peek())) {
                        reverse_polish.add( (String)tokenStack.pop() );
                    }
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                case "^":
                    // While stack
                    // not empty AND stack top element
                    // and is an operator
                    while (tokenStack.size() > 0 && isOperator((String) tokenStack.peek()))
                    {
                        if ( isPrecedent(token, (String) tokenStack.peek() )) {
                            reverse_polish.add((String)tokenStack.pop());
                            continue;
                        }
                        break;
                    }
                    // Push the new operator on the stack
                    tokenStack.push(token);
                    break;
                default:    // Default should be a number, there could be test here
                    this.reverse_polish.add(token);
            }
        }
        // Empty remaining tokens
        while (tokenStack.size() > 0) {
            reverse_polish.add((String)tokenStack.pop());
        }

    }

    public double calc(String operator, double a, double b) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            case "%":
                return a % b;
            case "^":
                return Math.pow(a, b);
            case "MAX":
                return Math.max(a, b);
            case "MIN":
                return Math.min(a, b);
            case "SIN":
                return Math.sin(Math.toDegrees(b));
            case "COS":
                return Math.cos(Math.toDegrees(b));
            case "SQRT":
                return Math.sqrt(b);
            default:
                throw new RuntimeException("Unsupported operator: " + operator);
        }
    }

    // Takes RPN and produces a final result
    private void rpnToResult()
    {
        // stack is used to hold operands and each calculation
        Stack<Double> calcStack = new Stack<Double>();

        // RPN is processed, ultimately calcStack has final result
        for (String token : this.reverse_polish)
        {
            // If the token is an operator, calculate
            if (isOperator(token) || isFunctionSingle(token) || isFunctionDouble(token))
            {
                // Pop the two top entries
                double b = Double.valueOf(calcStack.pop());
                double a;
                if (isFunctionSingle(token)) {
                    a = 0;
                } else {
                    a = Double.valueOf(calcStack.pop());
                }

                // Calculate intermediate results
                result = 0.0;
                result = calc(token, a, b);

                // Push intermediate result back onto the stack
                calcStack.push( result );
            }
            // else the token is a number push it onto the stack
            else
            {
                if (isVariable(token)) {
                    calcStack.push(VARIABLES.get(token));
                } else {
                    calcStack.push(Double.valueOf(token));
                }
            }
        }
        // Pop final result and set as final result for expression
        this.result = calcStack.pop();
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator("10 /2 + ( 4 -1 )");
        System.out.println(calc.toString());
        Calculator calc1 = new Calculator("8+ 4 * 2 /(1 -4) ^ 2^3");
        System.out.println(calc1.toString());
        Calculator calc2 = new Calculator("sin ( max ( 2, 5 ) / min (3,6) * PI )");
        System.out.println(calc2.toString());
        Calculator calc3 = new Calculator("a = 3; b = 4; SQRT(a^2 + b^2)");
        System.out.println(calc3.toString());
        Calculator calc4 = new Calculator("m = 4; m*G");
        System.out.println(calc4.toString());
        Calculator calc5 = new Calculator("((5*2)))");
        System.out.println(calc5.toString());

    }
}