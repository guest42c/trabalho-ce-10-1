
public class TrabalhoCE {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Construtor sem parametros:");
		Cromossomo cromossomo = new Cromossomo();
		System.out.println("");
		System.out.println("Construtor com parametro de solução:");
		Cromossomo cromossomo2 = new Cromossomo("1000000000000000000000000000000");
		System.out.println("");
		System.out.println("Construtor com parametro seed:");
		Cromossomo cromossomo3 = new Cromossomo(1);

	}

}
