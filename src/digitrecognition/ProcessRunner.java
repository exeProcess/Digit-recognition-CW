package digitrecognition;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ProcessRunner {
	

	
	private static int numberOfHiddenLayer = 200;
	public static double min = -10;
	public static double max = 10;
	public static double[] hiddenLayerValue = new double[numberOfHiddenLayer];
	public static int[] outputLayerValue = new int[10];
	public static double[][] hiddenLayer = new double[numberOfHiddenLayer][64];
	public static double[][] outputLayer = new double[10][numberOfHiddenLayer];
	public static int[] outputMap = new int[10];
	public static int[] dataSample;
	public static double learningRate = 0.02;
	// public static double accuarcy;
	public static int success = 0;
	public static int noOfRows = 0;
	public static void randWeight() {
		for(int i = 0; i < hiddenLayer.length; i++) {
			for(int j = 0; j < hiddenLayer[i].length; j++) {
				hiddenLayer[i][j] = Math.floor((Math.random() * (max - min)) + min);
			}
		}
		for(int i = 0; i < outputLayer.length; i++) {
			for(int j = 0; j < outputLayer[i].length; j++) {
				outputLayer[i][j] = Math.floor((Math.random() * (max - min)) + min);
			}
		}
	}
	
	public static void feedForward() {
		double summation = 0;
		for(int i = 0; i < numberOfHiddenLayer; i++) {
			summation = 0;
			for(int j = 0; j < 64;j++) {
				summation += dataSample[j] * hiddenLayer[i][j];  
			}
			hiddenLayerValue[i] = (1 / (1 + Math.pow(2.7, - summation)));
			// summation = 0;
		}
		
		for(int i = 0;i < 10;i++) {
			summation = 0;
			for(int j = 0; j < numberOfHiddenLayer;j++) {
				summation += hiddenLayerValue[j] * outputLayer[i][j];
			}
			// double sum = (int) (1 / (1 + Math.pow(2.7, - summation)));
			if(summation >= 0) {
				outputLayerValue[i] = 1;
			} else {
				outputLayerValue[i] = 0;
			}
		}
	}
	
	public static void training() {
		
		// System.out.println("Training method Called");
		
		int[] errorOutput = new int[10];
		//double[]errorHidden = new double[numberOfHiddenLayer];
		double[] errorHidden = new double[numberOfHiddenLayer];
		
		
		for(int i = 0; i < 10; i++) {
			errorOutput[i] = outputMap[i] - outputLayerValue[i];
		}
		
		
		for(int j = 0; j < numberOfHiddenLayer; j++) {
			double errorTemp = 0;
			for(int i = 0; i < 10; i++) {
				errorTemp += errorOutput[i] * outputLayer[i][j];
			}
			errorHidden[j] = hiddenLayerValue[j] * (1 - hiddenLayerValue[j]) * errorTemp;
		}
		
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < numberOfHiddenLayer; j++) {
				outputLayer[i][j] = outputLayer[i][j] + learningRate * hiddenLayerValue[j] * errorOutput[i]; 
			}
		}
		for(int i = 0; i < numberOfHiddenLayer; i++) {
			for(int j = 0; j < 64; j++) {
				hiddenLayer[i][j] = hiddenLayer[i][j] + learningRate * dataSample[j] * errorHidden[i]; 
			}
		}
	} 
	
	public static void test() throws FileNotFoundException {
		System.out.println("Running test .....");
		String line = "";  
		String splitBy = ",";
		FileReader file = new FileReader("test.csv");
		Scanner fileReader = new Scanner(file);
		
			while (fileReader.hasNextLine())   
			{  
				line = fileReader.nextLine();
				String[] data = line.split(splitBy);
				
				for (int i = 0; i < 10; i++) {
					outputMap[i] = 0;
				}
				
				dataSample = new int[line.split(splitBy).length - 1];
				for(int i = 0; i < dataSample.length - 1; i++) {
					dataSample[i] = Integer.parseInt(data[i]);
				}
				int targetOuput = Integer.parseInt(data[64]); 
				outputMap[targetOuput] = 1;
				
				feedForward();
				
				if (testError()) {
					training();
				} else {
					success++;
				}
				
				// for(int errorIndex = 0;errorIndex < outputMap.length; errorIndex++) {
					// if(testError(outputMap[errorIndex], outputLayerValue[errorIndex])) {
						//System.out.println("no error:"+ outputMap[errorIndex] + ":"+ outputLayerValue[errorIndex]);
						// success++;
					// }else {
						// training();
					//}
				//}
				//System.out.println(Arrays.toString(outputLayerValue)); 
				noOfRows++;
			}  
		
		double accuracy = (double) success/noOfRows * 100;
		
		System.out.println("Accuracy: " + accuracy + " Success: " + success + " File Rows: " + noOfRows);
		//cycle++; 
		success = 0;
		noOfRows = 0;
	} 
				
			
	
	public static boolean testError() {
		for (int i = 0; i < 10; i++) {
			if (outputMap[i] != outputLayerValue[i]) {
				return true;
			}
		}
		
		return false;
		// if(a != b) {
			// return false;
		// }
		// return true;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		randWeight();
		
		int i = 0;
		int cycle = 0;
		try { 
		while(cycle < 1000 ) {
			String line = "";  
			String splitBy = ",";
			FileReader file = new FileReader("train.csv");
			Scanner fileReader = new Scanner(file);
			// BufferedReader br = new BufferedReader(file); 
			
				while (fileReader.hasNextLine())   //returns a Boolean value  
				{  
					line = fileReader.nextLine();
					String[] data = line.split(splitBy);
					
					for (i = 0; i < 10; i++) {
						outputMap[i] = 0;
					}
					
					dataSample = new int[line.split(splitBy).length - 1];
					for(i = 0; i < dataSample.length - 1; i++) {
						dataSample[i] = Integer.parseInt(data[i]);
					}
					int targetOuput = Integer.parseInt(data[64]); 
					outputMap[targetOuput] = 1;
					
					feedForward();
					
					if (testError()) {
						training();
					} else {
						success++;
					}
					
					// for(int errorIndex = 0;errorIndex < outputMap.length; errorIndex++) {
						// if(testError(outputMap[errorIndex], outputLayerValue[errorIndex])) {
							//System.out.println("no error:"+ outputMap[errorIndex] + ":"+ outputLayerValue[errorIndex]);
							// success++;
						// }else {
							// training();
						//}
					//}
					//System.out.println(Arrays.toString(outputLayerValue)); 
					noOfRows++;
				}  
			
			double accuracy = (double) success/noOfRows * 100;
			
			System.out.println("Accuracy: " + accuracy + " Success: " + success + " File Rows: " + noOfRows);
			cycle++; 
			success = 0;
			noOfRows = 0;
		} 
		}   
		catch (FileNotFoundException e){  
			e.printStackTrace();  
		}
		
	
		test();
		
	}

}
