public class Main {
    public static void main(String[] args) {
        GrafoBrasil grafo = new GrafoBrasil();

        GrafoBrasil.printMatrizAd(grafo);
        System.out.println();
        GrafoBrasil.printMatrizInc(grafo);
        System.out.println();
        GrafoBrasil.printListaAdj(grafo);
        System.out.println();
        grafo.identificarMaxMinGrau();
        grafo.calcularFrequenciaGraus();
    }


}