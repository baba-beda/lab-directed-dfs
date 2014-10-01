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
    ArrayList<Integer>[] digraph, digraph_t;
    boolean[] visited;
    ArrayList<Integer> order;
    ArrayList<Integer> component;
    int[] color;
    int count = 0;
    ArrayList<Integer> ans;
    boolean cycle = false;

    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt();
        digraph = new ArrayList[n];
        color = new int[n];
        ans = new ArrayList<Integer>();
        if (!cycle) {
            out.println("NO");
        }
        else {
            int j = ans.size() - 1;
            out.print(ans.get(j));
        }
    }

    public void dfs(int u) {
        if (cycle) {
            return;
        }
        if (color[u] != 2) {
            color[u] = 1;
            for(int v : digraph[u]) {
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
