package utils;

public class Time {
	private Integer startingHour, endingHour;

	public Time(int startingHour, int endingHour) {
		// TODO Auto-generated constructor stub
		this.startingHour = startingHour;
		this.endingHour = endingHour;
	}
	
	public Integer getStartingHour() {
		return startingHour;
	}

	public void setStartingHour(Integer startingHour) {
		this.startingHour = startingHour;
	}

	public Integer getEndingHour() {
		return endingHour;
	}

	public void setEndingHour(Integer endingHour) {
		this.endingHour = endingHour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endingHour == null) ? 0 : endingHour.hashCode());
		result = prime * result + ((startingHour == null) ? 0 : startingHour.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Time other = (Time) obj;
		if (endingHour == null) {
			if (other.endingHour != null)
				return false;
		} else if (!endingHour.equals(other.endingHour))
			return false;
		if (startingHour == null) {
			if (other.startingHour != null)
				return false;
		} else if (!startingHour.equals(other.startingHour))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return startingHour + "-" + endingHour;
	}
	
}
