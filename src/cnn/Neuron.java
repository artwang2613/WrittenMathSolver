package cnn;

import java.util.ArrayList;
import cnn.Connection;

public class Neuron {
	private double value;
	private int id;
	private double errorChange;
	private ArrayList<Connection> relatedConnections = new ArrayList<Connection>();
	
	Neuron(double value, int id, ArrayList<Connection> connections, double error){
		this.value = value;
		this.id = id;
		this.relatedConnections = connections;
		this.errorChange = error;
	}
	
	public void setValue (double v) {
		this.value = v;
	}
	
	public double getValue	() {
		return value;
	}
	
	public void setId (int i) {
		this.id = i;
	}
	
	public void setErrorChange (double i) {
		this.errorChange = i;
	}
	
	public double getErrorC	() {
		return this.errorChange;
	}
	
	public int getId	() {
		return this.id;
	}
	
	public void addToRelations(Connection c) {
		this.relatedConnections.add(c);
	}
	public void setRelations(double value, int index) {
		this.relatedConnections.get(index).setWeight(relatedConnections.get(index).getValue() + value);
	}
	
	public double getRelatedValue(int index) {
		return relatedConnections.get(index).getValue();
	}
	
	public void setRelatedValue(int d, double weight) {
		relatedConnections.get(d).setWeight(weight);
	}
}
