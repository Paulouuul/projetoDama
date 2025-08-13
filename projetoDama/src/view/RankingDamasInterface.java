package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.RankingDamas;

import java.awt.*;
import java.sql.SQLException;

public class RankingDamasInterface extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private RankingDamas rankingDamas;

    public RankingDamasInterface() {
        setTitle("Ranking de Vencedores de Damas");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Definindo o painel principal com fundo preto
        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        setContentPane(contentPane);
        
        contentPane.setLayout(new BorderLayout());

        // Criando a tabela
        table = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // Torna todas as células não editáveis
            }
        };
        table.setForeground(Color.WHITE); // Cor do texto
        table.setBackground(new Color(30, 30, 30)); // Cor de fundo
        table.setSelectionForeground(Color.BLACK); // Cor do texto da seleção
        table.setSelectionBackground(new Color(200, 200, 200)); // Cor de fundo da seleção
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(scrollPane, BorderLayout.CENTER);

        rankingDamas = new RankingDamas();

        // Preenchendo a tabela ao abrir a interface
        preencherTabela();
    }

    // Método para preencher a tabela com os dados do ranking
    private void preencherTabela() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nome Jogador");
        model.addColumn("Cor da Peça");
        model.addColumn("Minutos Partida");
        model.addColumn("Segundos Partida");
        model.addColumn("Quantidade de Movimentos");

        try {
            Object[][] vencedores = rankingDamas.obterVencedoresOrdenadosPorSegundosTotais();
            for (Object[] vencedor : vencedores) {
                model.addRow(vencedor);
            }
            table.setModel(model); // Defina o modelo da tabela depois de adicionar todas as linhas

            // Estilizando o cabeçalho da tabela
            JTableHeader header = table.getTableHeader();
            header.setForeground(Color.WHITE);
            header.setBackground(new Color(50, 50, 50));
            header.setFont(new Font("Arial", Font.BOLD, 16));

            // Redimensionando a tabela de acordo com o número de linhas e colunas
            table.setPreferredScrollableViewportSize(new Dimension(
                    Math.min(800, table.getPreferredSize().width),
                    Math.min(600, table.getPreferredSize().height)));

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao listar vencedores: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

//    // Método principal
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            RankingDamasInterface frame = new RankingDamasInterface();
//            frame.setVisible(true);
//        });
//    }
}
