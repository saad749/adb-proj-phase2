/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qu.auction.dao;

import javax.ejb.Stateless;
import edu.qu.auction.domain.Bids;
import edu.qu.auction.domain.Items;
import javax.ejb.EJB;
import javax.ejb.LocalBean;

/**
 *
 * @author hisham_2
 */
@Stateless
public class BidsDaoImpl extends BaseDaoImpl<Bids> implements BidsDao{
    
    @EJB
    ItemsDao itemsDao;
    
    public BidsDaoImpl() {
        super(Bids.class);
    }

    @Override
    public void bid(Bids bids) {
        edit(bids);
        Items item = bids.getItemId();
        item.setCurrentBid(bids);
        itemsDao.edit(item);        
    }    
}
