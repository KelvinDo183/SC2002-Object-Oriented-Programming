package model_Classes;

/**
 * Create Pair
 */
public class Pair<T1, T2> {

	public T1 t1;
	public T2 t2;

	public Pair() {
		this.t1 = null;
		this.t2 = null;
	}

	public Pair(T1 t1, T2 t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	public void setPair(T1 t1, T2 t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

}
