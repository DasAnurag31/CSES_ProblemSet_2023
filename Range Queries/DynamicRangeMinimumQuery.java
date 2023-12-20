import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DynamicRangeMinimumQuery {
    private long[] arr;
    private long[] tree;
    private long n;

    public DynamicRangeMinimumQuery(long[] arr,long n) {
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

    public void pointUpdate(long index, long newValue) {
        pointUpdate(0, 0,(int) n - 1, index, newValue);
    }

    private void pointUpdate(int node, int start, int end, long index, long newValue) {
        if (start == end) {
            arr[(int)index] = newValue;
            tree[node] = newValue;
        } else {
            int mid = (start + end) / 2;
            if (index >= start && index <= mid) {
                pointUpdate(2 * node + 1, start, mid, index, newValue);
            } else {
                pointUpdate(2 * node + 2, mid + 1, end, index, newValue);
            }
            tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
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

        DynamicRangeMinimumQuery st = new  DynamicRangeMinimumQuery(values,n);

        StringBuilder output = new StringBuilder();

        for (long i = 0; i < q; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int type = Integer.parseInt(tokenizer.nextToken());
        
            if(type == 1){
                long k = Long.parseLong(tokenizer.nextToken()) -1;
                long u = Long.parseLong(tokenizer.nextToken());
                long delta = u - values[(int)k];
                values[(int) k] = u;
                st.pointUpdate(k, u);
            }
            else{
                long a = Long.parseLong(tokenizer.nextToken()) - 1;
                long b = Long.parseLong(tokenizer.nextToken()) - 1;
                long result = st.query(a,b);
                output.append(result).append('\n');
            }

        }
        System.out.print(output);

        reader.close();
    }
}
