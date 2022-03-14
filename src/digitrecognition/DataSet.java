package digitrecognition;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataSet {
	private List<List<String>> records = new ArrayList<>();
	

	public List<List<String>> getRecords() {
		return records;
	}



	public void fillData(){
		
		try (BufferedReader br = new BufferedReader(new FileReader("c://cw2DataSet1.csv"))) {
		    String line;
		    String COMMON_DELIMETER = ",";
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(COMMON_DELIMETER);
		        records.add(Arrays.asList(values));
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}
