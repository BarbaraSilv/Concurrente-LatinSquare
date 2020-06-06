import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        int cantidadDeCuadrados = 0;
        List<CuadradoLatino> cuadrados = new ArrayList<CuadradoLatino>();
        try {
            File myObj = new File("src/inputs-ejemplo");
            Scanner myReader = new Scanner(myObj);
            cantidadDeCuadrados = Integer.parseInt(myReader.nextLine());
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                cuadrados.add(new CuadradoLatino(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        ThreadPool threadPool = new ThreadPool(1000, 100);
        for (int i=0;i < cantidadDeCuadrados;i++){
            threadPool.launch(new TareaCuadradoLatino(i+1, cuadrados.get(i)));
        }
        threadPool.stop();
    }

}

