package app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Bid {
  @Id
  private String id;
  private String postId;
  private String bidUserId;
  private Double priceFixed;
  private Integer estimateHour; // estimate work hour
  private Date createdTs;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPostId() {
    return postId;
  }

  public void setPostId(String postId) {
    this.postId = postId;
  }

  public String getBidUserId() {
    return bidUserId;
  }

  public void setBidUserId(String bidUserId) {
    this.bidUserId = bidUserId;
  }

  public Double getPriceFixed() {
    return priceFixed;
  }

  public void setPriceFixed(Double priceFixed) {
    this.priceFixed = priceFixed;
  }

  public Integer getEstimateHour() {
    return estimateHour;
  }

  public void setEstimateHour(Integer estimateHour) {
    this.estimateHour = estimateHour;
  }

  public Date getCreatedTs() {
    return createdTs;
  }

  public void setCreatedTs(Date createdTs) {
    this.createdTs = createdTs;
  }
}
