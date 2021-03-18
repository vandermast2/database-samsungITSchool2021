package sss.com.database.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import sss.com.database.DBHelper;
import sss.com.database.R;
import sss.com.database.adapters.RecipeAdapter;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    private FirebaseFirestore db;
    public static String TAG = "MAIN_ACTIVITY";

    private RecyclerView rvRecipes;

    RecipeAdapter adapter;
    private DBHelper dbHelper;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        //Firestore
        mViewModel.connectToDatabase();
        //SQLite
        dbHelper = new DBHelper(getContext());
        mViewModel.connectToDatabase(dbHelper);

        adapter = new RecipeAdapter();

        rvRecipes = view.findViewById(R.id.rvRecipes);
        rvRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRecipes.setAdapter(adapter);

        mViewModel.getRecipes("garlic", "omelet", 3);
        mViewModel.results.observe(this, results -> adapter.setList(results));

    }




}