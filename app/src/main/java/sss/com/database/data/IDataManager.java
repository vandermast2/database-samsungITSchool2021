package sss.com.database.data;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import sss.com.database.models.RecipeModel;
import sss.com.database.models.Result;

public interface IDataManager {
    Call<RecipeModel> getRecipes(String ingredients, String query, int page);

    void addData(Map<String, Object> recipe);

    Result getResultById(long id);

    List<Result> getAllData();

    void deleteAll();

    void deleteByID(long id);
}
