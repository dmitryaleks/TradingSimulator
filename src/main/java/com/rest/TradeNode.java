package com.rest;

import com.rest.matching.MatchingEngine;
import com.rest.model.Orders;
import com.rest.util.InstrumentManager;
import com.rest.util.OrderManager;
import com.rest.util.TradeManager;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/trade")
public class TradeNode {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTrades() {

        try {
            List<JSONObject> orders = TradeManager.getAllTrades();
            JSONArray res = new JSONArray();
            orders.stream().forEach(ord -> res.put(ord));
            return Response.status(200).entity(res.toString()).build();
        } catch (final TradeManager.TradeLookupException ex) {
            ex.printStackTrace();
        }
        return Response.status(210).entity("No orders found").build();
    }

}