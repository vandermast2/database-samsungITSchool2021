package sss.com.database.ui.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sss.com.database.DBHelper;
import sss.com.database.models.RecipeModel;
import sss.com.database.models.Result;
import sss.com.database.network.NetworkService;

public class MainViewModel extends ViewModel {

    private SQLiteDatabase sqLiteDatabase;
    private FirebaseFirestore db;

    public MutableLiveData<List<Result>> results = new MutableLiveData<>();

    private Callback<RecipeModel> callback = new Callback<RecipeModel>() {
        @Override
        public void onResponse(Call<RecipeModel> call, Response<RecipeModel> response) {
            List<Result> recipes = response.body().getResults();
            results.postValue(recipes);

        }

        @Override
        public void onFailure(Call<RecipeModel> call, Throwable t) {
            Log.e(MainFragment.TAG, t.getLocalizedMessage());
        }
    };

    public void getRecipes(String ingredients, String query, int pages){
        NetworkService.
                getInstance().
                getApi().
                getRecipes(ingredients,query,pages).enqueue(callback);
    }

    private void addData(Map<String, Object> user) {
        // Create a new user with a first and last nam

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(MainFragment.TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(MainFragment.TAG, "Error adding document", e);
                    }
                });
    }

    private void addData(Context context, String name, String email) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.NAME, name);
        contentValues.put(DBHelper.EMAIL, email);

        long id = sqLiteDatabase.insert(DBHelper.TABLE_NAME, null, contentValues);
        Toast.makeText(context, "My id is:" + id, Toast.LENGTH_SHORT).show();
    }

    private Cursor getData(){
        Cursor cursor = sqLiteDatabase.query(DBHelper.TABLE_NAME,null,null,null,null,null,null);
        return cursor;
    }

    private ArrayList<Result> parseData(Cursor cursor){
        ArrayList<Result> tmp = new ArrayList<>();

        if (cursor.moveToFirst()){
            int idColIdx = cursor.getColumnIndex(DBHelper.ID);
            int nameColIdx = cursor.getColumnIndex(DBHelper.NAME);
            int emailColIdx = cursor.getColumnIndex(DBHelper.EMAIL);

            do{
                long id = cursor.getLong(idColIdx);
                String name = cursor.getString(nameColIdx);
                String email = cursor.getString(emailColIdx);
                Result userModel = new Result();
                tmp.add(userModel);
            } while (cursor.moveToNext());
        }

        return tmp;
    }

    private void deleteData(){
        sqLiteDatabase.delete(DBHelper.TABLE_NAME, null, null);
    }

    public void connectToDatabase(DBHelper dbHelper) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void connectToDatabase() {
        db = FirebaseFirestore.getInstance();
    }
}