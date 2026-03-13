import java.util.*;

public class implementation {
    public static void main(String args[]) {
        int arr[] = { 1, 2, 3, 4, 4, 5 };
        SegmentTree sgt = new SegmentTree(arr.length);
        sgt.build(0, 0, arr.length - 1, arr);
        sgt.display();
        System.out.println();
        System.out.println(sgt.getQueryAns(0, arr.length - 1, 2, 4, 0));
        sgt.update(0, arr.length - 1, 0, 2, 4, 10);
        System.out.println(sgt.getQueryAns(0, arr.length - 1, 2, 4, 0));

        // ----------RangeFreq------------
        RangeFreqQuery rgq = new RangeFreqQuery(arr);
        System.out.println("freq of {4} : " + rgq.query(2, 4, 4));
    }
}

class SegmentTree {
    int arr[];

    public SegmentTree(int n) {
        arr = new int[4 * n];
    }

    void build(int idx, int l, int r, int nums[]) {
        if (l == r) {
            arr[idx] = nums[l];
            return;
        }
        int mid = (l + r) / 2;
        build(2 * idx + 1, l, mid, nums);
        build(2 * idx + 2, mid + 1, r, nums);
        arr[idx] = arr[2 * idx + 1] + arr[2 * idx + 2];
    }

    void display() {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    void update(int rootidx, int idx, int val, int l, int r) {
        if (idx == l && idx == r) {
            arr[rootidx] = val;
        }
        int mid = (l + r) / 2;
        if (l <= idx && idx <= mid) {
            update(2 * rootidx + 1, idx, val, l, mid);
        } else {
            update(2 * rootidx + 2, idx, val, mid + 1, r);
        }
        arr[rootidx] = arr[2 * rootidx + 1] + arr[2 * rootidx + 2];
    }

    int getQueryAns(int l, int r, int start, int end, int rootIdx) {
        // case 1 out of region
        if (l > end || r < start)
            return 0;
        // case 2 : found the range
        if (l >= start && r <= end)
            return arr[rootIdx];
        int mid = (l + r) / 2;
        return getQueryAns(l, mid, start, end, 2 * rootIdx + 1) + getQueryAns(mid + 1, r, start, end, 2 * rootIdx + 2);
    }

    void update(int l, int r, int rootidx, int st, int end, int val) {
        if (l > end || r < st)
            return;
        if (l >= st && r <= end) {
            arr[rootidx] += val;
            return;
        }
        int mid = (l + r) / 2;
        update(l, mid, 2 * rootidx + 1, st, end, val);
        update(mid + 1, r, 2 * rootidx + 2, st, end, val);
        arr[rootidx] = arr[2 * rootidx + 1] + arr[2 * rootidx + 2];
    }
}

class RangeFreqQuery {
    int n;
    segmentTree sgt;

    public RangeFreqQuery(int[] arr) {
        n = arr.length;
        sgt = new segmentTree(n);
        sgt.build(0, n - 1, 0, arr);
    }

    public int query(int left, int right, int value) {
        return sgt.find(left, right, value, 0, 0, n - 1);
    }

    class segmentTree {
        Node arr[];

        segmentTree(int n) {
            arr = new Node[n * 4];
            for (int i = 0; i < 4 * n; i++) {
                arr[i] = new Node();
            }
        }

        void build(int l, int r, int rootidx, int nums[]) {
            if (l == r) {
                arr[rootidx].hmap.put(nums[l], 1);
                return;
            }
            int mid = (l + r) / 2;
            build(l, mid, 2 * rootidx + 1, nums);
            build(mid + 1, r, 2 * rootidx + 2, nums);
            for (int i : arr[2 * rootidx + 1].hmap.keySet()) {
                arr[rootidx].hmap.put(i, arr[2 * rootidx + 1].hmap.get(i));
            }
            for (int i : arr[2 * rootidx + 2].hmap.keySet()) {
                int newVal = arr[2 * rootidx + 1].hmap.getOrDefault(i, 0);
                arr[rootidx].hmap.put(i, arr[2 * rootidx + 2].hmap.get(i) + newVal);
            }
        }

        void display() {
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i].hmap);
            }
        }

        int find(int st, int end, int val, int rootidx, int l, int r) {
            if (l > end || r < st)
                return 0;
            if (l >= st && r <= end)
                return arr[rootidx].hmap.getOrDefault(val, 0);
            int mid = (l + r) / 2;
            return find(st, end, val, 2 * rootidx + 1, l, mid) + find(st, end, val, 2 * rootidx + 2, mid + 1, r);
        }
    }

    class Node {
        HashMap<Integer, Integer> hmap;

        Node() {
            hmap = new HashMap<>();
        }
    }
}