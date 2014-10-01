import java.io.*;
import java.util.*;
/**
 * Created by daria on 01.10.14.
 */
public class D {
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

    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt(), s = in.nextInt();
        digraph = new ArrayList[n];
        color = new int[n];
        Arrays.fill(color, 0);
        for (int i = 0; i < n; i++) {
            digraph[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            digraph[in.nextInt() - 1].add(in.nextInt() - 1);
        }
        if (dfs(s - 1))
            out.println("First player wins");
        else
            out.println("Second player wins");

    }

    public boolean dfs(int u) {
        boolean aux = false;
        color[u] = 1;
        for (int v : digraph[u]) {
                if (!dfs(v))
                    aux = true;
        }
        return aux;
    }

    public void run() {
        try {
            in = new FastScanner(new File("game.in"));
            out = new PrintWriter("game.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new D().run();
    }
}
