package id.sch.smktelkom_mlg.project.xiirpl506162636.mamacatering;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl506162636.mamacatering.activity.LoginActivity;
import id.sch.smktelkom_mlg.project.xiirpl506162636.mamacatering.adapter.FoodAdapter;
import id.sch.smktelkom_mlg.project.xiirpl506162636.mamacatering.model.Food;

public class MainActivity extends AppCompatActivity implements FoodAdapter.IFoodAdapter {

    public static final int REQUEST_CODE_ADD = 88;
    public static final int REQUEST_CODE_EDIT = 99;
    public static String FOOD;
    ArrayList<Food> mListAll = new ArrayList<>();
    boolean isFiltered;
    ArrayList<Integer> mListMapFilter = new ArrayList<>();
    String mQuery;
    ArrayList<Food> mList = new ArrayList<>();
    FoodAdapter mAdapter;
    int itemPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new FoodAdapter(this, mList);
        recyclerView.setAdapter(mAdapter);

        fillData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goAdd();
            }
        });
    }

    private void goAdd() {
        startActivityForResult(new Intent(this, InputActivity.class), REQUEST_CODE_ADD);
    }

    private void fillData() {
        Resources resources = getResources();
        String[] arJudul = resources.getStringArray(R.array.food);
        String[] arDeskripsi = resources.getStringArray(R.array.food_desc);
        String[] arDetail = resources.getStringArray(R.array.food_detail);
        String[] arLokasi = resources.getStringArray(R.array.food_price);
        TypedArray a = resources.obtainTypedArray(R.array.places_picture);
        String[] arFoto = new String[a.length()];

        for (int i = 0; i < arFoto.length; i++) {
            int id = a.getResourceId(i, 0);
            arFoto[i] = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                    + resources.getResourcePackageName(id) + '/'
                    + resources.getResourceTypeName(id) + '/'
                    + resources.getResourceEntryName(id);
        }
        a.recycle();

        for (int i = 0; i < arJudul.length; i++) {
            mList.add(new Food(arJudul[i], arDeskripsi[i],
                    arDetail[i], arLokasi[i], arFoto[i]));
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        mQuery = newText.toLowerCase();
                        doFilter(mQuery);
                        return true;
                    }
                });
        return true;
    }

    private void doFilter(String query) {
        if (!isFiltered) {
            mListAll.clear();
            mListAll.addAll(mList);
            isFiltered = true;
        }

        mList.clear();
        if (query == null || query.isEmpty()) {
            mList.addAll(mListAll);
            isFiltered = false;
        } else {
            mListMapFilter.clear();
            for (int i = 0; i < mListAll.size(); i++) {
                Food hotel = mListAll.get(i);
                if (hotel.judul.toLowerCase().contains(query) ||
                        hotel.deskripsi.toLowerCase().contains(query) ||
                        hotel.price.toLowerCase().contains(query)) {
                    mList.add(hotel);
                    mListMapFilter.add(i);
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK) {
            Food food = (Food) data.getSerializableExtra(FOOD);
            mList.add(food);
            if (isFiltered) mListAll.add(food);
            doFilter(mQuery);
            //mAdapter.notifiDataSetChanged();
        } else if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK) {
            Food food = (Food) data.getSerializableExtra(FOOD);
            mList.remove(itemPos);
            if (isFiltered) mListAll.remove(mListMapFilter.get(itemPos).intValue());
            mList.add(itemPos, food);
            if (isFiltered) mListAll.add(mListMapFilter.get(itemPos), food);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void doClick(int pos) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(FOOD, mList.get(pos));
        startActivity(intent);
    }

    @Override
    public void doEdit(int pos) {
        itemPos = pos;
        Intent intent = new Intent(this, InputActivity.class);
        intent.putExtra(FOOD, mList.get(pos));
        startActivityForResult(intent, REQUEST_CODE_EDIT);
    }

    @Override
    public void doDelete(int pos) {
        itemPos = pos;
        final Food food = mList.get(pos);
        mList.remove(itemPos);
        if (isFiltered) mListAll.remove(mListMapFilter.get(itemPos).intValue());
        mAdapter.notifyDataSetChanged();
        Snackbar.make(findViewById(R.id.fab), food.judul + " Terhapus", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mList.add(itemPos, food);
                        if (isFiltered) mListAll.add(mListMapFilter.get(itemPos), food);
                        mAdapter.notifyDataSetChanged();
                    }
                })
                .show();
    }

    @Override
    public void doFav(int pos) {

    }

    @Override
    public void doShare(int pos) {

    }
}


