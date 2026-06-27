/**
 * Autor: Miguel Curto Castela
 */
/**
 * Classe que representa um Guerreiro, uma subclasse de Personagens.
 */
public class Guerreiro extends Personagens {
    protected boolean armadura;
    protected int indexArmaGuerreiro;


    /**
     *
     * disponivel se quisermos obter a arma de um guerreiro usando armaGuerreiro[personagem.getindexArmaGuerreiro()];
     * @return o index da arma que pode ser usado no array armaGuerreiro
     */

    public int getindexArmaGuerreiroo()  {
        return indexArmaGuerreiro;
    }

    /**
     * Construtor da classe Guerreiro.
     *
     * @param nome               O nome do guerreiro.
     * @param armadura           Indica se o guerreiro possui armadura.
     * @param indexArmaGuerreiro O índice da arma do guerreiro.
     */
    public Guerreiro(String nome, boolean armadura, int indexArmaGuerreiro) {
        super(10, 5, 3, nome);
        this.armadura = armadura;
        this.indexArmaGuerreiro = indexArmaGuerreiro;
    }


    /**
     * override do método subir de nível da classe base.
     * Aumenta os atributos do guerreiro ao subir de nível.
     */
    @Override
    protected void subirNivel() {
        this.nivelExperiencia++;
        this.valorForça *= 1.2;
        this.valorAgilidade *= 1.10;
        this.valorInteligencia *= 1.05;
        printPersonagem();
    }

    /**
     * override do método que verifica se o guerreiro possui uma característica desejada.
     *
     * @return Verdadeiro se o guerreiro possui a característica desejada, caso contrário, falso.
     */
    @Override
    protected boolean tratoDesejado() {
        return armadura;
    }
}
