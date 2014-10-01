import sun.font.FontRunIterator;

import java.io.*;
import java.util.*;

/**
 * Created by daria on 30.09.14.
 */
public class C {
    class FastScanner {
        StreamTokenizer st;

        FastScanner() {
            st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        }

        FastScanner(File f) {
            try {
                st = new StreamTokenizer(new BufferedReader(new FileReader(f)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        int nextInt() throws IOException {
            st.nextToken();
            return (int)st.nval;
        }

        String nextString() throws IOException {
            st.nextToken();
            return st.sval;
        }
    }
    class Pair {
        int first;
        int last;
        Pair() {
            first = 0;
            last = 0;
        }

        Pair(int a, int b) {
            first = a;
            last = b;
        }
    }


    FastScanner in;
    PrintWriter out;
    ArrayList<Pair>[] digraph;
    int[] color;
    ArrayList<Integer> ans;
    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt();
        color = new int[n];
        ans = new ArrayList<Integer>();
        Arrays.fill(color, 0);
        digraph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            digraph[i] = new ArrayList<Pair>();
        }

        int start = in.nextInt(), end = in.nextInt();
        for (int i = 0; i < m; i++) {
            digraph[in.nextInt() - 1].add(new Pair(in.nextInt() - 1, in.nextInt()));
        }

        int[] d = new int[n];

        /* здесь могла быть ваша реклама
        и какой-то код проверки, который я так и не осилила написать
         */
    }

    public void run() {
        try {
            in = new FastScanner(new File("shortpath.in"));
            out = new PrintWriter("shortpath.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new C().run();
    }
}
