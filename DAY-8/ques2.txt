class StockSpanner {
    Stack<Pair> st=new Stack<>();
    class Pair {
        int first,second;
        Pair(int i, int j) {
            this.first=i;
            this.second=j;
        }
    }
    int index=-1;
    public StockSpanner() {
        index=-1;
        st.clear();
    }
    public int next(int price) {
        index+=1;
        while(!st.empty() && st.peek().first<=price) st.pop();
        int ans=(st.empty()) ? -1:st.peek().second;
        st.push(new Pair(price,index));
        return index-ans;
    }
}
/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */