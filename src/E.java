import java.io.*;
import java.util.*;

/**
 * Created by daria on 30.09.14.
 */
public class E {
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
    int start, end;
    int[] parent;
    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt();
        digraph = new ArrayList[n];
        color = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            digraph[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            digraph[in.nextInt() - 1].add(in.nextInt() - 1);
        }
        start = -1;
        Arrays.fill(parent, - 1);
        for (int i = 0; i < n; i++) {
            if (dfs(i))
                break;
        }
        if (start == -1) {
            out.print("NO");
        }
        else {
            out.println("YES");
            ArrayList<Integer> cycle = new ArrayList<Integer>();
            cycle.add(start + 1);
            for (int v = end; v != start; v = parent[v]) {
                cycle.add(v + 1);
            }
            Collections.reverse(cycle);
            for (int k : cycle) {
                out.print(k + " ");
            }
        }
    }

    public boolean dfs(int u) {
        color[u] = 1;
        for(int v : digraph[u]) {
            if (color[v] == 0) {
                parent[v] = u;
                if (dfs(v))
                    return true;
            }
            if (color[v] == 1) {
                end = u;
                start = v;
                return true;
            }
        }
        color[u] = 2;
        return false;
    }


    public void run() {
        try {
            in = new FastScanner(new File("cycle.in"));
            out = new PrintWriter("cycle.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new E().run();
    }

}
