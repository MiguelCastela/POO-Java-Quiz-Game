/**
 * Autor: Miguel Curto Castela
 */
/**
 * Classe que representa um Mercenário, uma subclasse de Personagens.
 */
public class Mercenario extends Personagens {
    protected int balas;
    protected int indexArmaMercenario;

    /**
     *
     * disponivel se quisermos obter a arma de um mercenario usando armaMercenario[personagem.getindexArmamercenario()];
     * @return o index da arma que pode ser usado no array armaMercenario
     */

    public int getindexArmamercenario()  {
        return indexArmaMercenario;
    }

    /**
     * Construtor da classe Mercenário.
     * @param nome               O nome do mercenário.
     * @param indexarmaMercenario O índice da arma do mercenário.
     * @param numeroBalas        O número de balas do mercenário.
     */
    public Mercenario(String nome, int indexarmaMercenario, int numeroBalas) {
        super(4, 10, 4, nome);
        this.indexArmaMercenario = indexarmaMercenario;
        this.balas = numeroBalas;
    }


    /**
     * override do método subir de nível da classe base.
     * Aumenta os atributos do mercenário ao subir de nível.
     */
    @Override
    protected void subirNivel() {
        this.nivelExperiencia++;
        this.valorForça *= 1.08;
        this.valorAgilidade *= 1.20;
        this.valorInteligencia *= 1.08;
        printPersonagem();
    }

    /**
     * override do método que verifica se o mercenário possui uma característica desejada.
     *
     * @return Verdadeiro se o mercenário possui a característica desejada, caso contrário, falso.
     */
    @Override
    protected boolean tratoDesejado() {
        return (this.indexArmaMercenario == 1);
    }
}
