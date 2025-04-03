/*
 * Classe que defineix una aula d'informàtica d'un campus. 
 * Es defineix pel seu codi, número d'aula, el seu cost per dia i el tamany de l'aula en metres quadrats.
 */
package universitat;

/**
 *
 */
public class AulaInformatica extends Aula {

    private double areaEnMetresQuadrats;

    public AulaInformatica(String codi, int numeroAula, double costPerDia, double areaEnMetresQuadrats) {
        super(codi, numeroAula, costPerDia);
        this.areaEnMetresQuadrats = areaEnMetresQuadrats;
    }

    public double getAreaEnMetresQuadrats() {
        return areaEnMetresQuadrats;
    }

    public void setAreaEnMetresQuadrats(double areaEnMetresQuadrats) {
        this.areaEnMetresQuadrats = areaEnMetresQuadrats;
    }

    public static AulaInformatica addAulaInformatica() {
        System.out.println("Codi de l'aula d'informàtica:");
        String codi = DADES.nextLine();

        System.out.println("Número de l'aula d'informàtica:");
        int numeroAula = Integer.parseInt(DADES.nextLine());

        System.out.println("Cost per dia de l'aula d'informàtica:");
        double costPerDia = Integer.parseInt(DADES.nextLine());

        System.out.println("Àrea en metres quadrats de l'aula d'informàtica:");
        double areaEnMetresQuadrats = Double.parseDouble(DADES.nextLine());

        return new AulaInformatica(codi, numeroAula, costPerDia, areaEnMetresQuadrats);
    }

    @Override
    public void updateUnitatUniversitat() {

        System.out.println("\nNoves dades de l'aula d'informàtica");

        super.updateUnitatUniversitat();

        System.out.println("\nÀrea en metres quadrats de l'aula d'informàtica: " + this.getAreaEnMetresQuadrats());
        System.out.println("Entra el nou valor de l'àrea en metres quadrats:");
        areaEnMetresQuadrats = Double.parseDouble(DADES.nextLine());
    }

    @Override
    public void showUnitatUniversitat() {
        System.out.println("\nLes dades de l'aula d'informàtica són:");

        super.showUnitatUniversitat();

        System.out.println("\nÀrea en metres quadrats: " + this.getAreaEnMetresQuadrats());
    }

    @Override
    public double costManteniment() {
        return (this.getCostPerDia() * 0.3) + (this.getAreaEnMetresQuadrats() * 0.05);
    }
}