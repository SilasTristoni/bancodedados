
package ProvaJackson3;

import java.util.ArrayList;

public class Aluno {
    int idAluno;
    String nomeAluno;
    String cpfAluno;
    String dataNascimento;
    int idCursoPorAluno;
    /*Definição das propriedades do aluno */
    static ArrayList<Aluno> alunos = new ArrayList<>();

    public Aluno(
            int idAluno,
            String nomeAluno,
            String cpfAluno,
            String dataNascimento,
            int idCursoPorAluno) {
        this.idAluno = idAluno;
        this.nomeAluno = nomeAluno;
        this.cpfAluno = cpfAluno;
        this.dataNascimento = dataNascimento;
        this.idCursoPorAluno = idCursoPorAluno;
        alunos.add(this);
        /*Definição do construtor do aluno */
    }
    
    /* Método que verifica se o id digitado existe, e lança uma mensagem de exceção quando não é encontrado o id digitado, usado para definir para qual curso cada aluno está matriculado*/
    static void verificaId(int id) throws Exception {
        for (Aluno alunoPrint : alunos) {
            if (alunoPrint.idAluno == id) {
                return;
            }
        }
        throw new Exception("Aluno não encontrado");
    }
    /* Método que busca o aluno pelo id e libera as informações para listagem do aluno*/
    static Aluno buscaAluno(int id) {
        for (Aluno alunoPrint : alunos) {
            if (alunoPrint.idAluno == id) {
                return alunoPrint;
            }
        }
        return null;
    }
    /*método utilizado para contar alunos por curso através do id, inicia um contador onde cada vez que o id do aluno for igual a um id de curso existente, ele aumenta */
    static int contarAlunosPorCurso(int id) {
        int cont = 0;
        for (Aluno alunoPrint : alunos) {
            if (alunoPrint.idCursoPorAluno == id) {
                cont++;
            }
        }
        return cont;
    }
    
    /* Método que verifica se o id já existe, para evitar que os usuários cadastrem uma "chave primária" repetida, caso não exista o id que, o programa segue normalmente*/
    static boolean verificaIdRepetido(int id) {
        for (Aluno alunoPrint : alunos) {
            if (alunoPrint.idAluno == id) {
                return true;
            }
        }
        return false;
    }
    /*método que limita o cpf da variavel cpfAluno, onde o número máximo de elementos é passado no parâmetro */
    static String limitacpf(String cpfAluno, int maximo){
        if (cpfAluno.length()<= maximo) {
            return cpfAluno;
        }
        else{
            return cpfAluno.substring(0, maximo);
        }
    }
    /*substring é um método que reduz o número de elementos para o valor máximo de parâmetro, isto é, caso o usuário cadastre uma string de 100 caracteres e o máximo é 20, 80 caracteres serão perdidos e só será retornado a quantidade máxima de elementos*/
}
