package com.teamcow.wheresmystuff.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.provider.MediaStore;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.teamcow.wheresmystuff.R;
import com.teamcow.wheresmystuff.model.ItemType;
import com.teamcow.wheresmystuff.model.LostItem;
import com.teamcow.wheresmystuff.model.LostItemData;
import com.teamcow.wheresmystuff.model.PosterType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * A page where users can register items.
 */
@SuppressWarnings("ALL")
public class ItemRegActivity extends AppCompatActivity {
    private LostItemData lid = LostItemData.getInstance();
    private EditText itemNF;
    private EditText itemDF;
    private EditText itemLocText;
    private Place itemLocation;
    private Spinner itemTypeSpinner;
    private Spinner posterTypeSpinner;
    private Uri uri;
    private Drawable itemImage;
    private ImageView imageView;
    private Bitmap imageBitmap;
    private ByteArrayInputStream bs;
    private boolean editing = false;
    private LostItem lostItem;
    private double x_coord;
    private double y_coord;
    private boolean uriChanged = false;

    private final int REQUEST_CODE_PLACEPICKER = 1;
    private final int REQUEST_PICK_IMAGE = 2;
    private final int REQUEST_CAPTURE_IMAGE = 3;
    private final String INTENT_EDIT = "editing";
    private final String INTENT_EDIT_ITEM = "lostitem";

    private DatabaseReference mDatabase;

    /**
     * creates a page where users can register items they've found.
     * @param savedInstanceState saves the instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_reg);
        Button itemPostButton = (Button) findViewById(R.id.itemreg_post_button);
        Button itemLocButton = (Button) findViewById(R.id.address_button);
        Button itemImageButton = (Button) findViewById(R.id.item_image_button);
        Button takePictureButton = (Button) findViewById(R.id.take_picture_button);
        itemNF = (EditText)findViewById(R.id.item_name_field);
        itemDF = (EditText)findViewById(R.id.item_des_field);
        itemTypeSpinner = (Spinner)findViewById(R.id.item_type_spinner);
        posterTypeSpinner = (Spinner)findViewById(R.id.poster_type_spinner);
        imageView = (ImageView) findViewById(R.id.item_reg_imageView);

        itemLocText = (EditText)findViewById(R.id.address_field);
        itemLocText.setFocusable(false);

        //Set up the adapter to display the item types in the spinner
        ArrayAdapter<String> adapter_1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,
                ItemType.values());
        adapter_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemTypeSpinner.setAdapter(adapter_1);

        //Set up the adapter to display the user types in the spinner
        ArrayAdapter<String> adapter_2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,
                PosterType.values());
        adapter_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        posterTypeSpinner.setAdapter(adapter_2);

        if (getIntent().hasExtra(INTENT_EDIT)) {
            editing = getIntent().getBooleanExtra(INTENT_EDIT, false);
            if (editing) {
                lostItem = getIntent().getParcelableExtra(INTENT_EDIT_ITEM);
                if (lostItem.getUri() != null) {
                    uri = Uri.parse(lostItem.getUri());
                }
                itemNF.setText(lostItem.getName());
                itemDF.setText(lostItem.getDescription());
                x_coord = lostItem.getX_coord();
                y_coord = lostItem.getY_coord();
                itemLocText.setText(x_coord + ", " + y_coord);
                itemTypeSpinner.setSelection(LostItem.findPosition(lostItem.getType().toString()));
                if (lostItem.getPoster().toString().equals("Finder")) {
                    posterTypeSpinner.setSelection(0);
                } else {
                    posterTypeSpinner.setSelection(1);
                }
            }
        }

        Uri defaultImage;
        if (uri == null) {
            defaultImage = Uri.parse("android.resource://com.teamcow.wheresmystuff/drawable/logo_black_720.png");
        } else {
            defaultImage = uri;
        }
        try {
            InputStream inputStream = getContentResolver().openInputStream(defaultImage);
            itemImage = Drawable.createFromStream(inputStream, defaultImage.toString() );
        } catch (FileNotFoundException e) {
            itemImage = getResources().getDrawable(R.drawable.logo_black_720);
        }

        imageView.setImageDrawable(itemImage);



        itemImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                selectImage();
            }
        });

        itemPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                postItem();
            }
        });

        itemLocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                startPlacePickerActivity();
            }
        });

        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                dispatchTakePictureIntent();
            }
        });
    }
    /**
     * adds a new item to a preexisting list of items
     */
    public void postItem() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference listRef = mDatabase.child("lostitems");

        StorageReference storageRef = FirebaseStorage.getInstance().getReference();

