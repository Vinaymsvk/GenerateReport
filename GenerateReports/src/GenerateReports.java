import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenerateReports {

	public static void main(String[] args) {
		ArrayList<String> inputString = new ArrayList<String>();
        Scanner s = new Scanner(System.in);

        while (s.hasNextLine() == true){
        	String read = s.nextLine();
            if(read == null || read.isEmpty()){
                break;  
            }
            inputString.add(read);
        }
        s.close();   
        processInputData(inputString);
        
          
	}

	/**
	 * Method to call the processor for processing and generating the report
	 * 
	 * @param inputString
	 */
	private static void processInputData(ArrayList<String> inputString ) {
		DataProcessor dataProcessor = new DataProcessor();
        List<ProjectDetail> projectDetails;
		try {
			projectDetails = dataProcessor.processData(inputString);
			ReportEntity reports = dataProcessor.prepareReport(projectDetails);
			dataProcessor.displayReport(reports);
		} catch (Exception e) {
			System.err.println("Data is not in corret format");
		}
		
	}

}
