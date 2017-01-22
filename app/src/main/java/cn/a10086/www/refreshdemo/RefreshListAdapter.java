package cn.a10086.www.refreshdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author
 * @time 2017/1/22  10:56
 * @desc ${TODD}
 */
public class RefreshListAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<ListViewItem> listitems;

    public RefreshListAdapter(Context context, ArrayList<ListViewItem> listitems) {
        this.context = context;
        this.listitems = listitems;
    }

    @Override
    public int getCount() {
        return listitems.size();
    }

    @Override
    public Object getItem(int position) {
        return listitems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Myholder myholder = null;
        if (convertView == null) {
            myholder = new Myholder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
            myholder.title = (TextView) convertView.findViewById(R.id.title);
            myholder.content = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(myholder);
        } else {
            myholder = (Myholder) convertView.getTag();
        }

        ListViewItem listViewItem = listitems.get(position);
        myholder.title.setText(listViewItem.getUserName());
        myholder.content.setText(listViewItem.getUserComment());
        return convertView;

    }

    class Myholder {
        private TextView title;
        private TextView content;

    }

}
