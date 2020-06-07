import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

    Buffer buffer;
    List<LatinWorker> workers = new ArrayList();

    public ThreadPool(int n, int workers, Countdown c, SortedList l){
        this.buffer = new Buffer(n);
        for (int i = 0; i < workers; i++){
            LatinWorker worker = new LatinWorker(buffer, c, l);
            worker.start();
            this.workers.add(worker);
        }
    }

    public void launch(TareaCuadradoLatino task){
        buffer.write(task);
    }

    public void stop(){
        for(int i=0; i< workers.size(); i++){
            buffer.write(new PoisonPill());
        }
    }
}