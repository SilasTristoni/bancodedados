import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 7) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Cadastrar Professor");
            System.out.println("2. Listar Professores");
            System.out.println("3. Cadastrar Curso");
            System.out.println("4. Listar Cursos");
            System.out.println("5. Cadastrar Aluno");
            System.out.println("6. Listar Alunos");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer de nova linha

            switch (opcao) {
                case 1:
                    cadastrarProfessor(scanner);
                    break;
                case 2:
                    listarProfessores();
                    break;
                case 3:
                    cadastrarCurso(scanner);
                    break;
                case 4:
                    listarCursos();
                    break;
                case 5:
                    cadastrarAluno(scanner);
                    break;
                case 6:
                    listarAlunos();
                    break;
                case 7:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }

    private static void cadastrarProfessor(Scanner scanner) {
        System.out.print("Digite o ID do professor: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de nova linha
        System.out.print("Digite o nome do professor: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o departamento: ");
        String departamento = scanner.nextLine();

        Professor professor = new Professor(id, nome, departamento);
        try {
            professor.salvarNoBanco();
            System.out.println("Professor cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar professor: " + e.getMessage());
        }
    }

    private static void listarProfessores() {
        try {
            Professor.listarProfessores();
        } catch (SQLException e) {
            System.out.println("Erro ao listar professores: " + e.getMessage());
        }
    }

    private static void cadastrarCurso(Scanner scanner) {
        System.out.print("Digite o ID do curso: ");
        int idCurso = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de nova linha
        System.out.print("Digite o nome do curso: ");
        String nomeCurso = scanner.nextLine();
        System.out.print("Digite a carga horária: ");
        int cargaHoraria = scanner.nextInt();
        System.out.print("Digite o ID do professor do curso: ");
        int idProfessor = scanner.nextInt();

        Curso curso = new Curso(idCurso, nomeCurso, cargaHoraria, idProfessor);
        try {
            curso.salvarNoBanco();
            System.out.println("Curso cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar curso: " + e.getMessage());
        }
    }

    private static void listarCursos() {
        try {
            Curso.listarCursos();
        } catch (SQLException e) {
            System.out.println("Erro ao listar cursos: " + e.getMessage());
        }
    }

    private static void cadastrarAluno(Scanner scanner) {
        System.out.print("Digite o ID do aluno: ");
        int idAluno = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de nova linha
        System.out.print("Digite o nome do aluno: ");
        String nomeAluno = scanner.nextLine();
        System.out.print("Digite o CPF do aluno: ");
        String cpfAluno = scanner.nextLine();
        System.out.print("Digite a data de nascimento (YYYY-MM-DD): ");
        String dataNascimento = scanner.nextLine();
        System.out.print("Digite o ID do curso do aluno: ");
        int idCursoPorAluno = scanner.nextInt();

        Aluno aluno = new Aluno(idAluno, nomeAluno, cpfAluno, dataNascimento, idCursoPorAluno);
        try {
            aluno.salvarNoBanco();
            System.out.println("Aluno cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    private static void listarAlunos() {
        try {
            Aluno.listarAlunos();
        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
        }
    }
}
