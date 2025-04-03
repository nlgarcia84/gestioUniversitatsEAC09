/*
 * Classe que defineix un Laboratori d'un campus.
 * Es defineix pel seu codi, numeroAula, cost per dia i la seva capacitat en nombre de persones.
 */
package universitat;

/**
 *
 */
public class Laboratori extends Aula {
    private int capacitat;

    public Laboratori(String codi, int numeroAula, double costPerDia, int capacitat) {
        super(codi, numeroAula, costPerDia);
        this.capacitat = capacitat;
    }

    public int getCapacitat() {
        return capacitat;
    }

    public void setCapacitat(int capacitat) {
        this.capacitat = capacitat;
    }

    public static Laboratori addLaboratori() {

        System.out.println("Codi del Laboratori:");
        String codi = DADES.nextLine();

        System.out.println("Número del Laboratori:");
        int numeroAula = Integer.parseInt(DADES.nextLine());

        System.out.println("Cost per dia del Laboratori:");
        double costPerDia = Integer.parseInt(DADES.nextLine());

        System.out.println("Capacitat del Laboratori:");
        int capacitat = Integer.parseInt(DADES.nextLine());

        return new Laboratori(codi, numeroAula, costPerDia, capacitat);
    }

    @Override
    public void updateUnitatUniversitat() {

        System.out.println("\nNoves dades del Laboratori:");

        super.updateUnitatUniversitat();

        System.out.println("\nCapacitat del Laboratori: " + this.getCapacitat());
        System.out.println("Entra el nou valor de la capacitat:");
        capacitat = Integer.parseInt(DADES.nextLine());

    }

    @Override
    public void showUnitatUniversitat() {

        System.out.println("\nLes dades del Laboratori són:");

        super.showUnitatUniversitat();

        System.out.println("\nCapacitat: " + this.getCapacitat());
    }

    @Override
    public double costManteniment() {
        return (double) (this.getCostPerDia() * 0.35) + (this.getCapacitat() * 0.1);
    }
}