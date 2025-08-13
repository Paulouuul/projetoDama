package view;
import javax.swing.*;

import dao.RankingDamas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import model.*;

public class ProjetoDama extends JFrame implements ActionListener {
    private Tabuleiro tabuleiro;
    private JButton[][] buttons;
    private int linhaSelecionada = -1;
    private int colunaSelecionada = -1;
    private JLabel labelTurno;
    private JLabel labelTempo;
    private final String caminhoImagens = "C:\\java\\workspace\\projetoDama\\imagens\\";
    private int segundos;
    private String jogador1;
    private String jogador2;
    public String getJogador1() {
		return jogador1;
	}

	public void setJogador1(String jogador1) {
		this.jogador1 = jogador1;
	}

	public String getJogador2() {
		return jogador2;
	}

	public void setJogador2(String jogador2) {
		this.jogador2 = jogador2;
	}

	private int segundos_da_partida;
    private int minutos_da_partida;
    private Timer timer;

    public ProjetoDama() {
        setTitle("Jogo de Damas");
        setSize(480, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tabuleiro = new Tabuleiro(8,8);
        tabuleiro.inicializarTabuleiro();

        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        initializeBoard(boardPanel);
        add(boardPanel, BorderLayout.CENTER);

        labelTurno = new JLabel();
        labelTurno.setHorizontalAlignment(SwingConstants.CENTER);
        labelTurno.setFont(new Font("Arial", Font.BOLD, 16));
        add(labelTurno, BorderLayout.SOUTH);

        labelTempo = new JLabel("Tempo: 00:00");
        labelTempo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTempo.setFont(new Font("Arial", Font.BOLD, 16));
        add(labelTempo, BorderLayout.NORTH);
        
        
        labelTurno.setHorizontalAlignment(SwingConstants.CENTER);
        labelTurno.setFont(new Font("Arial", Font.BOLD, 16));
        add(labelTurno, BorderLayout.SOUTH);
        

        updateBoardIcons();
        
        // Inicia o timer
        startTimer();
    }

    private void initializeBoard(JPanel boardPanel) {
        buttons = new JButton[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        Color lightColor = new Color(216, 216, 216);
        Color darkColor = new Color(56, 56, 56);
        
        for (int linha = 0; linha < tabuleiro.getLinhas(); linha++) {
            for (int coluna = 0; coluna < tabuleiro.getColunas(); coluna++) {
                buttons[linha][coluna] = new JButton();
                buttons[linha][coluna].setPreferredSize(new Dimension(60, 60));
                buttons[linha][coluna].setOpaque(true);
                buttons[linha][coluna].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                buttons[linha][coluna].setFont(new Font("Arial", Font.PLAIN, 20));
                buttons[linha][coluna].setBackground((linha + coluna) % 2 == 0 ? lightColor : darkColor);
                buttons[linha][coluna].setMargin(new Insets(5, 5, 5, 5));
                buttons[linha][coluna].addActionListener(this);
                boardPanel.add(buttons[linha][coluna]);
            }
        }
    }

    private void updateBoardIcons() {
        promoverPecas();
        for (int linha = 0; linha < tabuleiro.getLinhas(); linha++) {
            for (int coluna = 0; coluna < tabuleiro.getColunas(); coluna++) {
                Peca peca = tabuleiro.getPeca(linha, coluna);
                if (peca instanceof PecaBranca) {
                    buttons[linha][coluna].setIcon(new ImageIcon(ProjetoDama.class.getResource("/imagens/damaBranca.png")));
                } else if (peca instanceof PecaPreta) {
                    buttons[linha][coluna].setIcon(new ImageIcon(ProjetoDama.class.getResource("/imagens/damaPreta.png")));
                } else if (peca instanceof DamaPreta) {
                    buttons[linha][coluna].setIcon(new ImageIcon(ProjetoDama.class.getResource("/imagens/rainhaPreta.png")));
                } else if (peca instanceof DamaBranca) {
                    buttons[linha][coluna].setIcon(new ImageIcon(ProjetoDama.class.getResource("/imagens/rainhaBranca.png")));
                } else {
                    buttons[linha][coluna].setIcon(new ImageIcon(""));
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        updateLabelTurno();
        JButton button = (JButton) e.getSource();
        int linha = -1, coluna = -1;

        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                if (button == buttons[i][j]) {
                    linha = i;
                    coluna = j;
                    break;
                }
            }
            if (linha != -1) break;
        }

        if (linha != -1 && coluna != -1) {
            if (linhaSelecionada == -1 && colunaSelecionada == -1) {
                if (tabuleiro.getPeca(linha, coluna) != null) {
                    linhaSelecionada = linha;
                    colunaSelecionada = coluna;
                }
            } else {
                if (tabuleiro.movimentoValido(linhaSelecionada, colunaSelecionada, linha, coluna)) {
                    
                	String vencedor = tabuleiro.verificarVencedor();
                	if(vencedor != "Empate") {
                		if(vencedor == "Jogador1") {
                			
                			JOptionPane.showMessageDialog(this, jogador1 + " venceu em " + minutos_da_partida + " minutos e " + segundos_da_partida + "segundos com " + tabuleiro.getQtdmovimentosvalidos_brancas() +" movimentos! Parabéns!");
                			RankingDamas salvarvencedor = new RankingDamas();
                			salvarvencedor.inserirRegistro(jogador1, "Branco",  minutos_da_partida, segundos_da_partida, segundos, tabuleiro.getQtdmovimentosvalidos_brancas());
                			SwingUtilities.invokeLater(() -> {
                                RankingDamasInterface rankingInterface = new RankingDamasInterface();
                                rankingInterface.setVisible(true);
                            });
                		}else {
                			JOptionPane.showMessageDialog(this, jogador2 + " venceu em " + minutos_da_partida + " minutos e " + segundos_da_partida + "segundos com " + tabuleiro.getQtdmovimentosvalidos_pretas() +" movimentos! Parabéns!");
                			RankingDamas salvarvencedor = new RankingDamas();
                			salvarvencedor.inserirRegistro(jogador2, "Preto",  minutos_da_partida, segundos_da_partida, segundos, tabuleiro.getQtdmovimentosvalidos_pretas());
                			SwingUtilities.invokeLater(() -> {
                                RankingDamasInterface rankingInterface = new RankingDamasInterface();
                                rankingInterface.setVisible(true);
                            });
                		}
                		
                		dispose();
                    	
                    }
                    updateBoardIcons();
                    updateLabelTurno();
                } else {
                    JOptionPane.showMessageDialog(this, "Movimento inválido!");
                }
                linhaSelecionada = -1;
                colunaSelecionada = -1;
            }
        }
    }

    public void promoverPecas() {
        for (int linha = 0; linha < tabuleiro.getLinhas(); linha++) {
            for (int coluna = 0; coluna < tabuleiro.getColunas(); coluna++) {
                Peca peca = tabuleiro.getTabuleiro()[linha][coluna];
                if (peca instanceof PecaBranca && linha == 7) {
                    tabuleiro.getTabuleiro()[linha][coluna] = new DamaBranca(linha, coluna);
                } else if (peca instanceof PecaPreta && linha == 0) {
                    tabuleiro.getTabuleiro()[linha][coluna] = new DamaPreta(linha, coluna);
                }
            }
        }
    }

    public void updateLabelTurno() {
        if (tabuleiro.getTurno() == "Jogador1") {
            labelTurno.setText("Vez de "+ jogador1 + " (Peças Brancas)");
        } else {
            labelTurno.setText("Vez de "+ jogador2 + " (Peças Pretas)");
        }
    }
    
    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            

			@Override
            public void run() {
                segundos++;
                int minutos = segundos / 60;
                minutos_da_partida = minutos;
                int segundosRestantes = segundos % 60;
                segundos_da_partida = segundosRestantes;
                String tempoFormatado = String.format("Tempo: %02d:%02d", minutos, segundosRestantes);
                labelTempo.setText(tempoFormatado);
            }
        }, 1000, 1000); // 1000ms = 1 segundo, então o timer é atualizado a cada segundo.
    }

    
}
