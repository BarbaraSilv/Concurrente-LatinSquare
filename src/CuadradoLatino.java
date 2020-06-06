import java.util.ArrayList;
import java.util.List;

public class CuadradoLatino {

    public int dimension;

    public List<Integer> secuencia;

    public CuadradoLatino(String data){
        List<Integer> cuadrado = transformarACuadrado(data);
        dimension = cuadrado.get(0);
        secuencia = cuadrado.subList(1, cuadrado.size());
    }

    private List<Integer> transformarACuadrado(String data){
        List<Integer> lista = new ArrayList<>();

        return lista;
    }
}
