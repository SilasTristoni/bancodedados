package ProvaJackson3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws Exception {
        final String url = "jdbc:mysql://localhost:3306/JacksonBanco";
        final String user = "root";
        final String password = "";
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("*** Sistema de Cadastro de Professores, Cursos e Alunos ***");

        int opcao = 0;

        do {
            System.out.println("\nMenu:");
            System.out.println("[1] - Cadastrar Professor");
            System.out.println("[2] - Cadastrar Curso");
            System.out.println("[3] - Cadastrar Aluno");
            System.out.println("[4] - Listar Professores");
            System.out.println("[5] - Listar Cursos");
            System.out.println("[6] - Listar Alunos");
            System.out.println("[7] - Encerrar Programa");
            System.out.println("\nDigite uma opção:");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Limpeza do buffer

            switch (opcao) {
                case 1:
                    System.out.println("\nCadastrar Professor:");
                    System.out.println("Digite o Nome do Professor:");
                    String nomeProfessor = scanner.nextLine();
                    System.out.println("Digite o Departamento do Professor:");
                    String departamento = scanner.nextLine();

                    try (Connection con = DriverManager.getConnection(url, user, password);
                         Statement stm = con.createStatement()) {
                        String sql = "INSERT INTO Professor (nomeProfessor, departamento) VALUES ('" + nomeProfessor + "', '" + departamento + "')";
                        int result = stm.executeUpdate(sql);
                        if (result > 0) {
                            System.out.println("Professor cadastrado com sucesso!");
                        } else {
                            System.out.println("Falha na execução");
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao cadastrar professor: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("\nCadastrar Curso:");
                    System.out.println("Digite o Nome do Curso:");
                    String nomeCurso = scanner.nextLine();
                    System.out.println("Digite a Carga Horária do Curso:");
                    int cargaHoraria = scanner.nextInt();
                    System.out.println("Digite o ID do Professor do Curso:");
                    int idProfessorCurso = scanner.nextInt();

                    try (Connection con = DriverManager.getConnection(url, user, password);
                         Statement stm = con.createStatement()) {
                        String sql = "INSERT INTO Curso (nomeCurso, cargaHoraria, idProfessorDoCurso) VALUES ('" + nomeCurso + "', " + cargaHoraria + ", " + idProfessorCurso + ")";
                        int result = stm.executeUpdate(sql);
                        if (result > 0) {
                            System.out.println("Curso cadastrado com sucesso!");
                        } else {
                            System.out.println("Falha na execução");
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao cadastrar curso: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("\nCadastrar Aluno:");
                    System.out.println("Digite o Nome do Aluno:");
                    String nomeAluno = scanner.nextLine();
                    System.out.println("Digite a Data de Nascimento do Aluno:");
                    String dataNascimento = scanner.nextLine();
                    System.out.println("Digite o CPF do Aluno:");
                    String cpf = scanner.nextLine();
                    System.out.println("Digite o ID do Curso do Aluno:");
                    int idCursoAluno = scanner.nextInt();

                    try (Connection con = DriverManager.getConnection(url, user, password);
                         Statement stm = con.createStatement()) {
                        String sql = "INSERT INTO Aluno (nomeAluno, dataNascimento, cpf, idCurso) VALUES ('" + nomeAluno + "', '" + dataNascimento + "', '" + cpf + "', " + idCursoAluno + ")";
                        int result = stm.executeUpdate(sql);
                        if (result > 0) {
                            System.out.println("Aluno cadastrado com sucesso!");
                        } else {
                            System.out.println("Falha na execução");
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("\nListar Professores:");
                    try (Connection con = DriverManager.getConnection(url, user, password);
                         Statement stm = con.createStatement();
                         ResultSet rs = stm.executeQuery("SELECT * FROM professor")) {
                        while (rs.next()) {
                            System.out.println("- ID: " + rs.getInt("idProfessor") + " - Nome: " + rs.getString("nomeProfessor") + " - Departamento: " + rs.getString("departamento"));
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao listar professores: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("\nListar Cursos:");
                    try (Connection con = DriverManager.getConnection(url, user, password);
                         Statement stm = con.createStatement();
                         ResultSet rs = stm.executeQuery("SELECT * FROM curso")) {
                        while (rs.next()) {
                            System.out.println("- ID: " + rs.getInt("idCurso") + " - Nome: " + rs.getString("nomeCurso") + " - Carga Horária: " + rs.getInt("cargaHoraria") + " - ID do Professor: " + rs.getInt("idProfessorDoCurso"));
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao listar cursos: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("\nListar Alunos:");
                    try (Connection con = DriverManager.getConnection(url, user, password);
                         Statement stm = con.createStatement();
                         ResultSet rs = stm.executeQuery("SELECT * FROM aluno")) {
                        while (rs.next()) {
                            System.out.println("- ID: " + rs.getInt("idAluno") + " - Nome: " + rs.getString("nomeAluno") + " - Data de Nascimento: " + rs.getString("dataNascimento") + " - CPF: " + rs.getString("cpf") + " - ID do Curso: " + rs.getInt("idCurso"));
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao listar alunos: " + e.getMessage());
                    }
                    break;

                case 7:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        } while (opcao != 7);

        scanner.close();
    }
}
