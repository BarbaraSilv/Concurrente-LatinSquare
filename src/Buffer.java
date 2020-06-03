

public class Buffer {
	int n = 0; //ver forma de aplicar segun el tp
	private int newValor;
	private Object[] data = new Object [n+1];
	private int begin = 0, end= 0;
/*	
	public synchronized Object read() {
		while (isEmpty());
		wait();
		Object result = data[end];
		end= next (end);
		notifyAll();
		return result;
	}
	
	public synchronized Object write(int newValor) {
		while(isFull());
		wait();
		data [begin] = newValor;
		begin = next (begin);
		notifyAll();
	}

 
private boolean isEmpty() {return  begin =  end;}
private boolean isFull() {return next(begin) == end;}
private int next (int i) {return (i+1)%n;}
*/
}