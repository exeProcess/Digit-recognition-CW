package digitrecognition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ProcessRunner {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		DataSet set = new DataSet();
		//NeuralNetwork nn;
		//NeuralNetwork nn = new NeuralNetwork();
		set.fillData();
		//nn.setPerceptron(set.getRecords());
		for(List<String> data : set.getRecords()) {
			NeuralNetwork nn = new NeuralNetwork(data);
			//System.out.println(nn.getHiddenLayer()[0]);
			/*for(int i = 0; i < nn.getHiddenLayer().length; i++) {
				for(int j = 0;j < nn.getHiddenLayer()[i].length; j++) {
					double currentWieght = nn.getHiddenLayer()[i][j];
					System.out.println(currentWieght);
				}
			}*/
			//System.out.println(data);
			nn.setOuputLayerPerceptronWeight();
		}

	}

}
