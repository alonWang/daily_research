class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(candidates);
        combo(res,candidates,target,new ArrayList<Integer>(),0);
        return res;
    }
    //采用回溯思想
    private void combo(List<List<Integer>> res,int[]candidates,int target,List<Integer> tempList,int index){
        if(target<0){
            return;
        }else if(target==0){
            //注意这里要将tempList的内容通过构造函数复制过来,否则方法结束,tempList也会被释放,导致最后的结果只是几个空list.
            res.add(new ArrayList<>(tempList));
        }else{
            for(int i=index;i<candidates.length;i++){
                tempList.add(candidates[i]);
                combo(res,candidates,target-candidates[i],tempList,i+1);
                tempList.remove(tempList.size()-1);
                //去重
                while(i<candidates.length-1&&candidates[i]==candidates[i+1])i++;
            }
        }
    }
}