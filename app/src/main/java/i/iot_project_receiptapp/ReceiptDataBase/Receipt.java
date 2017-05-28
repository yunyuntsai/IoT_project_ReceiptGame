package i.iot_project_receiptapp.ReceiptDataBase;

/**
 * Created by tsaiyunyun on 2017/5/28.
 */
public class Receipt {
    private Long id;
    private String receiptID;
    private String createDate;
    private Integer period;
    private Boolean status;
    private Integer awards;

    public Receipt() {
    }

    public Receipt(Long id) {
        this.id = id;
    }

    public Receipt(Long id, String receiptID, String createDate, Integer period, Boolean status, Integer awards) {
        this.id = id;
        this.receiptID = receiptID;
        this.createDate = createDate;
        this.period = period;
        this.status = status;
        this.awards = awards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getAwards() {
        return awards;
    }

    public void setAwards(Integer awards) {
        this.awards = awards;
    }
}
