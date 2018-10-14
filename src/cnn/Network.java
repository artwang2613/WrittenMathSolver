package cnn;

import cnn.Neuron;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;
import cnn.Connection;
import cnn.NeuralLayer;

public class Network {

	private int size;
	private int count = 0;
	private int target;
	private double finalSum;
	private ArrayList<NeuralLayer> layers = new ArrayList<NeuralLayer>();
	private ArrayList<ArrayList<Double>> inputs = new ArrayList<ArrayList<Double>>();
	ArrayList<Double> labels = new ArrayList<Double>();
	private ArrayList<Double> outputs = new ArrayList<Double>();
	private ArrayList<Double> pixels = new ArrayList<Double>();
	private ArrayList<Double> secondPixels = new ArrayList<Double>();
	private boolean go;
	double[] ans = new double[16];
	double error = 0;

	Network() {

		layers.add(new NeuralLayer(0, 0, 0, "conv"));
		layers.add(new NeuralLayer(1, 0, 0, "conv"));
		layers.add(new NeuralLayer(2, 64, 0, "norm"));
		layers.add(new NeuralLayer(3, 16, 0, "norm"));
		fillNet();
	}

	public void generateConnections() throws IOException {
		
		File file = new File("C:/Users/wanga/eclipse-workspace1/CNN/Weights/weights.txt");
		PrintWriter pw = new PrintWriter(file);
		

		


		for (Neuron n : layers.get(3).neurons) {
			for (int j = 0; j < layers.get(2).neurons.size(); j++) {
				Random rand = new Random();
				n.addToRelations(new Connection(rand.nextDouble(), j));
				pw.println(String.valueOf(rand.nextDouble()));

				// System.out.println(rand.nextDouble());
			}

		}
		pw.close();
	}

	public void clearFile() throws IOException{
		File file = new File("C:/Users/wanga/eclipse-workspace1/CNN/Weights/weights.txt");
		PrintWriter pw = new PrintWriter(file);
		pw.close();
	}
	
	public void readConnections() throws IOException {
		File file = new File("C:/Users/wanga/eclipse-workspace1/CNN/Weights/weights.txt");
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		for (Neuron n : layers.get(3).neurons) {

			for (int j = 0; j < layers.get(2).neurons.size(); j++) {
				// layers.get(3).connections.add(new
				// Connection((Double.parseDouble(br.readLine())), j));
				// System.out.println(String.valueOf(Double.valueOf(br.readLine())));

				n.addToRelations(new Connection(0, (n.getId() + 1) * j));
				if (br.readLine() != null) {
					n.setRelatedValue((n.getId() + 1) * j, Double.parseDouble(br.readLine()));
				}

			}
		}
		br.close();

	}

	public void editWeightsLayers(int index) throws FileNotFoundException {
		File file = new File("C:/Users/wanga/eclipse-workspace1/CNN/Weights/weights.txt");
		PrintWriter pw = new PrintWriter(file);
		
		
		double temp = 0;
		calculateError(index);
		for (int k = 0; k < 16; k++) {

			for (int n = 0; n < 64; n++) {
				layers.get(3).connections.get(k).setWeight(3 + (layers.get(3).neurons.get(k).getValue() - ans[k])
						* ((Math.pow(Math.E, -layers.get(2).neurons.get(n).getValue()))
								/ (Math.pow(1 + (Math.pow(Math.E, -layers.get(2).neurons.get(n).getValue())), 2))));
				temp = 3 + (layers.get(3).neurons.get(k).getValue() - ans[k])
				* ((Math.pow(Math.E, -layers.get(2).neurons.get(n).getValue()))
						/ (Math.pow(1 + (Math.pow(Math.E, -layers.get(2).neurons.get(n).getValue())), 2)));
				//pw.println(temp);

			}
		}

		go = true;

	}

	public void fillNet() {
		layers.get(0).fillConLayers();
		layers.get(1).fillConLayers();
		layers.get(2).fillNormLayers();
		layers.get(3).fillNormLayers();

	}

