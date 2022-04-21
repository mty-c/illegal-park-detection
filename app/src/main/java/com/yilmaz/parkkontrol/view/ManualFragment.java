package com.yilmaz.parkkontrol.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yilmaz.parkkontrol.adapter.RecyclerAdapter;
import com.yilmaz.parkkontrolu.R;

import java.util.ArrayList;


public class ManualFragment extends Fragment {
    RecyclerView recyclerView;
    TextView totalPhotos;
    ImageView pickImage, takePlate;

    ArrayList<Uri> uri = new ArrayList<>();
    RecyclerAdapter recyclerAdapter;

    private static final int Read_Permission = 101;

    public ManualFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manual, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        totalPhotos = view.findViewById(R.id.totalPhotos);
        recyclerView = view.findViewById(R.id.recyclerView_Gallery_Images);
        pickImage = view.findViewById(R.id.imageViewUpload);
        takePlate = view.findViewById(R.id.imageViewTakePhoto);

        recyclerAdapter = new RecyclerAdapter(uri);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext().getApplicationContext(), 3));
        recyclerView.setAdapter(recyclerAdapter);

        if (ContextCompat.checkSelfPermission(getContext().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Read_Permission);
        }

        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                }
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

            }
        });
        takePlate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 2);
                } else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (data.getClipData() != null) {
                int x = data.getClipData().getItemCount();

                for (int i = 0; i < x; i++) {
                    uri.add(data.getClipData().getItemAt(i).getUri());
                }
                recyclerAdapter.notifyDataSetChanged();
                totalPhotos.setText("Photos (" + uri.size() + ")");
            } else if (data.getData() != null) {
                String imageURL = data.getData().getPath();
                uri.add(Uri.parse(imageURL));
            }
        }

    }
}