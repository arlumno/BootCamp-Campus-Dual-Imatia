package pruebas;

public class Main {
	public static void main(String[] args) {
		String a = new String("a");
		String b = new String("a");
		String n = null;

		System.out.println(a.equals(b));
		System.out.println(a.compareTo(b) == 0);
		System.out.println(a.equalsIgnoreCase(b));
	}
}
