class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n=nums.length;
        Stack<Integer> st=new Stack<>();
        int res[]=new int[nums.length];
        res[n-1]=n;
        st.push(n-1);
        for(int i=n-2;i>=0;i--)
        {
            while(st.size()!=0 && nums[i]>nums[st.peek()]) st.pop();
            if(st.size()==0) res[i]=n;
            else res[i]=st.peek();
            st.push(i);
        }
        int answer[]=new int[n-k+1];
        int z=0;
        for(int i=0;i<n-k+1;i++)
        {
            
            int j=i;
            if(j>=i+k) continue;
            int max=nums[j];
            while(j>=i && j<i+k){
                max=nums[j];
                j=res[j];
            }
            answer[z++]=max;
        }
        return answer;
    }
}