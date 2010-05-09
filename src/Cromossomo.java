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
	Integer [] genes = new Integer[MAX_GENES];	
	private static int bottom = -500; //limite inferior da solu��o
	private static int top = 500; //limite superior da solu��o
	private static int maskOne = Integer.parseInt("1000000000000000000000000000000", 2);
	
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
		//TODO Mudar as vari�veis genes[] para pegar o valor double que elas representam. Usar grayToDouble.
		double somatorio = 0;
		for (int i=0;i<MAX_GENES;i++) {
			somatorio =  somatorio + (-genes[i]*Math.sin(Math.sqrt(Math.abs(genes[i]))));
		}
		return 418982.9101 + somatorio;
	}
	
	
	public void mutation() {
		//TODO: implementar muta��o
		genes[0] = ((int)(Math.random()*(top-bottom+1)))+bottom;
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
	public Integer grayToBinary(Integer gray) {
		//TODO: Esta fazendo errado!!! Corrigir!
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
	
	/**
	 * Retorna o valor bin�rio do gray coding
	 * @param binary
	 * @return gray coding
	 */
	private Integer binaryToGray(Integer binary) {
		//TODO: implementar
		return 0;
	}
	
	/**
	 * Retorna diretamente o valor real (double) que o gray code representa
	 * @param gray
	 * @return
	 */
	private double grayToDouble(Integer gray) {
		//TODO: terminar metodo grayToBinary() e ent�o finalizar esse metodo aqui.
		Integer binary = grayToBinary(gray);
		
		return bottom + binary * ((top-bottom)/(Math.pow(2, 32)-1));
	}
	
    //Getters and Setters
	
	public Integer[] getCromossomo() {
		return genes;
	}

	public void setCromossomo(Integer[] cromossomo) {
		for (int i=0;i<MAX_GENES;i++) {
			this.genes[0] = cromossomo[i];
		}		
	}  

}
