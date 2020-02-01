package bluemwall.mhussain96.com.bluemwall.Model;

/**
 * Created by Muhtamim Fuwad Nahid on 3/16/2018.
 */

public class WallpaperItem {
    public String imageUrl;
    public String categoryId;
    public String name;
    public long viewCount;

    public WallpaperItem() {
    }

    public WallpaperItem(String imageUrl, String categoryId, String name) {
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }
}
