public class Exercicio1 {
    //função que coloca a primeira letra de cada palavra de uma string como maiúscula e converte as restantes para minúsculas
    //caso nome dos países no array estiver mal escrito / houver mais do que um codigo para o mesmo país e estes estejam escritos de maneira diferente
    public static String corrigenome(String input) {
        //verifica se a entrada é nula ou vazia (invalida)
        if (input == null || input.isEmpty()) {
            return input;
        }
        //para paises com mais do que uma palavra, da split para cada palavra ser separada
        String[] words = input.split(" ");
        for (int i = 0; i < words.length; i++) {
            //converte a string inteira para minúsculas
            String word = words[i].toLowerCase();
            //se for acronimo (ex:EUA) deixa em maiuscula
            if (word.length() <= 3) {
                word = word.toUpperCase();
            } else {
                //adiciona a primeira letra como maiusula, para paises com mais de uma palavra faz isso para cada uma (atraves do for)
                word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
            }
            //substitui a palavra no array
            words[i] = word;
        }
        //para strings de 2 palavras ou mais volta a juntar as substrings numa
        return String.join(" ", words);
    }
    public static void encontrapaises(String[] URLs, String[][] Paises, String[] codigopais, String[] ocorrenciaspais) {
        int outrocounts = 0;
        //itera sobre as URLs
        for (int i = 0; i < URLs.length; i++) {
            //Divide as URLs pelo caracter '/' (usando o metodo split)
            String[] partes = URLs[i].split("/");
            if (partes.length >= 3) {
                //extrai a parte que contém o código do país (www.nomedosite.codigodepaís)
                String codigo = partes[2];
                //divide o código do país por pontos '.' (usando o metodo split)
                String pais[] = codigo.split("[.]");
                //armazena o último elemento que corresponde ao código do país no array codigopais
                codigopais[i] = pais[pais.length - 1];
            } else {
                //imprime uma mensagem de erro para URLs que não contêm três barras (invalidos)
                System.out.println("URL: " + URLs[i] + " | URL Invalido (não contém 3 barras)");
                continue;
            }
            boolean found = false;

            //itera sobre o array Paises
            for (int j = 0; j < Paises.length; j++) {
                //se o código armazenado anteriormente em codigopais corresponder a um codigo no array Paises (em Paises [j][0]]
                if (codigopais[i].equals(Paises[j][0])) {
                    //armazena o nome do país (presente em Paises[j][1]]) no array ocorrenciaspais, e atravez da função 'corrigenome', no seu nome formal
                    ocorrenciaspais[i] = corrigenome(Paises[j][1]);
                    found = true;
                }
            }
            if (!found) {
                //incrementa outrocounts para codigos sem correspondencia no array Paises
                //feito um contador separado para ser possivel meter os 'Outro(s)' no final do output, mantendo a ordem desejada para o resto dos países
                outrocounts += 1;
            }
        }
        contaeimprimeocorrencias(ocorrenciaspais, outrocounts);
    }
    public static void contaeimprimeocorrencias(String[] ocorrenciaspais, int outrocounts) {
        //itera sobre o array ocorrenciaspais para contar as ocorrências de cada país (no array)
        for (int i = 0; i < ocorrenciaspais.length; i++) {
            //passa para a proxima interacao se o país já tiver sido processado(substituido por null)
            if (ocorrenciaspais[i] == null) continue;
            //count para os URLs de paises
            int count = 1;
            //itera novamente para contar as ocorrências do mesmo país no array
            for (int j = i + 1; j < ocorrenciaspais.length; j++) {
                if (ocorrenciaspais[i].equals(ocorrenciaspais[j])) {
                    count++;
                    //marca a entrada duplicada como nula para evitar repetições
                    ocorrenciaspais[j] = null;
                }
            }
            //imprime o nome do país juntamente com a contagem (por ordem de aparencia no array dos URLs)
            System.out.println(ocorrenciaspais[i] + ": " + count);
        }
        //por fim, imprime a contagem de 'Outro(s)'
        if (outrocounts > 0) {
            System.out.println("Outro(s): " + (outrocounts));
        }
    }
    public static void main(String[] args) {
        String[] URLs = {"https://www.dei.uc.pt/poao/exames", "http://www.scs.org/index.html",
                "https://www.nato.int/events", "https://www.btu.de/",
                "https://www.dei.uc.pt/poao/exames", "http://www.eth.ch/index.html",
                "http://www.osu.edu/"};
        String[][] Paises = {{"pt", "Portugal"}, {"org", "EUA"}, {"fr", "França"},
                {"uk", "Reino Unido"}, {"de", "Alemanha"}, {"edu", "EUA"}};
        String[] codigopais = new String[URLs.length];
        String[] ocorrenciaspais = new String[codigopais.length];
        encontrapaises(URLs, Paises, codigopais, ocorrenciaspais);
    }
}