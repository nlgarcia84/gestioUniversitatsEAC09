package persistencia;

import universitat.GestorUniversitatsException;
import universitat.Universitat;

/**
 *
 */
public interface ProveedorPersistencia {
    public void desarUniversitat(String nomFitxer, Universitat universitat)throws GestorUniversitatsException;
    public void carregarUniversitat(String nomFitxer)throws GestorUniversitatsException; 
}
