package shoeping.clientapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void ID반환() throws Exception {
        final DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.requestId("test", "test");
        databaseManager.setLoadCompleteListener(new DatabaseManager.LoadCompleteListener() {
            @Override
            public void whenLoadComplete(boolean isData) {
                assertEquals("test", databaseManager.getIdToken());
            }
        });
    }
}