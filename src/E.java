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
    int count = 0;
    boolean cycle = false;

    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt();
        digraph = new ArrayList[n];


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
