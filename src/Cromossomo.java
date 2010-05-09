import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;



/**
 * Esta classe é a representação de uma solução candidata ou seja do cromossomo em si.
 * Por questões de simplicidade e abrindo mão de desempenho usaremos essa classe para definir um cromossomo e
 * as operações válidas sobre ele.
 * 
 * Depois que o  programa estiver pronto e funcionando podemos substituir as chamadas de funções por 
 * códigos otimizados.
 * 
 * @author Guilherme
 *
 */
public class Cromossomo {
	
	private static int MAX_GENES = 10;
	Integer [] genes = new Integer[MAX_GENES];	
	private static int bottom = -500; //limite inferior da solução
	private static int top = 500; //limite superior da solução
	private static int maskOne = Integer.parseInt("1000000000000000000000000000000", 2);
	
	Random rand = new Random();
	
	public Cromossomo() {
		//TODO: Implementar construtor randomico (gerar uma solução randomica)
		for (int i=0;i<MAX_GENES;i++) {
			genes[i] = rand.nextInt();
		}
		
		String binaryString = Integer.toBinaryString(genes[0]);
		
		//Removendo o bit mais significativo
		//não sei pq ta dando problema, para outras funções só aceita 31bits
		//bit de sinal????
//		binaryString = binaryString.substring(1, binaryString.length());
//		Integer grayCoding = Integer.parseInt(binaryString,2);
//		System.out.println("Representação: " + binaryString);
//		System.out.println("Valor gray coding: " + grayCoding);
//		System.out.println("Valor inteiro: " + grayToBinary(grayCoding));
	}
	
	public Cromossomo(String[] solucao) {
		//TODO: Implementar construtor deterministico (gerar uma solução indicada)
		for (int i=0;i<MAX_GENES;i++) {
			genes[i] = Integer.parseInt(solucao[i],2);
		}
		String binaryString = Integer.toBinaryString(genes[0]);
		//Removendo o bit mais significativo
		//não sei pq ta dando problema, para outras funções só aceita 31bits
		//bit de sinal????
//		binaryString = binaryString.substring(1, binaryString.length());
//		Integer grayCoding = Integer.parseInt(binaryString,2);
//		System.out.println("Representação gray coding: " + binaryString);
//		System.out.println("Valor gray coding: " + grayCoding);
//		System.out.println("Representação inteiro: " + Integer.toBinaryString(grayToBinary(grayCoding)));
//		System.out.println("Valor inteiro: " + grayToBinary(grayCoding));
	}
	
	public Cromossomo(long seed) {
		//TODO: Implementar construtor passando seed
		rand.setSeed(seed);

		for (int i=0;i<MAX_GENES;i++) {
			genes[i] = rand.nextInt();
		}
		
		String binaryString = Integer.toBinaryString(genes[0]);
		//Removendo o bit mais significativo
		//não sei pq ta dando problema, para outras funções só aceita 31bits
		//bit de sinal????
//		binaryString = binaryString.substring(1, binaryString.length());
//		Integer grayCoding = Integer.parseInt(binaryString,2);
//		System.out.println("Representação gray coding: " + binaryString);
//		System.out.println("Valor gray coding: " + grayCoding);
//		System.out.println("Representação inteiro: " + Integer.toBinaryString(grayToBinary(grayCoding)));
//		System.out.println("Valor inteiro: " + grayToBinary(grayCoding));
	}
	
	public double evaluation() {
		double somatorio = 0;
		for (int i=0;i<MAX_GENES;i++) {
			somatorio =  somatorio + (-genes[i]*Math.sin(Math.sqrt(Math.abs(genes[i]))));
		}
		return 418982.9101 + somatorio;
	}
	
	public void mutation() {
		//TODO: implementar mutação
		genes[0] = ((int)(Math.random()*(top-bottom+1)))+bottom;
	}
	
	public Cromossomo crossover(Cromossomo partner) {
		//TODO: Implementar crossover
		return partner;
	}
	
	public Integer grayToBinary(Integer gray) {
		int mask = maskOne;
		String binaryString = "";
		if ((mask & gray) > 0) {
			binaryString = binaryString + "1";
		}
		for (int i=1;i<31;i++) {
			int maskA = mask >> i;
			int maskB = mask >> (i-1);
			if (((maskA & gray) ^ (maskB & gray))  > 0) 
				binaryString = binaryString + "1";
			else 
				binaryString = binaryString + "0"; 
		}
		return Integer.parseInt(binaryString, 2);
	}
	
	public Integer grayToBinary(String grayString) {
		int mask = maskOne;
		int maskA,maskB;
		Integer gray = Integer.parseInt(grayString,2);
		String binaryString = "";
		if ((mask & gray) > 0) {
			binaryString = binaryString + "1";
		} else {
			binaryString = binaryString + "0";
		}
		for (int i=1;i<32;i++) {
			maskA = mask >> i;
			maskB = mask >> (i-1);
			if (((maskA & gray) ^ (maskB & gray))  > 0) 
				binaryString = binaryString + "1";
			else 
				binaryString = binaryString + "0"; 
		}
		return Integer.parseInt(binaryString, 2);
	}
	
	private Integer binaryToGray(Integer binary) {
		return 0;
	}
	
	private double grayToDouble(Integer gray) {
		Integer binary = grayToBinary(gray);
		
		return bottom + binary * ((top-bottom)/(Math.pow(2, 32)-1));
	}
	
	public String getStringRepresentation() {
		//31Bits
		return Integer.toBinaryString(genes[0]);
	}
	
	public double getDoubleRepresentation(Integer cromossomo) {
		return grayToDouble(cromossomo);
	}
	
    //Getters and Setters
	
	public Integer getCromossomo() {
		return genes[0];
	}

	public void setCromossomo(Integer cromossomo) {
		this.genes[0] = cromossomo;
	}  

}
