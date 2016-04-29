import java.util.ArrayList;
import java.util.Scanner;

public class rle {
	static ArrayList<Integer> num = new ArrayList<Integer>();
	static ArrayList<Integer> comp = new ArrayList<Integer>();
	static String compressed = "";

	// we get the bitmap index and encode it using run length encoding.
	// if for example it is 10000000, it's encoded version will be 01, the rest
	// of the 0's will be ignored.
	public static ArrayList<Integer> encode(int[] x) {
		comp.clear();
		int count = 0;
		for (int i = 0; i < x.length; i++) {
			if (x[i] == 0) {
				count++;
			}
			if (x[i] == 1) {
				Binaryform(count);
				num.size();
				for (int j = 1; j <= num.size(); j++) {
					if (j < num.size()) {
						comp.add(1);
					} else {
						comp.add(0);
					}
				}

				for (int elem : num) {
					comp.add(elem);

				}
				num.clear();
				count = 0;
			}
		}

		return comp;

	}

	private static void Binaryform(int number) {
		int remainder;

		if (number <= 1) {

			num.add(number);
			return; // KICK OUT OF THE RECURSION
		}

		remainder = number % 2;
		Binaryform(number >> 1);
		num.add(remainder);

	}
	//we decode the compressed bitmap index back to its original form so as to perform join.

	public static ArrayList<Integer> decode(ArrayList<Integer> comp2) {
		String str = "";

		ArrayList<Integer> dec = new ArrayList<Integer>();
		ArrayList<Integer> decodedBM = new ArrayList<Integer>();
		decodedBM.clear();
		int j = 0;
		for (int i = 0; i < comp2.size(); i++) {
			j++;
			if (comp2.get(i) == 0) {
				for (int x = 0; x < j; x++) {
					dec.add(comp2.get(++i));

				}
				int freq = DForm(dec);

				if (freq == 0)
					decodedBM.add(1);
				// System.out.print(1);
				while (freq != 0) {
					decodedBM.add(0);
					// System.out.print(0);
					freq--;
					if (freq == 0)
						decodedBM.add(1);
					// System.out.print(1);
				}
				dec.clear();
				str = "";
				j = 0;
			}
			if (i > comp2.size()) {
				i = comp2.size();
			}
		}
		return decodedBM;

	}

	public static int Decimalform(String str) {

		double j = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '1') {
				j = j + Math.pow(2, str.length() - 1 - i);
			}

		}
		return (int) j;
	}

	public static int DForm(ArrayList<Integer> dec) {

		double j = 0;
		for (int i = 0; i < dec.size(); i++) {
			if (dec.get(i).equals(1)) {
				j = j + Math.pow(2, dec.size() - 1 - i);
			}

		}
		return (int) j;
	}

}