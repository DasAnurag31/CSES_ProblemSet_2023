import java.io.*;
import java.util.StringTokenizer;

public class DynamicRangeSumQuery {
    private static long pointQuery(long[] bit, long index) {
        index++;
        long result = 0;
        while (index > 0) {
            result += bit[(int) index];
            index -= (index & -index);
        }
        return result;
    }

    private static void updateBIT(long[] bit, long n, long index, long value) {
        index++;
        while (index <= n) {
            bit[(int) index] += value;
            index += (index & -index);
        }
    }

    private static long[] buildBIT(long[] values, long n) {
        long[] bit = new long[(int) (n + 1)];
        for (long i = 0; i < n; i++) {
            updateBIT(bit, n, i, values[(int) i]);
        }
        return bit;
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

        long[] bit = buildBIT(values, n);

        StringBuilder output = new StringBuilder();

        for (long i = 0; i < q; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int type = Integer.parseInt(tokenizer.nextToken());
        
            if(type == 1){
                long k = Long.parseLong(tokenizer.nextToken()) -1;
                long u = Long.parseLong(tokenizer.nextToken());
                long delta = u - values[(int)k];
                values[(int) k] = u;
                updateBIT(bit,n,k,delta);
            }
            else{
                long a = Long.parseLong(tokenizer.nextToken()) - 1;
                long b = Long.parseLong(tokenizer.nextToken()) - 1;
                long result = pointQuery(bit, b) - pointQuery(bit, a - 1);
                output.append(result).append('\n');
            }

        }
        System.out.print(output);

        reader.close();
    }
}
