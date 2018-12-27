import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = null;
        if(args.length == 0) {
            sc = new Scanner(System.in);
        }
        else if(args.length == 1) {
            try {
                sc = new Scanner(new FileInputStream(args[0]));
            } catch(FileNotFoundException e) {
                System.out.println(e);
                System.exit(0);
            }
        }
        else {
            System.out.println("More than one argument is not accepted");
            System.exit(0);
        }

        assert(sc != null);
        int N = sc.nextInt();
        int L = sc.nextInt();

        ArrayList<LinkedList<Integer>> neighbours = new ArrayList<LinkedList<Integer>>();
        for(int i = 0; i < N; i++) {
            neighbours.add(new LinkedList<Integer>());
        }

        for(int i = 0; i < N-1; i++) {
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;

            neighbours.get(u).add(v);
            neighbours.get(v).add(u);
        }

        ArrayList<Character> labelValues = new ArrayList<Character>();
        ArrayList<HashSet<Integer>> labels = new ArrayList<HashSet<Integer>>();

        for(int i = 0; i < L; i++) {
            int u = sc.nextInt() - 1;
            assert(neighbours.get(u).size() == 1);
            String label = sc.next();
            if(!label.equals("$")) {
                for(char c : label.toCharArray()) {
                    int index = labelValues.indexOf(c);
                    if(index == -1) {
                        HashSet<Integer> set = new HashSet<Integer>();
                        set.add(u);
                        labels.add(set);
                        labelValues.add(c);
                    }
                    else {
                        labels.get(index).add(u);
                    }
                }
            }
        }
        sc.close();
        System.out.println((new OptimalTreeLabeling()).minimumLabelWeight(neighbours, labels));
    }
}