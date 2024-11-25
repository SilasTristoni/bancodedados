package ProvaJackson3;
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