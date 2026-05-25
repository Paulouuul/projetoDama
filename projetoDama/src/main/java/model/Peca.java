package model;

public  class Peca {
    protected int linha;
    protected int coluna;
    protected String cor;
    protected static final int[][] DIRECOES = {{1, 1}, {-1, -1},{-1,1},{1,-1}};
    public Peca(int linha, int coluna, String cor) {
        this.linha = linha;
        this.coluna = coluna;
        this.cor = cor;
    }



    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }


	
	public void mover(int linhaDestino, int colunaDestino) {
        this.linha = linhaDestino;
        this.coluna = colunaDestino;
    }

	protected String getCor() {
		return cor;
	}



	protected boolean movimentoValido(int linhaDestino, int colunaDestino, Tabuleiro tabuleiro) {
		return false;
	}



	protected boolean comeuPeca(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino,Tabuleiro tabuleiro) {
		return false;
	}
	
	
	public boolean movimentoComum(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Tabuleiro tabuleiro) {
        
        return false;
    }



	public boolean podeCapturar(int linhaDestino, int colunaDestino, Tabuleiro tabuleiro) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean podeMover(int linhaOrigem, int colunaOrigem, Tabuleiro tabuleiro) {
		return false;
	}
	
	
	
	
	
	



	



	

	
	
	

	

   



	
		











	
	
}
