import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class InsertionSort {

	private static void log(String aMessage) {
		System.out.println(aMessage);
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int nosElement, keyValue, emptySpace;
		log("Enter the number of elements : ");
		
		nosElement = Integer.parseInt(br.readLine());
		
		Random randomGenerator = new Random();
		int[] elementsArray = new int[nosElement];
		for (int countEntered = 0; countEntered < nosElement; ++countEntered) {
			int randomInt = randomGenerator.nextInt(100000);
			elementsArray[countEntered] = randomInt;
			System.out.printf("Element [%d] : %d \n", countEntered,
					elementsArray[countEntered]);
		}
		log("Sorted array after insertion sort is as follows : ");
		
		for (int j = 1; j < elementsArray.length; j++) {
			keyValue = elementsArray[j];
			emptySpace = j;
			while (emptySpace > 0 && elementsArray[emptySpace - 1] > keyValue) {
				elementsArray[emptySpace] = elementsArray[emptySpace - 1];
				emptySpace = emptySpace - 1;
			}
			elementsArray[emptySpace] = keyValue;
		}
		for (int countEntered = 0; countEntered < nosElement; countEntered++) {
			log("" + elementsArray[countEntered]);
		}

	}
}
