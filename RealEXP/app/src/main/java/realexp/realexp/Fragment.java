package realexp.realexp;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Michaella on 2/21/2016.
 */
public class Fragment {
    public View onCreateView(LayoutInflator inflator, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflator.inflate(R.layout.article_view, container, false)
    }
}
