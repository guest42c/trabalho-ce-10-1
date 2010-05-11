
public class TrabalhoCE {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		System.out.println("Construtor com parametro de solução:");
		
		String[] candidato = new String[10];
		for (int i=0;i<10;i++) {
			candidato[i] = "11111100111111111111111";
		}
		Cromossomo cromossomo = new Cromossomo(candidato);
		System.out.println("Fitness cromosso instanciado a partir do parametro: " + cromossomo.evaluation() + "\n");
		
		Cromossomo cromossomo2 = new Cromossomo();
		System.out.println("Fitness inicial do cromossomo aleatório: " + cromossomo2.evaluation() + "\n");
		cromossomo2.mutation();
		System.out.println("Fitness após mutação: " + cromossomo2.evaluation() + "\n");
		
//		String grayString = "00000000000000000000000000000101";
//		int gray = Integer.parseInt(grayString,2);
//		int binary = cromossomo.grayToBinary(gray);
//		System.out.println("Gray  : " + grayString);
//		String binaryString = Integer.toBinaryString(binary);
//		while (binaryString.length() < 32) {
//			binaryString = "0" + binaryString;
//		}
//		System.out.println("Binary: " + binaryString);
//		
//		System.out.println();
//		
//		String binaryString2 = "01000000000000000000000000000110";
//		int binary2 = Integer.parseInt(binaryString2,2);
//		int gray2 = cromossomo.binaryToGray(binary2);
//		System.out.println("Binary: " + binaryString2);
//		String grayString2 = Integer.toBinaryString(gray2);
//		while (grayString2.length() < 32) {
//			grayString2 = "0" + grayString2;
//		}
//		System.out.println("Gray  : " + grayString2);
		
		//Testa retorno das funções
//		String valorString1 = "00000000000000000000000000000011";
//		int valorInt1 = Integer.parseInt(valorString1, 2);
//		String valorString2 = "11000000000000000000000000000011";
//		int valorInt2 = Integer.parseInt(valorString2.substring(1,32), 2);
//		Integer valorIntAnd = valorInt1 & valorInt2;
//		Integer valorIntOr = valorInt1 | valorInt2;
//		System.out.println("Valores\n" + valorString1 + " == " + valorInt1 + "\n"
//				+ valorString2 + " == " + valorInt2 + "\n" + 
//				"And: " + valorIntAnd.toBinaryString(valorIntAnd) + " == " + valorIntAnd.intValue() + "\n" + 
//				"Or: " + valorIntOr.toBinaryString(valorIntOr) + " == " + valorIntOr.intValue());
		
	}

}
