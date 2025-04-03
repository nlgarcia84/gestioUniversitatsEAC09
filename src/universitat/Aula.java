package universitat;

/**
 *
 */
public abstract class Aula implements UnitatUniversitat {

    private String codi;
    private int numeroAula;
    private double costPerDia;

    public Aula(String codi, int numeroAula, double costPerDia) {
        this.codi = codi;
        this.numeroAula = numeroAula;
        this.costPerDia = costPerDia;
    }

    public String getCodi() {
        return this.codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public int getNumeroAula() {
        return this.numeroAula;
    }

    public void setNumeroAula(int numeroAula) {
        this.numeroAula = numeroAula;
    }

    public double getCostPerDia() {
        return this.costPerDia;
    }

    public void setCostPerDia(double costPerDia) {
        this.costPerDia = costPerDia;
    }

    @Override
    public void updateUnitatUniversitat() {

        System.out.println("\nCodi de l'aula: " + this.getCodi());
        System.out.println("\nEntra el nou valor de codi: ");
        this.codi = DADES.nextLine();

        System.out.println("\nNúmero d'aula: " + this.getNumeroAula());
        System.out.println("\nEntra el nou número d'aula: ");
        this.numeroAula = Integer.parseInt(DADES.nextLine());

        System.out.println("\nCost per dia de l'aula: " + this.getCostPerDia());
        System.out.println("\nEntra el nou cost per dia: ");
        this.costPerDia = Integer.parseInt(DADES.nextLine());

    }

    @Override
    public void showUnitatUniversitat() {

        System.out.println("\nLes dades de l'aula amb codi " + this.getCodi() + " són:");
        System.out.println("\nNúmero d'aula: " + this.getNumeroAula());
        System.out.println("\nCost per dia: " + this.getCostPerDia());
        System.out.println("\nCost de manteniment: " + this.costManteniment() + " EUR");

    }

    public abstract double costManteniment();

}
