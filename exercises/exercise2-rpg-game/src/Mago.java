/**
 * Autor: Miguel Curto Castela
 */
import java.util.Arrays;

/**
 * Representa uma personagem Mago no jogo.
 */
public class Mago extends Personagens {

        /**
         * Itens mágicos na mochila do mago.
         */
        protected int[] mochila;

        /**
         *
         * disponivel se quisermos obter os items que o mago tem usando [personagem.getindexmochila()];
         * @return os indexes da mochila que pode ser usado no array mochilaMago
         */

        public int[] getMochila()  {
                return mochila;
        }

        /**
         * Construtor da classe Mago.
         *
         * @param nome    O nome do mago.
         * @param mochila O array representando os itens mágicos na mochila.
         */
        public Mago(String nome, int[] mochila) {
                super(2, 4, 9, nome);
                this.mochila = Arrays.copyOf(mochila, mochila.length);
        }

        /**
         * override do método subir de nível da classe base.
         * Aumenta o nível do mago e ajusta os atributos de acordo.
         * Imprime os detalhes atualizados do personagem.
         */
        @Override
        protected void subirNivel() {
                this.nivelExperiencia++;
                this.valorForça *= 1.05;
                this.valorAgilidade *= 1.10;
                this.valorInteligencia *= 1.20;
                printPersonagem();
        }

        /**
         *  override do método que verifica se o mago possui uma característica desejada
         * Verifica se o mago tem o item mágico desejado na sua mochila.
         *
         * @return {@code true} se o item mágico desejado for encontrado, caso contrário, {@code false}.
         */
        @Override
        protected boolean tratoDesejado() {
                for (int i = 0; i < mochila.length; i++) {
                        if (mochila[i] == 0) {
                                return true;
                        }
                }
                return false;
        }
}
