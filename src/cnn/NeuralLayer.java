package cnn;

import java.util.ArrayList;

import cnn.Neuron;

public class NeuralLayer {
	private int netID;
	private int numOfNeurons;
	private int numOfFilters;
	private double error;
	private String type = "";
	ArrayList<Neuron> neurons = new ArrayList<Neuron>();
	ArrayList<Convolution> filters = new ArrayList<Convolution>();
	ArrayList<Connection> connections = new ArrayList<Connection>();
	ArrayList<Double> expendables = new ArrayList<Double>();


	NeuralLayer(int id, int numOfNeurons, int numOfFilters, String type) {
		this.netID = id;
		this.numOfNeurons = numOfNeurons;
		this.numOfFilters = numOfFilters;

		this.type = type;
	}

	
	
	public void fillConLayers() {

		this.filters.add(new Convolution(0, new double[] { 1, 0, -1, // left vert
				2, 0, -2, 1, 0, -1 }, connections, expendables));
		this.filters.add(new Convolution(1, new double[] { -1, 0, 1, // right vert
				-2, 0, 2, -1, 0, 1 }, connections, expendables));
		this.filters.add(new Convolution(2, new double[] { 1, 2, 1, // top hori
				0, 0, 0, -1, -2, -1 }, connections, expendables));
		this.filters.add(new Convolution(3, new double[] { -1, -2, -1, // bot hori
				0, 0, 0, 1, 2, 1 }, connections, expendables));
		this.filters.add(new Convolution(4, new double[] { -2, -1, 0, // diag right
				-1, 0, 1, 0, 1, 2 }, connections, expendables));
		this.filters.add(new Convolution(5, new double[] { 2, 1, 0, // diag left
				1, 0, -1, 0, -1, -2 }, connections, expendables));

	}

	public void fillNormLayers() {
		for (int size = 0; size < numOfNeurons; size++) {
			this.neurons.add(new Neuron(0, size, connections, error));
		}
	}
}
