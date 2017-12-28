package com.mainproject.vishnu_neelancheri.lottery.add_view_user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mainproject.vishnu_neelancheri.lottery.R;
import com.mainproject.vishnu_neelancheri.lottery.dao.CustomerDao;

import java.util.ArrayList;

public class ViewCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);
        RecyclerView recyclerView = findViewById( R.id.recycler_show_customer);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this );
        recyclerView.setLayoutManager( layoutManager );
        final ArrayList< CustomerDetails > customerDetailsArrayList =  CustomerDao.getInstance().getCustomer(this);
        ViewCustomerAdapter viewCustomerAdapter = new ViewCustomerAdapter(customerDetailsArrayList ,
                new ViewCustomerAdapter.ItemSelection() {
                    @Override
                    public void delete(int pos) {

                    }

                    @Override
                    public void select(int pos) {
                        Toast.makeText( ViewCustomerActivity.this, Integer.toString(customerDetailsArrayList.get(pos).getId()), Toast.LENGTH_SHORT).show();
                    }
                });
        recyclerView.setAdapter( viewCustomerAdapter );

    }
}

