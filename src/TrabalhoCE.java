
public class TrabalhoCE {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		System.out.println("Construtor com parametro de solução:");
		
		String[] candidato = new String[10];
		for (int i=0;i<10;i++) {
			candidato[i] = "0000000000000000000000000000000";
		}
		Cromossomo cromossomo = new Cromossomo(candidato);
		System.out.println(cromossomo.evaluation() + "\n");
		
		Cromossomo cromossomo2 = new Cromossomo();
		System.out.println(cromossomo2.evaluation() + "\n");	
		
		String grayString = "00000000000000000000000000000101";
		int gray = Integer.parseInt(grayString,2);
		int binary = cromossomo.grayToBinary(gray);
		System.out.println("Gray  : " + grayString);
		String binaryString = Integer.toBinaryString(binary);
		while (binaryString.length() < 32) {
			binaryString = "0" + binaryString;
		}
		System.out.println("Binary: " + binaryString);

	}

}
