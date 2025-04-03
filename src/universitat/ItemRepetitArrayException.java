package universitat;

public class ItemRepetitArrayException extends Exception {
  // Dos constructors, un sense paràmetres i un altre amb un missatge
  public ItemRepetitArrayException() {
  };

  public ItemRepetitArrayException(String missatgeError) {
    super(missatgeError);
  }

}