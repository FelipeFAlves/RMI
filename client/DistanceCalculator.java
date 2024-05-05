import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DistanceCalculator extends Remote {
    double Euclidiana(double[] a, double[] b) throws RemoteException;
    double CityBlock(double[] a, double[] b) throws RemoteException;
}
