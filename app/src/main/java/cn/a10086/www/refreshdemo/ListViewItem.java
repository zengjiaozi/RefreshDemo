package cn.a10086.www.refreshdemo;

/**
 * @author
 * @time 2017/1/22  10:54
 * @desc ${TODD}
 */
public class ListViewItem {

    private  int UserImg;
    private  String UserName;
    private  String UserComment;

    public String getUserComment() {
        return UserComment;
    }

    public void setUserComment(String userComment) {
        UserComment = userComment;
    }

    public int getUserImg() {
        return UserImg;
    }

    public void setUserImg(int userImg) {
        UserImg = userImg;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
