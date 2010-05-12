import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import  java.util.Collections;  
import  java.util.Comparator;  
import java.util.Random;
import java.util.logging.Logger;

public class TrabalhoCE {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int populacao;
		double probMutacao;
		double porcentSobrevGerAnterior;		//Porcentagem de individuos da geração atual que passam para a proxima geração
		double deltaMelhoraSolucaoParametro;	//Variação de melhora na solução atual para a anterior (usada como condição de parada)
		int numMaxIteracoes; 					//Numero maximo de iterações (gerações), usado como condição de parada
		int contadorIteracoes = 0;
		double deltaAtual = 1;
		String log = "";
		
		//Definir variaveis
		populacao = 100; //Integer.parseInt(args[0]);
		probMutacao = 0.01; //Double.parseDouble(args[1]);
		porcentSobrevGerAnterior = 0.1; //Double.parseDouble(args[2]); 
		deltaMelhoraSolucaoParametro = 0.0001;
		numMaxIteracoes = 2000; //Integer.parseInt(args[3]);
		
		double numeroSobreviventesGerAnt =  Math.floor(porcentSobrevGerAnterior * populacao);
		
		double fitnessMelhorSolucaoAtual = 0;
		Cromossomo melhorSolucaoAtual = null;
		
		ArrayList<Cromossomo> geracaoAtual = new ArrayList<Cromossomo>();
		
		//Apaga log se ele ja existe
		try { 
			File file = new File("logAG.rtf");
			//Apaga o arquivo se ele ja existir
			file.delete();							
		} catch (Exception e) { }
		
		//Gerando população inicial
		for (int i=0;i<populacao;i++) {
			geracaoAtual.add(new Cromossomo());
		}
				
		while ( (contadorIteracoes < numMaxIteracoes) ) { 
			
			log = "[" + contadorIteracoes + "] \n";
						
			//Armazenando fitness das soluções da população no log
			for (Cromossomo solucaoi : geracaoAtual) {
				log = log + solucaoi.evaluation() + " \n";
			}
						
			try {
				FileWriter logWriter = new FileWriter("logAG.rtf",true);
				logWriter.append(log);
				logWriter.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			Collections.sort(geracaoAtual, new Comparator() {  
				public int compare(Object o1, Object o2) {  
					Cromossomo c1 = (Cromossomo) o1;  
					Cromossomo c2 = (Cromossomo) o2;  
					int retorno;
					double eval1 = c1.evaluation();
					double eval2 = c2.evaluation();
					if (eval1 > eval2) {
						retorno = -1;
					} else {
						if (eval1 == eval2) 
						retorno = 0;
						else retorno = 1;
					}
					return retorno;  
				}  
			});
			
			Cromossomo melhor = geracaoAtual.get(0);
			double melhorFitness = melhor.evaluation();
			deltaAtual = (melhorFitness/fitnessMelhorSolucaoAtual)-1;
			
			if (melhorFitness > fitnessMelhorSolucaoAtual) {
				melhorSolucaoAtual = melhor;
				fitnessMelhorSolucaoAtual = melhorFitness;
			}			
			
//			Começa a construir a nova geração
			ArrayList<Cromossomo> novaGeracao = new ArrayList<Cromossomo>();
			
						
			//Pega a porcentagem dos cromossomos que irão passar de uma geração a outra
			for (int i=0;i<numeroSobreviventesGerAnt;i++) {
				novaGeracao.add(geracaoAtual.get(i));
			}
			
			//Variaveis necessarias para gerar filhos por crossover
			double numeroFilhos = populacao - numeroSobreviventesGerAnt; //Quantos filhos temos que gerar
			double totalFitness = 0;
			for (Cromossomo solucaoi : geracaoAtual) {
				totalFitness = totalFitness + solucaoi.evaluation();
			}
			Random generator = new Random();
			
			//Faz os crossover dos melhores pais com os candidatos a parceiros
			for (int i=0;i<numeroFilhos;i++) {
				Cromossomo partner = null;
				double roleta = generator.nextDouble();
				
				double acumulado = 0;
				int index = 0;
				while (acumulado <= roleta) {
					acumulado = acumulado + (geracaoAtual.get(index).evaluation()/totalFitness);
					index++;
				}
				partner = geracaoAtual.get(index-1); //Nao entedi pq da erro se nao colocar -1 *VERIFICAR*
				
				//Gera filho com crossover
				Cromossomo filho = geracaoAtual.get(i).crossover(partner);
				
				//Aplica mutação
				filho.mutation(probMutacao);
				novaGeracao.add(filho);
				
			}
			
			geracaoAtual = novaGeracao;			
			contadorIteracoes++;
		}
				
		System.out.println("Melhor solução: \n" + melhorSolucaoAtual.toString());

	}
	
	public void bateriaTestesCromossomo() {
		
		System.out.println("Construtor com parametro de solução:");
		
		String[] candidato = new String[10];
		for (int i=0;i<10;i++) {
			candidato[i] = "11111100111111111111111";
		}
		Cromossomo cromossomo = new Cromossomo(candidato);
		System.out.println("Fitness cromosso instanciado a partir do parametro: " + cromossomo.evaluation() + "\n");
		
		Cromossomo cromossomo2 = new Cromossomo();
		System.out.println("Fitness inicial do cromossomo aleatório: " + cromossomo2.evaluation() + "\n");
		//cromossomo2.mutation();
		System.out.println("Fitness após mutação: " + cromossomo2.evaluation() + "\n");		

		String grayString = "00000000000000000000000000000101";
		int gray = Integer.parseInt(grayString,2);
		int binary = cromossomo.grayToBinary(gray);
		System.out.println("Gray  : " + grayString);
		String binaryString = Integer.toBinaryString(binary);
		while (binaryString.length() < 32) {
			binaryString = "0" + binaryString;
		}
		System.out.println("Binary: " + binaryString);

		System.out.println();

		String binaryString2 = "01000000000000000000000000000110";
		int binary2 = Integer.parseInt(binaryString2,2);
		int gray2 = cromossomo.binaryToGray(binary2);
		System.out.println("Binary: " + binaryString2);
		String grayString2 = Integer.toBinaryString(gray2);
		while (grayString2.length() < 32) {
			grayString2 = "0" + grayString2;
		}
		System.out.println("Gray  : " + grayString2);

		//Testa retorno das funções
		String valorString1 = "00000000000000000000000000000011";
		int valorInt1 = Integer.parseInt(valorString1, 2);
		String valorString2 = "11000000000000000000000000000011";
		int valorInt2 = Integer.parseInt(valorString2.substring(1,32), 2);
		Integer valorIntAnd = valorInt1 & valorInt2;
		Integer valorIntOr = valorInt1 | valorInt2;
		System.out.println("Valores\n" + valorString1 + " == " + valorInt1 + "\n"
				+ valorString2 + " == " + valorInt2 + "\n" + 
				"And: " + valorIntAnd.toBinaryString(valorIntAnd) + " == " + valorIntAnd.intValue() + "\n" + 
				"Or: " + valorIntOr.toBinaryString(valorIntOr) + " == " + valorIntOr.intValue());
			

		
		
	}

}







 