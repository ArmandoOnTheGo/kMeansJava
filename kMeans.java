import java.io.File;

public class kMeans {

	public static void main(String[] args) {
		kMeansAlgorithm test1 = new kMeansAlgorithm(new File("./src/input.txt"));
		test1.outputWrite();
	}
}