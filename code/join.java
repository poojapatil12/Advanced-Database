import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class join {
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		// jointables();

	}

	// the bitmap indexes are passed into this method to be join. the loops run
	// as long as the bitmap indexes. 0's are discarded at the end of the bitmap
	// indexes to make the loops terminate faster. eg. 10010101000000 will be
	// 10010101. each time there is a 1, we get the record from the file at that
	// position and join it with the other record from the other file with the same department.
	static void jointables(ArrayList<Integer> arrayList,
			ArrayList<Integer> arrayList2, BigInteger check, BufferedWriter bw) throws IOException {
		long n = 0;
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).equals(1)) {
				for (int j = 0; j < arrayList2.size(); j++) {
					if (arrayList2.get(j).equals(1)) {
						//bw.write(readfile(i)+readfile2(j).substring(0, 17)+readfile2(j).substring(20, 99));
						//bw.write("\r\n");
						++n;
					}
				}
			}
		}
		// we check if the number of join result for each department is equal to
		// what we had calculated before using their cardinalities.
		//System.out.println(check==BigInteger.valueOf(n));
		//System.out.println(BigInteger.valueOf(n).equals(check));
		//System.out.println(check);
		if (BigInteger.valueOf(n).equals(check)) {
			System.out
					.println("Information stored in part 2 is checked with join result ");
			System.out.println("According to calculation number of records "
					+ check);
			System.out.println("after join number of records " + n);
			System.out.println("They both are same");
			System.out.println();
		}

	}

	private static String readfile2(int i) throws IOException {
		// TODO Auto-generated method stub
		String filename = "c:/files/aaaT2.txt";
		FileReader input = new FileReader(filename);
		BufferedReader data2 = new BufferedReader(input);
		String first = "";
		data2.skip((100) * i);
		first = data2.readLine();
		data2.close();
		return first;
	}

	private static String readfile(int i) throws IOException {
		// TODO Auto-generated method stub
		String filename = "c:/files/aaaT1.txt";
		FileReader input = new FileReader(filename);
		BufferedReader data = new BufferedReader(input);
		String first = "";
		data.skip((100) * i);
		first = data.readLine();
		data.close();
		return first;

	}

}
