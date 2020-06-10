import java.util.ArrayList;
import java.util.List;

public class TareaCuadradoLatino implements Runnable{

    public int indice;
    public CuadradoLatino cuadradoLatino;
    Countdown countdown;
    SortedList lista;

    public TareaCuadradoLatino(int indice, CuadradoLatino cuadrado, Countdown contador, SortedList lista){
        this.indice = indice;
        this.cuadradoLatino = cuadrado;
        this.countdown = contador;
        this.lista = lista;
    }

    @Override
    public void run() {
        boolean esCuadradoLatino = verificar();
        if (esCuadradoLatino) {
            lista.add(indice);
        }
        countdown.dec();
    }

    public boolean verificar(){
        return verificarDimension() && cumplePropiedad();
    }

    private boolean cumplePropiedad(){
        int dimension = cuadradoLatino.dimension;
        List<Integer> secuencia = cuadradoLatino.secuencia;
        boolean cumple = true;

        for (int fila = 0; fila < dimension; fila++ ) {
            List<Integer> filaActual = obtenerFila(fila,secuencia,dimension);
            for (int columna = 0; columna < dimension; columna++ ) {
                List<Integer> columnaActual= obtenerColumna(columna,secuencia,dimension);
                cumple = cumple && verificarNumero(secuencia.get(fila+(columna*dimension)),filaActual) &&
                        verificarNumero(secuencia.get(fila+(columna*dimension)),columnaActual);
            }
        }
        return cumple;
    }

    private boolean verificarNumero(int numero, List<Integer> linea ) {
        return linea.stream().filter(i -> i == numero).count() == 1;
    }

    private boolean verificarDimension(){
        return cuadradoLatino.secuencia.stream().allMatch(i -> i <= cuadradoLatino.dimension);
    }

    private List<Integer> obtenerFila(int fila, List<Integer> secuencia, int dimension) {
        List<Integer> filaObtenida = new ArrayList<>();
        int posicion = fila * dimension;
        for (int i = 0; i < dimension; i++) {
            filaObtenida.add(secuencia.get(posicion));
            posicion += 1;
        }
        return filaObtenida;

    }

    private List<Integer> obtenerColumna(int columna, List<Integer> secuencia, int dimension) {
        List<Integer> columnaObtenida = new ArrayList<>();
        int posicion = columna;
        for (int i = 0; i < dimension; i++) {
            columnaObtenida.add(secuencia.get(posicion));
            posicion += dimension;
        }
        return columnaObtenida;
    }
}
