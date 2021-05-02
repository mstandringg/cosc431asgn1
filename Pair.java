import java.util.*;

//pair that enables storage of frequencies in hashmap
public  class Pair {

    private String l;
    private Integer r;
    private Float f;
    public Pair(String l, Integer r){
        this.l = l;
        this.r = r;
    }

    public Pair(String l, Float f){
        this.l = l;
        this.f= f;
    }

    public String getL(){ return l; }
    public Integer getR(){ return r; }
    public Float getF(){return f;  }
    public void setF(Float f){this.f = f; }
    public void setL(String l){ this.l = l; }
    public void setR(Integer r){ this.r = r; }
}


// need arraylist of pairs to contain   docid and freq in that document