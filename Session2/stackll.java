import java.util.Stack;

public class stackll {
    static class Node{
        int val;
        Node next;
        Node(int val){
            this.val = val;
        }
    }
    static class linkedStack{
        Node root;
        int size=0;
        Node prev,tail;
        linkedStack(){
            root = null;
            prev = root;
            tail = root;
        }
        void push(int ele){
            if(root == null){
                root = new Node(ele);
                tail = root;
            }else{
                prev = tail;
                tail.next = new Node(ele);
                tail = tail.next;
            }
            size++;
        }
        int pop(){
            if(root == null){
                throw new UnsupportedOperationException("Stack is Empty...plz fill it first");
            }
            int val = tail.val;
            if(prev == null){
                root = null;
                tail = null;
                return val;
            }
            tail = prev;
            tail.next = null;
            size--;
            return val;
        }
        int peek(){
            if(root == null){
                throw new UnsupportedOperationException("Stack is Empty...plz fill it first");
            }
            return tail.val;
        }
        boolean isEmpty(){
            return size==0;
        }
        int size(){
            return size;
        }
    }
    public static void main(String[] args) {
        linkedStack st = new linkedStack();
        st.push(10);
        System.out.println(st.peek()+"\n"+st.isEmpty());
        System.out.println(st.size());
        st.push(14);
        st.push(18);
        System.out.println(st.peek());
        System.out.println(st.size);
        // System.out.println(st.pop());
        // System.out.println(st.pop());
    }
}
