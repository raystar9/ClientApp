package shoeping.clientapp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static android.R.attr.id;

public class DatabaseManager {

    private String myJSONquery;
    private String RESULT = "result";

    private final String firstUrl = "http://server.raystar.kro.kr/html/phpfunc/";
    private String secondUrl;

    JSONArray jsArray = null;

    private ArrayList<HashMap<String, String>> hashList;

    private String _id;
    private String _pw;
    private UserInfo _userInfo;
    private ItemInfo _mainInfo[];

    private static DatabaseManager _instance = new DatabaseManager();

    public static DatabaseManager getInstance() {
        return _instance;
    }

    public void getData(String url, final String condition) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];

                BufferedReader bufferedReader;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }

            protected void onPostExecute(String str) {
                distributeJSON(parseStringToJSON(str), condition);
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
    }

    private JSONObject parseStringToJSON(String result) {
        try {
            JSONObject json = new JSONObject(result);
            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void requestGetId(String id, String pw) {
        secondUrl = "login_check.php?"
                + "id=" + id
                + "&pw=" + pw;

        getData(firstUrl + secondUrl, "toGetIdAndPassword");
    }

    public void requestGetPw(String id, String pw) {
        secondUrl = "login_check.php?"
                + "id=" + id
                + "&pw=" + pw;

        getData(firstUrl + secondUrl, "toGetIdAndPassword");
    }

    public void requestGetUserInfo() {
        secondUrl = "my_info.php?"+
                "id="+id;

        getData(firstUrl + secondUrl, "toGetUserInfo");
    }

    public void requestGetMainInfo() {
        // TODO : 재고번호, 신발명, 가격, 사이즈를 ItemInfo에 담음
    }

    public void requestSetUserInfo() {
        // TODO : 유저인포를 DB에 씀
    }

    private void distributeJSON(JSONObject json, String condition) {

        JSONConverter converter = new JSONConverter();
        switch (condition) {
            case "toGetIdAndPassword":
                converter.IdAndPw(json);
                break;
            case "toGetUserInfo":
                converter.getUserInfo();
                break;
            case "toGetCategoryShoes":
                converter.Shoes_category(json);
                break;
            default:
                break;
        }
    }

    class JSONConverter {

        protected void IdAndPw(JSONObject json) {
            try {
                jsArray = json.getJSONArray(RESULT);

                for (int i = 0; i < jsArray.length(); i++) {
                    JSONObject c = jsArray.getJSONObject(i);
                    String id = c.getString("id");
                    String pw = c.getString("pw");

                    _id = id;
                    _pw = pw;

                }
                _loadCompleteListener.onLoadComplete(true);
            } catch (Exception e) {
                e.printStackTrace();
                _loadCompleteListener.onLoadComplete(false);
            }

        }

        protected void Shoes_category(JSONObject json)
        {
            try
            {
                jsArray = json.getJSONArray(RESULT);

                for (int i = 0; i < jsArray.length(); i++) {
                    JSONObject c = jsArray.getJSONObject(i);
                    String serial = c.getString("serial_num");
                    String shoe_name = c.getString("shoe_name");
                    String species = c.getString("shoe_species");
                    String price = c.getString("shoe_price");
                    String desc= c.getString("shoe_desc");

                    HashMap<String, String> hash_shoes = new HashMap<String, String>();

                    hash_shoes.put("serial_num", serial);
                    hash_shoes.put("shoe_name", shoe_name);
                    hash_shoes.put("shoe_species", species);
                    hash_shoes.put("shoe_price", price);
                    hash_shoes.put("shoe_desc", desc);

                    hashList.add(0, hash_shoes);
                    _loadCompleteListener.onLoadComplete(true);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                _loadCompleteListener.onLoadComplete(false);
            }

        }

        protected void showCustomer() {
            try {
                JSONObject jsonObj = new JSONObject(myJSONquery);
                jsArray = jsonObj.getJSONArray(RESULT);

                for (int i = 0; i < jsArray.length(); i++) {
                    JSONObject c = jsArray.getJSONObject(i);
                    String id = c.getString("id");
                    String pw = c.getString("pw");
                    String name = c.getString("name");
                    String phone = c.getString("phone");
                    String address = c.getString("address");

                    HashMap<String, String> hash_customer = new HashMap<String, String>();

                    hash_customer.put("id", id);
                    hash_customer.put("pw", pw);
                    hash_customer.put("name", name);
                    hash_customer.put("phone", phone);
                    hash_customer.put("address", address);

                    hashList.add(0, hash_customer);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        protected void showShoes() {
            try {
                JSONObject jsonObj = new JSONObject(myJSONquery);
                jsArray = jsonObj.getJSONArray("result");

                for (int i = 0; i < jsArray.length(); i++) {
                    JSONObject c = jsArray.getJSONObject(i);
                    String serial = c.getString("serial_num");
                    String shoe_name = c.getString("shoe_name");
                    String species = c.getString("shoe_species");
                    String price = c.getString("price");

                    HashMap<String, String> hash_shoes = new HashMap<String, String>();

                    hash_shoes.put("serial_num", serial);
                    hash_shoes.put("shoe_name", shoe_name);
                    hash_shoes.put("shoe_species", species);
                    hash_shoes.put("price", price);

                    hashList.add(hash_shoes);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        protected void showStock() {
            try {
                JSONObject jsonObj = new JSONObject(myJSONquery);
                jsArray = jsonObj.getJSONArray("result");

                for (int i = 0; i < jsArray.length(); i++) {
                    JSONObject c = jsArray.getJSONObject(i);
                    String serial = c.getString("serial_num");
                    String size = c.getString("size");
                    String remain = c.getString("remain");

                    HashMap<String, String> hash_stock = new HashMap<String, String>();

                    hash_stock.put("serial_num", serial);
                    hash_stock.put("size", size);
                    hash_stock.put("remain", remain);

                    hashList.add(hash_stock);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        protected void showOrders() {
            try {
                JSONObject jsonObj = new JSONObject(myJSONquery);
                jsArray = jsonObj.getJSONArray("result");

                for (int i = 0; i < jsArray.length(); i++) {
                    JSONObject c = jsArray.getJSONObject(i);
                    String order_num = c.getString("order_num");
                    String id = c.getString("id");
                    String serial = c.getString("serial_num");
                    String recv_name = c.getString("recv_name");
                    String recv_addr = c.getString("recv_addr");
                    String recv_phone = c.getString("recv_phone");
                    String comment = c.getString("comment");

                    HashMap<String, String> hash_orders = new HashMap<String, String>();

                    hash_orders.put("order_num", order_num);
                    hash_orders.put("id", id);
                    hash_orders.put("serial_num", serial);
                    hash_orders.put("recv_name", recv_name);
                    hash_orders.put("recv_addr", recv_addr);
                    hash_orders.put("recv_phone", recv_phone);
                    hash_orders.put("comment", comment);

                    hashList.add(hash_orders);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        protected void getUserInfo() {
            _userInfo = new UserInfo();
            try
            {
                JSONObject jsonObj = new JSONObject(myJSONquery);
                jsArray = jsonObj.getJSONArray("result");

                for (int i = 0; i < jsArray.length(); i++) {
                    JSONObject c = jsArray.getJSONObject(i);
                    _userInfo.name = c.getString("name");
                    _userInfo.address = c.getString("addr");
                    _userInfo.phoneNo = c.getString("phone");
                }
                _loadCompleteListener.onLoadComplete(true);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                _loadCompleteListener.onLoadComplete(false);
            }


            //TODO : 구매자의 이름, 주소, 휴대폰 번호를 불러오고 저장한 뒤 리스너호출
        }
    }

    public String getIdToken(){
        return _id;
    }

    public UserInfo getUserInfo(){
        return _userInfo;
    }

    public ItemInfo[] getMainInfoArray() {
        return _mainInfo;
    }

    public void getShoesData(String category) {
        secondUrl = "category_search.php?";
        secondUrl += "shoe_species="+category;

        getData(firstUrl + secondUrl, "getCategoryShoes");
    }

    private LoadCompleteListener _loadCompleteListener;

    interface LoadCompleteListener {
        void onLoadComplete(boolean isData);
    }

    public void setLoadCompleteListener(LoadCompleteListener loadCompleteListener){
        if(_loadCompleteListener != loadCompleteListener)
            _loadCompleteListener = loadCompleteListener;
    }

    public class UserInfo {
        String name;
        String address;
        String phoneNo;
    }

    public class ItemInfo {
        String serialNumber;
        String shoesName;
        String price;
        String size;
    }
}