	public void flattenConv() {
		double sum = 0;
		for (int n = 0; n < 64; n++) {
			sum = 0;
			for (int i = 0; i < layers.get(1).filters.size(); i++) {
				for (int k = 0; k < 24 * 24; k++) {
					if (layers.get(1).filters.get(i).getPO(k) < 0) {
						sum += 0;
					} else {
						sum += layers.get(1).filters.get(i).getPO(k);
					}
				}
			}
			sum = sum / (layers.get(1).filters.size() * 24 * 24);
			//System.out.println(sum);
			layers.get(2).neurons.get(n).setValue(sum);
			sum = 0;

		}
	}

	public ArrayList<ArrayList<Double>> parseCsv() throws IOException {
		String csvFile = "C:/Users/wanga/eclipse-workspace1/CNN/MNIST/mnist_train.csv";
		FileReader fr = new FileReader(csvFile);

		CsvListReader cs = new CsvListReader(fr, CsvPreference.STANDARD_PREFERENCE);
		List<String> training;

		try {
			count = 0;
			while (count < 5000) {
				inputs.add(new ArrayList<Double>());
				for (String s : training = cs.read()) {
					inputs.get(count).add(Double.valueOf(s));
					// System.out.print(Double.valueOf(s));
				}
				// System.out.println("");
				count++;
			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}
		for (ArrayList<Double> d : inputs) {
			labels.add(d.get(0));
			d.remove(0);
		}

		cs.close();
		return inputs;
	}

	public void feedForward(int index) throws InterruptedException, IOException {
		for (int n = 0; n < layers.get(0).filters.size(); n++) {
			filterInputs(inputs.get(index), n);
			filterOutputs(outputs, n);
			flattenConv();
		}
		evaluateLastLayer();
		//clearFile();
		editWeightsLayers(index);
		System.out.println("GRAH: " + error + " %");
	}

	public void filterInputs(ArrayList<Double> n, int index) {
		pixels = n;
		double sum = 0;
		for (int y = 0; y < 26; y++) {
			for (int x = 0; x < 26; x++) {
				for (int i = (y); i < (3 + y); i++) { // height
					for (int h = (x); h < (x + 3); h++) { // width
						sum += pixels.get(h + i * 28)
								* layers.get(0).filters.get(index).getFilter(3 * (i - y) + (h - x));
						// System.out.println(this.pixels.get(h + i * 28));

					}
					sum = 1 / (1 + Math.pow(Math.E, -sum));
					outputs.add(sum);
					// System.out.println(sum);
					sum = 0;
				}
				//sum = 0;
			}
		}

	}

	public void filterOutputs(ArrayList<Double> k, int index) {
		secondPixels = k;
		// System.out.println("");
		double sum = 0;
		for (int y = 0; y < 24; y++) {
			for (int x = 0; x < 24; x++) {
				for (int i = (y); i < (3 + y); i++) { // height
					for (int h = (x); h < (x + 3); h++) { // width
						sum += secondPixels.get(h + i * 26)
								* layers.get(1).filters.get(index).getFilter((h - x) + 3 * (i - y));

					}
				}
				sum = 1 / (1 + Math.pow(Math.E, -sum));
				layers.get(1).filters.get(index).setPO(sum);
				//System.out.println(sum);
				sum = 0;
			}
		}

	}
	
	
	public void evaluateLastLayer() {
		double sum = 0;
		for (Neuron n : layers.get(3).neurons) {
			for (int m = 0; m < 64; m++) {
				sum = sum +  layers.get(2).neurons.get(m).getValue() * 1/(1+Math.pow(Math.E,(n.getRelatedValue(n.getId()))));
			}
			n.setValue(sum);
			sum = 0;
		}
	}
	

	public void calculateError(int index) {
		error = 0;
		
		
		for (int i = 0; i < 16; i++) {
			if (labels.get(index) == i) {
				ans[i] = 1;
			} else {
				ans[i] = 0;
			}

		}
		for (int i = 0; i < 16; i++) {

			error += Math.pow(layers.get(3).neurons.get(i).getValue() - ans[i], 2);
			
		}
		error = error / 16;
		
	}

}
