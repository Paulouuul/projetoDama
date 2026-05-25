package model;



public class Tabuleiro {
	private Peca[][] tabuleiro;
	private int linhas;
	private int colunas;
	private static final int NUMERO_LINHAS = 8;
	private static final int NUMERO_COLUNAS = 8;
	private static final int PECAS_BRANCAS = 12;
	private static final int PECAS_PRETAS = 12;
	private int brancasrestantes;
	private int pretasrestantes;
	private int brancasremovidas;
	private int pretasremovidas;
	private int qtdmovimentosvalidos_brancas = 0;
	private int qtdmovimentosvalidos_pretas = 0;
	
	
	public int getQtdmovimentosvalidos_brancas() {
		return qtdmovimentosvalidos_brancas;
	}

	

	
	
	
	public int getQtdmovimentosvalidos_pretas() {
		return qtdmovimentosvalidos_pretas;
	}



	private int[] podecapturarpeca = {-1,-1};
	private String turno;
	public Tabuleiro(int linhas, int colunas) {
		this.linhas = NUMERO_LINHAS;
		this.colunas = NUMERO_COLUNAS;
		this.tabuleiro = new Peca[linhas][colunas];
		this.brancasrestantes = PECAS_BRANCAS;
		this.pretasrestantes = PECAS_PRETAS;
		this.turno = "Jogador1";
		this.pretasremovidas = 0;
		this.brancasremovidas = 0;

	}

	public void inicializarTabuleiro() {

		for (int linha = 0; linha < linhas; linha++) {
			for (int coluna = 0; coluna < colunas; coluna++) {
				if ((linha + coluna) % 2 != 0) {
					if (linha < 3) {
						tabuleiro[linha][coluna] = new PecaBranca(linha, coluna);
					} else if (linha > 4) {
						tabuleiro[linha][coluna] = new PecaPreta(linha, coluna);
					}
				}
			}
		}
	}

	public String getTurno() {
		return turno;
	}

	public void alternarTurno() {
		podecapturarpeca[0] = -1;
		podecapturarpeca[1] = -1;
		System.out.println("Executou alterou turno!");
		if (turno.equals("Jogador1")) {
			turno = "Jogador2";
		} else {
			turno = "Jogador1";
		}
	}


	public boolean movimentoValido(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
//		System.out.println(brancasrestantes +"Brancas restantes" + "Pretas restantes" + pretasrestantes);
		System.out.println("Destino: " + linhaDestino + ":" + colunaDestino + " Origem: " + linhaOrigem + ":" + colunaOrigem);

		// Verifica se as coordenadas são válidas
		if (!coordenadasValidas(linhaOrigem, colunaOrigem) || !coordenadasValidas(linhaDestino, colunaDestino)) {
			return false;
		}

		// Obtém a peça na posição de origem
		Peca pecaOrigem = tabuleiro[linhaOrigem][colunaOrigem];

		// Verifica se há uma peça na posição de origem
		if (pecaOrigem == null) {
			return false;
		}

		// Verifica o turno do jogador
		if ((pecaOrigem.getCor().equals("branco") && !turno.equals("Jogador1")) ||
				(pecaOrigem.getCor().equals("preto") && !turno.equals("Jogador2"))) {
			return false;
		}

		// Verifica se a peça deve capturar outra peça novamente
		if (podecapturarpeca[0] != -1 && podecapturarpeca[1] != -1) {
			int linhacaptura = podecapturarpeca[0];
			int colunacaptura = podecapturarpeca[1];
			System.out.println("Linhacaptura" + podecapturarpeca[0] + ":" + podecapturarpeca[1]);
			if (pecaOrigem.getLinha() != linhacaptura || pecaOrigem.getColuna() != colunacaptura) {
				System.out.println("Retornou false por mover peça diferente!");
				return false;
			}

			// Verifica se o movimento é comum quando deveria ser uma captura
			if (pecaOrigem.movimentoComum(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, this)) {
				System.out.println("Retornou false por não capturar peça!");
				return false;
			}

			
			if(pecaOrigem.comeuPeca(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, this)) {
				podecapturarpeca[0] = -1;
				podecapturarpeca[1] = -1;
				System.out.println("Troucou de turno! Pq comeu peça");
				//            	alternarTurno();
			}
		}

		// Verifica se o movimento é válido para a peça de origem
		boolean movimentoValido = pecaOrigem.movimentoValido(linhaDestino, colunaDestino, this);
		if (movimentoValido) {
			
			
			if(pecaOrigem.getCor().equals("preto")) {
				qtdmovimentosvalidos_pretas++;
				System.out.println(qtdmovimentosvalidos_pretas);
				
			}
			
			if(pecaOrigem.getCor().equals("branco")) {
				qtdmovimentosvalidos_brancas++;
				System.out.println(qtdmovimentosvalidos_brancas);
				
			}
			boolean capturou = removerPecasCapturadas(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);

			// Move a peça
			moverPeca(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);

			// Verifica se a peça pode capturar novamente após o movimento

			if (capturou) {

			
					
					if(pecaOrigem instanceof PecaBranca || pecaOrigem instanceof PecaPreta) {
					if(pecaOrigem.podeCapturar(linhaDestino, colunaDestino, this)) {



						podecapturarpeca[0] = linhaDestino;
						podecapturarpeca[1] = colunaDestino;

					}else {
						System.out.println("Troucou de turno!");
						podecapturarpeca[0] = -1;
						podecapturarpeca[1] = -1;
						alternarTurno();

					}

				}else {
					podecapturarpeca[0] = -1;
					podecapturarpeca[1] = -1;
					alternarTurno();
				}


			} else {

				System.out.println("Troucou de turno!");
				alternarTurno();
			}



			System.out.println("Linhacaptura" + podecapturarpeca[0] + ":" + podecapturarpeca[1]);
			return true;
			
		}
		

		return false;
	}







