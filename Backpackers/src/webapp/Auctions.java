package webapp;

import Classes.ExecQuery;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Classes.Data.getUserId;
import static Classes.ExecQuery.closeConnection;
import static Classes.ExecQuery.createConnection;

/**
 * Created by Rahul on 12/07/17.
 */
@WebServlet(name = "auctions")
public class Auctions extends HttpServlet {
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String flightNo = data.get("flightNo").getAsString();
        String legId = data.get("legId").getAsString();
        int resrNo = data.get("rserNo").getAsInt();
        String[] flightDetails = flightNo.split(" ");
        boolean success = true;
        String error = "";

        try {
            createConnection();
            String exec = "SELECT * FROM Auction WHERE AirlineId='" + flightDetails[0] + "' AND FlightNo=" + flightDetails[1] + " AND LegId=" + legId;
            ResultSet rs_auction = ExecQuery.execQuery(exec);
            int auctionId = 0;
            if(rs_auction.next()) auctionId = rs_auction.getInt(1);

            exec = "UPDATE Bid SET Status=? WHERE AuctionId=? AND ResrNo=?";
            PreparedStatement pstmt = ExecQuery.updateTable(exec);
            pstmt.setInt(1, 1);
            pstmt.setInt(2, auctionId);
            pstmt.setInt(1, resrNo);
            pstmt.executeUpdate();

            exec = "UPDATE Reservation SET Status=? WHERE ResrNo=?";
            pstmt = ExecQuery.updateTable(exec);
            pstmt.setString(1, "booked");
            pstmt.setInt(2, resrNo);
            pstmt.executeUpdate();

        } catch (Exception e) {
            error = e.toString();
            success = false;
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        JsonObject resultSet = new JsonObject();
        resultSet.addProperty("success", success);
        if(!success){
            resultSet.addProperty("error", error);
        }
        response.getWriter().write(new Gson().toJson(resultSet));
        closeConnection();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String userName = request.getParameter("userName");
        boolean success = true;
        String error = "";
        int personId = 0;
        String user = getUserId("userName");
        try {
            createConnection();

            ResultSet rs_resr = ExecQuery.execQuery("SELECT * FROM Reservation WHERE UserId='"+user+"' AND CustomerId=" + personId);
            JsonArray jsonArray = new JsonArray();
            while(rs_resr.next()) {
                int resrNo = rs_resr.getInt(1);
                JsonObject object = new JsonObject();
                JsonArray bidArray = new JsonArray();
                ResultSet rs_bid = ExecQuery.execQuery("SELECT * FROM Bid WHERE ResrNo=" + resrNo);
                while(rs_bid.next()) {
                    ResultSet rs_auction = ExecQuery.execQuery("SELECT * FROM Auction WHERE Id=" + rs_bid.getInt(1));
                    String flight = "";
                    String date = "";
                    if(rs_auction.next()) {
                        flight = rs_auction.getString(4).concat(" ").concat(rs_auction.getString(3));
                        date = rs_auction.getString(5);
                    }
                    JsonObject bidObject = new JsonObject();
                    bidObject.addProperty("flight", flight);
                    bidObject.addProperty("travelDate", date);
                    bidObject.addProperty("bidamount", rs_bid.getDouble(4));
                    int status = rs_bid.getInt(3);
                    bidObject.addProperty("status", (status == 1)?"approved":"pending");
                    bidArray.add(bidObject);
                }
                if(bidArray.size() > 0) {
                    object.addProperty("resrNo", resrNo);
                    object.addProperty("bids", String.valueOf(bidArray));
                    jsonArray.add(object);
                }
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(new Gson().toJson(jsonArray));
        } catch (Exception e) {
            JsonObject resultSet = new JsonObject();
            resultSet.addProperty("success", false);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(new Gson().toJson(resultSet));
        }
        closeConnection();
    }
}
