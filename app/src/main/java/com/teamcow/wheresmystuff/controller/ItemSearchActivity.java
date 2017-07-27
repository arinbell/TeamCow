package com.teamcow.wheresmystuff.controller;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamcow.wheresmystuff.R;
import com.teamcow.wheresmystuff.model.ItemType;
import com.teamcow.wheresmystuff.model.LostItem;
import com.teamcow.wheresmystuff.model.LostItemData;
import com.teamcow.wheresmystuff.model.PosterType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A page where users can search for items.
 */
public class ItemSearchActivity extends AppCompatActivity {
    private LostItemData lid = LostItemData.getInstance();
    private ArrayList<LostItem> lostList;
    private ArrayList<LostItem> showList;
    private EditText searchBox;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String searchKey;
    private PosterType posterType;
    private ItemType itemType;
    private android.widget.SearchView searchView;
    private MenuItem searchItem;
    private MenuItem refreshOption;
    private SearchManager searchManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private static final int RESULT_CODE_EDIT = 101;

    /**
     * creates a page where users can search for items that they've lost.
     * @param savedInstanceState saves the instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        lostList = lid.getItemList();

        handleIntent(getIntent());

//        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
//            String query = getIntent().getStringExtra(SearchManager.QUERY);
//            handleSearch(query);
//        }

//        if (getIntent().hasExtra("searching")) {
//            if (getIntent().hasExtra("search_key")) {
//                searchKey = getIntent().getStringExtra("search_key");
//                showList = getSearchResult(searchKey);
//            } else {
//                if (getIntent().hasExtra("poster")) {
//                    posterType = (PosterType) getIntent().getSerializableExtra("poster");
//                    showList = posterSearch(posterType);
//                } else if (getIntent().hasExtra("itemtype")) {
//                    itemType = (ItemType) getIntent().getSerializableExtra("itemtype");
//                    showList = itemTypeSearch(itemType);
//                }
//            }
//        } else {
//            showList = lostList;
//        }



//        mRecyclerView = (RecyclerView) findViewById(R.id.item_search_recyclerview);
//
//        mRecyclerView.setHasFixedSize(true);
//
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        mAdapter = new DataAdapter(showList);
//        mRecyclerView.setAdapter(mAdapter);





//        Button itemSelectButton = (Button) findViewById(R.id.searchpage_search_button);
//        Button cancelButton = (Button) findViewById(R.id.searchpage_cancel_button);
//        searchBox = (EditText) findViewById(R.id.searchTextBox);
//        ListView itemDisplay = (ListView) findViewById(R.id.item_list);
//
//        itemSelectButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick (View view) {
//                Intent intent = new Intent(ItemSearchActivity.this,
//                        SearchResultActivity.class);
//                intent.putExtra("search_key", searchBox.getText().toString());
//                startActivity(intent);
//            }
//        });
//
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick (View view) {
//                cancelSearch();
//            }
//        });
//
//        ArrayList<String> itemList = new ArrayList<>();
//        for(LostItem item : lostList) {
//            itemList.add(item.getName());
//        }
//        ArrayAdapter<String> itemAdpt = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, itemList);
//        itemDisplay.setAdapter(itemAdpt);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            handleSearch(query);
        } else {
            showList = lostList;
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.item_search_recyclerview);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//        mAdapter = new FirebaseRecyclerAdapter<LostItem, DataHolder>(
//                LostItem.class,
//
//        ) {
//            @Override
//            protected void populateViewHolder(RecyclerView.ViewHolder viewHolder, Object model, int position) {
//
//            }
//        }

        mAdapter = new DataAdapter(showList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.options_menu, menu);

        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName())
//        );



        getMenuInflater().inflate(R.menu.options_menu, menu);
        searchItem = menu.findItem(R.id.search);
        searchView = (android.widget.SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo
                (searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.requestFocus();

        refreshOption = menu.findItem(R.id.menu_refresh);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                refresh();
                return true;
        }
        return true;
    }

    private ArrayList<LostItem> posterSearch(PosterType poster) {
        ArrayList<LostItem> list = new ArrayList<>();
        for(LostItem i : lostList) {
            if (i.getPoster().equals(poster)) {
                list.add(i);
            }
        }
        return list;
    }

    private ArrayList<LostItem> itemTypeSearch(ItemType type) {
        ArrayList<LostItem> list = new ArrayList<>();
        for (LostItem i : lostList) {
            if (i.getType().equals(type)) {
                list.add(i);
            }
        }
        return list;
    }

    private ArrayList<LostItem> getSearchResult(String pattern) {
        ArrayList<LostItem> list = new ArrayList<>();
        String pat = pattern.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String text;
        for(LostItem i : lostList) {
            text = i.getName().replaceAll("[^a-zA-Z]", "").toLowerCase();
            if (rabinKarp(pat, text)) {
                list.add(i);
            }
        }
        return list;
    }

    private void handleSearch(String pattern) {
        if (ItemType.contains(pattern)) {
            showList = itemTypeSearch(ItemType.valueOf(pattern));
        } else if (PosterType.contains(pattern)) {
            showList = posterSearch(PosterType.valueOf(pattern));
        } else {
            showList = getSearchResult(pattern);
        }
    }

    /**
     * allows users to stop searching for items.
     */
    public void cancelSearch() {
        finish();
    }


