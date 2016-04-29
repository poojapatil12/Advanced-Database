import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class mainClass {
	static Map<String, BitSet> distinct_val = new HashMap<String, BitSet>();
	static LinkedList<DEP> input1 = new LinkedList<DEP>();
	static LinkedList<DEP> input2 = new LinkedList<DEP>();
	static ArrayList<String> inputj = new ArrayList<String>();
	static int count = 0;
	static String path;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long startTime = System.currentTimeMillis();
		path = "C:\\files\\aaaT1.txt";
		int x = 1000;
		// we pass the file path and the number of records to be read from the
		// file
		// as we read we get the different departments and their occurrences
		// stored in a BitSet
		readLine(path, x);
		// after reading the file and getting a Bitset of each department, we
		// change it to a Bitmap index and encode/compress it.
		getBMindex();
		// we clear the map storing the Bitset for the first file so as to use
		// it for the second file.
		distinct_val.clear();
		x = 1000;
		path = "C:\\files\\aaaT2.txt";
		// we pass the file path and the number of records to be read from the
		// file
		// as we read we get the different departments and their occurrences
		// stored in a BitSet
		readLine(path, x);
		// after reading the file and getting a Bitset of each department, we
		// change it to a Bitmap index and encode/compress it.
		getBMindex();
		distinct_val.clear();
		// we match the departments from file 1 and file 2
		// for every department we get its compressed bitmap index and
		// decode/uncompress it
		// join the two departments using their bitmap idexes respectively.
		// we compare the results gotten with the product of cardinalities of
		// each department stored before
		Map<String, BigInteger> depts = new HashMap<String, BigInteger>();
		File file = File.createTempFile("join", ".txt", new File("c:/files/temp"));
		FileWriter fw = new FileWriter(file.getAbsolutePath());
		BufferedWriter bw = new BufferedWriter(fw);
		for (int j = 0; j < input1.size(); j++) {
			for (int i = 0; i < input2.size(); i++) {
				if (input2.get(i).depID.equals(input1.get(j).depID)) {

					// System.out.println(input1.get(i).depID+"    "+input2.get(j).depID);
					BigInteger check = BigInteger.valueOf(
							input1.get(j).cardinality).multiply(
							BigInteger.valueOf(input2.get(i).cardinality));

					BigInteger check1 = BigInteger
							.valueOf(input1.get(j).cardinality);
					BigInteger check2 = BigInteger
							.valueOf(input2.get(i).cardinality);
					depts.put(input2.get(i).depID, check);
					//System.out.println(check);

					System.out.println("Input 1   " + input1.get(j).depID
							+ "  input 2 " + input2.get(i).depID);

					join.jointables(rle.decode(input1.get(j).BMindex),
							rle.decode(input2.get(i).BMindex), check, bw);

				}
			}

		}bw.close();
		System.out
				.println("Enter the department name to check number of records");
		String dept_check = br.readLine();
		for (String key : depts.keySet()) {
			if (key.equalsIgnoreCase(dept_check)) {
				System.out.println("Number of records for dept " + dept_check
						+ "  are: " + depts.get(key));
			}
		}
		long endTime = System.currentTimeMillis();
		System.out
				.println("It took " + (endTime - startTime) + " milliseconds");

	}

	private static void getBMindex() throws IOException {
		// TODO Auto-generated method stub
		int bm[] = new int[count];
		long total = 0;
		long total1 = 0;
		for (String key : distinct_val.keySet()) {
			String bitmap = "";
			for (int i = 0; i < count; i++) {
				if (distinct_val.get(key).get(i) == false) {

					bm[i] = 0;
				} else {

					bm[i] = 1;
				}
			}
			// we pass the bitmap index to the method encode so as to get a
			// compressed data that takes less space to store.
			ArrayList<Integer> encoded = rle.encode(bm);

			// we store the department, compressed bitmap and its
			// cardinality(number of departments appearance) in input1 and
			// input2 according to which file it is from.
			if (path.equals("C:\\files\\aaaT1.txt")) {
				input1.add(new DEP(key, new ArrayList<Integer>(encoded),
						distinct_val.get(key).cardinality()));
				total = total + encoded.size();
				// System.out.println("total space"+total);

			} else {
				input2.add(new DEP(key, new ArrayList<Integer>(encoded),
						distinct_val.get(key).cardinality()));
				total1 = total1 + encoded.size();
				// System.out.println("total space for table 2"+total);
				// System.out.println("Uncompressed  "+bm.length+" compressed  "+encoded.size());
			}
			encoded.clear();

		}
		// this is the total number of bits used to store the compressed bitmap index. 8 bits is 1 byte is our assumption. 
		System.out.println("the total bits used:  "+total+"  total space for table 1 in bytes:  " + total
				/8);
		System.out.println("the total bits used:  "+total1+"total space for table 2 in bytes:   " + total1
				/8);
		/*
		 * for (int i=0; i<input1.size(); i++){ for (int j=0; j<input2.size();
		 * j++){ if(input1.get(i).depID.equalsIgnoreCase(input2.get(j).depID)){
		 * System.out.println(input1.get(i).depID+"    "+input2.get(j).depID);
		 * System
		 * .out.println(input1.get(i).cardinality*input2.get(j).cardinality); }
		 * 
		 * } }
		 */
	}

	private static void readLine(String path, int x) throws IOException {
		File f = new File(path);
		@SuppressWarnings("resource")
		BufferedReader fr = new BufferedReader(new FileReader(f));

		BitSet k = new BitSet();
		count = 0;
		while (count < x) {
			String s = fr.readLine();
			String temp = s.substring(17, 20);
			if (distinct_val.containsKey(temp)) {
				BitSet g = distinct_val.get(temp);
				g.set(count);
				distinct_val.put(temp, g);
			} else {
				k = new BitSet();
				k.set(count);
				distinct_val.put(temp, k);
			}
			count++;
		}
		fr.close();

	}

}
