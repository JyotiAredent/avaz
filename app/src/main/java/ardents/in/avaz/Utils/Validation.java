package ardents.in.avaz.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import ardents.in.avaz.Activity.ForgetPasswordActivity;

public class Validation {
    private static ProgressDialog progressDialog;
    static TextToSpeech textToSpeech;


    public static boolean validateEditText(EditText editText) {
        String validateText = editText.getText().toString();
        editText.setVisibility(View.VISIBLE);
        if (validateText.isEmpty()) {
            editText.setError("This field is required");
            return false;
        }  else {
            editText.setError(null);
            return true;
        }
    }

    public static void showPrograssDialog(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    public static void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
