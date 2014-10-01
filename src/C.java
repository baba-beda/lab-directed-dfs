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
        final int Con = 200000001;
        color = new int[n];
        ans = new ArrayList<Integer>();
        Arrays.fill(color, 0);
        digraph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            digraph[i] = new ArrayList<Pair>();
        }

        int start = in.nextInt() - 1, end = in.nextInt() - 1;
        for (int i = 0; i < m; i++) {
            digraph[in.nextInt() - 1].add(new Pair(in.nextInt() - 1, in.nextInt()));
        }

        int[] d = new int[n];
        Arrays.fill(d, Con);
        topSort();

        int j = ans.indexOf(start);
        d[start] = 0;
        for (int i = j; i >= 0; i--) {
            for (Pair v : digraph[ans.get(i)]) {
                if (ans.get(i) != Con) {
                    d[v.first] = Math.min(d[v.first], d[ans.get(i)] + digraph[ans.get(i)].get(digraph[ans.get(i)].indexOf(v)).last);
                }
            }
        }
        out.print(d[end] == Con ? "Unreachable" : d[end]);

    }

    public void dfs(int u) {
        if (color[u] != 2) {
            color[u] = 1;
            for(Pair v : digraph[u]) {
                if (color[v.first] == 0) {
                    dfs(v.first);
                }
            }
            color[u] = 2;
            ans.add(u);
        }
    }

    public void topSort() {
        for(int v = 0; v < digraph.length; v++) {
            if (color[v] == 0) {
                dfs(v);
            }
        }
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
