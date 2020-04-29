package definitely.exammt.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "all_raids")
public class RaidEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String raidTitle;
    private String raidDescription;
    private String imgUrl;

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

