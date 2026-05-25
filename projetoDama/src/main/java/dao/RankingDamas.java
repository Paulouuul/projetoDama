package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import io.github.cdimascio.dotenv.Dotenv;

public class RankingDamas {
    private static final Dotenv dotenv = Dotenv.load();
    static final String URL = dotenv.get("DB_URL");
    static final String DATABASE_NAME = dotenv.get("DB_NAME");
    static final String FULL_URL = URL + DATABASE_NAME;
    static final String USERNAME = dotenv.get("DB_USER");
    static final String PASSWORD = dotenv.get("DB_PASSWORD");

    public void inserirRegistro(String nome_jogador, String cor_peca, int minutos_partida, int segundos_partida, int segundos_totais, int movimentos_validos) {
        try (Connection conexao = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            if (!databaseExists(conexao, DATABASE_NAME)) {
                createDatabase(conexao, DATABASE_NAME);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return;
        }

        try (Connection dbConnection = DriverManager.getConnection(FULL_URL, USERNAME, PASSWORD)) {
            if (!tableExists(dbConnection, "vencedores")) {
                createTable(dbConnection);
            }
            insertRecord(dbConnection, nome_jogador, cor_peca, minutos_partida, segundos_partida, segundos_totais, movimentos_validos);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    private boolean databaseExists(Connection conexao, String databaseName) throws SQLException {
        try (ResultSet resultSet = conexao.getMetaData().getCatalogs()) {
            while (resultSet.next()) {
                if (databaseName.equals(resultSet.getString(1))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void createDatabase(Connection conexao, String databaseName) throws SQLException {
        try (java.sql.Statement stmt = conexao.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE " + databaseName);
            System.out.println("Banco de dados criado com sucesso!");
        }
    }

    private boolean tableExists(Connection conexao, String tableName) throws SQLException {
        try (ResultSet resultSet = conexao.getMetaData().getTables(null, null, tableName, null)) {
            return resultSet.next();
        }
    }

    private void createTable(Connection conexao) throws SQLException {
        String createTableSQL = "CREATE TABLE vencedores ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "nome_jogador VARCHAR(255) NOT NULL, "
                + "cor_peca VARCHAR(10) NOT NULL, "
                + "minutos_partida INT NOT NULL, "
                + "segundos_partida INT NOT NULL, "
                + "segundos_totais INT, "
                + "movimentos_validos INT NOT NULL)";
        try (java.sql.Statement stmt = conexao.createStatement()) {
            stmt.executeUpdate(createTableSQL);
            System.out.println("Tabela criada com sucesso!");
        }
    }

    private void insertRecord(Connection conexao, String nome_jogador, String cor_peca, int minutos_partida, int segundos_partida, int segundos_totais, int movimentos_validos) throws SQLException {
        String sql = "INSERT INTO vencedores(nome_jogador, cor_peca, minutos_partida, segundos_partida, segundos_totais, movimentos_validos) VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement operacao = conexao.prepareStatement(sql)) {
            operacao.setString(1, nome_jogador);
            operacao.setString(2, cor_peca);
            operacao.setInt(3, minutos_partida);
            operacao.setInt(4, segundos_partida);
            operacao.setInt(5, segundos_totais);
            operacao.setInt(6, movimentos_validos);
            operacao.executeUpdate();
            System.out.println("Registro inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir registro: " + e.getMessage());
        }
    }

    public Object[][] obterVencedoresOrdenadosPorSegundosTotais() throws SQLException {
        // Check if database and table exist, if not create them
        try (Connection conexao = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            if (!databaseExists(conexao, DATABASE_NAME)) {
                createDatabase(conexao, DATABASE_NAME);
            }
        }

        try (Connection dbConnection = DriverManager.getConnection(FULL_URL, USERNAME, PASSWORD)) {
            if (!tableExists(dbConnection, "vencedores")) {
                createTable(dbConnection);
            }
        }

        // Fetch ordered winners
        String sql = "SELECT nome_jogador, cor_peca, minutos_partida, segundos_partida, movimentos_validos FROM vencedores ORDER BY movimentos_validos";
        try (Connection conexao = DriverManager.getConnection(FULL_URL, USERNAME, PASSWORD);
             PreparedStatement operacao = conexao.prepareStatement(sql);
             ResultSet resultado = operacao.executeQuery()) {

            List<Object[]> dataList = new ArrayList<>();

            while (resultado.next()) {
                Object[] rowData = new Object[5]; // Corrigido para 5 colunas
                rowData[0] = resultado.getString("nome_jogador");
                rowData[1] = resultado.getString("cor_peca");
                rowData[2] = resultado.getInt("minutos_partida");
                rowData[3] = resultado.getInt("segundos_partida");
                rowData[4] = resultado.getInt("movimentos_validos");
                dataList.add(rowData);
            }

            Object[][] data = new Object[dataList.size()][5]; // Corrigido para 5 colunas
            for (int i = 0; i < dataList.size(); i++) {
                data[i] = dataList.get(i);
            }

            return data;
        }
    }
}
