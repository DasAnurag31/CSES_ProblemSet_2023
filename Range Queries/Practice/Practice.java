class SegmentTree {
    int[] tree;
    int[] arr;
    int n;

    SegmentTree(int[] arr) {
        this.arr = arr;
        this.n = arr.length;
        this.tree = new int[n * 4];
        build(0, 0, n - 1);

    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(node * 2 + 1, start, mid);
            build(node * 2 + 2, mid + 1, end);
            tree[node] = tree[node * 2 + 1] + tree[node * 2 + 2];
        }
    }

    private int pointQuery(int index) {
        return pointQuery(0, 0, n - 1, index);
    }

    private int pointQuery(int node, int start, int end, int index) {

    }

    private int rangeQuery(int left, int right) {
        return rangeQuery(0, 0, n - 1, left, right);
    }

    private int rangeQuery(int node, int start, int end, int left, int right) {

    }

    public static void main(String args[]) {
        int[] arr = { 1, 3, 5, 7, 9, 11 };
        SegmentTree st = new SegmentTree(arr);
        System.out.println(st.rangeQuery(3, 4));
        System.out.println(st.pointQuery(3));
    }
}
