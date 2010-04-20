import java.util.Random;

/**
 * Esta classe � a representa��o de uma solu��o candidata ou seja do cromossomo em si.
 * Por quest�es de simplicidade e abrindo m�o de desempenho usaremos essa classe para definir um cromossomo e
 * as opera��es v�lidas sobre ele.
 * 
 * Depois que o  programa estiver pronto e funcionando podemos substituir as chamadas de fun��es por 
 * c�digos otimizados.
 * 
 * @author Guilherme
 *
 */
public class Cromossomo {
	
	int representacao; //inteiro que representa a solu��o 
	private static int bottom = -500; //limite inferior da solu��o
	private static int top = 500; //limite superior da solu��o
	
	public Cromossomo() {
		//TODO: Implementar construtor randomico (gerar uma solu��o randomica)
		Random rand = new Random();
		representacao = rand.nextInt();
	}
	
	public Cromossomo(int solucao) {
		//TODO: Implementar construtor deterministico (gerar uma solu��o indicada)
		representacao = solucao;
	}
	
	public double evaluation() {
		//TODO: Implementar fun��o de avalia��o
		return 1.0;
	}
	
	public void mutation() {
		//TODO: implementar muta��o
		Random rand = new Random();
		representacao = rand.nextInt();
	}
	
	public Cromossomo crossover(Cromossomo partner) {
		//TODO: Implementar crossover
		return partner;
	}

}
