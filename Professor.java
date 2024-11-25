import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Professor {
    int idProfessor;
    String nomeProfessor;
    String departamento;
    static ArrayList<Professor> professores = new ArrayList<>();
    /*Definição das propriedades do Professor */
 
    public Professor (
        int idProfessor,
        String nomeProfessor,
        String departamento
       /*Definição do construtor do Professor */
    ){
        this.idProfessor = idProfessor;
        this.nomeProfessor = nomeProfessor;
        this.departamento = departamento;
        professores.add(this);
    }
    
    public void salvarNoBanco() throws SQLException {
        String sql = "INSERT INTO Professor (idProfessor, nomeProfessor, departamento) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, this.idProfessor);
            stmt.setString(2, this.nomeProfessor);
            stmt.setString(3, this.departamento);
            stmt.executeUpdate();
        }
    }

    // Método para listar todos os professores
    public static void listarProfessores() throws SQLException {
        String sql = "SELECT * FROM Professor";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idProfessor"));
                System.out.println("Nome: " + rs.getString("nomeProfessor"));
                System.out.println("Departamento: " + rs.getString("departamento"));
                System.out.println();
            }
        }
    }
    
    /* Método que verifica se o id digitado existe, e lança uma mensagem de exceção quando não é encontrado o id digitado, usado para definir qual o professor de cada curso*/
    static void verificaId(int id) throws Exception {
        for (Professor professorPrint : professores) {
            if (professorPrint.idProfessor == id) {
                return;
            }
        }
        throw new Exception("Professor não encontrado");
    }
    
    /* Método que verifica se o id já existe, para evitar que os usuários cadastrem uma "chave primária" repetida, caso não exista o id que, o programa segue normalmente*/
    static boolean verificaIdRepetido(int id) {
        for (Professor professorPrint : professores) {
            if (professorPrint.idProfessor == id) {
                return true;
            }
        }
        return false;
    }
    
    /* Método que busca o professor pelo id e libera as informações para listagem do professor*/
    static Professor buscaProfessor(int id) {
        for (Professor professorPrint : professores) {
            if (professorPrint.idProfessor == id) {
                return professorPrint;
            }
        }
        return null;
    }
    
}
