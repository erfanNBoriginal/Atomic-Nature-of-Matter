import java.util.Scanner;
public class Avogadro {
    public static void main(String[] args) {

        int count = 0;
        Scanner sc = new Scanner(System.in);
        double sum = 0;
        double convert = 0.000000175;
        double size ;

      while(sc.hasNext()) {
          if (sc.hasNextDouble()) {
              size = sc.nextDouble();
              sum += Math.pow(size * convert, 2);
              count++;
          }
      }
        double diffusion_constant = sum / (2 * count);

        double pi = Math.PI;
        double viscosity = 0.0009135;
        double radius = 0.0000005;
        double temp = 297;
        double gas_constant = 8.31446;

        double boltzmann = (6 * diffusion_constant * pi * radius * viscosity) / temp;
        double avogadro = gas_constant / boltzmann;

        System.out.println("Boltzmann = " + String.format("%.4e", boltzmann));
        System.out.println("Avogadro = " + String.format("%.4e", avogadro));

    }
}


