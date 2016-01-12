package realexp.realexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;

public class CheckList_Activity extends ActionBarActivity implements View.OnClickListener {

    ImageButton addToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);

        addToDo = (ImageButton) findViewById(R.id.addToDoItem);
        addToDo.setOnClickListener(this);



    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.addToDoItem:
                finish();
                startActivity(new Intent(this, createToDo_Activity.class));
                break;

        }
    }
}
