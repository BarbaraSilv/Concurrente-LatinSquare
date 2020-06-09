import java.io.*;
import java.text.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
	    int cantLatinWorkers;
        System.out.print("Ingresar la cantidad de Workers : ");
        Scanner scanner = new Scanner(System. in);
        cantLatinWorkers = Integer.parseInt(scanner.nextLine());
		NumberFormat formatter = new DecimalFormat("#0.00000");
		long inicio = System.currentTimeMillis();
        int cantidadDeCuadrados = 0;
		List<CuadradoLatino> cuadrados = new ArrayList<>();
		try {
			File archivo = new File("src/inputs-ejemplo");
			Scanner archivoLeido = new Scanner(archivo);
			cantidadDeCuadrados = Integer.parseInt(archivoLeido.nextLine());
			while (archivoLeido.hasNextLine()) {
				String data = archivoLeido.nextLine();
				List<String> listaDeNumeros = Arrays.asList(data.split(" "));
				cuadrados.add(new CuadradoLatino(listaDeNumeros));
			}
			archivoLeido.close();
		} catch (FileNotFoundException e) {
			System.out.println("Algo malio sal.");
			e.printStackTrace();
		}

		Countdown countdown = new Countdown(cantidadDeCuadrados);
		SortedList sortedList = new SortedList();
		ThreadPool threadPool = new ThreadPool(1000, cantLatinWorkers, countdown, sortedList);
		for (int i = 0; i < cantidadDeCuadrados; i++) {
			threadPool.launch(new TareaCuadradoLatino(i + 1, cuadrados.get(i)));
		}
		threadPool.stop();
		countdown.zero();
		System.out.println(sortedList.lista);
		long fin = System.currentTimeMillis();
		System.out.print("El tiempo de ejecucion es " + formatter.format((fin - inicio) / 1000d) + " segundos");
	}

}



