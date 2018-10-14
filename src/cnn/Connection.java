package cnn;

public class Connection {
	private double weight;
	private double id;
	
	Connection(double weight, double id){
		this.weight = weight;
		this.id = id;
	}
	
	public void setWeight (double v) {
		this.weight = v;
	}
	
	public double getValue() {
		return this.weight;
	}
	
	public void setId (double i) {
		this.id = i;
	}
	
	public double getId	() {
		return this.id;
	}
}
