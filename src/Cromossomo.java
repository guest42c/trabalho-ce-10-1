import java.sql.Time;
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
	
	Integer cromossomo; //inteiro que representa a solução 
	private static int bottom = -500; //limite inferior da solução
	private static int top = 500; //limite superior da solução
	private static int maskOne = Integer.parseInt("1000000000000000000000000000000", 2);
	
	Random rand = new Random();
	
	public Cromossomo() {
		//TODO: Implementar construtor randomico (gerar uma solução randomica)
		cromossomo = rand.nextInt();
		String binaryString = Integer.toBinaryString(cromossomo);
		//Removendo o bit mais significativo
		//não sei pq ta dando problema, para outras funções só aceita 31bits
		//bit de sinal????
		binaryString = binaryString.substring(1, binaryString.length());
		Integer grayCoding = Integer.parseInt(binaryString,2);
		System.out.println("Representação: " + binaryString);
		System.out.println("Valor gray coding: " + grayCoding);
		System.out.println("Valor inteiro: " + grayToBinary(grayCoding));
	}
	
	public Cromossomo(Integer solucao) {
		//TODO: Implementar construtor deterministico (gerar uma solução indicada)
		cromossomo = solucao;
		String binaryString = Integer.toBinaryString(cromossomo);
		//Removendo o bit mais significativo
		//não sei pq ta dando problema, para outras funções só aceita 31bits
		//bit de sinal????
		binaryString = binaryString.substring(1, binaryString.length());
		Integer grayCoding = Integer.parseInt(binaryString,2);
		System.out.println("Representação: " + binaryString);
		System.out.println("Valor gray coding: " + grayCoding);
		System.out.println("Valor inteiro: " + grayToBinary(grayCoding));
	}
	
	public Cromossomo(String solucao) {
		//TODO: Implementar construtor deterministico (gerar uma solução indicada)
		cromossomo = Integer.parseInt(solucao,2);
		String binaryString = Integer.toBinaryString(cromossomo);
		//Removendo o bit mais significativo
		//não sei pq ta dando problema, para outras funções só aceita 31bits
		//bit de sinal????
		binaryString = binaryString.substring(1, binaryString.length());
		Integer grayCoding = Integer.parseInt(binaryString,2);
		System.out.println("Representação: " + binaryString);
		System.out.println("Valor gray coding: " + grayCoding);
		System.out.println("Valor inteiro: " + grayToBinary(grayCoding));
	}
	
	public Cromossomo(long seed) {
		//TODO: Implementar construtor passando seed
		rand.setSeed(seed);
		cromossomo = rand.nextInt();
		String binaryString = Integer.toBinaryString(cromossomo);
		//Removendo o bit mais significativo
		//não sei pq ta dando problema, para outras funções só aceita 31bits
		//bit de sinal????
		binaryString = binaryString.substring(1, binaryString.length());
		Integer grayCoding = Integer.parseInt(binaryString,2);
		System.out.println("Representação: " + binaryString);
		System.out.println("Valor gray coding: " + grayCoding);
		System.out.println("Valor inteiro: " + grayToBinary(grayCoding));
	}
	
	public double evaluation() {
		//TODO: Implementar função de avaliação
		return 1.0;
	}
	
	public void mutation() {
		//TODO: implementar mutação
		cromossomo = ((int)(Math.random()*(top-bottom+1)))+bottom;
	}
	
	public Cromossomo crossover(Cromossomo partner) {
		//TODO: Implementar crossover
		return partner;
	}
	
	private Integer grayToBinary(Integer gray) {
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
	
	private Integer binaryToGray(Integer binary) {
		return 0;
	}
	
	private double grayToDouble(Integer gray) {
		Integer binary = grayToBinary(gray);
		
		return bottom + binary * ((top-bottom)/(Math.pow(2, 32)-1));
	}
	
	public String getStringRepresentation() {
		//32Bits
		return Integer.toBinaryString(cromossomo);
	}
	
	public double getDoubleRepresentation(Integer cromossomo) {
		return grayToDouble(cromossomo);
	}
	
    // append reverse of order n gray code to prefix string, and print
    static String yarg(String prefix, int n) {
        if (n == 0) return prefix;
        else {
            prefix = gray(prefix + "1", n - 1) + yarg(prefix + "0", n - 1);
            return prefix;
        }
    }  

    // append order n gray code to end of prefix string, and print
    static String gray(String prefix, int n) {
        if (n == 0) return prefix;
        else {
            prefix = gray(prefix + "0", n - 1) + yarg(prefix + "1", n - 1);
            return prefix;
        }
    }

	public Integer getCromossomo() {
		return cromossomo;
	}

	public void setCromossomo(Integer cromossomo) {
		this.cromossomo = cromossomo;
	}  

}
