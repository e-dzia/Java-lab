import javax.swing.*;

public class ShowImage
{
    public static void normal(Icon icon)
    {
        JOptionPane.showMessageDialog(null,icon,LanguageChoice.messages.getString("cover"),JOptionPane.PLAIN_MESSAGE,null);
    }
}