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
    FastScanner in;
    PrintWriter out;
    ArrayList<Integer>[] digraph;
    int[] color;
    ArrayList<Integer> ans;
    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt();
        color = new int[n];
        ans = new ArrayList<Integer>();
        Arrays.fill(color, 0);
        digraph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            digraph[i] = new ArrayList<Integer>();
        }
        int[][] weight = new int[n][n];
        int start = in.nextInt(), end = in.nextInt();
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1, b = in.nextInt() - 1;
            digraph[a].add(b);
            weight[a][b] = in.nextInt();
        }

        topSort();
        int[] d = new int[n];
        Arrays.fill(d, 300000001);
        d[start - 1] = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int v : digraph[ans.get(i)]) {
                d[v] = Math.min(d[v], d[ans.get(i)] + weight[ans.get(i)][v]);
            }
        }
        out.print(d[end - 1] != 300000001 ? d[end - 1] : "Unreachable");

    }

    public void dfs(int u) {
        if (color[u] != 2) {
            color[u] = 1;

            for(int v : digraph[u]) {
                if (color[v] == 0) {
                    dfs(v);
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
