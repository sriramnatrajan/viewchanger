package com.example.aravind.viewchanger;

import java.util.ArrayList;

public class Paginator {
    private final int itemPerPage,lastPageItem,lastPage;
    private final ArrayList<User> userList;



    public Paginator(int itemPerPage, ArrayList<User> userList) {
        this.itemPerPage = itemPerPage;
        //this.lastPageItem = lastPageItem;
        // this.lastPage = lastPage;
        this.userList = userList;
        int totalItem = userList.size();
        this.lastPage = totalItem / itemPerPage;
        this.lastPageItem = totalItem % itemPerPage;

    }

    public ArrayList<User> getUserList(int currentPage) {

        int startItem=currentPage * itemPerPage;
        ArrayList<User> newPageData = new ArrayList<>();
        if(currentPage == lastPage)
            for(int count=0;count<lastPageItem;count++)
                newPageData.add(userList.get(startItem+count));
        //return userList;
        else
            for(int count=0;count<itemPerPage;count++)
                newPageData.add(userList.get(startItem + count));
        return newPageData;

    }
   public int getLastPage()
{
    return lastPage;
}
}
