package algorithms.ClarkAndWright.model;

public class Saving implements Comparable<Saving>{
	public double val;
	public Node from;
	public Node to;
	
	public Saving(double v,Node f,Node t){
		val = v;
		from = f;
		to = t;
	}

	@Override
	public int compareTo(Saving o) {
		if(o.val<this.val){
			return -1;
		}else if(o.val == this.val){
			return 0;
		}else{
			return 1;
		}
	}
}
