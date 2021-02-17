package Classwork;

public class IntVector implements Cloneable{
	private int dim;
	private int[] coords;
	private static int numIntVectors = 0;
	
	public IntVector(int dimension) {
		if(dimension <=0) {
			throw new IllegalArgumentException(dimension);
		}
		dim = dimension;
		coords = new int[dim];
		numIntVectors++;
	}
	
	public IntVector(IntVector iv) {
		dim = iv.dim;
		coords = new int[dim];
		
		for(int i = 0; i < dim; i++) {
			coords[i] = iv.coords[i];
		}
		
		numIntVectors++;
	}
	
	public static int getNumIntVectors() {
		return numIntVectors;
	}
	
	@Override
	public IntVector clone() {
		IntVector copy;
		
		try {
			copy = (IntVector) super.clone();
			copy.coords = new int[dim];
			
			for(int i = 0; i < dim; i++) {
				copy.coords[i] = coords[i];
			}
		}
		catch(CloneNotSupportedException e) {
			return null;
		}
		
		return copy;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		
		IntVector other = (IntVector) obj;
		if(dim == other.dim) {
			for(int i = 0; i < dim; i++) {
				if(coords[i] != other.coords[i]) {
					return false;
				}
			}
			return true;
		}
		
		return false;
	}
	
	public void set(int index, int value) {
		coords[index] = value;
	}
}
