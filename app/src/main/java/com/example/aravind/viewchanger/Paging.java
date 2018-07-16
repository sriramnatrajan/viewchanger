package com.example.aravind.viewchanger;

import java.util.ArrayList;

public class Paging {
    public static final int ToTAL_NUM_ITEMS=52;
    public static final int ITEMS_PER_PAGE=10;
    public static final int ITEMS_REMAINING=ToTAL_NUM_ITEMS % ITEMS_PER_PAGE;
    public static final int LAST_PAGE=ToTAL_NUM_ITEMS/ITEMS_PER_PAGE;

    public ArrayList<String> generatePage(int currentPage)
    {
        int startItem=currentPage*ITEMS_PER_PAGE+1;
        int numofData=ITEMS_PER_PAGE;

        ArrayList<String> pageData=new ArrayList<>();


        if(currentPage==LAST_PAGE && ITEMS_REMAINING>0)
        {
            for (int i = startItem; i < startItem + ITEMS_REMAINING; i++)
            {
                pageData.add("NUMBER " + 1);
            }
        }
        else {
            for (int i = startItem; i < startItem + numofData; i++)
            {
                {
                pageData.add("NUMBER " + 1);
            }
        }

        }


        return pageData;
    }
}
