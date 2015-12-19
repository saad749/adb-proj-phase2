/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qu.auction.ws;

import edu.qu.auction.dao.BidsDao;
import edu.qu.auction.dao.ItemsDao;
import edu.qu.auction.dao.UsersDao;
import edu.qu.auction.domain.Bids;
import edu.qu.auction.domain.Items;
import edu.qu.auction.domain.Users;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ws")
public class AuctionWS {

    @EJB
    private ItemsDao itemsFacade;
    @EJB
    UsersDao usersDao;
    @EJB
    BidsDao bidsDao;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String methodTest() {
        List<Items> list = itemsFacade.findAll();
        int x = list.size();
        return "Hello World " + x;
    }

    @GET
    @Path("/items")
    @Produces(MediaType.APPLICATION_XML)
    public List<Items> getITems() {
        return itemsFacade.findAll();
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_XML)
    public List<Users> getUsers() {
        return usersDao.findAll();
    }

    @GET
    @Path("/bids")
    @Produces(MediaType.APPLICATION_XML)
    public List<Bids> getBids() {
        return bidsDao.findAll();
    }
    
    @POST
    public Response postBid( 
            @FormParam("userName") String userName,
            @FormParam("itemId") String itemId,
            @FormParam("value") String value
            
        ){
        
        Users user =  usersDao.findByUserName(userName);
        Items item = itemsFacade.find(new Integer(itemId));
        
        Bids bid = new Bids();
        
//        bid.setId(new Integer(id));
        bid.setItemId(item);
        bid.setUserId(user);
        bid.setBidValue(new Double(value));
        
        bidsDao.bid(bid);
        
        System.out.println("------->post" + userName);
        return Response.ok().build();
    }
}
