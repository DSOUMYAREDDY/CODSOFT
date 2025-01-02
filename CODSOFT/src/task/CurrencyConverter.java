package task;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Prompt user for base currency and target currency
            System.out.print("Enter the base currency (e.g., USD): ");
            String baseCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Enter the target currency (e.g., INR): ");
            String targetCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Enter the amount to convert: ");
            double amount = scanner.nextDouble();

            // Fetch exchange rate from API
            String apiUrl = "https://freetestapi.com/api/v1/currencies";
            String response = getApiResponse(apiUrl);

            if (response != null && response.contains(baseCurrency) && response.contains(targetCurrency)) {
                double baseRate = extractRate(response, baseCurrency);
                double targetRate = extractRate(response, targetCurrency);

                // Perform currency conversion
                double convertedAmount = (amount / baseRate) * targetRate;

                // Display result
                System.out.printf("%.2f %s = %.2f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);
            } else {
                System.out.println("Invalid currency codes or failed to fetch rates. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Method to fetch API response
    @SuppressWarnings("deprecation")
	private static String getApiResponse(String apiUrl) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            } else {
                System.out.println("Error: Unable to fetch data. HTTP code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }
        return response.toString();
    }

    // Method to extract currency rate from JSON response
    private static double extractRate(String json, String currencyCode) {
        int currencyIndex = json.indexOf(currencyCode);
        if (currencyIndex == -1) {
            throw new IllegalArgumentException("Currency code not found in JSON response.");
        }
        int startIndex = json.indexOf(":", currencyIndex) + 1;
        int endIndex = json.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = json.indexOf("}", startIndex);
        }
        return Double.parseDouble(json.substring(startIndex, endIndex).trim());
    }
}