	public int[] getPodecapturarpeca() {
		return podecapturarpeca;
	}

	public void setPodecapturarpeca(int[] podecapturarpeca) {
		this.podecapturarpeca = podecapturarpeca;
	}

	public void moverPeca(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {

		Peca pecaOrigem = tabuleiro[linhaOrigem][colunaOrigem];

		// Verifica se a peça de origem não é nula
		if (pecaOrigem != null) {
			// Movimenta a peça para o destino
			tabuleiro[linhaDestino][colunaDestino] = pecaOrigem;
			tabuleiro[linhaOrigem][colunaOrigem] = null;

			// Atualiza as coordenadas da peça movida
			pecaOrigem.setLinha(linhaDestino);
			pecaOrigem.setColuna(colunaDestino);

		}


	}

	public boolean verificaTabuleiro() {
		boolean tabuleirovalido = false;
		if(this.brancasrestantes == PECAS_BRANCAS - brancasremovidas) {
			tabuleirovalido = true;

		}

		if(this.pretasrestantes == PECAS_BRANCAS - pretasremovidas) {
			tabuleirovalido = true;

		}

		return tabuleirovalido;
	}

	public boolean coordenadasValidas(int linha, int coluna) {
		return linha >= 0 && linha < NUMERO_LINHAS && coluna >= 0 && coluna < NUMERO_COLUNAS;
	}

	public boolean removerPecasCapturadas(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
		boolean capturou = false;


		Peca peca = tabuleiro[linhaOrigem][colunaOrigem];
		// Determina o sentido do movimento
		int passoLinha = Integer.compare(linhaDestino, linhaOrigem);
		int passoColuna = Integer.compare(colunaDestino, colunaOrigem);

		// Itera sobre todas as posições ao longo da trajetória do movimento
		int linhaAtual = linhaOrigem + passoLinha;
		int colunaAtual = colunaOrigem + passoColuna;

		while (linhaAtual != linhaDestino || colunaAtual != colunaDestino) {
			// Define como null a peça na posição atual, se houver uma peça
			if (peca.comeuPeca(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, this)) {

				
				if(tabuleiro[linhaAtual][colunaAtual] instanceof PecaBranca || tabuleiro[linhaAtual][colunaAtual] instanceof PecaPreta) {
					brancasrestantes--;
					pretasremovidas++;
				}else {
					pretasrestantes--;
					pretasremovidas++;
				}

				System.out.println(this.brancasrestantes +" Numbrancas");
				System.out.println(this.pretasrestantes +" Numpretas");
				tabuleiro[linhaAtual][colunaAtual] = null;
				capturou = true;

				System.out.println("Capturou! Linhacaptura" + podecapturarpeca[0] + ":" + podecapturarpeca[1]);

			}
			linhaAtual += passoLinha;
			colunaAtual += passoColuna;
		}

		// Retorna verdadeiro se alguma peça foi capturada
		return capturou;
	}







//	public boolean naoPodemoverBrancas() {
//		boolean podemover = false;
//		for (int i = 0; i < 8; i++) { 
//			for (int j = 0; j < 8; j++) {
//				if(tabuleiro[i][j] instanceof PecaBranca || tabuleiro[i][j] instanceof DamaBranca) {
//					if(tabuleiro[i][j].podeMover(i, i, this)){
//						podemover = true;
//
//					}
//				}
//			}
//		}
//
//		return podemover;
//	}
//
//
//
//	public boolean naoPodemoverPretas() {
//		boolean podemover = false;
//
//		for (int i = 0; i < 8; i++) { 
//			for (int j = 0; j < 8; j++) {
//				if(tabuleiro[i][j] instanceof PecaPreta || tabuleiro[i][j] instanceof DamaPreta) {
//					if(tabuleiro[i][j].podeMover(i, i, this)){
//						System.out.println("Retornou true!" +i+":"+j);
//						podemover = true;
//
//					}else {
//
//					}
//				}
//			}
//		}
//
//		return podemover;
//	}



	public String verificarVencedor() {
		boolean tembranca = false;
		boolean tempreta = false;
		for(int i = 0; i< 8; i++) {
			for(int j = 0; j<8; j++) {
				
				if(tabuleiro[i][j] != null) {
				if(tabuleiro[i][j] instanceof PecaBranca || tabuleiro[i][j] instanceof DamaBranca) {
					tembranca = true;
				}
				
				if(tabuleiro[i][j] instanceof PecaPreta || tabuleiro[i][j] instanceof DamaPreta) {
					tempreta = true;
				}
				}
			}
		}
		if(!tempreta) {
			return "Jogador1";
		}else if(!tembranca) {
			return "Jogador2";
		}
		
//		else if(!naoPodemoverPretas()) {
//			return "Jogador1";
//		}else if(!naoPodemoverBrancas()) {
//			return  "Jogador2";
//		}


		return "Empate";
	}











	public Peca[][] getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Peca[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}

	public Peca getPeca(int linha, int coluna) {
		return tabuleiro[linha][coluna];
	}

}