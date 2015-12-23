/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qu.auction.dao;

import edu.qu.auction.domain.Bids;
import java.util.List;

/**
 *
 * @author hisham_2
 */
public interface BidsDao extends BaseDao<Bids>{
    
    public void bid( Bids bids);
        public List<Bids> getTopRankUser();
        public List<Bids> getTopRankItems();
}
