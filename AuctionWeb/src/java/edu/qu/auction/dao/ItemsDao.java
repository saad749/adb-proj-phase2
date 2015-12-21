/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qu.auction.dao;

import edu.qu.auction.domain.Items;
import java.util.List;

/**
 *
 * @author hisham_2
 */
public interface ItemsDao  extends BaseDao<Items>{
    public List<Items> getItemsByCode(String itemCode);
}
