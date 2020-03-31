package fun.android.andrevicente.androidfun;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("fun.android.andrevicente.androidfun", appContext.getPackageName());
    }

    /**
     * Traverses a string from beginning to end.
     * In example below, 'Hello!' will be traversed
     * and will display '!olleH' within the output
     */
    private void traverseString(String s) {
        s = (s == null) ? "Hello!" : s;
        String traversedString = "";

        for (int i=0; i < s.length(); i++) {
            String tempString = s.substring(s.length()-(i+1),s.length()-i);
            traversedString = traversedString + tempString;
        }
    }

    /**
     * Sample method to demonstrate recursion
     * when traversing a string.
     *
     * @param originalString - Original string that is to be passed in
     * @param recursionString - String in which will be outputted and should defaulted to ""
     * @return A traverses string utilizing recursion
     */
    private String recursion(String originalString, String recursionString) {
        if (originalString.length()==0) {
            return recursionString;
        }

        String tempString = originalString.substring(originalString.length()-1,originalString.length());
        recursionString = recursionString + tempString;
        originalString = originalString.substring(0, originalString.length()-1);

        return recursion(originalString, recursionString);
    }

}
