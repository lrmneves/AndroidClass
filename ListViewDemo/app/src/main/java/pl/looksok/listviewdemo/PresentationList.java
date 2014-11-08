package pl.looksok.listviewdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PresentationList extends Activity {

	private AtomPayListAdapter adapter;
    private Presentation itemToRemove;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		
		setupListViewAdapter();
		
		setupAddPresentationButton();
	}

	public void removePresentation(View v) {
        itemToRemove = (Presentation)v.getTag();

        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.remove(itemToRemove);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
	}
    public void editPresentation(View v) {
        Toast toast = Toast.makeText(this, "Teste do butao", Toast.LENGTH_SHORT);
        toast.show();
    }

	private void setupListViewAdapter() {
		adapter = new AtomPayListAdapter(PresentationList.this, R.layout.atom_pay_list_item, new ArrayList<Presentation>());
		ListView atomPaysListView = (ListView)findViewById(R.id.EnterPays_atomPaysList);
		atomPaysListView.setAdapter(adapter);
	}
	
	private void setupAddPresentationButton() {
		findViewById(R.id.EnterPays_addAtomPayment).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				adapter.insert(new Presentation("", 0), 0);
			}
		});
	}
}
