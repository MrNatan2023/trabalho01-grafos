import java.util.*;

public class GrafoBrasil {
    public static List<String> list = new ArrayList<>();
    private static final String[] estados = {
            "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS",
            "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC",
            "SE", "SP", "TO"
    };

    private static final Map<String, List<String>> fronteiras = new HashMap<>();

    static {
        fronteiras.put("AC", Arrays.asList("AM", "RO"));
        fronteiras.put("AL", Arrays.asList("BA", "PE", "SE"));
        fronteiras.put("AM", Arrays.asList("AC", "RR", "RO", "MT", "PA"));
        fronteiras.put("AP", List.of("PA"));
        fronteiras.put("BA", Arrays.asList("AL", "SE", "PE", "PI", "MG", "ES", "GO", "TO"));
        fronteiras.put("CE", Arrays.asList("RN", "PB", "PE", "PI"));
        fronteiras.put("DF", Arrays.asList("GO", "MG"));
        fronteiras.put("ES", Arrays.asList("BA", "MG", "RJ"));
        fronteiras.put("GO", Arrays.asList("DF", "MG", "BA", "MS", "MT", "TO"));
        fronteiras.put("MA", Arrays.asList("PI", "TO", "PA"));
        fronteiras.put("MG", Arrays.asList("DF", "GO", "BA", "ES", "RJ", "SP", "MS"));
        fronteiras.put("MS", Arrays.asList("GO", "MT", "MG", "SP", "PR"));
        fronteiras.put("MT", Arrays.asList("RO", "AM", "PA", "TO", "GO", "MS"));
        fronteiras.put("PA", Arrays.asList("AP", "MA", "TO", "MT", "AM"));
        fronteiras.put("PB", Arrays.asList("RN", "CE", "PE"));
        fronteiras.put("PE", Arrays.asList("PB", "RN", "CE", "AL", "BA", "PI"));
        fronteiras.put("PI", Arrays.asList("MA", "CE", "PE", "BA", "TO"));
        fronteiras.put("PR", Arrays.asList("MS", "SP", "SC"));
        fronteiras.put("RJ", Arrays.asList("ES", "MG", "SP"));
        fronteiras.put("RN", Arrays.asList("CE", "PB"));
        fronteiras.put("RO", Arrays.asList("AC", "AM", "MT"));
        fronteiras.put("RR", Arrays.asList("AM", "PA"));
        fronteiras.put("RS", List.of("SC"));
        fronteiras.put("SC", Arrays.asList("RS", "PR"));
        fronteiras.put("SE", Arrays.asList("BA", "AL"));
        fronteiras.put("SP", Arrays.asList("PR", "RJ", "MG", "MS"));
        fronteiras.put("TO", Arrays.asList("MA", "PI", "BA", "GO", "MT", "PA"));
    }

    public int[][] matrizAdjacencia() {
        int[][] matriz = new int[estados.length][estados.length];
        for (int i = 0; i < estados.length; i++) {
            for (String vizinho : fronteiras.get(estados[i])) {
                int j = Arrays.asList(estados).indexOf(vizinho);
                matriz[i][j] = 1;
                matriz[j][i] = 1;
            }
        }
        return matriz;
    }

    public int[][] matrizIncidencia() {
        List<int[]> listaIncidencia = new ArrayList<>();
        for (int i = 0; i < estados.length; i++) {
            for (String vizinho : fronteiras.get(estados[i])) {
                int j = Arrays.asList(estados).indexOf(vizinho);
                if (i < j) {
                    int[] incidencias = new int[estados.length];
                    incidencias[i] = 1;
                    incidencias[j] = 1;
                    listaIncidencia.add(incidencias);
                }
            }
        }
        return listaIncidencia.toArray(new int[listaIncidencia.size()][]);
    }

    public Map<String, List<String>> listaAdjacencia() {
        return fronteiras;
    }

    // Método para calcular o grau dos vértices
    public Map<String, Integer> calcularGraus() {
        Map<String, Integer> graus = new HashMap<>();
        for (String estado : fronteiras.keySet()) {
            graus.put(estado, fronteiras.get(estado).size());
        }
        return graus;
    }

    public void identificarMaxMinGrau() {
        Map<String, Integer> graus = calcularGraus();
        int maxGrau = Collections.max(graus.values());
        int minGrau = Collections.min(graus.values());

        System.out.println("Estados com maior número de vizinhos:");
        for (Map.Entry<String, Integer> entry : graus.entrySet()) {
            if (entry.getValue() == maxGrau) {
                System.out.println(entry.getKey() + " (Grau: " + entry.getValue() + ") Vizinhos: " + fronteiras.get(entry.getKey()));
            }
        }

        System.out.println("\nEstados com menor número de vizinhos:");
        for (Map.Entry<String, Integer> entry : graus.entrySet()) {
            if (entry.getValue() == minGrau) {
                System.out.println(entry.getKey() + " (Grau: " + entry.getValue() + ") Vizinhos: " + fronteiras.get(entry.getKey()));
            }
        }
    }

    public void calcularFrequenciaGraus() {
        Map<String, Integer> graus = calcularGraus();
        Map<Integer, Integer> frequenciaGraus = new HashMap<>();

        for (int grau : graus.values()) {
            frequenciaGraus.put(grau, frequenciaGraus.getOrDefault(grau, 0) + 1);
        }

        System.out.println("\nFrequência dos graus dos vértices:");
        for (Map.Entry<Integer, Integer> entry : frequenciaGraus.entrySet()) {
            System.out.println("Grau " + entry.getKey() + ": " + entry.getValue() + " estados");
        }
    }
    static void printListaAdj(GrafoBrasil grafo) {
        System.out.println("\nLista de Adjacência:");
        Map<String, List<String>> listaAdj = grafo.listaAdjacencia();
        for (Map.Entry<String, List<String>> entry : listaAdj.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    static void printMatrizInc(GrafoBrasil grafo) {
        int a = 0;
        System.out.println("\nMatriz de Incidência:");

        System.out.print("   ");
        for (String estado : estados){
            System.out.print(estado + " ");
        }
        System.out.println();
        int[][] matrizInc = grafo.matrizIncidencia();
        for (int[] incidencia : matrizInc) {
            System.out.println("e" + a + Arrays.toString(incidencia));
            a++;
        }
    }

    static void printMatrizAd(GrafoBrasil grafo) {
        int i = 0;
        System.out.println("Matriz de Adjacência:");

        System.out.print("   ");
        for (String estado : estados){
            System.out.print(estado + " ");

        }
        System.out.println();
        int[][] matrizAdj = grafo.matrizAdjacencia();
        for (int[] ints : matrizAdj) {
            System.out.println(estados[i] + Arrays.toString(ints));
            i++;

        }
    }
}