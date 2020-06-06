public class Buffer {

	private final Runnable[] data;
	private int begin = 0, end = 0;

	public Buffer(int n){
		this.data = new Runnable[n + 1];
	}

	public synchronized void write(Runnable task) {
		while (isFull()){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		data[begin] = task;
		begin = next(begin);
		notifyAll();
	}
	public synchronized Runnable read() {
		while (isEmpty()){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Runnable result = data[end];
		end = next(end);
		notifyAll();
		return result ;
	}
	private boolean isEmpty () { return begin == end; }
	private boolean isFull () { return next(begin) == end; }
	private int next (int i) { return (i +1) % (data.length); }
}
