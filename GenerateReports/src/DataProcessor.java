import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProcessor {

	/**
	 * Method to process the raw input multi-line string
	 * 
	 * @param inputString
	 * @return
	 * @throws Exception 
	 */
	public List<ProjectDetail> processData(ArrayList<String> inputString) throws Exception {
		List<ProjectDetail> projectDetails = new ArrayList<ProjectDetail>();
		String[] eachString = null;
		if(inputString != null) {
		for (String eachLine : inputString) {
			eachString = eachLine.split(",");
			if(eachLine.length() < 5 ) {
				throw new Exception();
			}
			ProjectDetail projectDetail = new ProjectDetail();
			projectDetail.setCustomerId(eachString[0]);
			projectDetail.setContractId(eachString[1]);
			projectDetail.setGeoZone(eachString[2]);
			projectDetail.setTestCode(eachString[3]);
			String durationString = eachString[5].split("s")[0];
			Double duration = Double.parseDouble(durationString);
			projectDetail.setBuildDuration(duration);
			projectDetails.add(projectDetail);
			}
		}

		return projectDetails;
	}

	/**
	 * Method to prepare the required report for contractIds, geoZones and duration.
	 * 
	 * @param projectDetails
	 * @return
	 */
	public ReportEntity prepareReport(List<ProjectDetail> projectDetails) {
		ReportEntity reports = new ReportEntity();
		Map<String, List<String>> contractDetails = new HashMap<String, List<String>>();
		Map<String, List<String>> geoZoneDetails = new HashMap<String, List<String>>();
		Map<String, List<Double>> durationforGeoZone = new HashMap<String, List<Double>>();
		for (ProjectDetail projectDetail : projectDetails) {
			reportForContractDetails(projectDetail, contractDetails);
			reportForGeoZoneDetails(projectDetail, geoZoneDetails);
			reportForBuildDurationDetails(projectDetail, durationforGeoZone);
		}
		reports.setContractDetails(contractDetails);
		reports.setGeoZoneDetails(geoZoneDetails);
		reports.setDurationforGeoZone(durationforGeoZone);

		return reports;
	}

	/**
	 * Method to get build duration for geoZones
	 * 
	 * @param projectDetail
	 * @param durationforGeoZone
	 */
	private void reportForBuildDurationDetails(ProjectDetail projectDetail,
			Map<String, List<Double>> durationforGeoZone) {
		if (durationforGeoZone.isEmpty()) {
			List<Double> buildDurations = new ArrayList<Double>();
			buildDurations.add(projectDetail.getBuildDuration());
			durationforGeoZone.put(projectDetail.getGeoZone(), buildDurations);
		} else {
			if (durationforGeoZone.get(projectDetail.getGeoZone()) != null) {
				durationforGeoZone.get(projectDetail.getGeoZone()).add(projectDetail.getBuildDuration());
			} else {
				List<Double> buildDurations = new ArrayList<Double>();
				buildDurations.add(projectDetail.getBuildDuration());
				durationforGeoZone.put(projectDetail.getGeoZone(), buildDurations);
			}
		}

	}

	/**
	 * Method to get customerIds for geoZone in the projects
	 * 
	 * @param projectDetail
	 * @param geoZoneDetails
	 */
	private void reportForGeoZoneDetails(ProjectDetail projectDetail, Map<String, List<String>> geoZoneDetails) {
		if (geoZoneDetails.isEmpty()) {
			List<String> customerIds = new ArrayList<String>();
			customerIds.add(projectDetail.getCustomerId());
			geoZoneDetails.put(projectDetail.getGeoZone(), customerIds);
		} else {
			if (geoZoneDetails.get(projectDetail.getGeoZone()) != null) {
				geoZoneDetails.get(projectDetail.getGeoZone()).add(projectDetail.getCustomerId());
			} else {
				List<String> customerIds = new ArrayList<String>();
				customerIds.add(projectDetail.getCustomerId());
				geoZoneDetails.put(projectDetail.getGeoZone(), customerIds);
			}
		}
	}

	/**
	 * Method to get customer ids for specific contractId
	 * 
	 * @param projectDetail
	 * @param contractDetails
	 */
	private void reportForContractDetails(ProjectDetail projectDetail, Map<String, List<String>> contractDetails) {
		if (contractDetails.isEmpty()) {
			List<String> customerIds = new ArrayList<String>();
			customerIds.add(projectDetail.getCustomerId());
			contractDetails.put(projectDetail.getContractId(), customerIds);
		} else {
			if (contractDetails.get(projectDetail.getContractId()) != null) {
				contractDetails.get(projectDetail.getContractId()).add(projectDetail.getCustomerId());
			} else {
				List<String> customerIds = new ArrayList<String>();
				customerIds.add(projectDetail.getCustomerId());
				contractDetails.put(projectDetail.getContractId(), customerIds);
			}
		}

	}

	/**
	 * Method to display the report
	 * 
	 * @param report
	 */
	public void displayReport(ReportEntity report) {
		Map<String, List<String>> contractDetails = report.getContractDetails();
		System.out.println("Number of unique customerId for each contractId: \n");
		for (Map.Entry<String, List<String>> contractDetail : contractDetails.entrySet()) {
			System.out.println(contractDetail.getKey() + " : " + contractDetail.getValue().size() + "\n");
		}
		System.out.println("--------------------------------------------------\n");

		Map<String, List<String>> geoZoneDetails = report.getGeoZoneDetails();
		System.out.println("Number of unique customerId for each geoZone: \n");
		for (Map.Entry<String, List<String>> geoZoneDetail : geoZoneDetails.entrySet()) {
			System.out.println(geoZoneDetail.getKey() + " : " + geoZoneDetail.getValue().size() + "\n");
		}
		System.out.println("--------------------------------------------------\n");

		Map<String, List<Double>> durationforGeoZone = report.getDurationforGeoZone();
		System.out.println("Average build duration for each geozone: \n");
		for (Map.Entry<String, List<Double>> geoZoneDetail : durationforGeoZone.entrySet()) {
			double avgBuildDuration = 0;
			for (Double buildDuration : geoZoneDetail.getValue()) {
				avgBuildDuration += buildDuration.intValue();
			}
			avgBuildDuration = avgBuildDuration / geoZoneDetail.getValue().size();
			System.out.println(geoZoneDetail.getKey() + " : " + avgBuildDuration + " seconds \n");
		}
		System.out.println("--------------------------------------------------\n");

		System.out.println("List of unique customerId for each geoZone: \n");
		for (Map.Entry<String, List<String>> geoZoneDetail : geoZoneDetails.entrySet()) {
			System.out.println(geoZoneDetail.getKey() + " : " + geoZoneDetail.getValue() + "\n");
		}
		System.out.println("--------------------------------------------------\n");
	}
}
