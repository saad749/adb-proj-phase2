/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qu.auction.dao.redis;

import java.util.List;
import java.util.Map;

/**
 *
 * @author hiy2001
 */
public interface RedisDao {

    void bid(String userName, String itemCode, String bidValue);

    void insertItemsHash(String itemCode, String itenDesc, String price);

    void clearItems();

    String getAllItemsHash();
    
     String getAllItemsAsList();
     void insertItemsAsList(String itemCode, String itenDesc, String price);
     String searchItems(String match);
     void deleteAllKeys();
      String getLBoardITems();
      String getLBoardUsers();
      void insertUsersHash(String userName, String firstName, String lastName, String email, String contactNumber) ;
       String getAllItemsSortedHash();

}
