/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qu.auction.dao;

import edu.qu.auction.domain.Users;

/**
 *
 * @author hisham_2
 */
public interface UsersDao extends BaseDao<Users>{
    
    public Users findByUserName(String userName);
}
