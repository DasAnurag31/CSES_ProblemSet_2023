public class StaticRangeMinimum {
    private int[] arr;
    private int[] tree;
    private int n;

    public StaticRangeMinimum(int[] arr) {
        this.arr = arr;
        this.n = arr.length;
        this.tree = new int[4 * n];
        build(0, 0, n - 1);
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(2 * node + 1, start, mid);
            build(2 * node + 2, mid + 1, end);
            tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
        }
    }

    public int query(int left, int right) {
        return query(0, 0, n - 1, left, right);
    }

    private int query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return Integer.MAX_VALUE; // Outside the range
        } else if (left <= start && right >= end) {
            return tree[node]; // Inside the range
        } else {
            int mid = (start + end) / 2;
            int leftMin = query(2 * node + 1, start, mid, left, right);
            int rightMin = query(2 * node + 2, mid + 1, end, left, right);
            return Math.min(leftMin, rightMin);
        }
    }

    // Laxy Propogation
    public void rangeUpdate(int left, int right, int addValue) {
        rangeUpdate(0, 0, n - 1, left, right, addValue);
    }

    private void rangeUpdate(int node, int start, int end, int left, int right, int addValue) {
        if (left > end || right < start) {
            return; // Outside the range
        } else if (left <= start && right >= end) {
            tree[node] += addValue;
            if (start != end) {
                tree[2 * node + 1] += addValue;
                tree[2 * node + 2] += addValue;
            }
        } else {
            int mid = (start + end) / 2;
            rangeUpdate(2 * node + 1, start, mid, left, right, addValue);
            rangeUpdate(2 * node + 2, mid + 1, end, left, right, addValue);
            tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        StaticRangeMinimum segmentTree = new StaticRangeMinimum(arr);

        // Point Query
        System.out.println("Point Query Result: " + segmentTree.query(5, 5)); // Expected Output: 11

        // Range Query and Update
        System.out.println("Range Query Result: " + segmentTree.query(2, 5)); // Expected Output: 5
        segmentTree.rangeUpdate(1, 3, -2);
        System.out.println("Range Query Result After Range Update: " + segmentTree.query(2, 5)); // Expected Output: 3
    }
}
