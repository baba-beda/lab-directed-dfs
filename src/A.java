import java.io.*;
import java.lang.Integer;
import java.util.*;

public class A {
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
    ArrayList<HashSet<Integer>> digraph;
    int[] color;
    boolean cycle = false;
    ArrayList<Integer> ans;

    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt();
        color = new int[n];
        ans = new ArrayList<Integer>();
        digraph = new ArrayList<HashSet<Integer>>();
        for (int i = 0; i < n; i++) {
            digraph.add(new HashSet<Integer>());
            color[i] = 0;
        }
        for (int i = 0; i < m; i++) {
            digraph.get(in.nextInt() - 1).add(in.nextInt() - 1);
        }
        topSort();
        if (cycle) {
            out.println(-1);
        }
        else {
            for (int i = n - 1; i >= 0; i--) {
                out.print(ans.get(i) + " ");
            }
        }

    }

    public void dfs(int u) {
        if (cycle) {
            return;
        }
        if (color[u] != 2) {
            color[u] = 1;
            Iterator i = digraph.get(u).iterator();
            while (i.hasNext()) {
                int v = (Integer) i.next();
                if (color[v] == 0) {
                    dfs(v);
                }
                if (color[v] == 1) {
                    cycle = true;
                    return;
                }
            }
            color[u] = 2;
            ans.add(u + 1);
        }
    }

    public void topSort() {
        for(int v = 0; v < digraph.size(); v++) {
            if (color[v] == 0) {
                dfs(v);
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("topsort.in"));
            out = new PrintWriter("topsort.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new A().run();
    }
}