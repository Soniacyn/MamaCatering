package id.sch.smktelkom_mlg.project.xiirpl506162636.mamacatering;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import id.sch.smktelkom_mlg.project.xiirpl506162636.mamacatering.model.Food;

public class InputActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_GET = 1;
    EditText etJudul, etDeskripsi, etDetail, etPrice;
    ImageView ivFoto;
    Uri uriFoto;
    Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        etJudul = (EditText) findViewById(R.id.editTextNama);
        etDeskripsi = (EditText) findViewById(R.id.editTextDeskripsi);
        etDetail = (EditText) findViewById(R.id.ediTextDetail);
        etPrice = (EditText) findViewById(R.id.ediTextPrice);
        ivFoto = (ImageView) findViewById(R.id.imageViewFoto);

        ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickPhoto();
            }
        });

        findViewById(R.id.buttonSimpan).setOnClickListener
                (
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doSave();
                    }
                });

        food = (Food) getIntent().getSerializableExtra(MainActivity.FOOD);
        if (food != null) {
            setTitle("Edit " + food.judul);
            fillData();
        } else {
            setTitle("New Food");
        }
    }

    private void fillData() {
        etJudul.setText(food.judul);
        etDeskripsi.setText(food.deskripsi);
        etDetail.setText(food.detail);
        etPrice.setText(food.price);
        uriFoto = Uri.parse(food.foto);
        ivFoto.setImageURI(uriFoto);
    }

    private void doSave() {
        String judul = etJudul.getText().toString();
        String deskripsi = etDeskripsi.getText().toString();
        String detail = etDetail.getText().toString();
        String price = etPrice.getText().toString();

        if (isValid(judul, deskripsi, detail, price, uriFoto)) {
            food = new Food(judul, deskripsi, detail, price, uriFoto.toString());
            Intent intent = new Intent();
            intent.putExtra(MainActivity.FOOD, food);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private boolean isValid(String judul, String deskripsi, String detail, String price, Uri uriFoto) {
        boolean valid = true;

        if (judul.isEmpty()) {
            setErrorEmpty(etJudul);
            valid = false;
        }
        if (deskripsi.isEmpty()) {
            setErrorEmpty(etDeskripsi);
            valid = false;
        }
        if (detail.isEmpty()) {
            setErrorEmpty(etDetail);
            valid = false;
        }
        if (price.isEmpty()) {
            setErrorEmpty(etPrice);
            valid = false;
        }
        if (uriFoto == null) {
            Snackbar.make(ivFoto, "Foto Belum Ada", Snackbar.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }

    private void setErrorEmpty(EditText editText) {
        editText.setError(((TextInputLayout) editText.getParent().getParent()).getHint() + " Belum Diisi");
    }

    private void pickPhoto() {
        Intent intent;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, REQUEST_IMAGE_GET);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            uriFoto = data.getData();
            ivFoto.setImageURI(uriFoto);
        }
    }
}
