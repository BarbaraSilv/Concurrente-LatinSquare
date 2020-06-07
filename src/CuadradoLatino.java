import java.util.ArrayList;import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CuadradoLatino {

    public int dimension;

    public List<Integer> secuencia;

    public CuadradoLatino(List<String> data){
        List<Integer> cuadrado = data.stream().map(i->Integer.parseInt(i)).collect(Collectors.toList());
        dimension = cuadrado.get(0);
        secuencia = cuadrado.subList(1, cuadrado.size());
       
    }


}
