package i.iot_project_receiptapp.ReceiptDataBase;

/**
 * Created by tsaiyunyun on 2017/5/28.
 */
public class RaffleReceipt {
    private Long id;
    private String year;
    private Integer period;
    private Integer talcount;
    private Integer superAwardsCount;
    private Integer specialAwardsCount;
    private Integer oneAwardsCount;
    private Integer twoAwardsCount;
    private Integer threeAwardsCount;
    private Integer fourAwardsCount;
    private Integer fiveAwardsCount;
    private Integer sixAwardsCount;

    public RaffleReceipt() {
    }

    public RaffleReceipt(Long id) {
        this.id = id;
    }

    public RaffleReceipt(Long id, String year, Integer period, Integer talcount, Integer superAwardsCount, Integer specialAwardsCount, Integer oneAwardsCount, Integer twoAwardsCount, Integer threeAwardsCount, Integer fourAwardsCount, Integer fiveAwardsCount, Integer sixAwardsCount) {
        this.id = id;
        this.year = year;
        this.period = period;
        this.talcount = talcount;
        this.superAwardsCount = superAwardsCount;
        this.specialAwardsCount = specialAwardsCount;
        this.oneAwardsCount = oneAwardsCount;
        this.twoAwardsCount = twoAwardsCount;
        this.threeAwardsCount = threeAwardsCount;
        this.fourAwardsCount = fourAwardsCount;
        this.fiveAwardsCount = fiveAwardsCount;
        this.sixAwardsCount = sixAwardsCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getTalcount() {
        return talcount;
    }

    public void setTalcount(Integer talcount) {
        this.talcount = talcount;
    }

    public Integer getSuperAwardsCount() {
        return superAwardsCount;
    }

    public void setSuperAwardsCount(Integer superAwardsCount) {
        this.superAwardsCount = superAwardsCount;
    }

    public Integer getSpecialAwardsCount() {
        return specialAwardsCount;
    }

    public void setSpecialAwardsCount(Integer specialAwardsCount) {
        this.specialAwardsCount = specialAwardsCount;
    }

    public Integer getOneAwardsCount() {
        return oneAwardsCount;
    }

    public void setOneAwardsCount(Integer oneAwardsCount) {
        this.oneAwardsCount = oneAwardsCount;
    }

    public Integer getTwoAwardsCount() {
        return twoAwardsCount;
    }

    public void setTwoAwardsCount(Integer twoAwardsCount) {
        this.twoAwardsCount = twoAwardsCount;
    }

    public Integer getThreeAwardsCount() {
        return threeAwardsCount;
    }

    public void setThreeAwardsCount(Integer threeAwardsCount) {
        this.threeAwardsCount = threeAwardsCount;
    }

    public Integer getFourAwardsCount() {
        return fourAwardsCount;
    }

    public void setFourAwardsCount(Integer fourAwardsCount) {
        this.fourAwardsCount = fourAwardsCount;
    }

    public Integer getFiveAwardsCount() {
        return fiveAwardsCount;
    }

    public void setFiveAwardsCount(Integer fiveAwardsCount) {
        this.fiveAwardsCount = fiveAwardsCount;
    }

    public Integer getSixAwardsCount() {
        return sixAwardsCount;
    }

    public void setSixAwardsCount(Integer sixAwardsCount) {
        this.sixAwardsCount = sixAwardsCount;
    }
}
