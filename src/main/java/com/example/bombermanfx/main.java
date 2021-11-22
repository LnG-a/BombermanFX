package com.example.bombermanfx;

import java.util.Queue;
import java.util.Stack;

public class main {
    public static void main(String[] args) {
        int n = 50;
        Stack<Integer> stack = new Stack();
        while (n > 0) {
            stack.push(n % 2);
            n = n / 2;
        }
        for (Object digit : stack) {
            System.out.print(digit);
        }
        System.out.println();

        int m=stack.size();
        for (int i =0;i<m;i++){
            System.out.print(stack.pop());
        }

    }
}
