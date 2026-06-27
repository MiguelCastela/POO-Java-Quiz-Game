/**
 * Autor: Miguel Curto Castela
 */


/**
 * Classe abstrata que representa personagens no jogo.
 */
public abstract class Personagens {

    /**
     * Nome da personagem.
     */
    protected String nome;

    /**
     * Armas disponíveis para a personagem mercenários.
     */
    protected String armaMercenario[] = {"Pedras", "Arco", "Pistola"};

    /**
     * Armas disponíveis para a personagem guerreiros.
     */
    protected String armaGuerreiro[] = {"Facas", "Machados", "Espadas"};

    /**
     * Itens possiveis na mochila de um mago.
     */
    protected String mochilaMago[] = {"sementes de abobora", "Folhas de Pereira", "Sementees de Tomate", "Folhas de Nogueira"};

    /**
     * Nível de experiência, valor de força, valor de agilidade e valor de inteligência do personagem.
     */
    protected int nivelExperiencia, valorForça, valorInteligencia, valorAgilidade;

    /**
     * Construtor da classe Personagens .
     *
     * @param valorForça      Valor da força do personagem.
     * @param valorAgilidade  Valor da agilidade do personagem.
     * @param valorInteligencia Valor da inteligência do personagem.
     * @param nome            Nome da personagem.
     */
    public Personagens(int valorForça, int valorAgilidade, int valorInteligencia, String nome) {
        this.nivelExperiencia = (int) (Math.random() * 25);
        this.valorForça = valorForça;
        this.valorAgilidade = valorAgilidade;
        this.valorInteligencia = valorInteligencia;
        this.nome = nome;
    }

    /**
     * Imprime as estatísticas da personagem após o aumento de nível.
     */
    public void printPersonagem() {
        System.out.println("Estatísticas da Personagem depois do levelup!   ");
        System.out.println("Nome: " + this.nome);
        System.out.println("Nível: " + this.nivelExperiencia);
        System.out.println("Agilidade: " + this.valorAgilidade);
        System.out.println("Força: " + this.valorForça);
        System.out.println("Inteligência: " + this.valorInteligencia);
    }

    /**
     * Método abstrato para aumentar o nível da personagem.
     */
    protected abstract void subirNivel();

    /**
     * Método abstrato que verifica se a personagem possui um trato desejado (mercenarios com arco,magos com sementes de abobora ou guerreiros com armadura).
     *
     * @return {@code true} se o tratado desejado for encontrado, caso contrário, {@code false}.
     */
    protected abstract boolean tratoDesejado();

    /**
     * Representação da personagem.
     *
     * @return Uma string que representa a personagem e niveldeexperiencia.
     */
    public String toString() {
        return ("Nome: " + nome + " Nível: " + nivelExperiencia);
    }

    /**
     * Obtém o nível de experiência da personagem.
     *
     * @return O nível de experiência da personagem.
     */
    protected int getNivelExperiencia() {
        return this.nivelExperiencia;
    }
}
