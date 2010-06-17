import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TrabalhoCE implements ActionListener {

	JButton buttonIniciar;
	int paramPopulacao;
	double paramProbMutacao;
	double paramTaxaSobrevivencia;
	int paramNumeroGeracoes;
	JTextField populacaoField;
	JTextField probMutacaoField;
	JTextField taxaSobrevivenciaField;
	JTextField numeroGeracoesField;
	JTextArea resposta;
	JRadioButton funcaoF6;
	JRadioButton funcaoF8;

	public static void main(String[] args) {

		// Interface
		TrabalhoCE trabCE = new TrabalhoCE();
		trabCE.goGui();

	}

	public void calcula() {
		int populacao;
		double probMutacao;
		double porcentSobrevGerAnterior; // Porcentagem de individuos da geração
											// atual que passam para a proxima
											// geração
		double deltaMelhoraSolucaoParametro; // Variação de melhora na solução
												// atual para a anterior (usada
												// como condição de parada)
		int numMaxIteracoes; // Numero maximo de iterações (gerações), usado
								// como condição de parada
		int contadorIteracoes = 0;
		double deltaAtual = 1;
		String log = "";
		ArrayList<EstatisticasGeracao> evolucaoGeracoes = new ArrayList<EstatisticasGeracao>();

		// Definir variaveis
		populacao = paramPopulacao; //10; // Integer.parseInt(args[0]); //
		probMutacao = paramProbMutacao; //0.01; // Double.parseDouble(args[1]); //
		porcentSobrevGerAnterior = paramTaxaSobrevivencia; //0.1; // Double.parseDouble(args[2]);//
		deltaMelhoraSolucaoParametro = 0.00000001;
		numMaxIteracoes = paramNumeroGeracoes; // 10; // Integer.parseInt(args[3]); //
		int turnosEstagnado = 0;

		double[] fitnessGeracao = new double[populacao];

		double numeroSobreviventesGerAnt = Math.floor(porcentSobrevGerAnterior
				* populacao);

		double fitnessMelhorSolucaoAtual = 0;
		Cromossomo melhorSolucaoAtual = null;

		ArrayList<Cromossomo> geracaoAtual = new ArrayList<Cromossomo>();

		// Apaga log se ele ja existe
		try {
			File file = new File("logAG.rtf");
			// Apaga o arquivo se ele ja existir
			file.delete();
		} catch (Exception e) {
		}

		// Apaga log se ele ja existe
		try {
			File file = new File("logMatlabAG.rtf");
			// Apaga o arquivo se ele ja existir
			file.delete();
		} catch (Exception e) {
		}

		// Apaga log se ele ja existe
		try {
			File file = new File("Ys.rtf");
			// Apaga o arquivo se ele ja existir
			file.delete();
		} catch (Exception e) {
		}
		
		// Apaga log se ele ja existe
		try {
			File file = new File("melhores.rtf");
			// Apaga o arquivo se ele ja existir
			file.delete();
		} catch (Exception e) {
		}

		// Gerando população inicial
		for (int i = 0; i < populacao; i++) {
			geracaoAtual.add(new Cromossomo());
		}

		String logMatlab;
		String Y;
		String melhores = "X = (";

		while (contadorIteracoes < numMaxIteracoes) {

			if (deltaAtual < deltaMelhoraSolucaoParametro) {
				turnosEstagnado++;
			} else {
				turnosEstagnado = 0;
			}

			Collections.sort(geracaoAtual, new Comparator() {
				public int compare(Object o1, Object o2) {
					Cromossomo c1 = (Cromossomo) o1;
					Cromossomo c2 = (Cromossomo) o2;
					int retorno;
					double eval1 = 0;
					double eval2= 0;
					if (funcaoF6.isSelected()) {
						eval1 = c1.evaluation();
						eval2 = c2.evaluation();
					} else {
						eval1 = c1.evaluation2();
						eval2 = c2.evaluation2();
					}
					
					if (eval1 > eval2) {
						retorno = -1;
					} else {
						if (eval1 == eval2)
							retorno = 0;
						else
							retorno = 1;
					}
					return retorno;
				}
			});

			log = "[" + contadorIteracoes + "] \n";
			logMatlab = "Iteracao " + contadorIteracoes + "\nPlot(";
			Y = "Geração: " + contadorIteracoes + "\nY = [";
			int candidatoIndex = 0;
			if (funcaoF6.isSelected()) {
				melhores = melhores + geracaoAtual.get(0).evaluation() + ",";
			} else {
				melhores = melhores + geracaoAtual.get(0).evaluation2() + ",";
			}
			double fitness;
			int contador = 0;
			// Armazenando fitness das soluções da população no log
			for (Cromossomo solucaoi : geracaoAtual) {
				if (funcaoF6.isSelected()) {
					fitness = solucaoi.evaluation();
				} else {
					fitness = solucaoi.evaluation2();
				}
				fitnessGeracao[candidatoIndex] = fitness;
				candidatoIndex++;
				log = log + fitness + " \n";
				contador++;
				logMatlab = logMatlab + contador + "," + fitness + ",";
				Y = Y + fitness + ",";
			}

			logMatlab = logMatlab.substring(0, logMatlab.length() - 1) + ");\n";
			Y = Y.substring(0, Y.length() - 1) + "];\n";

			EstatisticasGeracao umaGeracao = new EstatisticasGeracao(
					fitnessGeracao);
			evolucaoGeracoes.add(umaGeracao);

			try {
				FileWriter logWriter = new FileWriter("logAG.rtf", true);
				logWriter.append(log);
				logWriter.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			try {
				FileWriter logWriter = new FileWriter("logMatlabAG.rtf", true);
				logWriter.append(logMatlab);
				logWriter.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			try {
				FileWriter logWriter = new FileWriter("Ys.rtf", true);
				logWriter.append(Y);
				logWriter.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			Cromossomo melhor = null;
			double melhorFitness = 0;
			if (contadorIteracoes > 0) {
				melhor = geracaoAtual.get(0);
				if (funcaoF6.isSelected())
				melhorFitness = melhor.evaluation();
				else melhorFitness = melhor.evaluation2();

				deltaAtual = melhorFitness / fitnessMelhorSolucaoAtual;
			} else {
				melhorSolucaoAtual = geracaoAtual.get(0);
				if (funcaoF6.isSelected())
				fitnessMelhorSolucaoAtual = melhorSolucaoAtual.evaluation();
				else fitnessMelhorSolucaoAtual = melhorSolucaoAtual.evaluation2();
			}

			if (melhorFitness > fitnessMelhorSolucaoAtual) {
				melhorSolucaoAtual = melhor;
				fitnessMelhorSolucaoAtual = melhorFitness;
			}

			// Começa a construir a nova geração
			ArrayList<Cromossomo> novaGeracao = new ArrayList<Cromossomo>();

			// Pega a porcentagem dos cromossomos que irão passar de uma geração
			// a outra
			for (int i = 0; i < numeroSobreviventesGerAnt; i++) {
				novaGeracao.add(geracaoAtual.get(i));
			}

			// Variaveis necessarias para gerar filhos por crossover
			double numeroFilhos = populacao - numeroSobreviventesGerAnt; // Quantos
																			// filhos
																			// temos
																			// que
																			// gerar
			double totalFitness = 0;
			for (Cromossomo solucaoi : geracaoAtual) {
				if (funcaoF6.isSelected())
				totalFitness = totalFitness + solucaoi.evaluation();
				else totalFitness = totalFitness + solucaoi.evaluation2();
			}
			Random generator = new Random();

			// Faz os crossover dos melhores pais com os candidatos a parceiros
			for (int i = 0; i < numeroFilhos; i++) {
				Cromossomo partner = null;
				double roleta = generator.nextDouble();

				double acumulado = 0;
				int index = 0;
				while (acumulado <= roleta) {
					if (funcaoF6.isSelected())
					acumulado = acumulado
							+ (geracaoAtual.get(index).evaluation() / totalFitness);
					else acumulado = acumulado
					+ (geracaoAtual.get(index).evaluation2() / totalFitness);
					index++;
				}
				partner = geracaoAtual.get(index-1); // Nao entedi pq da erro
														// se nao colocar -1
														// *VERIFICAR*

				// Gera filho com crossover
				Cromossomo filho = geracaoAtual.get(i).crossover(partner);

				// Aplica mutação
				filho.mutation(probMutacao);
				novaGeracao.add(filho);

			}
			
			geracaoAtual = novaGeracao;
			contadorIteracoes++;
		}
		melhores = melhores.substring(0,melhores.length()-1) + ")";
		
		try {
			FileWriter logWriter = new FileWriter("melhores.rtf", true);
			logWriter.append(melhores);
			logWriter.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		if (funcaoF6.isSelected()) {
			resposta.setText(melhorSolucaoAtual.toString(0));
		} else {
			resposta.setText(melhorSolucaoAtual.toString(1));
		}
	
	}

	public void goGui() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JLabel populacao = new JLabel();
		JLabel probMutacao = new JLabel();
		JLabel taxaSobrevivencia = new JLabel();
		JLabel numeroGeracoes = new JLabel();
		JLabel funcao = new JLabel();
		funcaoF6 = new JRadioButton("Função F6");
		funcaoF8 = new JRadioButton("Função F8");
		funcaoF6.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		group.add(funcaoF6);
		group.add(funcaoF8);

		populacaoField = new JTextField(5);
		probMutacaoField = new JTextField(8);
		taxaSobrevivenciaField = new JTextField(5);
		numeroGeracoesField = new JTextField(4);
		resposta = new JTextArea(15,25);
		resposta.setLineWrap(true);
		resposta.setEditable(false);
		
		funcao.setText("Selecione a função que deseja otimizar: ");
		populacao.setText("Tamanho da população: ");
		probMutacao.setText("Probabilidade de mutação (ex.: 0.01): ");
		taxaSobrevivencia
				.setText("Taxa de Manutenção da Geração Anterior (ex.: 0.1 = 10%): ");
		numeroGeracoes.setText("Numero de gerações: ");
		buttonIniciar = new JButton("Iniciar");

		JPanel painelBotoes = new JPanel();

		buttonIniciar.addActionListener(this);
		
		panel.add(funcao);
		panel.add(funcaoF6);
		panel.add(funcaoF8);
		panel.add(populacao);
		panel.add(populacaoField);
		panel.add(probMutacao);
		panel.add(probMutacaoField);
		panel.add(taxaSobrevivencia);
		panel.add(taxaSobrevivenciaField);
		panel.add(numeroGeracoes);
		panel.add(numeroGeracoesField);		
		panel.add(resposta);
		painelBotoes.add(buttonIniciar);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.getContentPane().add(BorderLayout.SOUTH, painelBotoes);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(425,400);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		boolean continuar = true;
		resposta.setText("");
		try {
			paramPopulacao = Integer.parseInt(populacaoField.getText());
		} catch (Exception e) {
			resposta.append("Parametro de população inválido\n");
			continuar = false;
		}

		try {
			paramProbMutacao = Double.parseDouble(probMutacaoField.getText());
		} catch (Exception e) {
			resposta.append("Parametro de mutação inválido\n");
			continuar = false;
		} finally {
			if (paramProbMutacao > 1) {
				resposta.append("Parametro de mutação inválido\n");
				continuar = false;
			}
		}

		try {
			paramTaxaSobrevivencia = Double.parseDouble(taxaSobrevivenciaField
					.getText());
		} catch (Exception e) {
			resposta.append("Parametro de população inválido\n");
			continuar = false;
		} finally {
			if (paramTaxaSobrevivencia > 1) {
				resposta.append("Parametro de população inválido\n");
				continuar = false;
			}
		}

		try {
			paramNumeroGeracoes = Integer.parseInt(numeroGeracoesField
					.getText());
		} catch (Exception e) {
			resposta.append("Parametro de gerações inválido\n");
			continuar = false;
		}
		
		if (continuar) {
			resposta.setText("Calculando...\nAguarde");
			calcula();
		}
		
		
	}

}
