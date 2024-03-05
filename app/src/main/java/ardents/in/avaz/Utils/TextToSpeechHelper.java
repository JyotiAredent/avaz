package ardents.in.avaz.Utils;

import static android.speech.tts.TextToSpeech.QUEUE_FLUSH;

import android.content.Context;
import android.widget.Toast;

import java.util.Locale;

public class TextToSpeechHelper implements android.speech.tts.TextToSpeech.OnInitListener {

    private android.speech.tts.TextToSpeech textToSpeech;
    private Context context;

    public TextToSpeechHelper(Context context) {
        this.context = context;
        textToSpeech=new android.speech.tts.TextToSpeech(context,this);
    }

    @Override
    public void onInit(int status) {
        if (status == android.speech.tts.TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(new Locale("en", "IN"));
            if (result == android.speech.tts.TextToSpeech.LANG_MISSING_DATA || result == android.speech.tts.TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(context, "Language not supported", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Initialization failed", Toast.LENGTH_SHORT).show();
        }

    }

    public void speak(String text){
        textToSpeech.speak(text, QUEUE_FLUSH,null,null);
    }
}
