


public class EstatisticasGeracao {
	
	private int populacao;
	private double[] fitness;
	
	public EstatisticasGeracao(double[] fitness) {
		this.fitness = fitness;
		populacao = fitness.length;
	}

	public int getPopulacao() {
		return populacao;
	}

	public void setPopulacao(int populacao) {
		this.populacao = populacao;
	}

	public double[] getFitness() {
		return fitness;
	}

	public void setFitness(double[] fitness) {
		this.fitness = fitness;
	}
	
	

}
