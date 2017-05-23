package shoeping.clientapp;

/**
 * Created by Koo on 2017-05-22.
 */

public class DatabaseManager {

    String _id;
    String _pw;
    private static DatabaseManager _instance = new DatabaseManager();

    private DatabaseManager(){}

    public static DatabaseManager getInstance() {
        return _instance;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    public String getPw() {
        return _pw;
    }

    public void setPw(String pw) {
        _pw = pw;
    }


    public ShoesDataPack pack(int species, int position) {
        ShoesDataPack pack = new ShoesDataPack();
        return pack;
    }
}
