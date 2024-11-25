
package ProvaJackson3;

import java.util.ArrayList;
import java.util.List;
 
public class Curso {
    int idCurso;
    String nomeCurso;
    int cargaHoraria;
    int idProfessorDoCurso;
    static ArrayList<Curso> cursos = new ArrayList<>();
    /*Definição das propriedades do Curso */
public Curso(
    int idCurso,
    String nomeCurso,
    int cargaHoraria,
    int idProfessorDoCurso
    ) {
        this.idCurso = idCurso;
        this.nomeCurso = nomeCurso;
        this.cargaHoraria = cargaHoraria;
        this.idProfessorDoCurso = idProfessorDoCurso;
        cursos.add(this);
        /*Definição do construtor do Curso */
    }
    /* Método que verifica se o id digitado existe, e lança uma mensagem de exceção quando não é encontrado o id digitado, usado para definir para qual curso cada professor está ensinando*/
    static void verificaId(int id) throws Exception {
        for (Curso cursoPrint : cursos) {
            if (cursoPrint.idCurso == id) {
                return;
            }
        }
        throw new Exception("Curso não encontrada");
    }
 
    /* Método que busca o curso pelo id e libera as informações para listagem do curso*/
    static Curso buscaCurso(int id) {
        for (Curso cursoPrint : cursos) {
            if (cursoPrint.idCurso == id) {
                return cursoPrint;
            }
        }
        return null;
    }

    /* Método que verifica se o id já existe, para evitar que os usuários cadastrem uma "chave primária" repetida, caso não exista o id que, o programa segue normalmente*/
    static boolean verificaIdRepetido(int id){
        for (Curso cursoPrint : cursos) {
            if (cursoPrint.idCurso==id) {
                return true;
            }
        }
        return false;
    }
    

    /*Método do desafio para imprimir quantas horas o professor tem contemplado a partir dos cursos em que ele está ensinando*/
    static int totalCargaHorariaPorProfessor(int id){
        int acum=0;
        for (Curso cursoPrint : cursos) {
            if (cursoPrint.idProfessorDoCurso==id) {
                acum=acum+cursoPrint.cargaHoraria;
            }
        }
        return acum;
    }
    
    /*Método que cria uma list com todos os cursos que o professor está ensinando, a carga horária de cada curso e o número de alunos totais em cada curso*/
    static String cursosAssociadosComCargaHorariaEAlunos(int id){
        List<String> cursosAssociadosComCargaHorariaEAlunos = new ArrayList<>();
        for (Curso cursoPrint : cursos) {
            if (cursoPrint.idProfessorDoCurso==id) {
                int totalDeAlunos = Aluno.contarAlunosPorCurso(cursoPrint.idCurso);
                cursosAssociadosComCargaHorariaEAlunos.add(cursoPrint.nomeCurso + " ("+cursoPrint.cargaHoraria +"H) " + totalDeAlunos + " Alunos");
            }
        }
        return String.join(", ", cursosAssociadosComCargaHorariaEAlunos);
        /*String.join para concatenar os elementos da lista, tornando mais intuitivo na listagem*/
    }

}
