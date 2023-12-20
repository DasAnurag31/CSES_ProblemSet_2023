public class SegmentTree {
    private int[] arr;
    private int[] tree;
    private int n;

    public SegmentTree(int[] arr) {
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
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public int query(int left, int right) {
        return query(0, 0, n - 1, left, right);
    }

    private int query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return 0; // Outside the range
        } else if (left <= start && right >= end) {
            return tree[node]; // Inside the range
        } else {
            int mid = (start + end) / 2;
            int leftSum = query(2 * node + 1, start, mid, left, right);
            int rightSum = query(2 * node + 2, mid + 1, end, left, right);
            return leftSum + rightSum;
        }
    }

    public void update(int index, int newValue) {
        update(0, 0, n - 1, index, newValue);
    }

    private void update(int node, int start, int end, int index, int newValue) {
        if (start == end) {
            arr[index] = newValue;
            tree[node] = newValue;
        } else {
            int mid = (start + end) / 2;
            if (index >= start && index <= mid) {
                update(2 * node + 1, start, mid, index, newValue);
            } else {
                update(2 * node + 2, mid + 1, end, index, newValue);
            }
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
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
            tree[node] += addValue * (end - start + 1);
            if (start != end) {
                tree[2 * node + 1] += addValue;
                tree[2 * node + 2] += addValue;
            }
        } else {
            int mid = (start + end) / 2;
            rangeUpdate(2 * node + 1, start, mid, left, right, addValue);
            rangeUpdate(2 * node + 2, mid + 1, end, left, right, addValue);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public int pointQuery(int index) {
        return pointQuery(0, 0, n - 1, index);
    }

    private int pointQuery(int node, int start, int end, int index) {
        if (start == end && start == index) {
            return tree[node];
        } else {
            int mid = (start + end) / 2;
            if (index <= mid) {
                return pointQuery(2 * node + 1, start, mid, index);
            } else {
                return pointQuery(2 * node + 2, mid + 1, end, index);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTree segmentTree = new SegmentTree(arr);

        System.out.println("Point Query Result: " + segmentTree.pointQuery(5)); // Expected Output: 1

        // Point Query and Update
        System.out.println("Range Query Result: " + segmentTree.query(3, 4)); // Expected Output: 24
        segmentTree.update(2, 10);
        System.out.println("Range Query Result After Update: " + segmentTree.query(1, 4)); // Expected Output: 29

        // Range Query and Update
        System.out.println("Range Query Result: " + segmentTree.query(2, 5)); // Expected Output: 37
        segmentTree.rangeUpdate(1, 3, 2);
        System.out.println("Range Query Result After Range Update: " + segmentTree.query(2, 5)); // Expected Output: 41

        // Point Query
        System.out.println("Point Query Result: " + segmentTree.pointQuery(3)); // Expected Output: 9
    }
}
