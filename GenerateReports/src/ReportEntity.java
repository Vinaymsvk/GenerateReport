import java.util.List;
import java.util.Map;

public class ReportEntity {
	
	Map<String, List<String>> contractDetails;
	
	Map<String, List<String>> geoZoneDetails;
	
	Map<String, List<Double>> durationforGeoZone;

	public Map<String, List<String>> getContractDetails() {
		return contractDetails;
	}

	public void setContractDetails(Map<String, List<String>> contractDetails) {
		this.contractDetails = contractDetails;
	}

	public Map<String, List<String>> getGeoZoneDetails() {
		return geoZoneDetails;
	}

	public void setGeoZoneDetails(Map<String, List<String>> geoZoneDetails) {
		this.geoZoneDetails = geoZoneDetails;
	}

	public Map<String, List<Double>> getDurationforGeoZone() {
		return durationforGeoZone;
	}

	public void setDurationforGeoZone(Map<String, List<Double>> durationforGeoZone) {
		this.durationforGeoZone = durationforGeoZone;
	}

}
