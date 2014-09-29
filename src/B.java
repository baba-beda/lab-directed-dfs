import java.io.*;
import java.util.*;

/**
 * Created by daria on 30.09.14.
 */
public class B implements Runnable {
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
    ArrayList<Integer>[] digraph, digraph_t;
    boolean[] visited;
    ArrayList<Integer> order;
    ArrayList<ArrayList<Integer>> component;
    int count = 0;
    boolean cycle = false;
    ArrayList<Integer> ans;

    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt();
        ans = new ArrayList<Integer>();
        digraph = new ArrayList[n];
        digraph_t = new ArrayList[n];
        visited = new boolean[n];
        order = new ArrayList<Integer>();
        component = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            digraph[i] = new ArrayList<Integer>();
            digraph_t[i] = new ArrayList<Integer>();
            visited[i] = false;
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt(), b = in.nextInt();
            digraph[a - 1].add(b - 1);
            digraph_t[b - 1].add(a - 1);
        }

        for(int v = 0; v < n; v++) {
            if (!visited[v])
                dfs1(v);
        }

        Arrays.fill(visited, false);

        for(int v = 0; v < n; v++) {
            int u = order.get(n - 1 - v);
            if (!visited[u]) {
                component.add(new ArrayList<Integer>());
                dfs2(u);
                count++;
            }
        }
        out.println(component.size());
        int[] ans = new int[n];
        for(ArrayList<Integer> comp : component) {
            for (int v : comp) {
               ans[v] = component.indexOf(comp) + 1;
            }
        }
        for(int i : ans) {
            out.print(i + " ");
        }

    }

    public void dfs1(int u) {
        visited[u] = true;
        for(int w : digraph[u]) {
            if (!visited[w]) {
                dfs1(w);
            }
        }
        order.add(u);
    }

    public void dfs2(int u) {
        visited[u] = true;
        component.get(count).add(u);
        for(int w : digraph_t[u]) {
            if (!visited[w])
                dfs2(w);
        }
    }


    public void run() {
        try {
            in = new FastScanner(new File("input.txt"));
            out = new PrintWriter("output.txt");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new B().run();
    }
}
