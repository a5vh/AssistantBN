package com.a5vh.assistant;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

public class RoutesFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.fragment_routes);

        ListView listView = (ListView) getActivity().findViewById(R.id.route);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) this);
    }
}
