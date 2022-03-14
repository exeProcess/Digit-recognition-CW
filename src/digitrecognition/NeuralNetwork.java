package digitrecognition;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NeuralNetwork {
	
	private int numberOfInputNeuron;
	private int numberOfOutputNeuron = 10;
	private double[] hiddenLayerPerceptronValues = new double[4];
	private String desiredOutput;
	//private double[][] hiddenLayer = new double [4][numberOfInputNeuron];
	private double[][] outputLayer;
	public double[][] hiddenLayer;
	private double MINIMUM_WEIGHT = -1;
	private double MAXIMUM_WEIGHT = 1.0;
	
	
	
	public NeuralNetwork(List<String> dataset) {
		this.numberOfInputNeuron = dataset.size() - 1;
		this.desiredOutput = dataset.get(numberOfInputNeuron);
	}
	
	public void setHiddenLayerPerceptronWeights() {
		hiddenLayer = new double[4][numberOfInputNeuron];
		for(int i = 0; i < hiddenLayer.length;i++) {
			for(int j = 0; j < hiddenLayer[i].length; j++) {
				hiddenLayer[i][j] = randWeight();
			}
			//System.out.println(Arrays.toString(hiddenLayer[i]));
		}
	}
	public void setOuputLayerPerceptronWeight() {
		outputLayer = new double[numberOfOutputNeuron][4];
		for(int i = 0; i < outputLayer.length;i++) {
			for(int j = 0; j < outputLayer[i].length; j++) {
				outputLayer[i][j] = randWeight();
			}
			System.out.println(Arrays.toString(outputLayer[i]));
		}
	}
	
	public double randWeight() {
		return (this.MINIMUM_WEIGHT + Math.random()*(this.MAXIMUM_WEIGHT- this.MINIMUM_WEIGHT));
	}

	public double[][] getHiddenLayer() {
		return hiddenLayer;
	}

	public void setHiddenLayer(double[][] hiddenLayer) {
		this.hiddenLayer = hiddenLayer;
	}
	
	public void feedForward(String[] dataSample) {
		double summation = 0;
		for(int i = 0; i < hiddenLayer.length; i++){
			for(int j = 0; j < hiddenLayer[i].length;i++){
				summation += (int) (Integer.parseInt(dataSample[j]) * hiddenLayer[i][j]);
			}
			hiddenLayerPerceptronValues[i] = (1 / (1+Math.pow(2.7, -summation)));
		}
	}
	
	
}
