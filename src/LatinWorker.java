import java.util.ArrayList;
import java.util.List;

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
		Runnable tarea;
		try {
			while (true) {
				tarea = this.buffer.read();
				tarea.run();
				TareaCuadradoLatino tareaCuadradoLatino = (TareaCuadradoLatino) tarea;
				boolean esCuadradoLatino = verificar(tareaCuadradoLatino.cuadradoLatino);
				if (esCuadradoLatino) {
					lista.add(tareaCuadradoLatino.indice);
				}
				countdown.dec();
			}
		} catch (PoisonException e) {
			System.out.println("Me llego una poisonException");
		}
	}

    public boolean verificar(CuadradoLatino c){
        return verificarDimension(c) && cumplePropiedad(c);
    }

    private boolean cumplePropiedad(CuadradoLatino cuadradoLatino){
        int dimension = cuadradoLatino.dimension;
        List<Integer> secuencia = cuadradoLatino.secuencia;
        boolean cumple = true;
        
        for (int fila = 0; fila< dimension; fila++ ) {
        	List<Integer> filaActual = obtenerFila(fila,secuencia,dimension);
        	for (int columna = 0; columna< dimension; columna++ ) {
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
    

    private boolean verificarDimension(CuadradoLatino c){
       return c.secuencia.stream().allMatch(i -> i <= c.dimension);
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
