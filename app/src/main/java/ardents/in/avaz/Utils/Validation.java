package ardents.in.avaz.Utils;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Validation {


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

}
