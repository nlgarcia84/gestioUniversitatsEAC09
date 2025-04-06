package principal;

import universitat.Universitat;

import java.util.InputMismatchException;
import java.util.Scanner;

import persistencia.GestorPersistencia;
import persistencia.GestorXML;
import universitat.AulaEstandard;
import universitat.AulaInformatica;
import universitat.GestorUniversitatsException;
import universitat.ItemRepetitArrayException;
import universitat.Laboratori;

/**
 *
 */
public class Application {
    private final static Scanner DADES = new Scanner(System.in);

    private static Universitat[] universitats = new Universitat[10];
    private static int pUniversitats = 0; // Primera posició buida del vector universitats
    private static Universitat universitatActual = null;
    private final static String FITXER = "universitat";
    private static GestorPersistencia gp = new GestorPersistencia();
    private static GestorXML gestor = new GestorXML();
    private static String nomFitxer;

    public static void main(String[] args) {
        try {
            menuPrincipal();
        } catch (GestorUniversitatsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void menuPrincipal() throws GestorUniversitatsException {
        int opcio = 0;

        do {
            System.out.println("\nMenú principal. Selecciona una opció:");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Gestió d'universitats");
            System.out.println("\n2. Gestió de campus");
            System.out.println("\n3. Gestió d'aules estàndard");
            System.out.println("\n4. Gestió d'aules d'informàtica");
            System.out.println("\n5. Gestió de laboratoris");
            System.out.println("\n");

            try {
                opcio = DADES.nextInt();
                DADES.nextLine();
            } catch (InputMismatchException e) {
                throw new GestorUniversitatsException("1");
            }

            switch (opcio) {
                case 0:
                    break;
                case 1:
                    menuUniversitats();
                    break;
                case 2:
                    if (universitatActual != null) {

                        menuCampus();

                    } else {
                        System.out.println(
                                "\nPrimer s'ha de seleccionar la universitat al menú 1. Gestió d'universitats.");
                    }
                    break;
                case 3:
                    if (universitatActual != null) {

                        menuAules(1);

                    } else {
                        System.out.println(
                                "\nPrimer s'ha de seleccionar la universitat al menú 1. Gestió d'universitats.");
                    }
                    break;
                case 4:
                    if (universitatActual != null) {

                        menuAules(2);

                    } else {
                        System.out.println(
                                "\nPrimer s'ha de seleccionar la universitat al menú 1. Gestió d'universitats.");
                    }
                    break;
                case 5:
                    if (universitatActual != null) {

                        menuAules(3);

                    } else {
                        System.out.println(
                                "\nPrimer s'ha de seleccionar la universitat al menú 1. Gestió d'universitats.");
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuUniversitats() throws InputMismatchException, GestorUniversitatsException {
        int opcio;

        do {
            int indexSel;
            System.out.println("\nMenú d'universitats. Selecciona una opció:");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Seleccionar");
            System.out.println("\n3. Modificar");
            System.out.println("\n4. Llistar");
            System.out.println("\n5. Carregar universitats");
            System.out.println("\n6. Desar Universitats");

            System.out.println("\n");

            opcio = DADES.nextInt();
            DADES.nextLine();

            switch (opcio) {
                case 0:
                    break;
                case 1:
                    try {

                        Universitat novaUniversitat = Universitat.addUniversitat();

                        indexSel = selectUniversitat(novaUniversitat);
                        if (indexSel == -1) {
                            if (pUniversitats < universitats.length) {
                                universitats[pUniversitats] = novaUniversitat;
                                pUniversitats++;
                            } else {
                                throw new IndexOutOfBoundsException("S'ha arribat al limit d'universitats");
                            }
                        } else {
                            throw new ItemRepetitArrayException("Universitat repetida");
                        }
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        throw new GestorUniversitatsException("2");
                    } catch (ItemRepetitArrayException e) {
                        throw new GestorUniversitatsException("3");
                    }
                case 2:
                    indexSel = selectUniversitat(null);

                    if (indexSel >= 0) {
                        universitatActual = universitats[indexSel];
                    } else {
                        System.out.println("\nNo existeix aquesta universitat");
                    }
                    break;
                case 3:
                    indexSel = selectUniversitat(null);

                    if (indexSel >= 0) {
                        universitats[indexSel].updateUnitatUniversitat();
                    } else {
                        System.out.println("\nNo existeix aquesta universitat");
                    }
                    break;
                case 4:
                    for (int i = 0; i < pUniversitats; i++) {
                        universitats[i].showUnitatUniversitat();
                    }
                    break;
                case 5:
                    System.out.println(FITXER + " a carregar:");
                    nomFitxer = DADES.nextLine() + ".xml";
                    break;
                case 6:
                    gp.desarUniversitat("XML", universitatActual.getNomUniversitat(), universitatActual);
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
            }
        } while (opcio != 0);
    }

    public static void menuCampus() throws InputMismatchException {
        int opcio;

        do {
            System.out.println("\nMenú de campus. Selecciona una opció:");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Modificar");
            System.out.println("\n3. Llistar");
            System.out.println("\n");

            opcio = DADES.nextInt();
            DADES.nextLine();

            switch (opcio) {
                case 0:
                    break;
                case 1:
                    universitatActual.addCampus();
                    break;
                case 2:
                    int indexSel = universitatActual.selectCampus(null);

                    if (indexSel >= 0) {
                        universitatActual.getCampus().get(indexSel).updateUnitatUniversitat();
                    } else {
                        System.out.println("\nNo existeix aquest Campus");
                    }
                    break;
                case 3:
                    for (int i = 0; i < universitatActual.getCampus().size(); i++) {
                        universitatActual.getCampus().get(i).showUnitatUniversitat();
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuAules(int tipus) throws GestorUniversitatsException {
        int opcio;

        do {
            System.out.println("\nMenú d'aules. Selecciona una opció:");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Modificar");
            System.out.println("\n3. Llistar");

            try {
                opcio = DADES.nextInt();
                DADES.nextLine();
            } catch (InputMismatchException e) {
                throw new GestorUniversitatsException("1");
            }

            switch (opcio) {
                case 0:
                    break;
                case 1:
                    switch (tipus) {
                        case 1:
                            universitatActual.addAulaEstandardCampus();
                            break;
                        case 2:
                            universitatActual.addAulaInformaticaCampus();
                            break;
                        case 3:
                            universitatActual.addLaboratoriCampus();
                            break;
                    }
                    break;
                case 2:
                    switch (tipus) {
                        case 1:
                            universitatActual.updateAulaEstandardCampus();
                            break;
                        case 2:
                            universitatActual.updateAulaInformaticaCampus();
                            break;
                        case 3:
                            universitatActual.updateLaboratoriCampus();
                            break;
                    }
                    break;
                case 3:
                    for (int i = 0; i < universitatActual.getCampus().size(); i++) {
                        for (int j = 0; j < universitatActual.getCampus().get(i).getAules().size(); j++) {
                            if (universitatActual.getCampus().get(i).getAules().get(j) instanceof AulaEstandard
                                    && tipus == 1
                                    ||
                                    universitatActual.getCampus().get(i).getAules().get(j) instanceof AulaInformatica
                                            && tipus == 2
                                    ||
                                    universitatActual.getCampus().get(i).getAules().get(j) instanceof Laboratori
                                            && tipus == 3) {
                                universitatActual.getCampus().get(i).getAules().get(j).showUnitatUniversitat();
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static Integer selectUniversitat(Universitat Universitat) {

        String nom;

        if (Universitat == null) {
            System.out.println("\nNom de la universitat:");
            nom = DADES.nextLine();
        } else {
            nom = Universitat.getNomUniversitat();
        }

        for (int i = 0; i < pUniversitats; i++) {
            if (universitats[i].getNomUniversitat().equals(nom)) {
                return i;
            }
        }

        return -1;
    }
}
