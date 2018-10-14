package cnn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;



import cnn.ImageLoad;

public class Convolution {
	private int id;
	
	private double[] filter = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private ArrayList<Double> patternedOutputs = new ArrayList<Double>();
	private ArrayList<Connection> relatedConnections = new ArrayList<Connection>();
	


	Convolution(int id, double[] Filter, ArrayList<Connection> connections, ArrayList<Double> patout) {
		this.id = id;
		this.filter = Filter;
		this.relatedConnections = connections;
		this.patternedOutputs = patout;
	}

	public void setPO(double sum) {
		patternedOutputs.add(sum);
	}
	
	public ArrayList<Double> getAllPO() {
		return this.patternedOutputs;
	}
	
	public double getPO(int index) {
		return this.patternedOutputs.get(index);
	}
	
	public double getFilter(int index) {
		return this.filter[index];
	}
}
