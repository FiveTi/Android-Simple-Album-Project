package com.fiveti.a5tphoto.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fiveti.a5tphoto.Fragment.AlbumFragment;
import com.fiveti.a5tphoto.Fragment.GalleryFragment;
import com.fiveti.a5tphoto.Album;
import com.fiveti.a5tphoto.OpenCamera.openCamera;
import com.fiveti.a5tphoto.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Album> all_images_path = new ArrayList<>();
    boolean boolean_folder;

    private static final int REQUEST_PERMISSIONS = 100;
    private String ARRAY_PATH = "array_path";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager);
        setSupportActionBar(mToolbar);

        getImagesPath();

        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.add(getResources().getString(R.string.first), 1);
        adapter.add(getResources().getString(R.string.second), 2);
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragmentList = new ArrayList<>();
        private List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void add(String title, int id) {
            Fragment fragment = null;
            if (id == 1) {
                fragment = openGallery();
            } else if (id == 2) {
                fragment = openAlbum();
            }
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_camera) {
            Intent intent = new Intent(this, openCamera.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public ArrayList<Album> getImagesPath() {
        all_images_path.clear();

        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);

            for (int i = 0; i < all_images_path.size(); i++) {
                if (all_images_path.get(i).getFolder().equals(cursor.getString(column_index_folder_name))) {
                    boolean_folder = true;
                    int_position = i;
                    break;
                } else {
                    boolean_folder = false;
                }
            }

            if (boolean_folder) {

                ArrayList<String> al_path = new ArrayList<>();
                al_path.addAll(all_images_path.get(int_position).getAllImagePath());
                al_path.add(absolutePathOfImage);
                all_images_path.get(int_position).setAllImagePath(al_path);

            } else {
                ArrayList<String> al_path = new ArrayList<>();
                al_path.add(absolutePathOfImage);
                Album obj_model = new Album();
                obj_model.setFolder(cursor.getString(column_index_folder_name));
                obj_model.setAllImagePath(al_path);
                all_images_path.add(obj_model);
            }
        }
        return all_images_path;
    }

    public Fragment openGallery() {
        Fragment fragment = new GalleryFragment();
        Bundle bGallery = new Bundle();
        bGallery.putSerializable(ARRAY_PATH, all_images_path);
        fragment.setArguments(bGallery);
        return fragment;
    }

    public Fragment openAlbum() {
        Fragment fragment = new AlbumFragment();
        Bundle bAlbum = new Bundle();
        bAlbum.putSerializable(ARRAY_PATH, all_images_path);
        fragment.setArguments(bAlbum);
        return fragment;
    }

/*    @Override
    protected void onPause() {
        super.onPause();
        all_images_path.clear();
        all_images_path=getImagesPath();
    }*/
}
