package universitat;

public class GestorUniversitatsException extends Exception {

  private String tipusExcepcio;

  public GestorUniversitatsException(String tipusExcepcio) {
    super();
    this.tipusExcepcio = tipusExcepcio;
  }

  @Override
  public String getMessage() {

    // String tipoExcepcion = causa.getClass().getSimpleName();
    String missatgeExcepcio;

    switch (tipusExcepcio) {
      case "1":
        missatgeExcepcio = "codi de causa 1: Opció no numérica";
        break;
      case "2":
        missatgeExcepcio = "codi de causa 2: Superació del màxim d’universitats de l'array d’universitats de l’aplicació";
        break;
      case "3":
        missatgeExcepcio = "codi de causa 3: Ja existeix la universitat";
        break;
      case "GestorXML.model":
        missatgeExcepcio = "codi de causa GestorXML.model: Error en la construcció del model";
        break;
      case "GestorXML.desar":
        missatgeExcepcio = "codi de causa GestorXML.desar: Error al desar el model";
        break;
      case "GestorXML.carrega":
        missatgeExcepcio = "codi de causa GestorXML.carrega: Error al carregar al model";
        break;
      default:
        missatgeExcepcio = "error desconegut";
    }
    return missatgeExcepcio;
  }
}
