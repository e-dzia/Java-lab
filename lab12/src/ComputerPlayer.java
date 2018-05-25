import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class ComputerPlayer extends Player{
    
    ComputerPlayer(int min, int max) {
        super(min, max);
    }
    
    ComputerPlayer(ArrayList<Integer> otherCards) {
        super(otherCards);
    }
    
    int chooseCard(int opponentCard){
        if (cards.size() < 1) return -1;
        int toReturn = -1;
        
        ScriptEngineManager engineMgr = new ScriptEngineManager();
        ScriptEngine engine = engineMgr.getEngineByName("JavaScript");
        Invocable invocableEngine = (Invocable) engine;
        
        InputStream is = this.getClass().getResourceAsStream("/scripts/" + findScript());
        try {
            Reader reader = new InputStreamReader(is);
            engine.eval(reader);
            toReturn = (int) invocableEngine.invokeFunction("mystrategy", cards, opponentCards, opponentCard);
        } catch (ScriptException ex) {
            ex.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return toReturn;
    }
    
    private String findScript(){
        File folder = new File("src/scripts/");
        File[] listOfFiles = folder.listFiles();
    
        String name = "";
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".js")) {
                name = file.getName();
                break;
            }
        }
        return name;
    }
}
