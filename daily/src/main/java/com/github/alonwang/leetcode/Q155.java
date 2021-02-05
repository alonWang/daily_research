package com.github.alonwang.leetcode;

import java.util.Stack;

/**
 * 最小栈
 * @author alonwang
 * @date 2021/2/4 8:15 下午
 */
public class Q155 {
    class MinStack {
        private Stack<Long> minStack = new Stack<>();
        private long min;


        /** initialize your data structure here. */
        public MinStack() {

        }

        public void push(int x) {
            if (minStack.isEmpty()) {
                min = x;
            }
            minStack.push(min - x);
            min = Math.min(min, x);
        }

        public void pop() {
            long minX = minStack.pop();
            min = minX > 0 ? minX + min : min;
        }

        public int top() {
            long minX = minStack.peek();
            return (int)(minX >= 0 ? min : min - minX);

        }

        public int getMin() {
            return (int)min;
        }
    }
}
