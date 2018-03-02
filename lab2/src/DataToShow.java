import java.text.MessageFormat;
import java.util.Date;

public class DataToShow {
    static String[] columnNames = { "kolumna1", "kolumna2", "kolumna3"};
    static Object[][] data = { {"data1", "data2", "data3"},{"polski/Polska", "angielski/UK", "angielski/USA"}};
    private static int numberOfObjects;
    static String textNumOfObj;

    public static void prepareData(){
        translateData();
        translateHeader();
        numberOfObjects = data.length;
        transpateNumberOfObjects();
    }

    private static void transpateNumberOfObjects(){
        Object[] messageArguments = {null, null};
        messageArguments[0] = numberOfObjects;
        messageArguments[1] = numberOfObjects;
        textNumOfObj = LanguageChoice.messageForm.format(messageArguments);
    }

    private static void translateData(){
        data = new Object[Main.collection.books.size()][];
        for (int i = 0; i < Main.collection.books.size(); i++){
            data[i] = Main.collection.books.get(i).toArray();

            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j].getClass().getName().equals(Double.class.getName())) {
                    data[i][j] = LanguageChoice.currencyFormatter.format(data[i][j]);
                } else if (data[i][j].getClass().getName().equals(Date.class.getName())) {
                    data[i][j] = LanguageChoice.dateFormatter.format(data[i][j]);
                }
            }
        }
    }

    private static void translateHeader(){
        columnNames = new String[Main.collection.header.length];
        for (int i = 0; i < Main.collection.header.length; i++){
            if (Main.collection.header[i].equals("price")){
                columnNames[i] = MessageFormat.format(
                        LanguageChoice.messages.getString("price"),
                        LanguageChoice.currencyInstance.getDisplayName(LanguageChoice.currentLocale),
                        LanguageChoice.currencyInstance.getSymbol(LanguageChoice.currentLocale));
            }
            else columnNames[i] = LanguageChoice.messages.getString(Main.collection.header[i]);
        }
    }

}
