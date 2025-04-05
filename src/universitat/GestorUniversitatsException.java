package universitat;

public class GestorUniversitatsException extends Exception {

  public GestorUniversitatsException(Exception exception) {
    super(exception);
  }

  @Override
  public String getMessage() {
    Throwable causa = getCause();

    String tipoExcepcion = causa.getClass().getSimpleName();
    String missatgeExcepcio = "error desconegut";

    switch (tipoExcepcion) {
      case "InputMismatchException":
        missatgeExcepcio = "codi de causa 1: Opció no numérica";
        break;
      case "IndexOutOfBoundsException":
        missatgeExcepcio = "codi de causa 2: Superació del màxim d’universitats de l'array d’universitats de l’aplicació";
        break;
      case "ItemRepetitArrayException":
        missatgeExcepcio = "codi de causa 3: Ja existeix la universitat";
        break;
      default:
        break;
    }
    return missatgeExcepcio;
  }
}
