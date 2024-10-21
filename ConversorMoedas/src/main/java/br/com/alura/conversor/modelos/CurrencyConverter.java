import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import java.until.Scanner;

public class CurrencyConverter{

  public static int getUserChoice(){
    Scanner scanner = new Scanner(System.in);

    System.out.println("Bem-vindo ao super conversor de moedas!");
    System.out.println("1) Real -> Dólar");
    System.out.println("2) Dólar -> Real");
    System.out.println("3) Real -> Euro");
    System.out.println("4) Euro -> Real");
    System.out.println("5) Real -> Peso Argentino");
    System.out.println("6) Peso Argentino -> Real");

    return scanner.nextInt();
  }

  public static double getExchangeRate(String fromCurrency, String toCurrency, String apiKey){
    String url = "https://v6/exchangerate-api.com/v6/" + apiKey + "/latest/" + fromCurrency + "/" + toCurrency;

    try {
      HttpResponse<JsonNode> response = Unirest.get(url).asJson();

      return response.getBody().getObject().getDouble("conversion_rate");
    } catch (Exception e)
      {
        System.out.println("Erro ao obter a taxa de câmbio: " + e.getMessage());
        return -1;
      }
  }

  public static void performConversion(int choice, String apiKey){
    String fromCurrency = "";
    String toCurrency = "";

    switch(choice){
        case 1: 
        fromCurrency = "USD";
        toCurrency = "BRL";
        break;

        case 2:
        fromCurrency = "BRL";
        toCurrency = "USD";
        break;

      case 3:
        fromCurrency = "BRL";
        toCurrency = "EUR";
        break;

      case 4: 
        fromCurrency = "EUR";
        toCurrency = "BRL";
        break;

      case 5:
        fromCurrency = "BRL";
        toCurrency = "ARS";
        break;

      case 6:
        fromCurrency = "ARS";
        toCurrency = "BRL";
        break;
      default: System.out.println("inválido.");
        return;
    }

    double amount = getAmountToConverter();

    double exchangeRate = getExchangeRate(fromCurrency, toCurrency, apiKey);
    if (exchangeRate == -1){
      System.out.println("Erro ao converter");
      return;
    }

    System.out.println("%.2f %s é igual a %.2f %s", amount, fromCurrency, amount * exchangeRate, toCurrency);
  }
}