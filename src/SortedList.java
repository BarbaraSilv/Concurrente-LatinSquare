import java.util.ArrayList;
import java.util.Comparator;

public class SortedList {

    public ArrayList<Integer> lista = new ArrayList<Integer>();

    public synchronized void add(int i){
        lista.add(i);
        lista.sort(Comparator.naturalOrder()); // ponele que anda
    }
}
