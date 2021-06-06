package engine;

public class Distance {
	private String from;
	private String to;
	private int distance;
	
	public Distance(String from, String to, int distance){
		this.from = from;
		this.to = to;
		this.distance = distance;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public int getDistance() {
		return distance;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Distance other = (Distance) obj;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}
	
}
