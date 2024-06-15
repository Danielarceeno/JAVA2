import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PrincipalJogos {

    public static void main(String[] args) {
        ArrayList<Jogo> lista = new ArrayList<Jogo>();
        int op;
        //Menu de opções
        do {
            op = menu();
            if(op == 1) {
                lista.add(lerJogo());
            } else if(op == 2) {
                listarTodos(lista);
            } else if(op == 3) {
                buscarPorPlataforma(lista);
            } else if(op == 4) {
                buscarPorNota(lista);
            } else if(op == 5) {
                maiorNotaPlataformaAno(lista);
            } else if(op == 6) {
                msg("Saindo do programa.");
            } else {
                msg("Opção inválida.");
            }
        } while(op != 6);
    }
    //estrutura dos menus
    private static int menu() {
        String m = "1 - Cadastrar Jogo\n"
                 + "2 - Listar Todos\n"
                 + "3 - Buscar por Plataforma\n"
                 + "4 - Buscar por Nota\n"
                 + "5 - Jogo com maior nota para uma plataforma em um ano\n"
                 + "6 - Sair";
        return Integer.parseInt(JOptionPane.showInputDialog(m));
    }
    //mensagens
    private static void msg(String txt) {
        JOptionPane.showMessageDialog(null, txt);
    }
    //estrutura dos jogos
    private static String escreveJogo(Jogo j) {
        return "Título: " + j.titulo + "\n" +
               "Plataforma: " + j.plataforma + "\n" +
               "Ano: " + j.ano + "\n" +
               "Nota: " + j.nota;
    }
    //leitura dos jogos
    private static Jogo lerJogo() {
        Jogo j = new Jogo();
        j.titulo = lerString("Título");
        j.plataforma = lerString("Plataforma");
        j.ano = lerInt("Ano de lançamento (1990 a 2024)", 1990, 2024);
        j.nota = lerDouble("Nota (1 a 5)", 1, 5);
        return j;
    }
    //leitura para diferenciar os tipos de dados
    private static String lerString(String txt) {
        String str = JOptionPane.showInputDialog(txt);
        if (str.trim().equals("")) {
            return lerString(txt);
        }
        return str.toUpperCase();
    }
    // verifica valores inteiros (ano)
    private static int lerInt(String txt, int min, int max) {
        int i = Integer.parseInt(JOptionPane.showInputDialog(txt));
        if (i < min || i > max) {
            return lerInt(txt, min, max);
        }
        return i;
    }
    //verifica valores double (nota)
    private static double lerDouble(String txt, double min, double max) {
        double d = Double.parseDouble(JOptionPane.showInputDialog(txt));
        if (d < min || d > max) {
            return lerDouble(txt, min, max);
        }
        return d;
    }

    private static void listarTodos(ArrayList<Jogo> lista) {
        String ret = "";
        for (Jogo j : lista) {
            ret += escreveJogo(j) + "\n\n";
        }
        msg(ret);
    }
    //verifica por plataforma
    private static void buscarPorPlataforma(ArrayList<Jogo> lista) {
        String plataforma = lerString("Informe a plataforma");
        String ret = "";
        for (Jogo j : lista) {
            if (j.plataforma.equalsIgnoreCase(plataforma)) {
                ret += escreveJogo(j) + "\n\n";
            }
        }
        msg(ret.isEmpty() ? "Nenhum jogo encontrado para a plataforma: " + plataforma : ret);
    }
    //busca por nota
    private static void buscarPorNota(ArrayList<Jogo> lista) {
        double nota = lerDouble("Informe a nota mínima (1 a 5)", 1, 5);
        String ret = "";
        for (Jogo j : lista) {
            if (j.nota >= nota) {
                ret += escreveJogo(j) + "\n\n";
            }
        }
        msg(ret.isEmpty() ? "Nenhum jogo encontrado com nota maior ou igual a: " + nota : ret);
    }
    //busca por maior nota
    private static void maiorNotaPlataformaAno(ArrayList<Jogo> lista) {
        String plataforma = lerString("Informe a plataforma");
        int ano = lerInt("Informe o ano de lançamento (1990 a 2024)", 1990, 2024);
        Jogo melhorJogo = null;
        for (Jogo j : lista) {
            if (j.plataforma.equalsIgnoreCase(plataforma) && j.ano == ano) {
                if (melhorJogo == null || j.nota > melhorJogo.nota) {
                    melhorJogo = j;
                }
            }
        }
        msg(melhorJogo == null ? "Nenhum jogo encontrado para a plataforma " + plataforma + " no ano " + ano : escreveJogo(melhorJogo));
    }
}


