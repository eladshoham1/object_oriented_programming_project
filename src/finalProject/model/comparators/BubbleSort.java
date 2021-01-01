/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model.comparators;

import java.util.ArrayList;
import java.util.Comparator;

public class BubbleSort {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> void bubbleSort(ArrayList<T> array, Comparator c) {
		for (int i = array.size() - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (c.compare(array.get(j), array.get(j + 1)) > 0)
					swap(array, j);
			}
		}
	}

	private static <T> void swap(ArrayList<T> array, int index) {
		T temp = array.get(index);
		array.set(index, array.get(index + 1));
		array.set(index + 1, temp);
	}

}
