package app;

import java.text.*;
import java.util.Currency;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Klasa przechowujaca informacje o aktualnie wybranym jezyku i kraju
 */
public class LanguageChoice {
    static String[] languages = { "polski/Polska", "english/UK", "english/USA", "español/España"};
    static String languageChoice;
    static Locale currentLocale = new Locale("pl", "PL");
    static ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    static NumberFormat currencyFormatter;
    static Currency currencyInstance;
    static DateFormat dateFormatter;
    static MessageFormat messageForm;

    public static void setLocale(){
        String language;
        String country;

        String[] choices = languageChoice.split("/");
        switch(choices[0]){
            case "polski":
                language = "pl";
                break;
            case "english":
                language = "en";
                break;
            case "español":
                language = "es";
                break;
            default:
                language = "pl";
        }

        switch(choices[1]){
            case "Polska":
                country = "PL";
                break;
            case "UK":
                country = "GB";
                break;
            case "USA":
                country = "US";
                break;
            case "España":
                country = "ES";
                break;
            default:
                country = "PL";
        }

        currentLocale = new Locale(language, country);
        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
        currencyInstance = Currency.getInstance(currentLocale);
        dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, currentLocale);
        ResourceBundle bundle = ResourceBundle.getBundle("ChoiceBundle", currentLocale);

        double[] fileLimits = {0,1,2,5};
        String [] fileStrings = {
                bundle.getString("noFiles"),
                bundle.getString("oneFile"),
                bundle.getString("multipleFiles234"),
                bundle.getString("multipleFiles")
        };

        ChoiceFormat choiceForm = new ChoiceFormat(fileLimits, fileStrings);

        messageForm = new MessageFormat("");
        messageForm.setLocale(currentLocale);

        String pattern = bundle.getString("pattern");
        messageForm.applyPattern(pattern);

        Format[] formats = {choiceForm, null, NumberFormat.getInstance()};
        messageForm.setFormats(formats);

        /*Object[] messageArguments = {null, null};

        for (int numFiles = 0; numFiles < 30; numFiles++) {
            messageArguments[0] = 5;
            messageArguments[1] = new Integer(numFiles);
            String result = messageForm.format(messageArguments);
            System.out.println(result);
        }
*/

       /* System.out.println(messages.getString("author"));
        quantityOut = currencyFormatter.format(quantity);
        dateOut = dateFormatter.format(today);*/
    }
}