    /**
     * Prime base used for Rabin-Karp hashing.
     * DO NOT EDIT!
     */
    private static final int BASE = 661;

    /**
     * A map from integers representing an exponent to integers
     * representing {@code BASE} to that exponent. Used to recall certain
     * powers of the BASE when generating or updating the hash.
     */
    private static Map<Integer, Integer> baseMap = new HashMap<>();

    /**
     * Runs Rabin-Karp algorithm. Generate the pattern hash, and compare it with
     * the hash from a substring of text that's the same length as the pattern.
     * If the two hashes match, compare their individual characters, else update
     * the text hash and continue.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text is null
     * @param pattern a string you're searching for in a body of text
     * @param text the body of text where you search for pattern
     * @return list containing the starting index for each match found
     */
    public static boolean rabinKarp(CharSequence pattern,
                                          CharSequence text) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern is null");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text is null");
        }
        if (pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern is empty");
        }
        //ArrayList<Integer> matches = new ArrayList<Integer>();
        if (pattern.length() > text.length()) {
            return false;
        }
        int patternHash = generateHash(pattern, pattern.length());
        int textHash = generateHash(text, pattern.length());
        int i = 0;
        int j = 0;
        while (i <= text.length() - pattern.length()) {
            if (patternHash == textHash) {
                j = 0;
                while ((j < pattern.length())
                        && (text.charAt(i + j) == pattern.charAt(j))) {
                    j++;
                }
                if (j == pattern.length()) {
                    return true;
                }
            }
            i++;
            if (i <= text.length() - pattern.length()) {
                textHash = updateHash(textHash, pattern.length(),
                        text.charAt(i - 1),
                        text.charAt(i + pattern.length() - 1));
            }
        }
        return false;
    }

    /**
     * Hash function used for Rabin-Karp. The formula for hashing a string is:
     *
     * sum of: c * BASE ^ (pattern.length - 1 - i), where c is the integer
     * value of the current character, and i is the index of the character
     *
     * For example: Hashing "bunn" as a substring of "bunny" with base 661 hash
     * = b * 661 ^ 3 + u * 661 ^ 2 + n * 661 ^ 1 + n * 661 ^ 0 = 98 * 661 ^ 3 +
     * 117 * 661 ^ 2 + 110 * 661 ^ 1 + 110 * 661 ^ 0 = 28354061115
     *
     * However, note that that will roll over to -1710709957, because
     * the largest number that can be represented by an int is 2147483647.
     *
     * Do NOT use {@code Math.pow()} in this method.
     *
     * @throws IllegalArgumentException if current is null
     * @throws IllegalArgumentException if length is negative, 0, or greater
     *     than the length of current
     * @param current substring you are generating hash function for
     * @param length the length of the string you want to generate the hash for,
     * starting from index 0. For example, if length is 4 but current's length
     * is 6, then you include indices 0-3 in your hash (and pretend the actual
     * length is 4)
     * @return hash of the substring
     */
    public static int generateHash(CharSequence current, int length) {
        if (current == null) {
            throw new IllegalArgumentException("String is null");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("Length cannot be 0 or"
                    + " negative");
        }
        if (length > current.length()) {
            throw new IllegalArgumentException("Length cannot be"
                    + " greater than length of the string");
        }
        int returnInt = 0;
        baseMap.put(0, 1);
        int basePow = BASE;
        for (int i = 1; i < length; i++) {
            baseMap.put(i, basePow);
            basePow = basePow * BASE;
        }
        int powInt = length - 1;
        for (int i = 0; i < length; i++) {
            returnInt = returnInt + (current.charAt(i) * baseMap.get(powInt));
            powInt--;
        }
        return returnInt;
    }

    /**
     * Updates a hash in constant time to avoid constantly recalculating
     * entire hash. To update the hash:
     *
     *  remove the oldChar times BASE raised to the length - 1, multiply by
     *  BASE, and add the newChar.
     *
     * For example: Shifting from "bunn" to "unny" in "bunny" with base 661
     * hash("unny") = (hash("bunn") - b * 661 ^ 3) * 661 + y * 661 ^ 0 =
     * (-1710709957 - 98 * 661 ^ 3) * 661 + 121 * 661 ^ 0 = -19838975385074
     *
     * However, the number will roll over to -x.
     *
     * This method must run in O(1) time, here meaning nothing but basic
     * logical and arithmetic operations. Remember that
     *
     * Do NOT use {@code Math.pow()} in this method.
     *
     * @throws IllegalArgumentException if length is negative or 0
     * @param oldHash hash generated by generateHash
     * @param length length of pattern/substring of text
     * @param oldChar character we want to remove from hashed substring
     * @param newChar character we want to add to hashed substring
     * @return updated hash of this substring
     */
    public static int updateHash(int oldHash, int length, char oldChar,
                                 char newChar) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length cannot be zero"
                    + "or negative");
        }
        int newHash = oldHash - (oldChar * baseMap.get(length - 1));
        newHash = (newHash * BASE) + newChar;
        return newHash;
    }


    @Override
    public void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        handleIntent(data);
//        mAdapter.notifyDataSetChanged();
//        Log.d("Huh", "YOU CAME BAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACK");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        lostList = lid.getItemList();
        handleIntent(getIntent());
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
