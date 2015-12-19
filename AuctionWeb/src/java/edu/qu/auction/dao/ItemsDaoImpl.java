/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qu.auction.dao;

import javax.ejb.Stateless;
import edu.qu.auction.domain.Items;
import javax.ejb.LocalBean;

/**
 *
 * @author hisham_2
 */


@Stateless
public class ItemsDaoImpl extends BaseDaoImpl<Items> implements ItemsDao{

    public ItemsDaoImpl() {
        super(Items.class);
    } 
}
