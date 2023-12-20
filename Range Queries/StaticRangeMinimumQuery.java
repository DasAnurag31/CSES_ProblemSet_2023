import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StaticRangeMinimumQuery {
    private long[] arr;
    private long[] tree;
    private long n;

    public StaticRangeMinimumQuery(long[] arr,long n) {
        this.arr = arr;
        this.n = n;
        this.tree = new long[4 * (int) n];
        build(0, 0, (int) n - 1);
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

    public long query(long left, long right) {
        return query(0, 0,(int) n - 1, left, right);
    }

    private long query(int node, int start, int end, long left, long right) {
        if (left > end || right < start) {
            return Integer.MAX_VALUE; // Outside the range
        } else if (left <= start && right >= end) {
            return tree[node]; // Inside the range
        } else {
            int mid =(int) (start + end) / 2;
            long leftMin = query(2 * node + 1, start, mid, left, right);
            long rightMin = query(2 * node + 2, mid + 1, end, left, right);
            return Math.min(leftMin, rightMin);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        long n = Long.parseLong(tokenizer.nextToken());
        long q = Long.parseLong(tokenizer.nextToken());

        long[] values = new long[(int) n];
        tokenizer = new StringTokenizer(reader.readLine());
        for (long i = 0; i < n; i++) {
            values[(int) i] = Long.parseLong(tokenizer.nextToken());
        }

        StaticRangeMinimumQuery st = new  StaticRangeMinimumQuery(values,n);

        StringBuilder output = new StringBuilder();

        for (long i = 0; i < q; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            long a = Long.parseLong(tokenizer.nextToken()) - 1;
            long b = Long.parseLong(tokenizer.nextToken()) - 1;

            long result = st.query(a,b);
            output.append(result).append('\n');
        }

        System.out.print(output);

        reader.close();
    }
}
