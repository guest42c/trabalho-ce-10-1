import java.util.Random;



/**
 * Esta classe � a representa��o de uma solu��o candidata ou seja do cromossomo em si.
 * Por quest�es de simplicidade e abrindo m�o de desempenho usaremos essa classe para 
 * definir um cromossomo e as opera��es v�lidas sobre ele.
 * 
 * Depois que o  programa estiver pronto e funcionando podemos substituir as chamadas de fun��es por 
 * c�digos otimizados.
 * 
 * @author Guilherme
 *
 */
public class Cromossomo {
	
	private static int MAX_GENES = 10;
	int [] genes = new int[MAX_GENES];	
	private static int bottom = -500; //limite inferior da solu��o
	private static int top = 500; //limite superior da solu��o
	private static int maskOne = Integer.parseInt("01000000000000000000000000000000",2);
	
	Random rand = new Random();
	
	/**
	 * Construtores
	 * Cromossomo() gera um cromossomo aleatorio;
	 * Cromossomo(String[] solucao) gera um cromossomo a partir do parametro solu��o;
	 * Cromossomo(long seed) gera um cromossomo aleatorio a partir de um seed passado como parametro;
	 * 
	 */
	public Cromossomo() {
		for (int i=0;i<MAX_GENES;i++) {
			genes[i] = rand.nextInt();
		}
	}
	
	public Cromossomo(String[] solucao) {
		for (int i=0;i<MAX_GENES;i++) {
			genes[i] = Integer.parseInt(solucao[i],2);
		}
	}
	
	public Cromossomo(long seed) {
		rand.setSeed(seed);
		for (int i=0;i<MAX_GENES;i++) {
			genes[i] = rand.nextInt();
		}	
	}
	
	/**
	 * Funcao de avalia��o da solu��o candidata (cromossomo)
	 * @return fitness do cromossomo
	 */
	public double evaluation() {
		//TODO: esta apresentando problema na chamada grayToDouble que leva at� grayToBinary
		double somatorio = 0;
		for (int i=0;i<MAX_GENES;i++) {
			somatorio =  somatorio + 
				(-grayToDouble(genes[i])*Math.sin(Math.sqrt(Math.abs(grayToDouble(genes[i])))));
		}
		return 418982.9101 + somatorio;
	}
		
	public void mutation() {
		int value = ((int)(Math.random()*(top-bottom+1)))+bottom;
		int gene = ((int)(Math.random()*(MAX_GENES)));
		genes[gene] = value;
	}
	
	/**
	 * Opera��o de crossover.
	 * Funcionamento: em discuss�o.
	 * @developer 
	 * @param partner
	 * @return
	 */
	public Cromossomo crossover(Cromossomo partner) {
		//TODO: Implementar crossover
		return partner;
	}
	
	/**
	 * A paritr do formato gray coding retorna um correspondente bin�rio
	 * Duas vers�es: 
	 * 		uma passando um inteiro do gray coding
	 * 		outra a string bin�ria que representa gray coding 
	 * @param gray
	 * @return
	 */
	public int grayToBinary(int gray) {
		boolean value;
		int maskA = Integer.parseInt("01000000000000000000000000000000",2);
		String binaryString = "";
		//TODO: if problematico (mesma solu��o do binaryToGray)
		if (true) {
			binaryString = "0";
			value = false;
		}
		//FIM da parte com problema com problema
		for (int i=1;i<=31;i++) {
			if ((maskA & gray) > 0) 
				value = !value;
			if (value) {
				binaryString = binaryString + "1";
			} else {
				binaryString = binaryString + "0";
			}
			maskA = maskA >> 1;
		}
		return Integer.parseInt(binaryString, 2);
	}
	
	
	public int grayToBinary(String grayString) {
		boolean value;
		int maskA = Integer.parseInt("01000000000000000000000000000000",2);
		int gray = Integer.parseInt(grayString, 2);
		String binaryString = "";
		//TODO: if problematico (mesma solu��o do binaryToGray)
		if (true) {
			binaryString = "0";
			value = false;
		}
		//FIM da parte com problema com problema
		for (int i=1;i<=31;i++) {
			if ((maskA & gray) > 0) 
				value = !value;
			if (value) {
				binaryString = binaryString + "1";
			} else {
				binaryString = binaryString + "0";
			}
			maskA = maskA >> 1;
		}
		return Integer.parseInt(binaryString, 2);
	}
	
	
	/**
	 * Retorna o valor bin�rio do gray coding.
	 * @author Guilherme
	 * @param binary
	 * @return gray coding
	 */
	public int binaryToGray(Integer binary) {
		int mask = maskOne;
		int maskA = Integer.parseInt("01000000000000000000000000000000",2);
		int maskB = Integer.parseInt("00000000000000000000000000000000",2);
		String grayString = "";
		if (((1 << 31) & binary) != 0) {
			grayString = grayString + "1";
		} else {
			grayString = grayString + "0";
		}
		for (int i=1;i<=31;i++) {
			if (((maskA & binary) ^ (maskB & binary))  > 0) 
				grayString = grayString + "1";
			else 
				grayString = grayString + "0";
			maskB = maskA;
			maskA = mask >> i;
		}
		return Integer.parseInt(grayString, 2);
	}
	
	/**
	 * Retorna diretamente o valor real (double) que o gray code representa
	 * @param gray
	 * @return
	 */
	private double grayToDouble(Integer gray) {
		Integer binary = grayToBinary(gray);		
		return bottom + binary * ((top-bottom)/(Math.pow(2, 32)-1));
	}
	
    //Getters and Setters	
	public int[] getCromossomo() {
		return genes;
	}

	public void setCromossomo(int[] cromossomo) {
		for (int i=0;i<MAX_GENES;i++) {
			this.genes[0] = cromossomo[i];
		}		
	}  
}
