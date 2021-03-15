package sss.com.database.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sss.com.database.models.RecipeModel;

public interface Api {
    @GET()
    Call<RecipeModel> getRecipes(@Query("i") String ingredients, @Query("q") String query, @Query("p") int page);

}
