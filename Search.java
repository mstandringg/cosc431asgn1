import java.util.*;
import java.io.*;
public class Search {

    public static void main(String[] args){

        Indexer<String, Pair> m = new Indexer();
        BufferedReader r;
        String line;
        String[] arr = new String[3];
        String cool;
        try {
//load index from disc
            Scanner in = new Scanner(System.in);
           // String s = in.nextLine();
            r = new BufferedReader(new FileReader("saved.txt"));

            while((line = r.readLine()) != null){

                arr = line.split(" ");

                    Pair p = new Pair(arr[1], Integer.parseInt(arr[2]));

                    m.put(arr[0], p);
                }
        }catch(IOException e){
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);

        String search;
//give rankings and find relevant results
        while((search = sc.nextLine())!=null){

            String[] terms = search.split(" ");

            float freq = 0;
            int dup = 0;
            LinkedList<Pair> l = new LinkedList<Pair>();

            for(int i = 0; i < terms.length; i++) {

                freq = 0;

                if (m.containsKey(terms[i])) {
                    for(Pair n : m.get(terms[i])){
                        freq = freq + n.getR();

                    }
                    for(Pair pr : m.get(terms[i])){
                         String doc = pr.getL();
                        dup = 0;

                        for(Pair zr : l){

                            if((doc).equals(zr.getL())){
                                dup = 1;
                            }
                        }
                        if(dup == 0){
                       //     int q =
                            float k = freq;

                            float rel =   ((pr.getR()) * 1000)/ k;

                            Pair np = new Pair(doc, rel);

                            if(rel > 0.5) {
                                l.add(np);
                            }

                        }

                    }
                }
            }


//sort results
            LinkedList<Pair> sort = new LinkedList<Pair>();
            if(!l.isEmpty()) {
                Pair maxpair = l.get(0);
                for (int v = 0; v < l.size(); v++) {
                    maxpair = l.get(v);
                    int k = 0;
                    for (int y = 0; y < l.size(); y++) {
                        if (l.get(y).getF() > maxpair.getF()) {
                            maxpair = l.get(y);
                            k = y;


                        }
                    }
                    sort.add(maxpair);
                    l.remove(k);
                }
                //display results
                for (int t = 0; t < sort.size(); t++) {
                    System.out.println(sort.get(t).getL() + "  " + sort.get(t).getF());
                }
            } else{
                System.out.println("no results were considered relevant");
            }

        }
    }
}
