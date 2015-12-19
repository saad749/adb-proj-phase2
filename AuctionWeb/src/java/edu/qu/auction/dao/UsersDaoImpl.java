/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qu.auction.dao;

import javax.ejb.Stateless;
import edu.qu.auction.domain.Users;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author hisham_2
 */
@Stateless
public class UsersDaoImpl extends BaseDaoImpl<Users> implements UsersDao {

    public UsersDaoImpl() {
        super(Users.class);
    }

    @Override
    public Users findByUserName(String userName) {
        Query query = getEntityManager().createNamedQuery("Users.findByUserName");
        query.setParameter("userName", userName);
        List<Users> list = query.getResultList();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
