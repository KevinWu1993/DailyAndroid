package cn.kevinwu.zhihudaily_android.model;

/**
 * author: KevinWu
 * date:   On 2018/11/8.
 */

public class NewsModel {
    private int id;
    private String title;
    private String image;
    private String shareUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof NewsModel) {
            return ((NewsModel)obj).id == this.id;
        }else {
            return super.equals(obj);
        }
    }
}
