package app.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
public class Post {
  @Id
  private String id;
  private String jobDesc;
  private Date bidDueTs;
  private transient List<Bid> currBids;
  private String acceptedBidId;
  private Boolean status;
  private String ownerId;
  private String laborType;
  private Date createdTs;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getJobDesc() {
    return jobDesc;
  }

  public void setJobDesc(String jobDesc) {
    this.jobDesc = jobDesc;
  }

  public Date getBidDueTs() {
    return bidDueTs;
  }

  public void setBidDueTs(Date bidDueTs) {
    this.bidDueTs = bidDueTs;
  }

  public List<Bid> getCurrBids() {
    return currBids;
  }

  public void setCurrBids(List<Bid> currBids) {
    this.currBids = currBids;
  }

  public String getAcceptedBidId() {
    return acceptedBidId;
  }

  public void setAcceptedBidId(String acceptedBidId) {
    this.acceptedBidId = acceptedBidId;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public String getLaborType() {
    return laborType;
  }

  public void setLaborType(String laborType) {
    this.laborType = laborType;
  }

  public Date getCreatedTs() {
    return createdTs;
  }

  public void setCreatedTs(Date createdTs) {
    this.createdTs = createdTs;
  }
}
