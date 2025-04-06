package persistencia;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import universitat.GestorUniversitatsException;
import universitat.Laboratori;
import universitat.Universitat;
import universitat.Campus;
import universitat.Aula;
import universitat.AulaInformatica;

/**
 *
 */
public class GestorXML implements ProveedorPersistencia {
    private Document doc;
    private Universitat universitat;
    private String nomFitxer;

    public Document getDoc() {
        return doc;
    }

    public Universitat getUniversitat() {
        return universitat;
    }

    public String getNomFitxer() {
        return nomFitxer;
    }

    @Override
    public void desarUniversitat(String nomFitxer, Universitat universitat) throws GestorUniversitatsException {
        construeixModel(universitat);
        desarModel(nomFitxer);
    }

    @Override
    public void carregarUniversitat(String nomFitxer) throws GestorUniversitatsException {
        carregarFitxer(nomFitxer);
        llegirFitxerUniversitat();
    }

    /**
     * construeixModel
     * 
     * Paràmetres: Universitat a partir de la qual volem construir el model
     *
     * Acció:
     * - Llegir els atributs de l'objecte universitat passat per paràmetre per
     * construir
     * un model (document XML) sobre el Document doc (atribut de GestorXML).
     *
     * - L'arrel del document XML és "universitat". Aquesta arrel, l'heu d'afegir a
     * doc. Un
     * cop fet això, heu de recórrer l'ArrayList campus de la universitat passat per
     * paràmetre i per a cada campus, afegir un fill a doc. Cada fill tindrà
     * com atributs els atributs de l'objecte (nom, ubicacio i les aules)
     *
     * - En el cas de les aules de cada campus, heu de recórrer l'ArrayList
     * aules del campus corresponent i per a cada aula, afegir un fill a doc,
     * tenint en compte que hi haurà tres tipus d'elements, un per les aules
     * estàndard, un altre per les
     * aules d'informàtica i un altre per als laboratoris. Cada fill tindrà com
     * atributs els atributs de
     * l'objecte (codi, número d'aula i cost per dia, etc.)
     *
     * - Si heu de gestionar alguna excepció relacionada amb la construcció del
     * model,
     * heu de llençar una excepció de tipus GestorUniversitatsException amb codi
     * "GestorXML.model".
     *
     * Retorn: cap
     */
    public void construeixModel(Universitat universitat) throws GestorUniversitatsException {
        // Creació del document
        DocumentBuilder builder;
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new GestorUniversitatsException("GestorXML.model");
        }
        // Creem el document dom
        this.doc = builder.newDocument();

        // Construcció de l'element arrel
        // Creem l'element arrel
        Element arrel = doc.createElement("universitat");
        // Afegim l'element arrel creat al dom
        this.doc.appendChild(arrel);
        // afegim els atributs a l'arrel
        arrel.setAttribute("nom", universitat.getNomUniversitat());
        arrel.setAttribute("ubicacio", universitat.getUbicacioSeu());

        // Per a cada campus d'objecte campus creem un element campusXML i li afegim
        // els dos atributs nom i ubicació
        for (Campus elementCampus : universitat.getCampus()) {
            Element campusXML = doc.createElement("campus");
            campusXML.setAttribute("nom", elementCampus.getNomCampus());
            campusXML.setAttribute("ubicacio", elementCampus.getUbicacio());
            // L'afegim com a fill de l'element arrel o sigui universitat
            arrel.appendChild(campusXML);
            // Afegim l'aula per a cada campus
            for (Aula elementAula : elementCampus.getAules()) {

                String tipusAula = elementAula.getClass().getSimpleName();

                switch (tipusAula) {
                    case "AulaEstandard":
                        Element aulaEstandardXML = doc.createElement("aulaEstandard");
                        aulaEstandardXML.setAttribute("codi", elementAula.getCodi());
                        aulaEstandardXML.setAttribute("numeroAula", Integer.toString(elementAula.getNumeroAula()));
                        aulaEstandardXML.setAttribute("costPerDia", Double.toString(elementAula.getCostPerDia()));
                        campusXML.appendChild(aulaEstandardXML);
                        break;
                    case "AulaInformatica":
                        Element aulaInformaticaXML = doc.createElement("aulaInformatica");
                        aulaInformaticaXML.setAttribute("codi", elementAula.getCodi());
                        aulaInformaticaXML.setAttribute("numeroAula", Integer.toString(elementAula.getNumeroAula()));
                        aulaInformaticaXML.setAttribute("costPerDia", Double.toString(elementAula.getCostPerDia()));
                        aulaInformaticaXML.setAttribute("areaEnMetresQuadrats",
                                Double.toString(((AulaInformatica) elementAula).getAreaEnMetresQuadrats()));
                        campusXML.appendChild(aulaInformaticaXML);
                        break;
                    case "Laboratori":
                        Element laboratoriXML = doc.createElement("laboratori");
                        laboratoriXML.setAttribute("codi", elementAula.getCodi());
                        laboratoriXML.setAttribute("numeroAula", Integer.toString(elementAula.getNumeroAula()));
                        laboratoriXML.setAttribute("costPerDia", Double.toString(elementAula.getCostPerDia()));
                        laboratoriXML.setAttribute("capacitat",
                                String.valueOf(((Laboratori) elementAula).getCapacitat()));
                        campusXML.appendChild(laboratoriXML);
                        break;
                    default:
                        break;
                }

            }
        }
    }

    public void desarModel(String nomFitxer) throws GestorUniversitatsException {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            File f = new File(nomFitxer + ".xml");
            StreamResult result = new StreamResult(f);
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            throw new GestorUniversitatsException("GestorXML.desar");
        }
    }

    public void carregarFitxer(String nomFitxer) throws GestorUniversitatsException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            File f = new File(nomFitxer + ".xml");
            doc = builder.parse(f);
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            throw new GestorUniversitatsException("GestorXML.carrega");
        }
    }

    /**
     * llegirFitxerUniversitat
     * 
     * Paràmetres: cap
     *
     * Acció:
     * - Llegir el fitxer del vostre sistema i carregar-lo sobre l'atribut doc, per
     * assignar valors als atributs de la Universitat i la resta d'objectes que
     * formen els
     * campus de la Universitat.
     * 
     * - Primer heu de crear l'objecte de la classe Universitat a partir de l'arrel
     * del document XML
     * per després recórrer el doc i per cada fill, afegir un objecte a l'atribut
     * campus
     * de la Universitat mitjançant el mètode escaient de la classe Universitat.
     * Recordeu que com que l'arrel conté
     * els atributs nom i ubicació de la Universitat, al fer Element arrel =
     * doc.getDocumentElement();
     * ja podeu crear l'objecte de la classe.
     *
     * Retorn: cap
     */
    private void llegirFitxerUniversitat() throws GestorUniversitatsException {

        Element arrel = doc.getDocumentElement();
        // Creem l'objecte universitat a partir del element arrel que te els atributs de
        // nom i ubicacio
        Universitat universitat = new Universitat(arrel.getAttribute("nom"), arrel.getAttribute("ubicacio"));
        NodeList campusList = arrel.getChildNodes();
        for (int i = 0; i < campusList.getLength(); i++) {
            universitat.addCampus();
        }
    }
}