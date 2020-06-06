public class Countdown {

    private int contador = 0;

    public Countdown(int x){
        contador = x;
    }

    public synchronized void dec(){
        contador --;
        if(contador <= 0){
            notifyAll();
        }
    }

    public synchronized void zero(){
        while (contador > 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
