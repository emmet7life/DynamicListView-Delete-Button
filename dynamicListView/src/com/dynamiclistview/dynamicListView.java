package com.dynamiclistview;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class dynamicListView extends ListActivity implements OnClickListener  {
   
    EditText textContent;
    Button submit;
    ListView mainListview;
    Spinner spin;

    private static class ListViewAdapter extends BaseAdapter
    {
    private LayoutInflater mInflater;
    
    public ListViewAdapter(Context context) 
    {  
        mInflater = LayoutInflater.from(context);     
    }
    public int getCount() 
    {
        return ListviewContent.size();
    }
    public Object getItem(int position) 
    {
        return position;
    }
    public long getItemId(int position)
    {
        return position;
    }
    public View getView(final int position, View convertView, ViewGroup parent) 
    {         	
        ListContent holder;
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.listviewinflate, null);
            holder = new ListContent();
            holder.delete=(Button)convertView.findViewById(R.id.del);
            holder.delete.setTag(position);
            holder.text = (TextView) convertView.findViewById(R.id.TextView01);
            holder.text.setCompoundDrawables(convertView.getResources().getDrawable(R.drawable.icon), null, null, null);
            convertView.setTag(holder);
        } 
        else 
        {
            
            holder = (ListContent) convertView.getTag();
        }   
        holder.delete.setOnClickListener(new OnClickListener() 
        {				
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				ListviewContent.remove(position);
				notifyDataSetChanged();
			}
		});
        holder.text.setText(ListviewContent.get(position));
              return convertView;
    }
    static class ListContent
    {
        TextView text;
        Button delete;
    }
}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);   
        textContent=(EditText)findViewById(R.id.EditText01);
        spin=(Spinner)findViewById(R.id.spinner1);
        submit=(Button)findViewById(R.id.Button01);
        mainListview=(ListView)findViewById(android.R.id.list);
        submit.setOnClickListener(this);
        setListAdapter(new ListViewAdapter(this));
        mainListview.setOnItemClickListener(new OnItemClickListener() 
        {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(dynamicListView.this, String.valueOf(arg2), Toast.LENGTH_SHORT).show();
			}
        	
		});
    }
	private static final ArrayList<String> ListviewContent = new ArrayList<String>();
	@Override
	public void onClick(View v) {
		if(v==submit)
		{
			ListviewContent.add(spin.getSelectedItem().toString()+"   "+textContent.getText().toString());
			setListAdapter(new ListViewAdapter(this));
		}
	}
}