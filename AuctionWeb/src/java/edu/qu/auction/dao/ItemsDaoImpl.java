/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qu.auction.dao;

import javax.ejb.Stateless;
import edu.qu.auction.domain.Items;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author hisham_2
 */


@Stateless
public class ItemsDaoImpl extends BaseDaoImpl<Items> implements ItemsDao{

    public ItemsDaoImpl() {
        super(Items.class);
    } 
    
    public List<Items> getItemsByCode(String itemCode){
        Query query = getEntityManager().createNamedQuery("Items.findByItemCode");
        query.setParameter("itemCode", itemCode);
        List<Items> list = query.getResultList();       
       return list;
       
    } 
    
    public List<Items> getAllItemsOrdered(){
        Query query = getEntityManager().createNamedQuery("Items.findAll");        
        List<Items> list = query.getResultList();       
       return list;
       
    } 
    
}
