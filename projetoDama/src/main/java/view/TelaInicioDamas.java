package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaInicioDamas extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField textFieldPlayer1;
    private JTextField textFieldPlayer2;

    /**
     * Create the frame.
     */
    public TelaInicioDamas() {
        setTitle("Jogo de Damas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 250);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setBackground(new Color(56, 56, 56)); // light background color
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblPlayer1 = new JLabel("Jogador 1:");
        lblPlayer1.setFont(new Font("Arial", Font.BOLD, 14));
        lblPlayer1.setBounds(30, 30, 100, 25);
        lblPlayer1.setForeground(new Color(216, 216, 216)); // dark blue color
        contentPane.add(lblPlayer1);

        textFieldPlayer1 = new JTextField();
        textFieldPlayer1.setBounds(140, 30, 250, 25);
        textFieldPlayer1.setFont(new Font("Arial", Font.PLAIN, 14));
        textFieldPlayer1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        contentPane.add(textFieldPlayer1);
        textFieldPlayer1.setColumns(10);

        JLabel lblPlayer2 = new JLabel("Jogador 2:");
        lblPlayer2.setFont(new Font("Arial", Font.BOLD, 14));
        lblPlayer2.setBounds(30, 70, 100, 25);
        lblPlayer2.setForeground(new Color(216, 216, 216)); // dark blue color
        contentPane.add(lblPlayer2);

        textFieldPlayer2 = new JTextField();
        textFieldPlayer2.setBounds(140, 70, 250, 25);
        textFieldPlayer2.setFont(new Font("Arial", Font.PLAIN, 14));
        textFieldPlayer2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        contentPane.add(textFieldPlayer2);
        textFieldPlayer2.setColumns(10);

        JButton btnStartGame = new JButton("Iniciar Jogo");
        btnStartGame.setFont(new Font("Arial", Font.BOLD, 14));
        btnStartGame.setBackground(new Color(68, 90, 62)); // cornflower blue color
        btnStartGame.setForeground(new Color(216, 216, 216));
        btnStartGame.setBorder(new LineBorder(new Color(25, 75, 12), 2));
        btnStartGame.setBounds(140, 120, 150, 35);
        btnStartGame.setFocusPainted(false);
        btnStartGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String player1Name = textFieldPlayer1.getText();
                String player2Name = textFieldPlayer2.getText();

                JOptionPane.showMessageDialog(TelaInicioDamas.this, "Jogo de damas iniciado!\nJogador 1: " + player1Name + "\nJogador 2: " + player2Name);
                SwingUtilities.invokeLater(() -> {
                    ProjetoDama game = new ProjetoDama();
                    
                    game.setJogador1(player1Name);
                    game.setJogador2(player2Name);
                    game.updateLabelTurno();
                    game.setVisible(true);
                    dispose();
                });
            }
        });
        contentPane.add(btnStartGame);
        
        // Adicionando o botão para exibir o ranking
        JButton btnExibirRanking = new JButton("Exibir Ranking");
        btnExibirRanking.setFont(new Font("Arial", Font.BOLD, 14));
        btnExibirRanking.setBackground(new Color(68, 90, 62)); // cornflower blue color
        btnExibirRanking.setForeground(new Color(216, 216, 216));
        btnExibirRanking.setBorder(new LineBorder(new Color(25, 75, 12), 2));
        btnExibirRanking.setBounds(300, 120, 150, 35);
        btnExibirRanking.setFocusPainted(false);
        btnExibirRanking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aqui criamos e exibimos a interface do ranking
                SwingUtilities.invokeLater(() -> {
                    RankingDamasInterface rankingInterface = new RankingDamasInterface();
                    rankingInterface.setVisible(true);
                });
            }
        });
        contentPane.add(btnExibirRanking);
    }

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    TelaInicioDamas frame = new TelaInicioDamas();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
}
