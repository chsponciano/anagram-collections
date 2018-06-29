
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Carlos Henrique Ponciano da Silva && Vinicius Luis da Silva
 */
public class Anagrama {
    

    public String anagramas(String local, int quantidade) {
        return anagramas(leitura(local), quantidade);
    }
    
    private List leitura(final String local) {
        List<String> listaPalavras = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(local));
            while (br.ready()) {
                listaPalavras.add(br.readLine().toLowerCase());
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return listaPalavras;
    }

    private String anagramas(List<String> conteudo, final int quantidade) {
        Map<String, List<String>> anagramas = new HashMap<>();
        String aux;
        
        for (String palavra : conteudo) {
            aux = ordena(palavra);
            List<String> aList;
            
            if((aList = anagramas.get(aux))!= null){
                aList.add(palavra);
            }else{
                aList = new ArrayList<>();
                aList.add(palavra);
                anagramas.put(aux, aList);
            }
        }
        
        return montaSaida(anagramas, quantidade);
    }

    private String ordena(String p1) {
        char[] arrP1 = p1.replaceAll("\\s", "").toLowerCase().toCharArray();
        Arrays.sort(arrP1);
        return String.copyValueOf(arrP1);
    }

    private String montaSaida(Map<String, List<String>> aux, final int quantidade) {
        List<String> lista = new ArrayList<String>(){
            @Override
            public String toString() {
                String str = "Anagramas: \n";
                for (String conteudo : this) {
                    str += conteudo;
                }
                return str;
            }
        };
        
        String str = "";
        for (Map.Entry<String, List<String>> entry : aux.entrySet()) {
            List<String> list = entry.getValue();
            
            if(list.size() > quantidade){
                str = list.size() + "-[";
                for (String anagrama : list) {
                    str += anagrama + ", ";
                }
                str = str.substring(0, str.length()-2)+"]\n";
                lista.add(str);
            }
        }
        Collections.sort(lista);
        return lista.toString();
    }
    
}
