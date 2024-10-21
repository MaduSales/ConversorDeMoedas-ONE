import br.com.alura.conversor.modelos.*;

public class Main {
  public static void main(String[] args) {
    String apiKey = "eceaa75488bd0e9783ba6a84";
    int choice = CurrencyConverter.getUserChoice();
    CurrencyConverter.performConversion(choice, apiKey);
  }

  // @Test
  // void addition() {
  //     assertEquals(2, 1 + 1);
  // }
}