import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int cantidadDeCuadrados = 0;
		List<CuadradoLatino> cuadrados = new ArrayList<CuadradoLatino>();
		try {
			File archivo = new File("src/inputs-ejemplo");
			Scanner myReader = new Scanner(archivo);
			cantidadDeCuadrados = Integer.parseInt(myReader.nextLine());
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				List<String> listaDeNumeros = Arrays.asList(data.split(" "));
				cuadrados.add(new CuadradoLatino(listaDeNumeros));
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
        
        
		Countdown countdown = new Countdown(cantidadDeCuadrados);
		SortedList sortedList = new SortedList();
		ThreadPool threadPool = new ThreadPool(1000, cantidadDeCuadrados, countdown, sortedList);
		for (int i = 0; i < cantidadDeCuadrados; i++) {
			threadPool.launch(new TareaCuadradoLatino(i + 1, cuadrados.get(i)));
		}
		threadPool.stop();

		countdown.zero();
		System.out.println(sortedList.lista);

	}

}



