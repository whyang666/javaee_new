/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homepage;

/**
 *
 * @author 昊阳
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/faces")
public class MyHomePage extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf8");
        response.setBufferSize(8192);
        try (PrintWriter out = response.getWriter()) {

            out.println(
                    "<?xml version='1.0' encoding='UTF-8' ?><html xmlns='http://www.w3.org/1999/xhtml'"
                    + "xmlns:h='http://xmlns.jcp.org/jsf/html'"
                    + "xmlns:p='http://primefaces.org/ui'"
                    + "xmlns:c='http://xmlns.jcp.org/jsp/jstl/core'>"
                    + "<h:head><style>body{margin:auto;text-align:center;width: 1300px;background-image: url(image/background.jpg);background-size:100% 100%;background-repeat:no-repeat;background-attachment: fixed;}"
                    + "div#header {align:center;margin-bottom:20px;height:15%; width: 90%;background-color:rgba(255,255,255,0.3);}"
                    + "div#globallink{margin:auto;width:90%;height:30px;text-align:center;background-color:rgba(255,255,255,0.9);}"
                    + " #traverse{margin:0px;width:100%;height:40px;background-color:rgba(255,255,255,0.9); padding:0px;}"
                    + " #traverse a{ text-decoration: none; }"
                    + "#traverse ul{"
                    + "list-style-type:none;"
                    + "text-align:center;"
                    + "margin:15px;"
                    + "}"
                    + "#traverse ul li{float:left;text-align:center;width:20%;font-size: 15pt; }"
                    + "div#recommend{width:100%;height:80%; margin:auto;ackground-color:rgba(255,255,255,0.3);margin-bottom:20px;}"
                    + "div#footer {text-align:center;background-color:rgba(255,255,255,0.5);}"
                    + "div.sort{width:50%;height:100%;float:left;margin:auto;}"
                    + "div#fun{display: inline; }"
                    + "div#food{display: inline;}"
                    + "a{text-decoration: none;}"
                    + "li.num{width:100%;height:160px;margin-bottom:20px;background-color:rgba(255,255,255,0.6);list-style-type: none}"
                    + ".pic{width: 40%;height:160px;float:left; display:inline;margin:0;}"
                    + "p.brif{width:70%;float:left;text-align:justify;margin:0;display: inline}"
                    + "h2.title{background-color:#FFE4E1}"
                    + "li.num:hover{background-color:rgba(255,255,255,0.8);}"
                    + "div#regist{margin-left: auto;width: 40%;}</style>"
            );
            out.println(
                    " <title>吃喝玩乐在北京</title></h:head>"
            );
            out.println(
                    "<h:body>"
                    + "<p:growl id=\"growl\" sticky=\"true\" showDetail=\"true\" />"
                    + "<h:form>"
                    + " <div id=\"traverse\">"
                    + "<ul>"
                    + "<li><a href=\"homepage.xhtml\">首页</a></li>"
                    + "<li><h:commandLink value=\"我的收藏\" action=\"#{userMB.toCol()}\"></h:commandLink></li>"
                    + "</ul>"
                    + "</div>"
                    + "</h:form>"
                    + ""
            );
            out.println(
                    "<h:body>"
                    + "<p:growl id=\"growl\" sticky=\"true\" showDetail=\"true\" />"
                    + "<h:form>"
                    + " <div id=\"traverse\">"
                    + "<ul>"
                    + "<li><a href=\"homepage.xhtml\">首页</a></li>"
                    + "<li><h:commandLink value=\"我的收藏\" action=\"#{userMB.toCol()}\"></h:commandLink></li>"
                    + "</ul>"
                    + "</div>"
                    + "</h:form>"
                    + ""
            );
        }

    }
//    @Override
//    public String getServletInfo() {
//        return "The Hello servlet says hello.";
//
//    }
}
