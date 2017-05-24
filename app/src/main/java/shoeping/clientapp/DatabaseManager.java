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

public class DatabaseManager {

    String myJSONquery;

    final String firstURL = "http://server.raystar.kro.kr/html/phpfunc/";
    String secURL;

    JSONArray jsArray = null;

    ArrayList<HashMap<String, String>> hashList;

    String _id;
    String _pw;

    private static DatabaseManager _instance = new DatabaseManager();

    private DatabaseManager(){
        _id = "hi";
        _pw = "hi";
    }

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

    /*
    public ShoesDataPack packShoesData(int species, int position) {
        ShoesDataPack pack = new ShoesDataPack();   //drawable에 있는 resource 이용. position은 위치.
        return pack;
    }
    public ShoesDataPack[] packShoesData(int species) {
        ShoesDataPack[] pack = new ShoesDataPack();
        return pack;
    }
    */

    public void CheckIdPw()
    {
        secURL = "login_check.php?";
        secURL += "id=" + _id + "&";
        secURL += "pw=" + _pw;

        getData(firstURL+secURL,"login");
    }

    public void getData(String url, final String table){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();
                }catch(Exception e){
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String result){
                myJSONquery = result;
                switch(table)
                {
                    case "login":
                        IdAndPw();
                        break;
                    case "orders":
                        showOrders();
                        break;
                    case "customer":
                        showCustomer();
                        break;
                    case "stock":
                        showStock();
                        break;
                    default:
                        break;
                }
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

    protected void IdAndPw()
    {
        try {
            JSONObject jsonObj = new JSONObject(myJSONquery);
            jsArray = jsonObj.getJSONArray("result");

            for(int i=0;i<jsArray.length();i++){
                JSONObject c = jsArray.getJSONObject(i);
                String id = c.getString("id");
                String pw = c.getString("pw");

                _id = id;
                _pw = pw;
            }
            /*
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, hashList, R.layout.list_item,
                    new String[]{TAG_ID},
                    new int[]{R.id.view_id}
            );
            list.setAdapter(adapter);
            */

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void showCustomer(){
        try {
            JSONObject jsonObj = new JSONObject(myJSONquery);
            jsArray = jsonObj.getJSONArray("result");

            for(int i=0;i<jsArray.length();i++){
                JSONObject c = jsArray.getJSONObject(i);
                String id = c.getString("id");
                String pw = c.getString("pw");
                String name = c.getString("name");
                String phone = c.getString("phone");
                String address = c.getString("address");

                HashMap<String,String> hash_customer = new HashMap<String,String>();

                hash_customer.put("id",id);
                hash_customer.put("pw",pw);
                hash_customer.put("name",name);
                hash_customer.put("phone",phone);
                hash_customer.put("address",address);

                hashList.add(hash_customer);
            }
            /*
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, hashList, R.layout.list_item,
                    new String[]{TAG_ID},
                    new int[]{R.id.view_id}
            );
            list.setAdapter(adapter);
            */

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void showShoes(){
        try {
            JSONObject jsonObj = new JSONObject(myJSONquery);
            jsArray = jsonObj.getJSONArray("result");

            for(int i=0;i<jsArray.length();i++){
                JSONObject c = jsArray.getJSONObject(i);
                String serial = c.getString("serial_num");
                String shoe_name = c.getString("shoe_name");
                String species = c.getString("shoe_species");
                String price = c.getString("price");

                HashMap<String,String> hash_shoes = new HashMap<String,String>();

                hash_shoes.put("serial_num",serial);
                hash_shoes.put("shoe_name",shoe_name);
                hash_shoes.put("shoe_species",species);
                hash_shoes.put("price",price);

                hashList.add(hash_shoes);
            }
            /*
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, hashList, R.layout.list_item,
                    new String[]{TAG_ID},
                    new int[]{R.id.view_id}
            );
            list.setAdapter(adapter);
            */

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void showStock(){
        try {
            JSONObject jsonObj = new JSONObject(myJSONquery);
            jsArray = jsonObj.getJSONArray("result");

            for(int i=0;i<jsArray.length();i++){
                JSONObject c = jsArray.getJSONObject(i);
                String serial = c.getString("serial_num");
                String size = c.getString("size");
                String remain = c.getString("remain");

                HashMap<String,String> hash_stock = new HashMap<String,String>();

                hash_stock.put("serial_num",serial);
                hash_stock.put("size",size);
                hash_stock.put("remain",remain);

                hashList.add(hash_stock);
            }
            /*
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, hashList, R.layout.list_item,
                    new String[]{TAG_ID},
                    new int[]{R.id.view_id}
            );
            list.setAdapter(adapter);
            */

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void showOrders(){
        try {
            JSONObject jsonObj = new JSONObject(myJSONquery);
            jsArray = jsonObj.getJSONArray("result");

            for(int i=0;i<jsArray.length();i++){
                JSONObject c = jsArray.getJSONObject(i);
                String order_num = c.getString("order_num");
                String id = c.getString("id");
                String serial = c.getString("serial_num");
                String recv_name = c.getString("recv_name");
                String recv_addr = c.getString("recv_addr");
                String recv_phone = c.getString("recv_phone");
                String comment = c.getString("comment");

                HashMap<String,String> hash_orders = new HashMap<String,String>();

                hash_orders.put("order_num",order_num);
                hash_orders.put("id",id);
                hash_orders.put("serial_num",serial);
                hash_orders.put("recv_name",recv_name);
                hash_orders.put("recv_addr",recv_addr);
                hash_orders.put("recv_phone",recv_phone);
                hash_orders.put("comment",comment);
                
                hashList.add(hash_orders);
            }
            /*
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, hashList, R.layout.list_item,
                    new String[]{TAG_ID},
                    new int[]{R.id.view_id}
            );
            list.setAdapter(adapter);
            */

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
