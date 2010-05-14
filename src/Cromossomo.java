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
	private static int bottom = -600; //limite inferior da solu��o
	private static int top = 600; //limite superior da solu��o
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
			String bitString = solucao[i];
			while (bitString.length() < 32) {
				bitString = "0" + bitString; 
			}
			genes[i] = Integer.parseInt(bitString.substring(1,32),2);
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
		double somatorio = 0;
		for (int i=0;i<MAX_GENES;i++) {
			somatorio =  somatorio + 
				(-grayToDouble(genes[i])*Math.sin(Math.sqrt(Math.abs(grayToDouble(genes[i])))));
		}
		return 418982.9101 + somatorio;
	}
	
	public double evaluation2() {
		double produtorio = 1;
		double somatorio = 0;
		for (int i=0;i<MAX_GENES;i++) {
			somatorio = somatorio + (Math.pow(grayToDouble(genes[i]),2)/4000);
			produtorio = produtorio * Math.cos(grayToDouble(genes[i])/Math.sqrt(i));
		}
		return (1+somatorio-produtorio);
	}
		
	public void mutation(double probabilidade) {
		for (int gene=0;gene<MAX_GENES;gene++) {
			int value;
			if (Math.random() > probabilidade) {
				//((int)(Math.random()*(top-bottom+1)))+bottom;
				int shift = (int) Math.floor(Math.random()*32);
				int mascaraMutacao = 1;
				mascaraMutacao = mascaraMutacao << shift;
				value = genes[gene] ^ mascaraMutacao; 
				genes[gene] = value;
			}			
		}
		
	}
	
	public int mascaraCrossover(){
		
		int corte = Math.abs((rand.nextInt()%29)+1);
		String mascaraTemp = new String();
		for (int i = 31; i >= 0; i--){
			if (i <= corte){
				mascaraTemp += "1";
			}
			else
				mascaraTemp += "0";
		}
		return Integer.parseInt(mascaraTemp,2);
		
	}
	
	/**
	 * Opera��o de crossover.
	 * Funcionamento: em discuss�o.
	 * @developer 
	 * @param partner
	 * @return
	 */
	public Cromossomo crossover(Cromossomo partner) {
		//TODO: Implementar crossover com mascara aleatoria
		
		int mascara	= mascaraCrossover();
		
		int [] heranca1 = new int[MAX_GENES];
		int [] heranca2 = new int[MAX_GENES];
		for (int i=0;i<MAX_GENES;i++) {
			heranca1[i] = genes[i] & mascara;
			heranca2[i] = partner.genes[i] & ~mascara;
			heranca1[i] = genes[i] & ~mascara;
			heranca2[i] = partner.genes[i] & mascara;
		}
		int combinacao;
		String [] genesFilho = new String[MAX_GENES];
		for (int i=0;i<MAX_GENES;i++) {
			combinacao = heranca1[i] | heranca2[i];
			genesFilho[i] = Integer.toBinaryString(combinacao);
		}
		Cromossomo filho = new Cromossomo(genesFilho);		
		return filho;
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
		binaryString = "0";
		value = false;
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
		return Integer.parseInt(binaryString.substring(1,32), 2);
	}
	
	
	public int grayToBinary(String grayString) {
		boolean value;
		int maskA = Integer.parseInt("01000000000000000000000000000000",2);
		int gray = Integer.parseInt(grayString, 2);
		String binaryString = "";
		binaryString = "0";
		value = false;
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
		return Integer.parseInt(binaryString.substring(1,32), 2);
	}
	
	
	/**
	 * Retorna o valor bin�rio do gray coding.
	 * @author Guilherme
	 * @param binary
	 * @return gray coding
	 */
	/*public int binaryToGray(Integer binary) {
		int mask = maskOne;
		int maskA = Integer.parseInt("01000000000000000000000000000000",2);
		int maskB = Integer.parseInt("00000000000000000000000000000000",2);
		String grayString = "";
		grayString = grayString + "0";
		for (int i=1;i<=31;i++) {
			if (((maskA & binary) ^ (maskB & binary))  > 0) 
				grayString = grayString + "1";
			else 
				grayString = grayString + "0";
			maskB = maskA;
			maskA = mask >> i;
		}
		return Integer.parseInt(grayString.substring(1,32), 2);
	}*/
	
	public int binaryToGray(Integer binary) {
		String binaryString = Integer.toBinaryString(binary);
		System.out.println(binaryString.length());
		String grayString = new String();
		grayString = grayString.concat(String.valueOf(binaryString.charAt(0)));
		for (int i=1;i<=30;i++) {
			grayString = grayString.concat(String.valueOf(Integer.parseInt(String.valueOf(binaryString.charAt(i-1)))^ Integer.parseInt(String.valueOf(binaryString.charAt(i)))));
			
		}
		return Integer.parseInt(grayString.substring(0,31), 2);
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
	
	public String toString(int opcao) {
		String retorno;
		if (opcao == 0) {
			retorno = "Fitness: " + evaluation() + "\n";
		} else {
			retorno = "Fitness: " + evaluation2() + "\n";
		}
		for (int i=0;i<MAX_GENES;i++) {
			retorno =  retorno + "x" + i + " = " + grayToDouble(genes[i]) + "\n";
		}
		return retorno;
		
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
