package com.example.rijogeorge.peel.utilities;

import com.example.rijogeorge.peel.model.domain.Content;
import com.example.rijogeorge.peel.model.domain.PlayList;
import com.example.rijogeorge.peel.model.mock.ContentJson;
import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rijogeorge on 11/18/17.
 */
public class PlaylistHelperTest {

    @Before
    public void setUp() throws Exception {
        Content content = new Gson().fromJson(ContentJson.contentJson,Content.class);
    }

    @Test
    public void getPlayListsForIllegalCombination(){
        List<PlayList> playLists = PlaylistHelper.getPlayLists("MI3", "US");
        Assert.assertEquals(0,playLists.size());
    }

    @Test
    public void getPlayListsForMI3andCA() throws Exception {
        List<PlayList> playLists = PlaylistHelper.getPlayLists("MI3", "CA");
        Assert.assertEquals(1,playLists.size());
    }

    @Test
    public void getPlayListsForMI3andUK() {
        List<PlayList> playLists = PlaylistHelper.getPlayLists("MI3", "UK");
        Assert.assertEquals(2,playLists.size());
    }
}