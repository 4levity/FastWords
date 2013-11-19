package com.ijklibrarian.fastwords;

import android.text.ClipboardManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListAdapterSearch extends BaseExpandableListAdapter {
	static private WordSearchProvider[] _providers={
		new WSPDummy(),
		new WSPUrbanDict(),
		new WSPWiktionary()
		// add additional WordSearchProviders here

		// TODO: user can select which providers to use
	};
	private SelectWordSearch _context;
	
	ExpandableListAdapterSearch(SelectWordSearch c) {
		super();
		_context=c;
	}
	
	@Override
	public void onGroupExpanded(int groupPosition) {		
		super.onGroupExpanded(groupPosition);
	}
	
	public Object getChild(int groupPosition, int childPosition) {
		WordSearchProvider wsp=(WordSearchProvider)this.getGroup(groupPosition);
		return wsp.results(this.getSearchTerm(wsp));
	}

	public long getChildId(int groupPosition, int childPosition) {
		return 1000+groupPosition;
	}

	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		TextView v;
		if(convertView == null || !(convertView instanceof TextView)) {
			v=new TextView(_context);
		} else {
			v=(TextView)convertView;
		}
		v.setText(getChild(groupPosition, childPosition).toString());
		return v;
	}

	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	public Object getGroup(int groupPosition) {
		return _providers[groupPosition];
	}

	public int getGroupCount() {
		return _providers.length;
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	
	private String getSearchTerm(WordSearchProvider wsp) {
		ClipboardManager cm=_context.clipboard;
		String word=null;

		CharSequence txt=cm.getText();
		if(txt != null) {
			String searchTerms=txt.toString();
			if(searchTerms!=null) {
				searchTerms=searchTerms.trim();
				int ix=0;
				int maxLen=wsp.getMaximumSearchChars();
				while(word==null && ix<searchTerms.length()) {
					char c=searchTerms.charAt(ix);
					if (Character.isWhitespace(c)) {
						word=searchTerms.substring(0, ix);
					}
					ix++;
				}
				if(word==null) {
					word=searchTerms;
				}		
			}
		} 
		
		return word;
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView v;
		if(convertView == null || !(convertView instanceof TextView)) {
			v=new TextView(_context);			
			v.setHeight(50);
			v.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
		} else {
			v=(TextView)convertView;
		}
		String searchTerm=this.getSearchTerm(_providers[groupPosition]);
		if(searchTerm==null) 
			searchTerm="Nothing selected";
		v.setText(this.getGroup(groupPosition).toString()
				+": "+searchTerm);
		return v;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
