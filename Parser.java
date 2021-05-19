import java.io.*;
import java.util.*;


public class Parser {

    public static void main(String[] args) {

        Indexer<String, Pair> map = new Indexer();
        BufferedReader reader;
        String line;
        BufferedWriter w;

        try {
            w = new BufferedWriter(new FileWriter("parsed.txt"));
            try {
                reader = new BufferedReader(new FileReader("wsj.xml"));
                String docno = null;
                int nextDocno;
//iterate through wsj
                while ((line = reader.readLine()) != null) {
                    if (!(line.trim().isEmpty())) {
                        nextDocno = 0;
//divide based on delim characters
                        String[] a = line.split("[^a-zA-Z0-9><'-]");

                        for (int z = 0; z < a.length; z++) {


                            a[z] = a[z].toLowerCase();
                            a[z] = a[z].trim();

                            if (a[z].equals("<docno>") && (a.length > 2)) {
                                docno = a[z + 1];
                            } else if (a[z].equals("<docno>")) {
                                line = reader.readLine();


                                docno = line;

                            }
//if there is a docno associated with text add to index
                            if ((docno != null)) {
                                map.putParse(a[z], new Pair(docno, 1));
                                w.write((a[z].trim()).replaceAll("[^a-zA-Z0-9]", ""));
                                if(!(a[z].equals(""))){
                                    w.write("\n");
                                }
                                if(a[z].equals("</doc>")){
                                    w.write("\n");
                                }


                            }


                        }

                    }
                }

            } catch (IOException e) {
                System.out.println("didnt worKR");
                e.printStackTrace();
            }
        } catch (IOException eb) {
            eb.printStackTrace();
        }


        try {
//write index to memory
            BufferedWriter f = new BufferedWriter(new FileWriter("saved.txt"));
            for (String lastName : map.keySet()) {
                for (Pair l : map.get(lastName)) {

                    if (!(lastName.trim().isEmpty())) {

                        f.write(lastName + " " + l.getL() + " " + l.getR() + "\n");
                    }
                }

            }

            f.close();

        } catch (IOException e) {
            e.printStackTrace();


        }
    }
}



