package id.dekz.retrofitexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.dekz.retrofitexample.model.APIResponse;
import id.dekz.retrofitexample.model.Owner;

/**
 * Created by DEKZ on 8/24/2017.
 */

public class ListAdapter extends BaseAdapter {

    private List<APIResponse> data = new ArrayList<>();

    public ListAdapter() {
    }

    public void setData(List<APIResponse> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int pos, View view, final ViewGroup viewGroup) {
        View v = view;
        final String str = data.get(pos).getLogin();
        if(v == null) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        }

        TextView text = v.findViewById(R.id.tvRow);
        text.setText(str);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(viewGroup.getContext(), str, Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }
}
