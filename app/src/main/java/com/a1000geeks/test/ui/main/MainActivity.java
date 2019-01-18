package com.a1000geeks.test.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.a1000geeks.test.R;
import com.a1000geeks.test.data.models.ResponseCars;
import com.a1000geeks.test.ui.base.BaseActivity;
import com.a1000geeks.test.utils.CustomPopup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainMvpView, CarsAdapter.Callback {

    private static final String SORTER_ASC = "SORTER_ASC";
    private static final String SORTER_DESC = "SORTER_DESC";

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.spinner)
    Spinner spinner;

    @Inject
    MainPresenter<MainMvpView> presenter;

    private CarsAdapter carsAdapter;
    private ResponseCars responseCarsUpdate;

    String[] cities = {"По возрастающей цене", "По убывающей цене"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getScreenComponent().inject(this);
        presenter.onAttach(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        carsAdapter = new CarsAdapter();
        carsAdapter.setCallback(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(carsAdapter);
        presenter.getCars(SORTER_ASC);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
               if (selectedItemPosition == 0){
                   presenter.getCars(SORTER_ASC);
                   progressBar.setVisibility(View.VISIBLE);
                   recyclerView.setVisibility(View.GONE);
               } else if (selectedItemPosition == 1){
                   presenter.getCars(SORTER_DESC);
                   progressBar.setVisibility(View.VISIBLE);
                   recyclerView.setVisibility(View.GONE);
               }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @OnClick(R.id.add_car_button)
    public void addCar() {
        showHandbookPopup(recyclerView);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onReceivedCars(List<ResponseCars> responseCars) {
        carsAdapter.setItems(responseCars);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onErrorReceivedCars(Throwable throwable) {
        Snackbar.make(recyclerView, "Ошибка получения данных", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSavedCars() {
        presenter.getCars(SORTER_ASC);
        Snackbar.make(recyclerView, "Машина сохранена успешно", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorSavedCars() {
        Snackbar.make(recyclerView, "Ошибка сохранения", Snackbar.LENGTH_SHORT).show();
    }

    public void showHandbookPopup(View contentView) {
        @SuppressLint("InflateParams") View popupView = getLayoutInflater().inflate(R.layout.popup, null);
        CustomPopup popupWindow = new CustomPopup(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setUp(contentView);
        EditText nameManufacturer = popupView.findViewById(R.id.name_manufacturer);
        EditText name = popupView.findViewById(R.id.name);
        EditText price = popupView.findViewById(R.id.price);
        if (responseCarsUpdate != null){
            nameManufacturer.setText(responseCarsUpdate.getNameManufacturer());
            name.setText(responseCarsUpdate.getName());
            price.setText(String.valueOf(responseCarsUpdate.getPrice()));
        }
        popupView.findViewById(R.id.save_popup_button).setOnClickListener(v -> {
            if (nameManufacturer.getText().toString().equals("")) {
                Snackbar.make(popupView, "Заполнены не все поля", Snackbar.LENGTH_LONG).show();
            } else if (name.getText().toString().equals("")) {
                Snackbar.make(popupView, "Заполнены не все поля", Snackbar.LENGTH_LONG).show();
            } else if (price.getText().toString().equals("")) {
                Snackbar.make(popupView, "Заполнены не все поля", Snackbar.LENGTH_LONG).show();
            } else {
                ResponseCars responseCars = new ResponseCars();
                responseCars.setNameManufacturer(nameManufacturer.getText().toString());
                responseCars.setName(name.getText().toString());
                try {
                    responseCars.setPrice(Integer.parseInt(price.getText().toString()));
                } catch (Exception e) {
                    responseCars.setPrice(0);
                }
                try {
                    responseCars.setId(responseCarsUpdate.getId());
                    presenter.updateCar(responseCars);
                } catch (Exception e){
                    presenter.saveCar(responseCars);
                }
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                popupWindow.dismiss();
            }
        });
        popupView.findViewById(R.id.abort_popup_button).setOnClickListener(v -> {
            popupWindow.dismiss();
        });
    }

    @Override
    public void updateRecord(ResponseCars responseCarsUpdate) {
        this.responseCarsUpdate = responseCarsUpdate;
        showHandbookPopup(recyclerView);
    }
}
