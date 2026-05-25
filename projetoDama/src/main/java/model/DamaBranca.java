package model;

public class DamaBranca extends Peca {
    public DamaBranca(int linha, int coluna) {
        super(linha, coluna, "branco");
    }

	
    @Override
    public boolean movimentoValido(int linhaDestino, int colunaDestino, Tabuleiro tabuleiro) {
        return movimentoComum(this.getLinha(), this.getColuna(), linhaDestino, colunaDestino, tabuleiro) || comeuPeca(this.getLinha(), this.getColuna(),linhaDestino,colunaDestino, tabuleiro);
    }

    
    public boolean movimentoComum(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Tabuleiro tabuleiro) {
        int deltaLinha = linhaDestino - linhaOrigem;
        int deltaColuna = colunaDestino - colunaOrigem;
        int passoupor2 = 0;
        
        boolean passouporbranca = false;
        int contadorPecasBrancas = 0;
        // Verifica se o movimento é na diagonal
        if (Math.abs(deltaLinha) != Math.abs(deltaColuna)) {
            System.out.println("Movimento inválido: não é na diagonal");
            return false;
        }

        // Verifica se a casa de destino está vazia
        if (tabuleiro.getPeca(linhaDestino, colunaDestino) != null) {
            System.out.println("Movimento inválido: a casa de destino está ocupada");
            return false;
        }

        // Verifica se o movimento ultrapassa uma peça de outra cor
        int linhaAtual = linhaOrigem;
        int colunaAtual = colunaOrigem;
        int linhaPasso = (deltaLinha > 0) ? 1 : -1;
        int colunaPasso = (deltaColuna > 0) ? 1 : -1;

        while (linhaAtual != linhaDestino - linhaPasso && colunaAtual != colunaDestino - colunaPasso) {
            linhaAtual += linhaPasso;
            colunaAtual += colunaPasso;
            if (tabuleiro.getPeca(linhaAtual, colunaAtual) != null && tabuleiro.getPeca(linhaAtual, colunaAtual) instanceof PecaBranca || tabuleiro.getPeca(linhaAtual, colunaAtual) instanceof DamaBranca) {
                System.out.println("Movimento inválido: ultrapassou uma peça de outra cor");
                return false;
            }else if(tabuleiro.getPeca(linhaAtual, colunaAtual) != null && tabuleiro.getPeca(linhaAtual, colunaAtual) instanceof PecaPreta || tabuleiro.getPeca(linhaAtual, colunaAtual) instanceof DamaPreta){
            	
            	
            	
            	if(linhaAtual != 0 && colunaAtual != 0 && linhaAtual != 7 && colunaAtual != 7) {
            	contadorPecasBrancas++;
            	passouporbranca = true;
            	
            	}
            	System.out.println("incrementou" + contadorPecasBrancas);
            	if(contadorPecasBrancas == 2) {
            		
            		
            		
            		
            		
            		passoupor2++;
            		System.out.println("Entrou no if" + passoupor2);
            		
            		
            		
            		contadorPecasBrancas = 0;
                	
                }
            	
            	
                
            	
            	
				
            }else if(contadorPecasBrancas == 1){
            	contadorPecasBrancas = 0;
            }
            
            
        }
        
        System.out.println("movimento valido retornou"  );
        boolean retorno = passoupor2  < 1 && !passouporbranca;
        System.out.println("Retornou movimentoi simples" + retorno);
        return passoupor2  < 1 && !passouporbranca;
    }


    public boolean comeuPeca(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Tabuleiro tabuleiro) {
    	int deltaLinha = linhaDestino - linhaOrigem;
        int deltaColuna = colunaDestino - colunaOrigem;
        boolean passouporbranca = false;
        int passoupor2 = 0;
        int contadorPecasPretas = 0;
        // Verifica se o movimento é na diagonal
        if (Math.abs(deltaLinha) != Math.abs(deltaColuna)) {
            System.out.println("Movimento inválido: não é na diagonal");
            return false;
        }

        // Verifica se a casa de destino está vazia
        if (tabuleiro.getPeca(linhaDestino, colunaDestino) != null) {
            System.out.println("Movimento inválido: a casa de destino está ocupada");
            return false;
        }

        // Verifica se o movimento ultrapassa uma peça de outra cor
        int linhaAtual = linhaOrigem;
        int colunaAtual = colunaOrigem;
        int linhaPasso = (deltaLinha > 0) ? 1 : -1;
        int colunaPasso = (deltaColuna > 0) ? 1 : -1;

        while (linhaAtual != linhaDestino - linhaPasso && colunaAtual != colunaDestino - colunaPasso) {
            linhaAtual += linhaPasso;
            colunaAtual += colunaPasso;
            if (tabuleiro.getPeca(linhaAtual, colunaAtual) != null && tabuleiro.getPeca(linhaAtual, colunaAtual) instanceof PecaBranca || tabuleiro.getPeca(linhaAtual, colunaAtual) instanceof DamaBranca) {
                System.out.println("Movimento inválido: ultrapassou uma peça de outra cor");
                return false;
            }else if(tabuleiro.getPeca(linhaAtual, colunaAtual) != null && tabuleiro.getPeca(linhaAtual, colunaAtual) instanceof PecaPreta || tabuleiro.getPeca(linhaAtual, colunaAtual) instanceof DamaPreta){
            	passouporbranca = true;
            	contadorPecasPretas++;
            	System.out.println("incrementou" + contadorPecasPretas);
            	if(contadorPecasPretas == 2) {
            		
            		
            		
            		
            		
            		passoupor2++;
            		System.out.println("Entrou no if" + passoupor2);
            		
            		
            		
            		contadorPecasPretas = 0;
                	
                }
            	
            	
                
            	
            	
				
            }else if(contadorPecasPretas == 1){
            	contadorPecasPretas = 0;
            }
            
            
        }

        return passoupor2  < 1 && passouporbranca;

    
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
//
//    
//    
//    public boolean podeCapturar(int linhaOrigem, int colunaOrigem, Tabuleiro tabuleiro) {
//        int[][] direcoes = PecaBranca.DIRECOES;
//        for (int[] direcao : direcoes) {
//            int linhaDestino = linhaOrigem + direcao[0];
//            int colunaDestino = colunaOrigem + direcao[1];
//            
//            if (tabuleiro.coordenadasValidas(linhaDestino, colunaDestino)) {
//                if (comeuPeca(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, tabuleiro)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    
    
}
