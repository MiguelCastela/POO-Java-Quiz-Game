/**
 * Autor: Miguel Curto Castela
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.*;

/**
 * Classe principal que contém métodos para manipular uma lista de personagens.
 */
public class Pow {

    /**
     * Método para criar e retornar uma lista de personagens.
     *
     * @return Uma lista de personagens inicializada.
     */
    public static ArrayList<Personagens> personagens() {

        ArrayList<Personagens> personagens = new ArrayList<Personagens>();

        // Adicione 5 personagens Guerreiros
        Guerreiro Julio = new Guerreiro("GuerreiroJulio", true, 0);
        Guerreiro Jeremias = new Guerreiro("GuerreiroJeremias", true, 1);
        Guerreiro Joao = new Guerreiro("Guerreirojoao", true, 2);
        Guerreiro joca = new Guerreiro("GuerreiroJoca", false, 0);
        Guerreiro Jessica = new Guerreiro("GuerreiraJessica", false, 1);

        personagens.add(Julio);
        personagens.add(Jeremias);
        personagens.add(Joao);
        personagens.add(joca);
        personagens.add(Jessica);

        Mago Miguel = new Mago("MagoMigel", new int[] {0,4,5});
        Mago Mario = new Mago("MagoMario", new int[] {1,3,4,5});
        Mago Marta = new Mago("MagaMarta", new int[] {0,1,4});
        Mago Mariana = new Mago("MagaMariana", new int[] {0,5});
        Mago Maria = new Mago("MagaMaria", new int[] {2,3,4,5});

        personagens.add(Miguel);
        personagens.add(Mario);
        personagens.add(Marta);
        personagens.add(Mariana);
        personagens.add(Maria);

        Mercenario Luis = new Mercenario("Mercenárioluis", 0, 15);
        Mercenario Ludmilla = new Mercenario("MercenáriaLudimilla", 1, 25);
        Mercenario Luisa = new Mercenario("MercenáriaLuisa", 2, 35);
        Mercenario Lara = new Mercenario("MercenáriaLara", 0, 13);
        Mercenario Lucio = new Mercenario("MercenáriaLucio", 2, 25);

        personagens.add(Luis);
        personagens.add(Ludmilla);
        personagens.add(Luisa);
        personagens.add(Lara);
        personagens.add(Lucio);

        return personagens;
    }

    /**
     * Gera uma representação em string das personagens.
     *
     * @param personagens A lista de personagens.
     * @return Uma string representando as personagens e os níveis de experiência.
     */
    public static String Personagemtr(ArrayList<Personagens> personagens) {
        StringBuilder result = new StringBuilder();

        for (Personagens personagem : personagens) {
            result.append(personagem.nome)
                    .append(" - Nível de Experiência: ")
                    .append(personagem.getNivelExperiencia())
                    .append("\n");
        }

        return result.toString();
    }

    /**
     * Gera uma representação em string das personagens com nível de experiência superior a 10.
     *
     * @param personagens A lista de personagens.
     * @return Uma string representando os personagens com nível superior a 10.
     */
    public static String charactersStrnvlmaior10(ArrayList<Personagens> personagens) {
        StringBuilder result = new StringBuilder();

        for (Personagens personagem : personagens) {
            if (personagem.getNivelExperiencia() >= 10) {
                result.append(personagem.nome)
                        .append(" - Nível de Experiência: ")
                        .append(personagem.getNivelExperiencia())
                        .append("\n");
            }
        }

        return result.toString();
    }

    /**
     * Imprime personagens que possuem características específicas.
     *
     * @param personagens A lista de personagens.
     */
    public static String printdesejado(ArrayList<Personagens> personagens) {
        StringBuilder result = new StringBuilder();
        System.out.println("Personagens com traços específicos: ");
        for (Personagens personagem : personagens) {
            if (personagem.tratoDesejado() == true) {
                System.out.println(personagem);
            }
        }

        return result.toString();
    }


    /**
     * Método principal que executa o programa.
     *
     * @param args Os argumentos da linha de comando.
     */
    public static void main(String args[]) {
        ArrayList<Personagens> personagens = personagens();
        System.out.println("LISTA DE PERSONAGENS: \n" + Personagemtr(personagens) + "\n\n");
        System.out.println("LISTA DE PERSONAGENS COM NÍVEL SUPERIOR A 10:\n" + charactersStrnvlmaior10(personagens));
        printdesejado(personagens);
        personagens.get(2).subirNivel();
        personagens.get(10).subirNivel();
    }
}
