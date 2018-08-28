package com.mngh.floattube.fragments.local.bookmark;

import com.mngh.floattube.database.stream.StreamStatisticsEntry;
import com.mngh.floattube.database.stream.StreamStatisticsEntry;

import com.mngh.floattube.R;
import com.mngh.floattube.database.stream.StreamStatisticsEntry;

import java.util.Collections;
import java.util.List;

public final class MostPlayedFragment extends StatisticsPlaylistFragment {
    @Override
    protected String getName() {
        return getString(com.mngh.floattube.R.string.title_most_played);
    }

    @Override
    protected List<StreamStatisticsEntry> processResult(List<StreamStatisticsEntry> results)  {
        Collections.sort(results, (left, right) ->
                ((Long) right.watchCount).compareTo(left.watchCount));
        return results;
    }

}
