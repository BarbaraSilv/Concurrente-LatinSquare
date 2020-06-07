import java.util.List;
import java.util.concurrent.Callable;

public class LatinWorker extends Thread{

    Buffer buffer;
    Countdown countdown;
    SortedList lista;

    public LatinWorker(Buffer b, Countdown c, SortedList l){
        buffer = b;
        countdown = c;
        lista = l;
    }

    public void run() {
        Runnable tarea = null;
        try {
            while (true){
                tarea = this.buffer.read();
                tarea.run();
            }
        } catch (PoisonException e) {
            System.out.println("Me llego una poisonException");
        }
        TareaCuadradoLatino tareaCuadradoLatino = (TareaCuadradoLatino) tarea;
        boolean esCuadradoLatino = verificar(tareaCuadradoLatino.cuadradoLatino);
        countdown.dec();
        if(esCuadradoLatino){
            lista.add(tareaCuadradoLatino.indice);
        }

    }

    public boolean verificar(CuadradoLatino c){
        return verificarDimension(c) && cumplePropiedad(c);
    }

    private boolean cumplePropiedad(CuadradoLatino cuadradoLatino){
        int dimension = cuadradoLatino.dimension;
        List<Integer> secuencia = cuadradoLatino.secuencia;
        return true;
    }

    private boolean verificarDimension(CuadradoLatino c){
       return c.secuencia.stream().allMatch(i -> i <= c.dimension);
    }
}
