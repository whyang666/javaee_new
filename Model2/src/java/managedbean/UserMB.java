/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Business;
import model.Coll;
import model.Play;
import model.Tag;
import model.User;
import org.primefaces.context.RequestContext;
import sessionbean.DataConnectionLocal;

/**
 *
 * @author lenovo
 */
@ManagedBean
@SessionScoped
public class UserMB {
    // @EJB
    //private DataConnection isb;

    @EJB
    DataConnectionLocal is;
    private String pp;
    private String name;
    private String password;//数据传递，从html获得值，之后更具情况是否传递给current
    private User current;//记录当前登录账号，未登录无值不为null
    boolean isLogged;//记录当前是否已登录
    private Business viewed;//记录正在访问或即将访问的shop 
    private List<Business> initialItems;//记录主页初始化的shop
    private int itSize;//记录实际初始化items size
    private int index;//记录viewed在initialItems下标
    //

    public String getPp() {
        return pp;
    }
    
    private boolean searched = false;//记录是否已经初始化items

    private List<Business> collectedItems;//收藏页的items

    public List<Business> getCollectedItems() {
        if (collectedItems == null) {
            collectedItems = is.findCollectedByUser(current);
            //collectedItems=is.collectedItems(current.getId());
            return collectedItems;
        }
        return collectedItems;
    }

    public void setCollectedItems(List<Business> collectedItems) {
        this.collectedItems = collectedItems;
    }
    private List<Business> allItems;//所有businessItems
    private List<Business> tagItems;//分类items5
    private List<Business> truetag;
    private String tag = "西餐";//tag名字,默认为中餐
    private Tag choosedTag;
    private boolean alreadyBC=false;//记录viewed和current关系
    private String words;

