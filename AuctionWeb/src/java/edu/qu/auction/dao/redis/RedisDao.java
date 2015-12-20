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

    void bid(String userName, String itemCode, double bidValue);

    void insertItem(String itemCode, String itenDesc, String price);

    void clearItems();

    String getAllItemsHash();
    
     String getAllItemsAsList();
     void insertItemsAsList(String itemCode, String itenDesc, String price);
     String searchItems(String match);

}
