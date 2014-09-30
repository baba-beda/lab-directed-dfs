import java.io.*;
import java.util.*;
/**
 * Created by daria on 30.09.14.
 */
public class F {
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
        digraph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            digraph[i] = new ArrayList<Integer>();
        }
        Arrays.fill(color, 0);

        for (int i = 0; i < m; i++) {
            int a = in.nextInt(), b = in.nextInt();
            digraph[a - 1].add(b - 1);
        }
        topSort();
        int j = n - 1;
        while (j > 0 && digraph[ans.get(j) - 1].contains(ans.get(j - 1) - 1)) {
            j--;
        }
        if (j == 0) {
            out.print("YES");
        }
        else {
            out.print("NO");
        }
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
            ans.add(u + 1);
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
            in = new FastScanner(new File("hamiltonian.in"));
            out = new PrintWriter("hamiltonian.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new F().run();
    }
}
