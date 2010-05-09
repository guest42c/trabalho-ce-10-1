import java.util.Random;



/**
 * Esta classe é a representação de uma solução candidata ou seja do cromossomo em si.
 * Por questões de simplicidade e abrindo mão de desempenho usaremos essa classe para 
 * definir um cromossomo e as operações válidas sobre ele.
 * 
 * Depois que o  programa estiver pronto e funcionando podemos substituir as chamadas de funções por 
 * códigos otimizados.
 * 
 * @author Guilherme
 *
 */
public class Cromossomo {
	
	private static int MAX_GENES = 10;
	int [] genes = new int[MAX_GENES];	
	private static int bottom = -500; //limite inferior da solução
	private static int top = 500; //limite superior da solução
	private static int maskOne = Integer.parseInt("01000000000000000000000000000000",2);
	
	Random rand = new Random();
	
	/**
	 * Construtores
	 * Cromossomo() gera um cromossomo aleatorio;
	 * Cromossomo(String[] solucao) gera um cromossomo a partir do parametro solução;
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
	 * Funcao de avaliação da solução candidata (cromossomo)
	 * @return fitness do cromossomo
	 */
	public double evaluation() {
		//TODO Mudar as variáveis genes[] para pegar o valor double que elas representam. Usar grayToDouble.
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
	
	/**
	 * Operação de crossover.
	 * Funcionamento: em discussão.
	 * @developer 
	 * @param partner
	 * @return
	 */
	public Cromossomo crossover(Cromossomo partner) {
		//TODO: Implementar crossover
		return partner;
	}
	
	/**
	 * A paritr do formato gray coding retorna um correspondente binário
	 * Duas versões: 
	 * 		uma passando um inteiro do gray coding
	 * 		outra a string binária que representa gray coding 
	 * @param gray
	 * @return
	 */
	public int grayToBinary(int gray) {
		boolean value;
		int maskA = Integer.parseInt("01000000000000000000000000000000",2);
		String binaryString = "";
		//TODO: if problematico (mesma solução do binaryToGray)
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
	
	/*
	public int grayToBinary(String grayString) {
		//TODO: fazer		
		return 0;
	}
	*/
	
	/**
	 * Retorna o valor binário do gray coding.
	 * @author Guilherme
	 * @param binary
	 * @return gray coding
	 */
	public int binaryToGray(Integer binary) {
		int mask = maskOne;
		int maskA = Integer.parseInt("01000000000000000000000000000000",2);
		int maskB = Integer.parseInt("00000000000000000000000000000000",2);
		String grayString = "";
		//TODO: corrigir esse if
		//Problema: mascara "10000000000000000000000000000000" não existe pq não é uma representação valida
		//Ou seja, tem que testar se primeiro bit (mais significativo é 1) 
		//se for add 1 pra String se não add 0.
		if ((mask & binary) > 0) {
			grayString = grayString + "1";
		} else {
			grayString = grayString + "0";
		}
		//FIM DO IF COM PROBLEMA
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
		//TODO: terminar metodo grayToBinary() e então finalizar esse metodo aqui.
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
