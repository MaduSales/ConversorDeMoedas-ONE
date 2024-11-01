//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
import br.com.alura.conversor.modelos.*;

public class Main {
    public static void main(String[] args) {
        String apiKey = "eceaa75488bd0e9783ba6a84";
        int choice = CurrencyConverter.getUserChoice();
        CurrencyConverter.performConversion(choice, apiKey);
    }
}