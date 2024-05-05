import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
        Registry conexao = LocateRegistry.getRegistry("192.168.0.164", 1100);
        DistanceCalculator servidor = (DistanceCalculator) conexao.lookup("DistanceCalculator");
        double[] objA = new double[4]; //objeto A 
        double[] objB = new double[4]; //objeto B
        double[] objC = new double[4]; //objeto C
        Scanner leitor = new Scanner(System.in); // Ler valor

        System.out.println("Objeto 1");
        for (int i = 0; i < 4; i++) {
            double var = leitor.nextDouble();
            objA[i] = var;
        }

        System.out.println("Objeto 2");
        for (int i = 0; i < 4; i++) {
            double var = leitor.nextDouble();
            objB[i] = var;
        }

        System.out.println("Objeto 3");
        for (int i = 0; i < 4; i++) {
            double var = leitor.nextDouble();
            objC[i] = var;
        }
        
        // Escolha do tipo de distância
        System.out.println("Escolha 1 para Euclidiana e 0 para City Block: ");
       int tipo = leitor.nextInt();

        double AB, AC, BC;
        if (tipo == 1) {
            AB = servidor.Euclidiana(objA, objB);
            AC = servidor.Euclidiana(objA, objC);
            BC = servidor.Euclidiana(objB, objC);
        } else {
            AB = servidor.CityBlock(objA, objB);
            AC = servidor.CityBlock(objA, objC);
            BC = servidor.CityBlock(objB, objC);
        }

        System.out.println("Distância A para B: " + AB);
        System.out.println("Distância A para C: " + AC);
        System.out.println("Distância B para C: " + BC);

        // Encontra o par mais similar
        if (AB < AC && AB < BC) {
            System.out.println("O par mais similar é A-B");
        } else if (AC < AB && AC < BC) {
            System.out.println("O par mais similar é A-C");
        } else {
            System.out.println("O par mais similar é B-C");
        }

        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.toString());
            e.printStackTrace();
        }
    }
}
