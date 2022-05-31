
public class App {
	public static void main(String[] args) {
		boolean dataExist = false;
		try {

			if (!dataExist) {
				Control control = Loader.load();
			} else {

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
