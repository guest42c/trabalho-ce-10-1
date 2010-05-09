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
	
	public Cromossomo() {
		//TODO: Implementar construtor randomico (gerar uma solução randomica)
		Random rand = new Random();
		cromossomo = (rand.nextInt()%top)-bottom;
	}
	
	public Cromossomo(Integer solucao) {
		//TODO: Implementar construtor deterministico (gerar uma solução indicada)
		cromossomo = solucao;
	}
	
	public double evaluation() {
		//TODO: Implementar função de avaliação
		return 1.0;
	}
	
	public void mutation() {
		//TODO: implementar mutação
		Random rand = new Random();
		cromossomo = rand.nextInt();
	}
	
	public Cromossomo crossover(Cromossomo partner) {
		//TODO: Implementar crossover
		return partner;
	}
	
	public Integer binaryToGray(Integer binary) {
		//TODO: Implementar
		return binary;
	}
	
	public Integer grayToBinary(Integer gray) {
		//TODO: Implementar
		return gray;
	}

}
