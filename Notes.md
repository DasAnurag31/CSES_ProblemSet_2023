# Lessons Learned

1. Use of Buffered Reader is faster than using Scanner class - credit: Joydeep Bannerjee.
    ```java
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.close();
    }
    ```
2. When taking multiple inputs from the single line, make use of Tokenizer
   ```java
    StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
    long n = Long.parseLong(tokenizer.nextToken());
    long q = Long.parseLong(tokenizer.nextToken());
   ```
3. When there are multiple lines of output, do not use System.out multiple times, rather use a string builder and keep appending the results to it and output it all at once
   ```java
    StringBuilder output = new StringBuilder();
    for (long i = 0; i < q; i++) {
            long result = someFunction();
            output.append(result).append('\n');
        }
    System.out.print(output);
   ```
4.  Division by constants is well optimized by compilers.
5.  