package course.labs.asynctasklab;


import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;


//@RunWith(AndroidJUnit4.class)
public class DownloaderTaskFragmentTest /*extends
        ActivityInstrumentationTestCase2 */{


    @Test (expected = NullPointerException.class)
    public void downloadTweets_expectNPE() {
        new DownloaderTaskFragment().downloadTweets(null);
    }

    @Ignore //java.lang.NullPointerException on android Context -- need to use ActivityInstrumentationTestCase2
    @Test
    public void downloadTweets_expectNout() {
        Integer[] input = new Integer[]{	R.raw.tswift, R.raw.lgaga};
        assertEquals(2, input.length);

        assertEquals("n in should be n out", 2, new DownloaderTaskFragment().
                downloadTweets( input).length);
    }

}
