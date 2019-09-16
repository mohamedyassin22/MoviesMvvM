package com.movies.moviesmvvm.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.movies.moviesmvvm.R;
import com.movies.moviesmvvm.databinding.ListGroupBinding;
import com.movies.moviesmvvm.databinding.MoviesListItemBinding;
import com.movies.moviesmvvm.model.HeaderList;
import com.movies.moviesmvvm.model.Movie;

import java.util.HashMap;
import java.util.List;

public class movieExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<HeaderList> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<Movie>> _listDataChild;
    private List<Movie> movies;

    public movieExpandableListAdapter(Context context, List<HeaderList> listDataHeader,
                                      HashMap<String, List<Movie>> listChildData, List<Movie> movies) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.movies = movies;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        HeaderList headerTitle = _listDataHeader.get(groupPosition);
        ListGroupBinding headerItemBinding = null;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             headerItemBinding = DataBindingUtil.inflate(infalInflater,
                    R.layout.list_group, parent, false);


            
        }

        headerItemBinding.lblListHeader.setTypeface(null, Typeface.BOLD);
        headerItemBinding.setHeader(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        MoviesListItemBinding moviesListItemBinding = null;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             moviesListItemBinding =
                    DataBindingUtil.inflate(infalInflater, R.layout.movies_list_item,
                            parent, false);

        }
        moviesListItemBinding.setMovie(movies.get(groupPosition));
        convertView = moviesListItemBinding.getRoot();

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
