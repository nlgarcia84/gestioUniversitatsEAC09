package persistencia;

import universitat.GestorUniversitatsException;
import universitat.Universitat;

/**
 *
 */
public class GestorPersistencia {
    private GestorXML gestor;

    public GestorXML getGestor() {
        return gestor;
    }

    public void setGestor(GestorXML pGestor) {
        gestor = pGestor;
    }

    public void desarUniversitat(String tipusPersistencia, String nomFitxer, Universitat universitat) throws GestorUniversitatsException {
        if (tipusPersistencia.equals("XML")) {
            gestor = new GestorXML();
            gestor.desarUniversitat(nomFitxer, universitat);
        }
    }

    public void carregarUniversitat(String tipusPersistencia, String nomFitxer) throws GestorUniversitatsException {
        if (tipusPersistencia.equals("XML")) {
            gestor = new GestorXML();
            gestor.carregarUniversitat(nomFitxer);
        }
    }
}
