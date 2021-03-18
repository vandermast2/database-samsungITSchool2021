package sss.com.database.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import sss.com.database.DBHelper;
import sss.com.database.models.RecipeModel;
import sss.com.database.models.Result;
import sss.com.database.network.Api;
import sss.com.database.network.NetworkService;

public class DataManager implements IDataManager {
    private FirebaseFirestore db;
    private SQLiteDatabase sqLiteDatabase;
    private Api api;

    public DataManager(Context context) {
        sqLiteDatabase = new DBHelper(context).getWritableDatabase();
        api = NetworkService.
                getInstance().
                getApi();
    }


    @Override
    public Call<RecipeModel> getRecipes(String ingredients, String query, int page) {
        return null;
    }

    @Override
    public void addData(Map<String, Object> recipe) {

    }

    @Override
    public Result getResultById(long id) {
        return null;
    }

    @Override
    public List<Result> getAllData() {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteByID(long id) {

    }
}
