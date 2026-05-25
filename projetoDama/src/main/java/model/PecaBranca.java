package model;

public class PecaBranca extends Peca {

    public PecaBranca(int linha, int coluna) {
        super(linha, coluna, "branco");
    }

    
    public String getCor() {
		return cor;
	}

    public void setCor(String cor) {
		this.cor = cor;
	}
    @Override
    public boolean movimentoValido(int linhaDestino, int colunaDestino, Tabuleiro tabuleiro) {
    	
        return movimentoComum(this.getLinha(), this.getColuna(), linhaDestino, colunaDestino, tabuleiro) || comeuPeca(this.getLinha(), this.getColuna(),linhaDestino,colunaDestino, tabuleiro);
    }


    public boolean movimentoComum(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Tabuleiro tabuleiro) {
        int deltaLinha = linhaDestino - linhaOrigem;
        int deltaColuna = colunaDestino - colunaOrigem;
        if(tabuleiro.getPeca(linhaDestino, colunaDestino) != null) {
        	return false;
        }
        // Movimento normal para frente (diagonal)
        if (deltaLinha == 1 && Math.abs(deltaColuna) == 1) { // Movimento para cima
            // Verificar se a casa de destino está vazia
            return tabuleiro.getPeca(linhaDestino, colunaDestino) == null;
        }
        
        return false;
    }
    
    
 
    public boolean comeuPeca(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Tabuleiro tabuleiro) {
        // Verifica se o movimento é na diagonal
    	if(tabuleiro.getPeca(linhaDestino, colunaDestino) != null) {
        	return false;
        }
        int deltaLinha = linhaDestino - linhaOrigem;
        int deltaColuna = colunaDestino - colunaOrigem;
        if (Math.abs(deltaLinha) != Math.abs(deltaColuna) || deltaLinha == 0 || deltaColuna == 0) {
                       return false;
        }

        // Verifica se a peça moveu duas casas na diagonal
        if (Math.abs(deltaLinha) == 2 && Math.abs(deltaColuna) == 2) {
//            System.out.println("É na diagonal 2 casas");
            // Verifica se a casa de destino está vazia
            if (tabuleiro.getPeca(linhaDestino, colunaDestino) == null) {
                // Calcula a posição da peça intermediária
//                System.out.println("A coluna de destino é vazia");
                int linhaIntermediaria = (linhaOrigem + linhaDestino) / 2;
                int colunaIntermediaria = (colunaOrigem + colunaDestino) / 2;

                // Verifica se a posição intermediária está dentro dos limites do tabuleiro e se contém uma peça
                if (tabuleiro.coordenadasValidas(linhaIntermediaria, colunaIntermediaria) && linhaIntermediaria != 7 && linhaIntermediaria != 0 && colunaIntermediaria!= 7 && colunaIntermediaria != 0) {
                    // Verifica se há uma peça intermediária de cor oposta à cor da peça de origem
                    Peca pecaIntermediaria = tabuleiro.getPeca(linhaIntermediaria, colunaIntermediaria);
                    if (pecaIntermediaria != null && (pecaIntermediaria instanceof PecaPreta || pecaIntermediaria instanceof DamaPreta)) {
//                        System.out.println("Movimento de captura realizada de (" + linhaOrigem + ", " + colunaOrigem + ") para (" + linhaDestino + ", " + colunaDestino + ")");
                        return true; // Captura bem-sucedida
                    }
                }
            }
        } else {
//            System.out.println("Movimento inválido: não é na diagonal ou é de apenas uma casa");
        }

        // Se não houver movimento de duas casas na diagonal, ou se a casa de destino não estiver vazia, ou se não ultrapassou uma peça intermediária, retorna false
        return false;
    }
    
//    public boolean podeMover(int linhaOrigem, int colunaOrigem, Tabuleiro tabuleiro) {
//    	int qtdtestes = 0;
//    	int[] diagonal_1 = new int[2];
//    	int[] diagonal_2 = new int[2];
//    	int[] diagonal_3 = new int[2];
//    	int[] diagonal_4 = new int[2];
//    	while(qtdtestes < 3) {
//    		
//    		diagonal_1[0] = linhaOrigem + PecaBranca.DIRECOES[0][0];
//    		diagonal_1[1] = colunaOrigem + PecaBranca.DIRECOES[0][1];
//    		diagonal_2[0] = linhaOrigem + PecaBranca.DIRECOES[1][0];
//    		diagonal_2[1] = colunaOrigem + PecaBranca.DIRECOES[1][1];
//    		diagonal_3[0] = linhaOrigem + PecaBranca.DIRECOES[2][0];
//    		diagonal_3[1] = colunaOrigem + PecaBranca.DIRECOES[2][1];
//    		diagonal_4[0] = linhaOrigem + PecaBranca.DIRECOES[3][0];
//    		diagonal_4[1] = colunaOrigem + PecaBranca.DIRECOES[3][1];
//    		
//    		
//    		
//    		if(tabuleiro.coordenadasValidas(diagonal_1[0],diagonal_1[1])) {
//    			if(movimentoComum(linhaOrigem, colunaOrigem, diagonal_1[0], diagonal_1[1], tabuleiro) || comeuPeca(this.getLinha(), this.getColuna(),diagonal_1[0],diagonal_1[1], tabuleiro)) {
//	    			return true;
//	    		}
//    		}
//    		
//    		if(tabuleiro.coordenadasValidas(diagonal_2[0],diagonal_2[1])) {
//	    		if(movimentoComum(linhaOrigem, colunaOrigem, diagonal_2[0], diagonal_2[1], tabuleiro) || comeuPeca(this.getLinha(), this.getColuna(),diagonal_2[0],diagonal_2[1], tabuleiro)) {
//	    			return true;
//	    		}
//    		}
//    		
//    		
//    		if(tabuleiro.coordenadasValidas(diagonal_3[0],diagonal_3[1])) {
//    			if(movimentoComum(linhaOrigem, colunaOrigem, diagonal_3[0], diagonal_3[1], tabuleiro) || comeuPeca(this.getLinha(), this.getColuna(),diagonal_3[0],diagonal_3[1], tabuleiro)) {
//	    			return true;
//	    		}
//    		}
//    		
//    		if(tabuleiro.coordenadasValidas(diagonal_4[0],diagonal_4[1])) {
//    			if(movimentoComum(linhaOrigem, colunaOrigem, diagonal_4[0], diagonal_4[1], tabuleiro) || comeuPeca(this.getLinha(), this.getColuna(),diagonal_4[0],diagonal_4[1], tabuleiro)) {
//	    			return true;
//	    		}
//    		
//    		}
//    		qtdtestes++;
//    		}
//    	
//		return false;
//    }
//    
    