        if (uriChanged) {
            if (editing && Uri.parse(lostItem.getUri()) != null) {
                StorageReference deleteRef = FirebaseStorage.getInstance().getReferenceFromUrl(lostItem.getUri());
                deleteRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("WheresMyStuff", "Remote image deleted successfully");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("WheresMyStuff", "Remote image deletion failed");
                    }
                });
            }

        }

        if (!editing) {
            DatabaseReference postRef = listRef.push();
            String key = postRef.getKey();
            lostItem = new LostItem(key,
                    itemNF.getText().toString(),
                    itemDF.getText().toString(),
                    FirebaseAuth.getInstance().getCurrentUser().getUid(),
                    x_coord,
                    y_coord,
                    (ItemType) itemTypeSpinner.getSelectedItem(),
                    (PosterType) posterTypeSpinner.getSelectedItem(),
                    "");
            postRef.setValue(lostItem);
        } else {
            lostItem.setName(itemNF.getText().toString());
            lostItem.setDescription(itemDF.getText().toString());
            lostItem.setX_coord(x_coord);
            lostItem.setY_coord(y_coord);
            lostItem.setType((ItemType) itemTypeSpinner.getSelectedItem());
            lostItem.setPoster((PosterType) posterTypeSpinner.getSelectedItem());
            lostItem.setUri(uri.toString());
            DatabaseReference postRef = listRef.child(lostItem.getId());
            postRef.setValue(lostItem);
        }

        if (uriChanged) {
            StorageReference uploadRef = storageRef.child("images/" + lostItem.getId().toString());
//            StorageReference uploadRef = storageRef.child("images/test.jpg");
            UploadTask uploadTask = uploadRef.putFile(uri);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("WheresMyStuff", "Upload failed");
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.d("WheresMyStuff", "Upload successful");
                    lostItem.setUri(taskSnapshot.getDownloadUrl().toString());

                    DatabaseReference imageRef = listRef.child(lostItem.getId()).child("uri");
                    imageRef.setValue(lostItem.getUri());
                }
            });
        }

        finish();

//        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
//        StorageReference imageRef = storageRef.child("images/" + )
//
//        UploadTask uploadTask =

//        String itemX_string = itemX.getText().toString();
//        String itemY_string = itemY.getText().toString();
//        double itemX_coordinate = Double.parseDouble(itemX_string);
//        double itemY_coordinate = Double.parseDouble(itemY_string);
//
//        if(itemX_string.length() == 0 || itemY_string.length() == 0)
//        {
//            lid.getItemList().add(new LostItem(itemNF.getText().toString(), itemDF.getText().toString(),
//                    (ItemType) itemTypeSpinner.getSelectedItem(),
//                    (PosterType) posterTypeSpinner.getSelectedItem()));
//            finish();
//        }
//        else
//        {
//            lid.getItemList().add(new LostItem(itemNF.getText().toString(), itemDF.getText().toString(),
//                    (ItemType) itemTypeSpinner.getSelectedItem(),
//                    (PosterType) posterTypeSpinner.getSelectedItem(), itemX_coordinate,
//                    itemY_coordinate));
//            finish();
//        }
    }

    public void startPlacePickerActivity() {
        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();

        try {
            Intent intent = intentBuilder.build(this);
            startActivityForResult(intent, REQUEST_CODE_PLACEPICKER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PLACEPICKER && resultCode == RESULT_OK) {
            displayandSetLocation(data);
        } else if (requestCode == REQUEST_PICK_IMAGE
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {
            uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                itemImage = Drawable.createFromStream(inputStream, uri.toString());
                uriChanged = true;
            } catch (FileNotFoundException e) {
                itemImage = getResources().getDrawable(R.drawable.logo_black_720);
                uri = null;
            }
            imageView.setImageDrawable(itemImage);
        } else if (requestCode == REQUEST_CAPTURE_IMAGE && resultCode == RESULT_OK) {
            //uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                itemImage = Drawable.createFromStream(inputStream, uri.toString());
                uriChanged = true;
            } catch (FileNotFoundException e) {
                itemImage = getResources().getDrawable(R.drawable.logo_black_720);
                uri = null;
            }
            imageView.setImageDrawable(itemImage);


//            Bundle extras = data.getExtras();
//            imageBitmap = (Bitmap) extras.get("data");
//            imageView.setImageBitmap(imageBitmap);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            imageBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
//            byte[] bitmapdata = bos.toByteArray();
//            bs = new ByteArrayInputStream(bitmapdata);
        }
    }

    private void displayandSetLocation(Intent data) {
        Place placeSelected = PlacePicker.getPlace(this, data);

        String name = placeSelected.getName().toString();
        String address = placeSelected.getAddress().toString();

        itemLocText.setText(name + "\n" + address);
        itemLocation = placeSelected;
        x_coord = itemLocation.getLatLng().latitude;
        y_coord = itemLocation.getLatLng().longitude;
        if (name == null && address == null) {
            itemLocText.setText(x_coord + ", " + y_coord);
        }
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_PICK_IMAGE);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_CAPTURE_IMAGE);
//        }

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (photoFile != null) {
                uri = FileProvider.getUriForFile(this,
                        "com.teamcow.wheresmystuff.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(takePictureIntent, REQUEST_CAPTURE_IMAGE);
            }
        }
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


}
