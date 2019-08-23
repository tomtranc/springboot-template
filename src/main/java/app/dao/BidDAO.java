package app.dao;

import app.models.Bid;

import java.util.List;

public interface BidDAO {

  public List<Bid> list();
  public List<Bid> listBidByProjId(String projectId);
  public Bid save(Bid bid);
  public Bid update(Bid bid);
  public Bid delete(Bid bid);
}
