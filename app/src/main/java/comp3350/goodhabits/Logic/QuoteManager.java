package comp3350.goodhabits.Logic;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class QuoteManager {

    //Fields
    public ArrayList<String> allQuotes = new ArrayList<>();
    private final Context quoteContext;
    private static boolean testMode = false;

    // Constructor
    public QuoteManager(Context context){
        this.quoteContext = context;
        loadQuoteList();
    }

    //This method loads all the quotes into an array list
    public void loadQuoteList(){
        AssetManager am = quoteContext.getAssets();

        try {
            InputStream is = am.open("Quotes.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null)
                allQuotes.add(line);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testMode(){
        testMode = true;
    }

    public void notTestMode(){
        testMode = false;
    }

    //returns quoteOfTheDay
    public String getQuote()
    {
        if(!testMode) {
            Random rand = new Random();
            int index = rand.nextInt(allQuotes.size());
            return allQuotes.get(index);
        }
        else{
            return "This quote is for System Testing - devTeam";
        }
    }
}
