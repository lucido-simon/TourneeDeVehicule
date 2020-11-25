package algorithms.ClarkAndWright.model;

import common.Vertex.BasicVertex;

public class Saving implements Comparable<Saving>{
	public double val;
	public BasicVertex from;
	public BasicVertex to;
	
	public Saving(double v, BasicVertex f, BasicVertex t){
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
