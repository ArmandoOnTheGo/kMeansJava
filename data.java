public class data {
	
	int x;
	int y;
	int cluster = 0;
	
	public data(int x, int y, int cluster) {
		this.x = x;
		this.y = y;
		this.cluster = cluster;
	}
	
	public void setCluster(int num) {cluster = num;}
	
	public boolean equals(data toCheck) {
		if(this.x == toCheck.x && this.y == toCheck.y)
			return true;
		return false;
	}
}
