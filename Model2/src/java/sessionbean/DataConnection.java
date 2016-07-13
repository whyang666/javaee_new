/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;


import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Bt;
import model.Business;
import model.Coll;
import model.CollPlay;
import model.Comment;
import model.Commentp;
import model.Play;
import model.Pt;
import model.User;

/**
 *
 * @author lenovo
 */
@Stateless
public class DataConnection implements DataConnectionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;
    public DataConnection(){
        
    }
    @Override
    public void perisit(Object obj) {
        em.persist(obj);
    }

    @Override
    public boolean checkByAccount(String name) {
        Query q = em.createQuery("select s from User s");
        List<User> items=q.getResultList();
        int k=items.size();
        if(k==0) return false;
        else{
            for(int i=0;i<=k-1;i++){
                if(name.equals(items.get(i).getUname())) return true;
            }
            return false;
        }
    }

    @Override
    public void insertUser(User current) {
          perisit(current);
    }

    @Override
    public boolean checkCollection(int uid, int bid) {
        Coll newC=new Coll();
        Query q=em.createQuery("select s from Coll s");
        List<Coll> items=q.getResultList();
        int k = items.size();
        if(k>0){
            for(int i=0;i<=k-1;i++){
                if(uid==items.get(i).getIdu().getId()&&bid==items.get(i).getIdb().getBid()){
                    return true;
                }
            }           
        }
        return false;   
    }

    @Override
    public void insertCollection(Coll coll) {
        perisit(coll);
    }

    @Override
    public boolean exsitSelectedUser(String name, String password) {
         Query q = em.createQuery("select s from User s");
        List<User> items=q.getResultList();
        int size=items.size();
        if(size==0) return false;
        else{
            for(int i=0;i<=size-1;i++){
                if(name.equals(items.get(i).getUname())&&password.equals(items.get(i).getUpassword()))
                    return true;
            }
            return false;
        }
    }

    @Override
    public int getSelectedUserID(String name, String password) {
         Query q = em.createQuery("select s from User s");
        List<User> items=q.getResultList();
        int size=items.size();
        if(size==0) return -1;
        else{
            for(int i=0;i<=size-1;i++){
                if(name.equals(items.get(i).getUname())&&password.equals(items.get(i).getUpassword())) {
                    int k = items.get(i).getId();
                    return k;//存在这样的user，返回他的id 
                }        
            }
             return -1;
        }
    }

    @Override
    public List<Business> limitedBusiness(int number) {
          Query q = em.createQuery("select s from Business s");
        List<Business> items=q.getResultList();
        if(items.size()<=number)
            return items;
        else{
            items=items.subList(0, number);
            return items;
            }
    }

    @Override
    public List<Business> limitedBusiness() {
         Query q = em.createQuery("select s from Business s");
        List<Business> items=q.getResultList();
        return items;
    }

    @Override
    public List<Business> taggedBusiness(String tag) {
        Query q = em.createQuery("select s from Bt s");
        List<Bt> items=q.getResultList();
        int size=items.size();
        List<Bt> item = new ArrayList<>();
        for(int i=0;i<=size-1;i++){
            if(items.get(i).getTname().getTname().equals(tag)){//是此tag下的东西
              // items.remove(i);
               // i--;
                item.add(items.get(i));
            }
        }
        size=item.size();
        Query q2 = em.createQuery("select s from Business s");
        List<Business> busItems=q2.getResultList();
        int size2=busItems.size();
        List<Business> its = new ArrayList<>();
        for(int i=0;i<=size-1;i++){
            its.add(item.get(i).getBid());
        }
      //for(int i =0;i<=size2-1;i++){
           // for(int j=0;j<=size-1;i++){/////////
           // if(busItems.get(i).getBid()==items.get(j).getBid().getBid())
            //        x=1;
           ///         break;
           // }
            
            
            
            
            //未通过过滤，淘汰!!!!!
            //Remove有毒！！！！！！ 
            //busItems.remove(i);
           // i--;
            
            
            
            
            
        //}
        return its;
    }

    @Override
    public List<Business> collectedItems(int uid) {
        Query q = em.createNamedQuery("Coll.findByIdu");
        q.setParameter("idu", uid);
        List<Coll> items=q.getResultList();//应选得所有business
        int size=items.size();
        if(size<=0) return null;
        List<Business> r = new ArrayList<>();
        for(int i=0;i<=size-1;i++){
            r.add(items.get(i).getIdb());
        }
        return r;
    }
    
    @Override
    public int saveAndId(String name, String password) {
        User stored = new User();
        stored.setUname(name);
        stored.setUpassword(password); 
        
         em.persist(stored);
        int id = getSelectedUserID(name,password);
        return id;
    }

    @Override
    public User findUserById(int uid) {
        Query q = em.createNamedQuery("User.findById");
        q.setParameter("id", uid);
        User returned = (User)q.getResultList().get(0);
        return returned;
    }

    @Override
    public Business findBusinessById(int bid) {
         Query q = em.createNamedQuery("Business.findById");
        q.setParameter("id", bid);
        Business returned = (Business)q.getResultList().get(0);
        return returned;
      
    }

    @Override
    public List<Business> findCollectedByUser(User current) {
         Query q = em.createQuery("select s from Coll s");
        List<Coll> items=q.getResultList();//应选得所有business
        int size=items.size();
        if(size<=0) return null;
        List<Business>returned = new ArrayList<Business>();
        for(int i=0;i<=size-1;i++){
            if(current.equals(items.get(i).getIdu()))
                    returned.add(items.get(i).getIdb());
                    
        }
       
        return returned;
    }

    @Override
    public Coll findSelectedColl(User current, Business viewed) {
        Query q = em.createQuery("select s from Coll s");
        List<Coll> x = q.getResultList();
        int k =x.size();
        if(k==0) return null;
        for(int i = 0;i<=k-1;i++){
            if(current.equals(x.get(i).getIdu())&&viewed.equals(x.get(i).getIdb()))
                return x.get(i);
        }
        return null;
    }

    @Override
    public void removeData(Object obj) {
        em.remove(obj);
    }

    @Override
    public int findSelectedCollId(User current, Business viewed) {
         Query q = em.createQuery("select s from Coll s");
        List<Coll> x = q.getResultList();
        int k =x.size();
        if(k==0) return -1;
         for(int i = 0;i<=k-1;i++){
            if(current.equals(x.get(i).getIdu())&&viewed.equals(x.get(i).getIdb()))
                return x.get(i).getId();
        }
         return -1;
    }

    @Override
    public void removeCollById(int cid) {
        Query q = em.createNamedQuery("Coll.findById");
        q.setParameter("id", cid);
        Coll c = (Coll)q.getResultList().get(0);
        em.remove(c);
    }

    @Override
    public List<Business> randomBusiness(int numbers) {
        Query q = em.createQuery("select s from Business s");
        List<Business> x = q.getResultList();
        int size = x.size();
        if (size <= numbers) {
            return x;
        } else {
            int[] result = new int[numbers];
            for(int k=0;k<=7;k++)
                result[k]=-1;
            int count = 0;
            while (count < numbers) {
                int num = (int) (Math.random() * size);
                boolean flag = true;
                for (int j = 0; j < numbers; j++) {
                    if (num == result[j]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    result[count] = num;
                    count++;
                }
            }
            List<Business> re = new ArrayList<Business>();
            for (int i = 0; i <= numbers - 1; i++) {
                re.add(x.get(result[i]));
            }
            return re;
        }


    }

    @Override
    public List<Play> randomPlay(int numbers) {
        Query q = em.createQuery("select s from Play s");
        List<Play> x = q.getResultList();
        int size = x.size();
        if (size <= numbers) {
            return x;
        } else {
            int[] result = new int[numbers];
            for(int k=0;k<=7;k++)
                result[k]=-1;
            int count = 0;
            while (count < numbers) {
                int num = (int) (Math.random() * size);
                boolean flag = true;
                for (int j = 0; j < numbers; j++) {
                    if (num == result[j]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    result[count] = num;
                    count++;
                }
            }
            List<Play> re = new ArrayList<Play>();
            for (int i = 0; i <= numbers - 1; i++) {
                re.add(x.get(result[i]));
            }
            return re;
        }
    }



    @Override
    public List<Play> allPlay() {
          Query q = em.createQuery("select s from Play s");
        List<Play> x = q.getResultList();
        return x;

    }

    @Override
    public List<Play> findPlayByTag(String tag) {
              Query q = em.createQuery("select s from Pt s");
        List<Pt> x = q.getResultList();
        List<Play> re = new ArrayList<Play>();
        int size = x.size();
        if(size==0) return null;
        else{
            for(int i=0;i<=size-1;i++)
                if(tag.equals(x.get(i).getTpname().getTpname()))
                    re.add(x.get(i).getPid());
        
            return re;
    }
    }

    @Override
    public List<Play> collectedPlay(User current) {
       Query q = em.createNamedQuery("CollPlay.findAll");
        List<CollPlay> x = q.getResultList();
        List<Play> returned = new ArrayList<Play>();
        int m = x.size();
        if(m==0) return null;
        else{
            for(int i=0;i<=m-1;i++){
                if(x.get(i).getIdu().equals(current))
                    returned.add(x.get(i).getIdp());
            }
            return returned;
        }

    }

    @Override
    public boolean checkPC(User current, Play v) {
        Query q = em.createQuery("select s from CollPlay s");
        List<CollPlay> x = q.getResultList();
        int size=x.size();
        if(size==0) return false;
        else {
            for(int i=0;i<=size-1;i++){
                if(current.equals(x.get(i).getIdu())&&v.equals(x.get(i).getIdp()))
                    return true;
            }
            return false;
        }

    }

    @Override
    public void cancelPC(User current, Play v) {
        CollPlay c = new CollPlay();
        Query q = em.createQuery("select s from CollPlay s");
        List<CollPlay> x = q.getResultList();
        int size=x.size();
         for(int i=0;i<=size-1;i++){
                if(current.equals(x.get(i).getIdu())&&v.equals(x.get(i).getIdp()))
                    c = x.get(i);
            }
         em.remove(c);

    }

    @Override
    public void confirmPC(User current, Play v) {
         CollPlay x = new CollPlay();
        x.setIdu(current);
        x.setIdp(v);
        em.persist(x);

    }

    @Override
    public boolean checkBC(User current, Business viewed) {
        Query q = em.createQuery("select s from Coll s");
        List<Coll> x = q.getResultList();
        int size = x.size();
        if(size==0) return false;
        else{
            for(int i=0;i<=size-1;i++){
                if(current.equals(x.get(i).getIdu())&&viewed.equals(x.get(i).getIdb()))
                    return true;
            }
        }
        return false;
    }

//    @Override
//    public void persistComment(User current, Business viewed, String comments) {
//        Comment c = new Comment();
//        c.setUid(current);
//        c.setBid(viewed);
//        c.setComments(comments);
//        em.persist(c);    
//        int x=3;
//    }
    
    
    
    @Override
    public List<Comment> findCommentsByBusiness(Business viewed) {
        Query q = em.createNamedQuery("Comment.findAll");
       List<Comment> x=q.getResultList();
       int k=x.size();
       List<Comment> returned = new ArrayList<Comment>();
       if(k==0) return null;
       else{
           for(int i=0;i<=k-1;i++){
               if(x.get(i).getBid().equals(viewed))
                   returned.add(x.get(i));
           }
       }
       return returned;

    }

    @Override
    public void persistPComment(User current, Play v, String comments) {
         Commentp x = new Commentp();
        x.setCommments(comments);
        x.setUid(current);
        x.setPid(v);
        em.persist(x);
    }

    @Override
    public List<Commentp> findCommentByPlay(Play v) {
       Query q = em.createNamedQuery("Commentp.findAll");
        List<Commentp> x=q.getResultList();
        int k=x.size();
        List<Commentp> returned=new ArrayList<Commentp>();
        if(k==0) return null;
        else{
            for(int i=0;i<=k-1;i++){
                if(x.get(i).getPid().equals(v))
                    returned.add(x.get(i));
            }
        }
        return returned;

    }

    @Override
    public void persistComment(User current, Business viewed, String comments) {
        Comment c = new Comment();
        c.setBid(viewed);
        c.setUid(current);
        c.setComments(comments);
        em.persist(c);
    }
    
    

  
    
    
    
    
}
