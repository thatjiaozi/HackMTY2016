package com.example.guillermo.mapsprueba3;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Guillermo on 21/02/2016.
 */
public class UtilsListFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( getActivity(), android.R.layout.simple_list_item_1 );
        String[] items = getResources().getStringArray( R.array.list_items );
        adapter.addAll( new ArrayList( Arrays.asList(items) ) );
        setListAdapter( adapter );
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String item = ( (TextView) v ).getText().toString();
        if( getString( R.string.item_clustering ).equalsIgnoreCase( item ) ) {
            startActivity( new Intent( getActivity(), ClusterMarkerActivity.class ) );
        } else if( getString( R.string.item_heat_map ).equalsIgnoreCase( item ) ) {
            startActivity( new Intent( getActivity(), HeatMapActivity.class ) );
        }
    }
}
