package shoeping.clientapp;

/**
 * Created by Koo on 2017-05-23.
 */

public class ShoesDataPack {

    private String _name;
    private String _price;
    private String _size;
    private String _description;

    public ShoesDataPack(String name, String price, String size, String description) {
        _name = name;
        _price = price;
        _size = size;
        _description = description;
    }

    public String getName() {
        return _name;
    }

    public String getPrice() {
        return _price;
    }

    public String getSize() {
        return _size;
    }

    public String getDescrption() {
        return _description;
    }
}
