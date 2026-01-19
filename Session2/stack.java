public class stack {
    static class Stack{
        int size=0;
        int idx=-1;
        int arr[];
        int capacity;
        Stack(int n){
            arr = new int[n];
            capacity = n;
        }
        void push(int ele){
            if(idx == capacity){
                throw new UnsupportedOperationException("Stack is full");
            }
            arr[++idx] = ele;
            size++;
        }
        int pop(){
            if(idx == -1){
              throw new UnsupportedOperationException("Stack is Empty...plz fill it");
            }
            size--;
            return arr[idx--];
        }
        int peek(){
            if(idx == -1){
              throw new UnsupportedOperationException("Stack is Empty...plz fill it");
            }
            return arr[idx];
        }
        boolean isEmpty(){
            return size == 0;
        }
        int size(){
            return size;
        }
    }
    public static void main(String[] args) {
        Stack st = new Stack(6);
        st.push(10);
        System.out.println(st.peek()+"\n"+st.isEmpty());
        System.out.println(st.size());
        System.out.println(st.pop());
        System.out.println(st.pop());
    }
}
