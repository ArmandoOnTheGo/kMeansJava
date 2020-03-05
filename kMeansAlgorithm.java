import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class kMeansAlgorithm {

	File file;
	ArrayList<data> fromFile;
	ArrayList<data> cluster1;
	ArrayList<data> cluster2;
	ArrayList<data> cluster3;
	ArrayList<data> cluster4;
	ArrayList<data> output;
	int k;
	data seed1, seed2, seed3, seed4;
	int TotalX1 = 0, TotalX2 = 0, TotalX3 = 0, TotalX4 = 0;
	int TotalY1 = 0, TotalY2 = 0, TotalY3 = 0, TotalY4 = 0;
	double distance1;
	double distance2;
	double distance3;
	double distance4;
	data temp1, temp2, temp3, temp4;
	boolean done;

	public kMeansAlgorithm(File file) {
		this.file = file;
		fromFile = new ArrayList<data>();
		cluster1 = new ArrayList<data>();
		cluster2 = new ArrayList<data>();
		
		//Reading from the file and calculating k and loading data values
		try {
			Scanner scanner = new Scanner(file);
			String s = scanner.nextLine();
			String clean = s.replaceAll("\\D+", "");
			k = (Integer.parseInt(clean)) % 10;
			scanner.nextLine();
			while (scanner.hasNext()) {
				s = scanner.nextLine();
				String[] line = s.split("\\t+");
				fromFile.add(new data(Integer.valueOf(line[0]), Integer.valueOf(line[1]), 0));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		runAlgorithm();
	}

	public void runAlgorithm() {
//		ArrayList<data> seeds = new ArrayList<data>(); 
//		data temp;
		Random rand = new Random();
		
//		for(int i = 1; i <= k; i++) {
//			do{
//				temp = fromFile.get(rand.nextInt(fromFile.size()));
//			} while(seeds.contains(temp));
//			seeds.add(temp);
//		}
		
		seed1 = fromFile.get(rand.nextInt(fromFile.size()));
		do {
			seed2 = fromFile.get(rand.nextInt(fromFile.size()));
		} while (seed1 == seed2);

		if (k >= 3) {
			do {
				seed3 = fromFile.get(rand.nextInt(fromFile.size()));
			} while (seed3 == seed2 || seed3 == seed2);
		}

		if (k == 4) {
			do {
				seed4 = fromFile.get(rand.nextInt(fromFile.size()));
			} while (seed4 == seed2 || seed4 == seed2 || seed4 == seed3);
		}

		do {
			switch (k) {
			case 2:
				createClusters2();
				break;
			case 3:
				createClusters3();
				break;
			case 4:
				createClusters4();
				break;
			}

			for (data Node1 : cluster1) {
				TotalX1 += Node1.x;
				TotalY1 += Node1.y;
			}

			temp1 = new data((TotalX1 / cluster1.size()), (TotalY1 / cluster1.size()), 0);

			for (data Node2 : cluster2) {
				TotalX2 += Node2.x;
				TotalY2 += Node2.y;
			}

			temp2 = new data((TotalX2 / cluster2.size()), (TotalY2 / cluster2.size()), 0);

			if (k >= 3) {
				for (data Node3 : cluster3) {
					TotalX3 += Node3.x;
					TotalY3 += Node3.y;
				}
				temp3 = new data((TotalX3 / cluster3.size()), (TotalY3 / cluster3.size()), 0);
			}

			if (k == 4) {
				for (data Node4 : cluster4) {
					TotalX4 += Node4.x;
					TotalY4 += Node4.y;
				}

				temp4 = new data((TotalX4 / cluster4.size()), (TotalY4 / cluster4.size()), 0);
			}
			
			switch (k) {
			case 2:
				finished2();
				break;
			case 3:
				finished3();
				break;
			case 4:
				finished4();
				break;
			}
			
		} while (!done);
	}
	


	private void createClusters2() {
		for (data Node : fromFile) {

			distance1 = Math.sqrt((Math.pow((Node.x - seed1.x), 2)) + Math.pow((Node.y - seed1.y), 2));

			distance2 = Math.sqrt((Math.pow((Node.x - seed2.x), 2)) + Math.pow((Node.y - seed2.y), 2));

			if (distance1 <= distance2)
				cluster1.add(new data(Node.x, Node.y, 1));
			else
				cluster2.add(new data(Node.x, Node.y, 2));
		}
	}

	private void createClusters3() {
		cluster3 =  new ArrayList<data>();
		
		for (data Node : fromFile) {

			distance1 = Math.sqrt((Math.pow((Node.x - seed1.x), 2)) + Math.pow((Node.y - seed1.y), 2));
			distance2 = Math.sqrt((Math.pow((Node.x - seed2.x), 2)) + Math.pow((Node.y - seed2.y), 2));
			distance3 = Math.sqrt((Math.pow((Node.x - seed3.x), 2)) + Math.pow((Node.y - seed3.y), 2));

			if (distance1 <= distance2 && distance1 <= distance3)
				cluster1.add(new data(Node.x, Node.y, 1));
			else if (distance2 <= distance1 && distance2 <= distance3)
				cluster2.add(new data(Node.x, Node.y, 2));
			else
				cluster3.add(new data(Node.x, Node.y, 3));
		}
	}

	private void createClusters4() {
		cluster3 = new ArrayList<data>();
		cluster4 = new ArrayList<data>();
		
		for (data Node : fromFile) {
//			distance1 = Math.sqrt((Math.pow((Node.x - seed1.x), 2)) + Math.pow((Node.y - seed1.y), 2));
//			distance2 = Math.sqrt((Math.pow((Node.x - seed2.x), 2)) + Math.pow((Node.y - seed2.y), 2));
//			distance3 = Math.sqrt((Math.pow((Node.x - seed3.x), 2)) + Math.pow((Node.y - seed3.y), 2));
//			distance4 = Math.sqrt((Math.pow((Node.x - seed4.x), 2)) + Math.pow((Node.y - seed4.y), 2));

			if (distance1 <= distance2 && distance1 <= distance3 && distance1 <= distance4)
				cluster1.add(new data(Node.x, Node.y, 1));
			else if (distance2 <= distance1 && distance2 <= distance3 && distance2 <= distance4)
				cluster2.add(new data(Node.x, Node.y, 2));
			else if (distance3 <= distance1 && distance3 <= distance2 && distance3 <= distance4)
				cluster3.add(new data(Node.x, Node.y, 3));
			else
				cluster4.add(new data(Node.x, Node.y, 4));
		}
	}
	
	private void finished2() {
		if (seed1.x == temp1.x && seed1.y == temp1.y && seed2.x == temp2.x && seed2.y == temp2.y)
			done = true;

		else {
			seed1 = temp1;
			seed2 = temp2;
			cluster1.clear();
			cluster2.clear();
			TotalX1 = 0;
			TotalY1 = 0;
			TotalX2 = 0;
			TotalY2 = 0;
		}
	}
	
	private void finished3() {
		if (seed1.x == temp1.x && seed1.y == temp1.y && seed2.x == temp2.x && seed2.y == temp2.y
				&& seed3.x == temp3.x && seed3.y == temp3.y)
			done = true;

		else {
			seed1 = temp1;
			seed2 = temp2;
			seed3 = temp3;
			cluster1.clear();
			cluster2.clear();
			cluster3.clear();
			TotalX1 = 0;
			TotalY1 = 0;
			TotalX2 = 0;
			TotalY2 = 0;
			TotalX3 = 0;
			TotalY3 = 0;
		}
	}
	
	private void finished4() {
		if (seed1.x == temp1.x && seed1.y == temp1.y && seed2.x == temp2.x && seed2.y == temp2.y
				&& seed3.x == temp3.x && seed3.y == temp3.y && seed4.x == temp4.x && seed4.y == temp4.y)
			done = true;

		else {
			seed1 = temp1;
			seed2 = temp2;
			seed3 = temp3;
			seed4 = temp4;
			cluster1.clear();
			cluster2.clear();
			cluster3.clear();
			cluster4.clear();
			TotalX1 = 0;
			TotalY1 = 0;
			TotalX2 = 0;
			TotalY2 = 0;
			TotalX3 = 0;
			TotalY3 = 0;
			TotalX4 = 0;
			TotalY4 = 0;
		}
	}

	public void outputWrite() {
		output = new ArrayList<data>();
		
		if (k >= 2) {
			for (data c1 : cluster1)
				output.add(c1);
			for (data c2 : cluster2)
				output.add(c2);
		}
		if (k >= 3) {
			for (data c3 : cluster3)
				output.add(c3);
		}
		if (k == 4)
			for (data c4 : cluster4)
				output.add(c4);
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("./src/output.txt"));
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(new File("./src/output.txt"), true));

			for (data write : output)
				out.write(write.x + "\t" + write.y + "\t" + write.cluster + "\n");

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}