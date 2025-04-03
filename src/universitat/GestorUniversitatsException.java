package universitat;

import java.util.InputMismatchException;

public class GestorUniversitatsException extends Exception {
  private final Exception exception;

  public GestorUniversitatsException(Exception exception) {
    super(exception);
    this.exception = exception;
  }

  @Override
  public String getMessage() {
    if (exception instanceof InputMismatchException) {
      return "codi de causa 1 - Opció no numérica";
    } else if (exception instanceof IndexOutOfBoundsException) {
      return "codi de causa 2 - Superació del màxim d’universitats de l'array d’universitats de l’aplicació";
    } else if (exception instanceof ItemRepetitArrayException) {
      return "codi de causa 3 - Ja existeix la universitat";
    }
    return "error desconegut";
  }
}
