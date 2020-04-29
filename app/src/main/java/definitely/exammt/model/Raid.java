package definitely.exammt.model;

public class Raid {

    private String raidTitle;
    private String raidDescription;
    private String imgUrl;


    public Raid() {
    }

    public Raid(String raidTitle, String raidDescription, String imgUrl) {
        this.raidTitle = raidTitle;
        this.raidDescription = raidDescription;
        this.imgUrl = imgUrl;
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRaidTitle() {
        return raidTitle;
    }

    public void setRaidTitle(String raidTitle) {
        this.raidTitle = raidTitle;
    }

    public String getRaidDescription() {
        return raidDescription;
    }

    public void setRaidDescription(String raidDescription) {
        this.raidDescription = raidDescription;
    }

}
