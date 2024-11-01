package br.com.alura.conversor.modelos;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import java.util.Scanner;

public class CurrencyConverter {

    public static int getUserChoice(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao super conversor de moedas! Selecione uma opção: ");
        System.out.println("1) Real -> Dólar");
        System.out.println("2) Dólar -> Real");
        System.out.println("3) Real -> Euro");
        System.out.println("4) Euro -> Real");
        System.out.println("5) Real -> Peso Argentino");
        System.out.println("6) Peso Argentino -> Real");
        return scanner.nextInt();
    }

    public static double getAmountToConverter(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o valor do converter: ");
        return sc.nextDouble();
    }

    public static double getExchangeRate(String fromCurrency, String toCurrency, String apiKey){
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + fromCurrency;

        try {
            HttpResponse<JsonNode> response = Unirest.get(url).asJson();

            if (response.getBody().getObject().has("conversion_rates")) {
                JSONObject rates = response.getBody().getObject().getJSONObject("conversion_rates");
                if (rates.has(toCurrency)) {
                    return rates.getDouble(toCurrency); // Retorna a taxa de câmbio específica
                } else {
                    System.out.println("Taxa de câmbio para " + toCurrency + " não encontrada.");
                    return -1;
                }
            } else {
                System.out.println("Taxa de câmbio não encontrada.");
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter taxa de câmbio: " + e.getMessage());
            return -1;
        }

    }

    public static void performConversion(int choice, String apiKey){
        String fromCurrency = "";
        String toCurrency = "";

        switch (choice){
            case 1:
                fromCurrency = "BRL";
                toCurrency = "USD";
                break;

            case 2:
                fromCurrency = "USD";
                toCurrency = "BRL";
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
        if (exchangeRate == 1){
            System.out.println("Erro ao converter");
            return;
        }

        System.out.printf("%.2f %s é igual a %.2f %s", amount, fromCurrency, amount * exchangeRate, toCurrency);
    }
}