    public boolean podeCapturar(int linhaOrigem, int colunaOrigem, Tabuleiro tabuleiro) {
    	
    	int[] diagonal_1 = new int[2];
    	int[] diagonal_2 = new int[2];
    	int[] diagonal_3 = new int[2];
    	int[] diagonal_4 = new int[2];
    	diagonal_1[0] = linhaOrigem + (PecaBranca.DIRECOES[0][0] * 2);
		diagonal_1[1] = colunaOrigem + (PecaBranca.DIRECOES[0][1] * 2);
		diagonal_2[0] = linhaOrigem + (PecaBranca.DIRECOES[1][0] * 2);
		diagonal_2[1] = colunaOrigem + (PecaBranca.DIRECOES[1][1]*2);
		diagonal_3[0] = linhaOrigem + (PecaBranca.DIRECOES[2][0]* 2);
		diagonal_3[1] = colunaOrigem + (PecaBranca.DIRECOES[2][1] * 2);
		diagonal_4[0] = linhaOrigem + (PecaBranca.DIRECOES[3][0] * 2);
		diagonal_4[1] = colunaOrigem + (PecaBranca.DIRECOES[3][1] * 2);
    		
    		
    		
    		
    		
    		if(tabuleiro.coordenadasValidas(diagonal_1[0],diagonal_1[1])) {
	    		if(comeuPeca(linhaOrigem,colunaOrigem,diagonal_1[0],diagonal_1[1], tabuleiro)) {
	    			System.out.println("Pode Capturar");
	    			return true;
	    		}
    		}
    		
    		if(tabuleiro.coordenadasValidas(diagonal_2[0],diagonal_2[1])) {
	    		if(comeuPeca(linhaOrigem,colunaOrigem,diagonal_2[0],diagonal_2[1], tabuleiro)) {
	    			System.out.println("Pode Capturar");
	    			return true;
	    		}
    		}
    		
    		
    		if(tabuleiro.coordenadasValidas(diagonal_3[0],diagonal_3[1])) {
    			if(comeuPeca(linhaOrigem,colunaOrigem,diagonal_3[0],diagonal_3[1], tabuleiro)) {
    				System.out.println("Pode Capturar");
    			return true;
    			}
    		}
    		
    		if(tabuleiro.coordenadasValidas(diagonal_4[0],diagonal_4[1])) {
	    		if(comeuPeca(linhaOrigem,colunaOrigem,diagonal_4[0],diagonal_4[1], tabuleiro)) {
	    			return true;
	    		}
    		
    		}
    		
    		
    	
		return false;
    }










    
    



}
