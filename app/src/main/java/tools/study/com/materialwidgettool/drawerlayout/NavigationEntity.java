package tools.study.com.materialwidgettool.drawerlayout;


/**
 * @author suzhuning
 * @date 2016/11/7.
 * Description:
 */
public class NavigationEntity {

    private String id;
    private String name;
    private int iconRes;

    public NavigationEntity(String id, String name, int iconRes) {
        this.id = id;
        this.name = name;
        this.iconRes = iconRes;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getIconRes() {
        return iconRes;
    }
}
