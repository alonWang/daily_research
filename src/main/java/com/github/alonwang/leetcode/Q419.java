package com.github.alonwang.leetcode;

/*
横/竖(但不同时)相连为同一艘战舰.
如果一个点为X,只有它的左侧和上方都不为X才有效
*/
class Q419 {
    public int countBattleships(char[][] board) {
        if(board.length==0||board[0].length==0){
            return 0;
        }

        int count=0;
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[i].length; j++){
                if(board[i][j]=='X'){
                    if(i>0&&board[i-1][j]=='X')
                        continue;
                    if(j>0&&board[i][j-1]=='X')
                        continue;
                    count++;
                }
            }
        }
        return count;
    }
}