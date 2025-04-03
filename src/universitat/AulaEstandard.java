/*
 * Classe que defineix una aula estàndard d'un campus.
 * Es defineixen pel seu codi, número d'aula i el seu cost per dia
 */
package universitat;

/**
 *
 */
public class AulaEstandard extends Aula {

    public AulaEstandard(String codi, int numeroAula, double costPerDia) {
        super(codi, numeroAula, costPerDia);
    }

    public static AulaEstandard addAulaEstandard() {

        System.out.println("\nCodi de l'Aula Estandàrd:");
        String codi = DADES.nextLine();

        System.out.println("\nNúmero de l'Aula Estandàrd:");
        int numeroAula = Integer.parseInt(DADES.nextLine());

        System.out.println("\nCost per dia de l'Aula Estàndard:");
        double costPerDia = Double.parseDouble(DADES.nextLine());

        return new AulaEstandard(codi, numeroAula, costPerDia);
    }

    @Override
    public void updateUnitatUniversitat() {

        System.out.println("\nNoves dades de l'Aula Estàndard");

        super.updateUnitatUniversitat();

        System.out.println("\nCost per dia de l'Aula Estàndard: " + this.getCostPerDia());
        System.out.println("\nEntra nou valor del cost per dia:");
        setCostPerDia(Double.parseDouble(DADES.nextLine()));
    }

    @Override
    public void showUnitatUniversitat() {

        System.out.println("\nLes dades de l'Aula Estàndard són:");

        super.showUnitatUniversitat();

        System.out.println("\nCost per dia: " + this.getCostPerDia());
    }

    @Override
    public double costManteniment() {
        return (this.getCostPerDia() * 0.2);
    }
}