    public String getWords() {
        if(alreadyBC) words = "取消收藏";
        else words = "收藏";
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
    

    public Business getViewed() {
        return viewed;
    }
    //

    public void setViewed(Business viewed) {
        this.viewed = viewed;
    }

    //foodpage自己跳转
    public String findTag(int index) { //跳转向自己
        switch (index) {
            case 0:
                choosedTag.setTname("全部");
                break;
            case 1:
                choosedTag.setTname("自助");
                break;
            case 2:
                choosedTag.setTname("中餐");
                break;
            case 3:
                choosedTag.setTname("西餐");
                break;
            case 4:
                choosedTag.setTname("美式快餐");
                break;
            case 5:
                choosedTag.setTname("日本料理");
                break;
            case 6:
                choosedTag.setTname("韩国料理");
                break;
            default:
                break;
        }
        tagItems = null;
        return "foodpage.xhtml?faces-redirect=true";
    }

    public List<Business> getAllItems() {
        if (allItems == null) {
            allItems = is.limitedBusiness();
        }
        return allItems;
    }

    public void setAllItems(List<Business> allItems) {
        this.allItems = allItems;
    }

    public List<Business> getTagItems() {//如果已有预加载内容，返回预加载；否则，返回搜索内容
        if (choosedTag == null) {
            choosedTag = new Tag("全部");
        }
        if (choosedTag.getTname().equals("全部")) {
            tagItems = is.limitedBusiness();
        } else {
            List<Business> it = is.taggedBusiness(choosedTag.getTname());
            tagItems = it;
        }

        // tagItems=isb.taggedBusiness(tag);
        return tagItems;
    }

    public void setTagItems(List<Business> tagItems) {
        this.tagItems = tagItems;
    }

    //我的收藏页面载入时应当载入所到
    public List<Business> getInitialItems() {//若非第一次进入界面，则不刷新界面
        if (initialItems == null) {
            initialItems = is.randomBusiness(8);
            itSize = initialItems.size();
        }
        return initialItems;
    }

    public void refreshInitialItems() {//强制刷新，可能用于刷新按钮
        searched = false;
    }

    public void setInitialItems(List<Business> initialItems) {
        this.initialItems = initialItems;
    }

    public UserMB() {
        current = new User();
        collectedItems = null;
        isLogged = false;
        allItems = null;
        initialItems = null;

    }

    public String colToDetail(int index) {
        viewed = collectedItems.get(index);
        return "shopdetail";
    }

    public String obj(Business e) {
        viewed = e;
        return "shopdetail";
    }

    public String foodNo(int index) {//固定数目主页到特定shopdetaill
        //viewed=initialItems.get(index);
        viewed = initialItems.get(index);
        alreadyBC=is.checkBC(current, viewed);
        return "shopdetail";
    }

    public String foodTNo(int index) {
        viewed = tagItems.get(index);
        return "shopdetail";
    }

    public String homeToDetailB(int index) {//最大数目主页到特定的shopdetail
        viewed = allItems.get(index);
        return "shopdetail";
    }

    public String homeTodetail(Business v) {
        viewed = v;
        return "shopdetail";
    }

    //
    public String getName() {
        return name;
    }

    //
    public void setName(String name) {
        this.name = name;
    }

    //
    public String getPassword() {
        return password;
    }

    //
    public void setPassword(String password) {
        this.password = password;
    }

    //
    public User getCurrent() {
        return current;
    }

    //
    public void setCurrent(User current) {
        this.current = current;
    }

    //
    public boolean isIsLogged() {
        return isLogged;
    }

    //
    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    //
    public String logButton() {
        if (!isLogged) {
            int thisID = is.getSelectedUserID(name, password);
            if (thisID != -1) {
                isLogged = true;
                current.setUname(name);
                current.setUpassword(password);
                current.setId(thisID);
                /////current.setId(Integer.SIZE);重要！
                //
                //
                RequestContext.getCurrentInstance().update("growl");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " 登陆成功！", "欢迎您，" + name));
                // name=null;//输入框
                password = null;
                return "homepage.xhtml?faces-redirect=true";
            } else {
                RequestContext.getCurrentInstance().update("growl");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "您输入的用户名或密码错误！", "请重新输入"));
                return "";
            }
        }
        RequestContext.getCurrentInstance().update("growl");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "您已经处于登录状态", "无法重复登录"));
        return "";

    }

    //
    public String prepare() {//防止空指针错误，初始化相应变量
        current = new User();
        collectedItems = null;
        isLogged = false;
        allItems = null;
        initialItems = null;
        return "homepage";
    }

    //
    public String detaillToCol() {
        if (isLogged == true) {
            return "collect";
        } else {
            return "homepage";
        }
    }

    public String toCol() {
        if (!isLogged) {
            RequestContext.getCurrentInstance().update("growl");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " 您尚未登录！", "请您先登录!，" + name));
            return "";
        } else {
            return "collect";
        }
    }

    public String toRegister() {
        if (isLogged) {
            RequestContext.getCurrentInstance().update("growl");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " 您已经登录！", "无法注册，请退出当前账号!" + name));
            return "";
        }
        return "register";
    }

    //注册按钮点击
    public String confirmR() {//确认提交
        if (is.checkByAccount(name)) {
            RequestContext.getCurrentInstance().update("growl");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " 账户名已经存在！！", "请重新输入"));
            return "";
        } else {
            int thisID = is.saveAndId(name, password);
            current.setUname(name);
            current.setUpassword(password);
            current.setId(thisID);
//            name=null;
            password = null;
            isLogged = true;
            RequestContext.getCurrentInstance().update("growl");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " 注册成功", "请继续浏览"));
            return "homepage";
        }

    }

    //收藏按钮点击
    public String confirmC() {
        if (!isLogged) {
            RequestContext.getCurrentInstance().update("growl");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " 您尚未注册或登录", "请先注册或登录"));
            return "";
        } 
        else {
            int uid = current.getId();
            int bid = viewed.getBid();
            if (is.checkCollection(uid, bid)) {
                RequestContext.getCurrentInstance().update("growl");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " 您已经收藏了此商铺", "无法重复收藏"));
                return "";
            }
            Coll x = new Coll();
            x.setIdu(current);
            x.setIdb(viewed);
            is.insertCollection(x);
            RequestContext.getCurrentInstance().update("growl");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " 收藏成功", "请继续浏览"));
            pp="cancel";
            alreadyBC=true;
            collectedItems = null;
            return "shopdetail";
        }

    }

    public String onSpecialItemSelected(Business e) {
        viewed = e;
        return "shopdetail";
    }

    public String cancelC() {
        if (!isLogged) {
            RequestContext.getCurrentInstance().update("growl");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " 您尚未注册或登录", "请先注册或登录"));
            return "";
        } 
        else {
            
            int k = is.findSelectedCollId(current, viewed);
            if (k == -1) {
                RequestContext.getCurrentInstance().update("growl");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " 您尚未收藏此店铺", "请继续浏览"));
                return "";
            }
            is.removeCollById(k);
            RequestContext.getCurrentInstance().update("growl");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " 已取消收藏", "请继续浏览"));           
            collectedItems = null;
            return "shopdetail";
        }
    }
    public boolean businessIsNotCollected(){
        return is.findSelectedCollId(current, viewed) == -1;
    }
    
    
    
    
    
    
    
    private String ptag="全部";
    private Play v;//当前正在访问的PLAY
    List<Play> initialPlay;//初始页面展示的paly
    List<Play> collectedPlay;//收藏页面的PLAY
    List<Play> tagPlay;//展示分类的play
    private boolean alreadyC;

    public List<Play> getTagPlay() {
        if(tagPlay!= null) return tagPlay;
        if(ptag.equals("全部"))
            tagPlay=is.allPlay();
        else{
            if(ptag.equals("户外"))
                tagPlay=is.findPlayByTag("户外");
            else if(ptag.equals("室内"))
                tagPlay=is.findPlayByTag("室内");
            else if(ptag.equals("著名景点"))
                  tagPlay=is.findPlayByTag("著名景点");
            else if(ptag.equals("参观展览"))
                  tagPlay=is.findPlayByTag("参观展览");
            else if(ptag.equals("休闲娱乐"))
                  tagPlay=is.findPlayByTag("休闲娱乐");
            else if(ptag.equals("游山玩水"))
                 tagPlay=is.findPlayByTag("游山玩水");
        }
        return tagPlay;
            
    }

    public void setTagPlay(List<Play> tagPlay) {
        this.tagPlay = tagPlay;
    }

    
    
    
    public Play getV() {
        return v;
    }

    public void setV(Play v) {
        this.v = v;
    }

    public List<Play> getInitialPlay() {
        if (initialPlay != null) {
            return initialPlay;
        } else {
            initialPlay=is.randomPlay(8);
            return initialPlay;
        }

    }

    public void setInitialPlay(List<Play> initialPlay) {

        this.initialPlay = initialPlay;
    }

    public List<Play> getCollectedPlay() {
        if(collectedPlay !=null) return collectedPlay;
        else{
            collectedPlay = is.collectedPlay(current);
            return collectedPlay;
        }
    }

    public void setCollectedPlay(List<Play> collectedPlay) {
        this.collectedPlay = collectedPlay;
    }

    public String onSpecialPlaySelected(Play x) {
        v = x;
        return "playdetail";
    }
    
    public String findTagP(int index){
        if(index==0) ptag="全部";
        else if(index==1) ptag="户外";
        else if(index==2) ptag="室内";
        else if(index==3) ptag="著名景点";
        else if(index==4) ptag="参观展览";
        else if(index==5) ptag="休闲娱乐";
        else if(index==6) ptag="游山玩水";
        tagPlay=null;
        return "playpage.xhtml?faces-redirect=true";
        
    }
    //户外，室内，著名景点，参观/展览，休闲娱乐/游山玩水
    
    public String collectOrNot(){
        if(!isLogged){
            RequestContext.getCurrentInstance().update("growl");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " 您尚未注册或登录", "请先注册或登录"));
            return "";
        }
        else{
            if(alreadyC){//已收藏，则取消
                is.cancelPC(current, v);
                RequestContext.getCurrentInstance().update("growl");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " 取消收藏成功!", "您可以继续浏览"));
                alreadyC=false;
            }
            else{
                is.confirmPC(current, v);
                RequestContext.getCurrentInstance().update("growl");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " 收藏成功!", "您可以继续浏览"));
                alreadyC=true;
            }
        }
        
        
        
        
        return "";
    }
    public String logOut(){
        isLogged=false;
        current=null;
        initialItems=null;
        initialPlay=null;
        tagItems=null;
        tagPlay=null;
        viewed=null;
        v=null;
        return "homepage.xhtml?faces-redirect=true";
    }


}
