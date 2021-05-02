import java.util.*;
import java.io.*;



    public class Indexer<K, V>
    {

        private HashMap<K, ArrayList<V>> map = new HashMap<>();
        private String currentdocid = "";


        public void put(K key, V value)
        {
            if (map.get(key) == null) {
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(value);
        }

        public void putParse(K key, V value) {

            if (map.get(key) == null) {
                map.put(key, new ArrayList<>());
                map.get(key).add(value);
                int size = map.get(key).size();
                Pair p = (Pair) map.get(key).get(size-1);
                currentdocid = p.getL();

            }

            else{
             //   if(map.get(key) != null){
                    int size = map.get(key).size();
                    Pair p = (Pair) map.get(key).get(size-1);
                    //currentdocid = p.getL();




                // if cuurent docid has this key already update freq
                if ((p.getL().equals(currentdocid))) {
                  //  map.get(key).add(value);
                    int i = p.getR() + 1;
                    p.setR(i);



                //    System.out.println(key + "  " + p.getL() + "  "  + p.getR());
                    currentdocid = p.getL();

                }
                //if word is in but not for this docid add but dont update size
                else  {
                    map.get(key).add(value);
                    size = map.get(key).size();
                    p = (Pair) map.get(key).get(size-1);
                    currentdocid = p.getL();
               //     System.out.println(key + "  " + p.getL() + "  "  + p.getR());
                }

            }


        }


        public Collection<V> get(Object key) {
            return map.get(key);
        }

        public Set<K> keySet() {
            return map.keySet();
        }

        public boolean containsKey(Object key) {
            return map.containsKey(key);
        }

    }







