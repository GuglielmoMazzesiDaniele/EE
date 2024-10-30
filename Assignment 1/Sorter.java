abstract class Sorter<T extends Comparable<T>> {

	protected String name = new String();

	abstract void sort(T[] items);

	String getName(){
		return name;
	}
}