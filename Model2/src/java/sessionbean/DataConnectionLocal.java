/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import java.util.List;
import javax.ejb.Local;
import model.Business;
import model.Coll;
import model.Comment;
import model.Commentp;
import model.Play;
import model.User;

/**
 *
 * @author lenovo
 */
@Local
public interface DataConnectionLocal {

    void perisit(Object obj);

    boolean checkByAccount(String name);

    void insertUser(User current);

    boolean checkCollection(int uid, int bid);

    void insertCollection(Coll coll);

    boolean exsitSelectedUser(String name, String password);

    int getSelectedUserID(String name, String password);

    List<Business> limitedBusiness(int number);

    List<Business> limitedBusiness();

    List<Business> taggedBusiness(String tag);

    List<Business> collectedItems(int uid);

    int saveAndId(String name, String password);

    User findUserById(int uid);

    Business findBusinessById(int bid);

    List<Business> findCollectedByUser(User current);

    Coll findSelectedColl(User current, Business viewed);

    void removeData(Object obj);

    int findSelectedCollId(User current, Business viewed);

    void removeCollById(int cid);

    List<Business> randomBusiness(int numbers);

    List<Play> randomPlay(int numbers);



    List<Play> allPlay();

    List<Play> findPlayByTag(String tag);

    List<Play> collectedPlay(User current);

    boolean checkPC(User current, Play v);

    void cancelPC(User current, Play v);

    void confirmPC(User current, Play v);

    boolean checkBC(User current, Business viewed);

   // void persistComment(User current, Business viewed, String comments);

    List<Comment> findCommentsByBusiness(Business viewed);

    void persistPComment(User current, Play v, String comments);

    List<Commentp> findCommentByPlay(Play v);

    void persistComment(User current, Business viewed, String comments);

   
    
    
    
}
