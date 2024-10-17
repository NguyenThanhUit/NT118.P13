package com.example.lab2_cau5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Button btn;
    EditText editTextFood;
    CheckBox checkBox;
    ArrayAdapter<Dish> Dishadapter;
    ArrayList<Dish> monan;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        List<Thumbnail> thumbnails = new ArrayList<>();
        thumbnails.add(new Thumbnail("Thumbnail 1", R.drawable.first_thumbnail));
        thumbnails.add(new Thumbnail("Thumbnail 2", R.drawable.second_thumbnail));
        thumbnails.add(new Thumbnail("Thumbnail 3", R.drawable.third_thumbnail));
        thumbnails.add(new Thumbnail("Thumbnail 4", R.drawable.fourth_thumbnail));

        spinner = findViewById(R.id.spinnerfood);
        ThumbnailAdapter adapter = new ThumbnailAdapter(this, thumbnails);
        spinner.setAdapter(adapter);

        btn = findViewById(R.id.button);
        editTextFood = findViewById(R.id.editText_name);
        checkBox = findViewById(R.id.checkBox);
        monan = new ArrayList<>();
        Dishadapter = new DishAdapter(this, R.layout.item_dish, monan);

        btn.setOnClickListener(v -> addNewDish());

        gridView = findViewById(R.id.gridview);
        gridView.setAdapter(Dishadapter);

    }
    public static class Thumbnail {
        private String name;
        private int img;

        public Thumbnail(String name, int img) {
            this.name = name;
            this.img = img;
        }

        public String getName() {
            return name;
        }
        public int getImg() {
            return img;
        }
    }
    public static class Dish{
        private final String name;
        private final int img;
        boolean isPromote;

        public Dish(String name, int img, boolean isPromote) {
            this.name = name;
            this.img = img;
            this.isPromote = isPromote;
        }
        String getName(){
            return name;
        }
        int getImg(){
            return img;
        }
        boolean isPromote(){
            return isPromote;
        }
    }
    private void addNewDish() {
        String name = editTextFood.getText().toString();
        Thumbnail selectedThumbnail = (Thumbnail) spinner.getSelectedItem();
        int image = selectedThumbnail.getImg();
        Dish dish;
        if (checkBox.isChecked()) {
            dish = new Dish(name, image, true);
        } else {
            dish = new Dish(name, image, false);
        }

        monan.add(dish);
        Dishadapter.notifyDataSetChanged();
        editTextFood.setText("");
        spinner.setSelection(0);
        checkBox.setChecked(false);
    }
}