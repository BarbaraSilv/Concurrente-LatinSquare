import java.util.ArrayList;
import java.util.List;

public class LatinWorker extends Thread{

    Buffer buffer;


    public LatinWorker(Buffer b){
        buffer = b;
    }

	public void run() {
		try {
			while (true) {
				this.buffer.read().run();
			}
		} catch (PoisonException e) {
			System.out.println("Me llego una poisonException");
		}
	}


}
