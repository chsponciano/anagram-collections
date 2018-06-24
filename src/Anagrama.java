
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Henrique Ponciano da Silva && Vinicius Luis da Silva
 */
public class Anagrama {

    public String anagramas(final String local, final int quantidade) {
        return anagramas(leitura(local), quantidade);
    }

    private List leitura(final String local) {
        List<String> listaPalavras = new LinkedList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(local));
            while (br.ready()) {
                listaPalavras.add(br.readLine().toLowerCase());
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Anagrama.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaPalavras;
    }

    private String anagramas(List<String> conteudo, final int quantidade) {
        List<List<String>> anagramas = new LinkedList();

        ListIterator<String> iterator;
        List<String> aux;
        String p2;

        for (String p1 : conteudo) {
            if (p1 == null) {
                continue;
            }
            iterator = conteudo.listIterator(0);
            aux = new LinkedList();
            
            while (iterator.hasNext()) {
                p2 = iterator.next();
                if (p1.equals(p2)) {
                    aux.add(p1);
                    iterator.set(null);
                } else if ((p1 != null) && (p2 != null) && eAnagrama(p1, p2)) {
                    aux.add(p2);
                    iterator.set(null);
                }
            }
            anagramas.add(aux);
        }

        return montaSaida(anagramas, quantidade);
    }

    private boolean eAnagrama(String p1, String p2) {
        char[] arrP1 = p1.replaceAll("\\s", "").toLowerCase().toCharArray();
        char[] arrP2 = p2.replaceAll("\\s", "").toLowerCase().toCharArray();

        Arrays.sort(arrP1);
        Arrays.sort(arrP2);

        return (Arrays.equals(arrP1, arrP2));
    }

    private String montaSaida(List<List<String>> anagramas, final int quantidade) {
        String aux = "";
        for (List<String> anagrama : anagramas) {
            if (anagrama.size() == quantidade) {
                aux += quantidade + "-[";
                for (String p1 : anagrama) {
                    aux += p1 + ", ";
                }
                aux = aux.substring(0, aux.length()-2) + "]\n";
            }
        }
        return aux;
    }
}
